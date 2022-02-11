package com.quinngiebel.portfolio.controls;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GalleryDisplayServlet", value = "/gallery")
public class GalleryDisplayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardUrl = "/gallery.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
