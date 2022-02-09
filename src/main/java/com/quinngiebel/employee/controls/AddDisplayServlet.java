package com.quinngiebel.employee.controls;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class serves a page to add an employee to the directory.
 *
 * @author Quinn Giebel
 */
@WebServlet(name = "newEmpServlet", urlPatterns = { "/new-employee" })
public class AddDisplayServlet extends HttpServlet {

    /**
     * This method forwards to the add jsp.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @exception ServletException if there is a Servlet failure
     * @exception IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardUrl = "/jsp/employee-add.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
