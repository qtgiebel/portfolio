package com.quinngiebel.admin.controls;

import com.quinngiebel.admin.persistence.PieceDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.IOException;

/**
 * This class forwards to a jsp page.
 *
 * @author Quinn Giebel
 */
@WebServlet(name = "AdminDisplayServlet", value = "/admin")
public class AdminDisplayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("verifiedUser") == null)
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/logIn");
            dispatcher.forward(request, response);
            return;
        }

        String forwardUrl = "/admin/index.jsp";
        PieceDao pieceDao = new PieceDao();

        request.setAttribute("images", pieceDao.toJSON(pieceDao.getUnarchivedPieces()));
        request.setAttribute("user", request.getSession().getAttribute("verifiedUser"));

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}