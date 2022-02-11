package com.quinngiebel.portfolio.controls;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CommissionsDisplayServlet", value = "/commissions")
public class CommissionsDisplayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardUrl = "/commissions.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
