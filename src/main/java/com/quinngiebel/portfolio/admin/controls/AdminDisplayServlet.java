package com.quinngiebel.portfolio.admin.controls;

import com.quinngiebel.portfolio.admin.entities.Piece;
import com.quinngiebel.portfolio.admin.persistence.PieceDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * This class forwards to a jsp page.
 *
 * @author Quinn Giebel
 */
@WebServlet(name = "AdminDisplayServlet", value = "/admin")
public class AdminDisplayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardUrl = "/admin/admin-index.jsp";
        PieceDao pieceDao = new PieceDao();
        List<Piece> pieces = pieceDao.getAllPieces();

        request.setAttribute("pieces", pieces);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
