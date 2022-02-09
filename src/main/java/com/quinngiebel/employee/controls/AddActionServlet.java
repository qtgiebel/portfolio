package com.quinngiebel.employee.controls;

import com.quinngiebel.employee.persistence.EmployeeDirectory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class adds and employee to the database.
 *
 * @author Quinn Giebel
 */
@WebServlet(name = "addServlet", urlPatterns = { "/add" })
public class AddActionServlet extends HttpServlet {

    /**
     * This method adds a new employee to the database and redirects to the add jsp.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @exception ServletException if there is a Servlet failure
     * @exception IOException      if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        EmployeeDirectory directory = (EmployeeDirectory) context.getAttribute("directory");
        HttpSession session = request.getSession();

        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String ssn = request.getParameter("ssn").trim();
        String dept = request.getParameter("dept").trim();
        String room = request.getParameter("room").trim();
        String phone = request.getParameter("phone").trim();

        session.setAttribute("addSuccessful", directory.addEmployee(firstName, lastName, ssn, dept, room, phone));

        String redirectUrl = "/new-employee";

        response.sendRedirect(redirectUrl);
    }

}
