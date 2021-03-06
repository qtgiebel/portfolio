package com.quinngiebel.admin.controls;

import com.quinngiebel.admin.entities.Category;
import com.quinngiebel.admin.persistence.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Creates a new category.
 *
 * @author Quinn Giebel
 */
@WebServlet(name = "AddNewCategoryServlet", value = "/admin/addCategory")
public class AddNewCategoryServlet extends HttpServlet {

    /**
     * Inserts a new category into the db and redirects to the main admin page to show the new category.
     * @param request           The servlet request.
     * @param response          The servlet response.
     * @throws ServletException Throws when the dispatcher cannot be forwarded.
     * @throws IOException      Throws when the dispatcher cannot be forwarded.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectUrl = "/portfolio/admin";

        String categoryTitle = request.getParameter("category").toLowerCase(Locale.ROOT);
        new CategoryDao().insert(new Category(categoryTitle));

        response.sendRedirect(redirectUrl);
    }
}
