package com.quinngiebel.admin.controls;

import com.quinngiebel.admin.entities.Piece;
import com.quinngiebel.admin.persistence.PieceDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
@WebServlet(name = "ListPiecesServlet", urlPatterns = "/list")
public class ListPiecesServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardUrl = "/list-pieces.jsp";
        PieceDao pieceDao = new PieceDao();
        List<Piece> pieces = pieceDao.getAll();

        request.setAttribute("pieces", pieces);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
