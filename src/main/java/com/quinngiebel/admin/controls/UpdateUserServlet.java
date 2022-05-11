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
@WebServlet(name = "UpdateUserServlet", value = "/admin/updateUser")
public class UpdateUserServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectUrl = "/portfolio/admin/users";
        UserDao userDao = new UserDao();
        User toUpdate = userDao.getByColumn("id", request.getParameter("userId")).get(0);

        String[] permissions = request.getParameterValues("permission");
        switch (permissions.length) {
            case 0:
                toUpdate.setViewPermission(false);
                toUpdate.setAdminPermission(false);
                break;
            case 1:
                toUpdate.setViewPermission(true);
                toUpdate.setAdminPermission(false);
                break;
            case 2:
                toUpdate.setViewPermission(true);
                toUpdate.setAdminPermission(true);
                break;
        }

        userDao.update(toUpdate);

        response.sendRedirect(redirectUrl);
    }
}
