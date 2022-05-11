package com.quinngiebel.auth.controller;

import com.quinngiebel.admin.entities.User;
import com.quinngiebel.admin.persistence.UserDao;
import com.quinngiebel.auth.CognitoJWTParser;
import com.quinngiebel.auth.CognitoTokenHeader;
import com.quinngiebel.auth.Keys;
import com.quinngiebel.auth.KeysItem;
import com.quinngiebel.auth.TokenResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.io.FileUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Collectors;


/**
 * Inspired by: https://stackoverflow.com/questions/52144721/how-to-get-access-token-using-client-credentials-using-java-code
 */
@WebServlet(urlPatterns = {"/auth"})
public class Auth extends HttpServlet  {
    String CLIENT_ID;
    String CLIENT_SECRET;
    String OAUTH_URL;
    String LOGIN_URL;
    String REDIRECT_URL;
    String REGION;
    String POOL_ID;
    Keys jwks;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext context = getServletContext();
        CLIENT_ID = (String) context.getAttribute("CLIENT_ID");
        CLIENT_SECRET = (String) context.getAttribute("CLIENT_SECRET");
        OAUTH_URL = (String) context.getAttribute("OAUTH_URL");
        LOGIN_URL = (String) context.getAttribute("LOGIN_URL");
        REDIRECT_URL = (String) context.getAttribute("REDIRECT_URL");
        REGION = (String) context.getAttribute("REGION");
        POOL_ID = (String) context.getAttribute("POOL_ID");

