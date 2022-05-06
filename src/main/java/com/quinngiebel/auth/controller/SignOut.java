package com.quinngiebel.auth.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/signOut"})
/*
 * Begins the authentication process using AWS Cognito
 */
public class SignOut extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String LOGOUT_URL;
    public static String REVOKE_ACCESS_URL;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        CLIENT_ID = (String) context.getAttribute("CLIENT_ID");
        LOGOUT_URL = (String) context.getAttribute("LOGOUT_URL");
        REVOKE_ACCESS_URL = (String) context.getAttribute("REVOKE_ACCESS_URL");
    }

    /**
     * Route to the aws-hosted cognito login page.
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        logger.info("user logged out");

        String url = LOGOUT_URL + "?client_id=" + CLIENT_ID + "&logout_uri=" + REVOKE_ACCESS_URL;
        response.sendRedirect(url);
    }
}
