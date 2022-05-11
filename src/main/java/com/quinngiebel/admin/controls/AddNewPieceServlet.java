package com.quinngiebel.admin.controls;

import com.quinngiebel.admin.entities.Category;
import com.quinngiebel.admin.entities.Piece;
import com.quinngiebel.admin.persistence.CategoryDao;
import com.quinngiebel.admin.persistence.GenericDao;
import com.quinngiebel.admin.persistence.PieceDao;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AddNewPieceServlet", value = "/admin/addPiece")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,     // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddNewPieceServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    String S3_API_URL;
    String CDN_DOMAIN;

    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext context = getServletContext();
        S3_API_URL = (String) context.getAttribute("S3_API_URL");
        CDN_DOMAIN = (String) context.getAttribute("CDN_DOMAIN");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectURL = "/portfolio/admin";
        String s3Location = CDN_DOMAIN;

        try {
            s3Location += this.uploadImage(request.getPart("file"), request.getParameter("title"));
        } catch (IOException | RuntimeException e) {
            request.setAttribute("errorMsg", "File input error; could not upload.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Piece toInsert = new Piece(
                request.getParameter("title"),
                s3Location,
                new CategoryDao().getById(Integer.parseInt(request.getParameter("category"))));

        logger.info("New piece: " + toInsert);

        new PieceDao().insert(toInsert);

        response.sendRedirect(redirectURL);
    }

    /**
     *
     * @param file      The Part object of the image being uploaded.
     * @param fileName  The name of the file.
     * @return          A string that can be used to access the file in S3.
     */
    public String uploadImage(Part file, String fileName) throws RuntimeException {
        File f = writeTempFile(file);

        if (f == null) {
            throw new RuntimeException("Failed to write file.");
        }

        String encodedImage = encodeImageFile(f);
        fileName += ("." + FilenameUtils.getExtension(file.getSubmittedFileName()));
        logger.debug("file name: " + fileName);

        if (encodedImage == null) {
            throw new RuntimeException("Failed to encode image.");
        }

        f.delete();

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(S3_API_URL);
        String body = "{\"title\":\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8) +
                "\",\"encodedImage\":\"" + encodedImage + "\"}";

        Response response = webTarget.request("application/json").post(Entity.json(body));

        if (response.getStatus() != 200) {
            throw new RuntimeException("Upload failed : HTTP error code : " + response.getStatus());
        }

        return fileName;
    }

    private File writeTempFile(Part file) {
        InputStream fileContent;
        OutputStream outputStream;
        File f;

        try {
            fileContent = file.getInputStream();
            f = new File(getServletContext() + "temp");
            outputStream = new FileOutputStream(f);

            byte[] bytes = new byte[(int) file.getSize()];
            fileContent.read(bytes);
            outputStream.write(bytes);

            return f;

        } catch (IOException e) {
            logger.error("Could not read file AddNewPieceServlet::writeFile", e);
            return null;
        }
    }

    private String encodeImageFile(File file) {
        String encodedString;

        try (FileInputStream fileInputStreamReader = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedString = new String(Base64.encodeBase64(bytes), Charset.defaultCharset());
        } catch (FileNotFoundException e) {
            logger.error("File Not found AddNewPieceServlet::encodeImageFile", e);
            return null;
        } catch (IOException e) {
            logger.error("IOException AddNewPieceServlet::encodeImageFile", e);
            return null;
        }

        return encodedString;
    }
}