        loadKey();
    }

    /**
     * Gets the auth code from the request and exchanges it for a token containing user info.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authCode = request.getParameter("code");
        String ERROR_PAGE = "admin/error.jsp";

        if (authCode == null) {
            request.setAttribute("errorMsg", "Could not authorize.");
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
            return;
        }

        HttpRequest authRequest = buildAuthRequest(authCode);
        try {
            TokenResponse tokenResponse = getToken(authRequest);

            // TODO update to pass user object as cookie when user dao is further along
            User verifiedUser = validate(tokenResponse);
            request.getSession().setAttribute("verifiedUser", verifiedUser);
        } catch (IOException e) {
            logger.error("Error getting or validating the token: " + e.getMessage(), e);
            request.setAttribute("errorMsg", "Error getting or validating the token.");
            request.setAttribute("stackTrace", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
            return;
        } catch (InterruptedException e) {
            logger.error("Error getting token from Cognito oauth url " + e.getMessage(), e);
            request.setAttribute("errorMsg", "Error getting token from Cognito oauth url.");
            request.setAttribute("stackTrace", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
            return;
        } catch (Exception e) {
            logger.info("e");
            request.setAttribute("errorMsg", "Validation error");
            request.setAttribute("stackTrace", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
            return;
        }

        response.sendRedirect("/portfolio/admin");
    }

    /**
     * Sends the request for a token to Cognito and maps the response
     * @param authRequest the request to the oauth2/token url in cognito
     * @return response from the oauth2/token endpoint which should include id token, access token and refresh token
     * @throws IOException
     * @throws InterruptedException
     */
    private TokenResponse getToken(HttpRequest authRequest) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<?> response;

        response = client.send(authRequest, HttpResponse.BodyHandlers.ofString());

        logger.debug("Response headers: " + response.headers().toString());
        logger.debug("Response body: " + response.body().toString());

        ObjectMapper mapper = new ObjectMapper();
        TokenResponse tokenResponse = mapper.readValue(response.body().toString(), TokenResponse.class);
        logger.debug("Id token: " + tokenResponse.getIdToken());

        return tokenResponse;
    }

    /**
     * Get values out of the header to verify the token is legit. If it is legit, get the claims from it, such
     * as username.
     * @param tokenResponse The tokens received from cognito.
     * @return              An object representation of the authorized user.
     * @throws IOException  Throws IOException if the tokens cannot be read.
     */
    private User validate(TokenResponse tokenResponse) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CognitoTokenHeader tokenHeader = mapper.readValue(CognitoJWTParser.getHeader(tokenResponse.getIdToken()).toString(), CognitoTokenHeader.class);

        // Header should have kid and alg- https://docs.aws.amazon.com/cognito/latest/developerguide/amazon-cognito-user-pools-using-the-id-token.html
        String keyId = tokenHeader.getKid();
        String alg = tokenHeader.getAlg();

        // Use Key's N and E
        BigInteger modulus = new BigInteger(1, org.apache.commons.codec.binary.Base64.decodeBase64(jwks.getKeys().get(0).getN()));
        BigInteger exponent = new BigInteger(1, org.apache.commons.codec.binary.Base64.decodeBase64(jwks.getKeys().get(0).getE()));

        // Create a public key
        PublicKey publicKey;
        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (InvalidKeySpecException e) {
            logger.error("Invalid Key Error " + e.getMessage(), e);
            throw new InvalidKeySpecException();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Algorithm Error " + e.getMessage(), e);
            throw new NoSuchAlgorithmException();
        }

        // get an algorithm instance
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);

        // Verify ISS field of the token to make sure it's from the Cognito source
        String iss = String.format("https://cognito-idp.%s.amazonaws.com/%s", REGION, POOL_ID);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(iss)
                .withClaim("token_use", "id") // make sure you're verifying id token
                .acceptLeeway(1000)
                .build();

        // Verify the token
        DecodedJWT jwt = verifier.verify(tokenResponse.getIdToken());
        User user = checkIfNewUser(jwt);

        logger.debug("here's the user: " + user);
        logger.debug("here are all the available claims: " + jwt.getClaims());

        return user;
    }

    /**
     * Checks if the user exists in the user db and creates a new record if not.
     * @param jwt The authentication data returned from cognito.
     * @return The user record that has been authenticated.
     */
    private User checkIfNewUser(DecodedJWT jwt) {
        UserDao userAuth = new UserDao();
        User user = userAuth.getByColumn("id", jwt.getClaim("cognito:username").asString()).get(0);

        if (user == null) {
            userAuth.insert(new User(jwt.getClaim("cognito:username").asString(), jwt.getClaim("email").asString()));
            user = userAuth.getByColumn("id", jwt.getClaim("cognito:username").asString()).get(0);
        }

        return user;
    }

    /** Create the auth url and use it to build the request.
     *
     * @param authCode auth code received from Cognito as part of the login process
     * @return the constructed oauth request
     */
    private HttpRequest buildAuthRequest(String authCode) {
        String keys = CLIENT_ID + ":" + CLIENT_SECRET;

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "authorization_code");
        parameters.put("client-secret", CLIENT_SECRET);
        parameters.put("client_id", CLIENT_ID);
        parameters.put("code", authCode);
        parameters.put("redirect_uri", REDIRECT_URL);

        String form = parameters.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        String encoding = Base64.getEncoder().encodeToString(keys.getBytes());

        return HttpRequest.newBuilder().uri(URI.create(OAUTH_URL))
                .headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", "Basic " + encoding)
                .POST(HttpRequest.BodyPublishers.ofString(form)).build();
    }

    /**
     * Gets the JSON Web Key Set (JWKS) for the user pool from cognito and loads it
     * into objects for easier use.
     *
     * JSON Web Key Set (JWKS) location: https://cognito-idp.{region}.amazonaws.com/{userPoolId}/.well-known/jwks.json
     * Demo url: https://cognito-idp.us-east-2.amazonaws.com/us-east-2_XaRYHsmKB/.well-known/jwks.json
     *
     * @see Keys
     * @see KeysItem
     */
    private void loadKey() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL jwksURL = new URL(String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json", REGION, POOL_ID));
            File jwksFile = new File("jwks.json");
            FileUtils.copyURLToFile(jwksURL, jwksFile);
            jwks = mapper.readValue(jwksFile, Keys.class);
            logger.debug("Keys are loaded. Here's e: " + jwks.getKeys().get(0).getE());
        } catch (IOException ioException) {
            logger.error("Cannot load json..." + ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading json" + e.getMessage(), e);
        }
    }
}

