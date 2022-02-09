package com.quinngiebel.employee.controls;

import com.quinngiebel.employee.entities.Search;
import com.quinngiebel.employee.persistence.*;
import com.quinngiebel.utilities.PropertiesLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * This class serves a page that retrieves and displays a list of employees
 * based on form data form a previous page.
 *
 * @author Quinn Giebel
 */
@WebServlet(name = "resultServlet", urlPatterns = { "/search-results" })
public class SearchResultsServlet extends HttpServlet implements PropertiesLoader {
    Properties properties;

    /**
     * Retrieves attributes from the servlet context, searches for employees and
     * adds the list of employees to a javabean set as an attribute.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @exception ServletException if there is a Servlet failure
     * @exception IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        Search results;
        String forwardUrl = "/jsp/employee-search-results.jsp";
        EmployeeDirectory directory = (EmployeeDirectory) context.getAttribute("directory");

        results = directory.searchEmployee(request.getParameter("type").trim(), request.getParameter("term").trim());

        request.setAttribute("results", results);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
