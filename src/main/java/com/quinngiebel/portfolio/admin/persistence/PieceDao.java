package com.quinngiebel.portfolio.admin.persistence;

import com.quinngiebel.portfolio.admin.entities.Piece;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

public class PieceDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


/** Create */
    public boolean addPiece(Piece piece) {
        //TODO hib:add piece
        return false;
    }

/** Read */
    public List<Piece> getAllPieces(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Piece> query = builder.createQuery(Piece.class);
        Root<Piece> root = query.from(Piece.class);
        List<Piece> pieces = session.createQuery(query).getResultList();
        session.close();

        return pieces;
    }

    public List<Piece> getPiecesByColumn(String column, Object value) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Piece> query = builder.createQuery(Piece.class);
        Root<Piece> root = query.from(Piece.class);

        Expression<String> propertyPath = root.get(column);
        query.where(builder.equal(propertyPath, value));

        List<Piece> results = session.createQuery(query).getResultList();
        session.close();

        return results;
    }

    public Piece getPieceById(int id) {
        Session session = sessionFactory.openSession();
        Piece piece = session.get(Piece.class, id);
        session.close();

        return piece;
    }

    public Piece getPieceByTitle(String title) {
        return getPiecesByColumn("title", title).get(0);
    }

    public List<Piece> getPiecesByCategory(String category) {
        return getPiecesByColumn("category", category);
    }

    public List<Piece> getPiecesByArchived(boolean isArchived) {
        return getPiecesByColumn("isArchived", isArchived);
    }

/** Update */
    public void archivePiece(int id) {
        Piece toArchive = getPieceById(id);

        //TODO hib:update toArchive
    }

/** Delete */
    public void deletePiece(int id) {
        Piece toDelete = getPieceById(id);

        //TODO hib:delete toDelete
    }
}
