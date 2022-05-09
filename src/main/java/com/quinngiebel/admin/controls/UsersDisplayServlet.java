package com.quinngiebel.admin.controls;

import com.quinngiebel.admin.entities.Piece;
import com.quinngiebel.admin.persistence.GenericDao;
import com.quinngiebel.admin.persistence.PieceDao;
import com.quinngiebel.admin.persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This class forwards to a jsp page.
 *
 * @author Quinn Giebel
 */
@WebServlet(name = "UsersDisplayServlet", value = "/admin/users")
public class UsersDisplayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardUrl = "/admin/users.jsp";
        UserDao userDao = new UserDao();

        request.setAttribute("users", userDao.toJSON(userDao.getAll()));

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
