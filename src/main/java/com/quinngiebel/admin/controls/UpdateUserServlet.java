package com.quinngiebel.admin.controls;

import com.quinngiebel.admin.entities.User;
import com.quinngiebel.admin.persistence.UserDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class forwards to a jsp page.
 *
 * @author Quinn Giebel
 */
@WebServlet(name = "UpdateUserServlet", value = "/admin/update-user")
public class UpdateUserServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User toUpdate = userDao.getByColumn("id", request.getParameter("userId")).get(0);

        String[] permissions = request.getParameterValues("permission");

        logger.info("values: " + request.getParameter("userId"));

        logger.info("values: " + request.getParameter("admin"));
        toUpdate.setViewPermission(Boolean.parseBoolean(request.getParameter("view")));
        logger.info("toUpdate: " + toUpdate.toJSON());
//        userDao.update(toUpdate);
        request.setAttribute("view", request.getParameter("view"));
        request.setAttribute("admin", request.getParameter("admin"));
        String forwardUrl = "/admin/error.jsp";


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
