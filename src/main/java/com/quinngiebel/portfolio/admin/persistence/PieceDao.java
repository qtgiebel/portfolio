package com.quinngiebel.portfolio.admin.persistence;

import com.quinngiebel.portfolio.admin.entities.Piece;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PieceDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Piece> getAllPieces(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Piece> query = builder.createQuery(Piece.class);
        Root<Piece> root = query.from(Piece.class);
        List<Piece> pieces = session.createQuery(query).getResultList();
        session.close();

        return pieces;
    }

    public Piece addArt(Piece piece) {
        return null;
    }

    public Piece getArt(int rollNo) {
        return null;
    }

    public void updateArt(Piece piece) {

    }

    public void deleteStudent(Piece piece) {

    }
}
