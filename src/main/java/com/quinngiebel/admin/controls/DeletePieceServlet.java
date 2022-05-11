package com.quinngiebel.admin.controls;

import com.quinngiebel.admin.entities.Piece;
import com.quinngiebel.admin.persistence.PieceDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "DeletePieceServlet", urlPatterns = "/admin/deletePiece")
public class DeletePieceServlet extends HttpServlet {
    private String S3_API_URL;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext context = getServletContext();
        S3_API_URL = (String) context.getAttribute("S3_API_URL");
//        CDN_DOMAIN = (String) context.getAttribute("CDN_DOMAIN");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectUrl = "/portfolio/admin";
        PieceDao pieceDao = new PieceDao();
        Piece toDelete = pieceDao.getById(Integer.parseInt(request.getParameter("pieceId")));
        
        this.deleteFromS3(toDelete.getTitle());
        
        pieceDao.delete(toDelete);

        response.sendRedirect(redirectUrl);
    }

    private void deleteFromS3(String title) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(S3_API_URL + "/" + URLEncoder.encode(title, StandardCharsets.UTF_8));

        Response response = webTarget.request("application/json").delete();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Delete failed : HTTP error code : " + response.getStatus());
        }
    }
}
