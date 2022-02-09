package com.quinngiebel.employee.controls;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class serves a page to search for an employee.
 *
 * @author Quinn Giebel
 */
@WebServlet(name = "searchServlet", urlPatterns = { "/search" })
public class SearchDisplayServlet extends HttpServlet {

    /**
     * This method forwards to the search jsp.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @exception ServletException if there is a Servlet failure
     * @exception IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardUrl = "/jsp/employee-search.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
