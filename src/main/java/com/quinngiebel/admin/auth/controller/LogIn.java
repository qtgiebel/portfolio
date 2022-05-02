package com.quinngiebel.admin.auth.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/logIn"})
/*
 * Begins the authentication process using AWS Cognito
 */
public class LogIn extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String LOGIN_URL;
    public static String REDIRECT_URL;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        CLIENT_ID = (String) context.getAttribute("CLIENT_ID");
        LOGIN_URL = (String) context.getAttribute("LOGIN_URL");
        REDIRECT_URL = (String) context.getAttribute("REDIRECT_URL");
    }

    /**
     * Route to the aws-hosted cognito login page.
     * @param req servlet request
     * @param resp servlet response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO if properties weren't loaded properly, route to an error page
        String url = LOGIN_URL + "?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL;
        resp.sendRedirect(url);
    }
}
