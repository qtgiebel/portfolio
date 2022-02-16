package com.quinngiebel.portfolio.admin.controls;

import com.quinngiebel.portfolio.admin.entities.Piece;
import com.quinngiebel.portfolio.admin.persistence.PieceDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddNewPieceServlet", value = "/admin/add-piece")
public class AddNewPieceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardURL = "/admin/admin-index.jsp";
        request.setAttribute("operation", "Add");
        request.setAttribute("fail", false);

        PieceDao pieceDao = new PieceDao();
        Piece newPiece = new Piece(request.getParameter("title"), request.getParameter("location"),
                request.getParameter("category"));


        if (pieceDao.addPiece(newPiece) == null) {
            request.setAttribute("fail", true);
        }

        request.setAttribute("pieces", pieceDao.getAllPieces());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardURL);
        dispatcher.forward(request, response);
    }
}
