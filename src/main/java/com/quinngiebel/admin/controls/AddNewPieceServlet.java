package com.quinngiebel.admin.controls;

import com.quinngiebel.admin.entities.Category;
import com.quinngiebel.admin.entities.Piece;
import com.quinngiebel.admin.persistence.CategoryDao;
import com.quinngiebel.admin.persistence.GenericDao;
import com.quinngiebel.admin.persistence.PieceDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNewPieceServlet", value = "/add-piece")
public class AddNewPieceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardURL = "/index.jsp";
        request.setAttribute("operation", "Add");
        request.setAttribute("fail", false);

        PieceDao pieceDao = new PieceDao();
        CategoryDao categoryDao = new CategoryDao();
        //Piece newPiece = new Piece(request.getParameter("title"),
        //        request.getParameter("location"),
        //        categoryDao.getByColumn("name",
        //                (Category) request.getParameter("category")));


        //if (pieceDao.insert(newPiece) < 1) {
        //    request.setAttribute("fail", true);
        //}

        request.setAttribute("pieces", pieceDao.getAll());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardURL);
        dispatcher.forward(request, response);
    }
}
