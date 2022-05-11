package com.quinngiebel.admin.controls;

import com.quinngiebel.admin.entities.Piece;
import com.quinngiebel.admin.persistence.PieceDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateArchiveStatusServlet", value = "/admin/archivePiece")
public class UpdateArchiveStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectUrl = "/portfolio/admin";
        PieceDao pieceDao = new PieceDao();
        Piece toUpdate = pieceDao.getById(Integer.parseInt(request.getParameter("pieceId")));

        toUpdate.setArchived(!toUpdate.isArchived());

        pieceDao.update(toUpdate);

        response.sendRedirect(redirectUrl);
    }
}
