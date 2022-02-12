package com.quinngiebel.portfolio.admin.controls;

import com.quinngiebel.portfolio.admin.persistence.PieceDao;

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
@WebServlet(name = "AddUserDisplayServlet", value = "/admin/list")
public class ListPiecesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardUrl = "/admin/list-pieces.jsp";
        PieceDao pieceDao = new PieceDao();

        request.setAttribute("pieces", pieceDao.getAllPieces());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
