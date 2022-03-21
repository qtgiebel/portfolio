package com.quinngiebel.admin.persistence;

import com.quinngiebel.admin.entities.Piece;
import com.quinngiebel.test.utilities.Database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceDaoTest {

    PieceDao pieceDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Reset the database and create the DAO.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        if (database.runSQL("create_art_test.sql")) {
            logger.info("Database reset success");
        }
        pieceDao = new PieceDao();
    }

    /**
     * Verifies dao type is set correctly.
     */
    @Test
    void setTypeSuccess() {
        assert(pieceDao.getType().equals(Piece.class));
    }

    /**
     * Verifies archives a piece
     */
    @Test
    void archivePieceSuccess() {
        Piece toArchive = pieceDao.getById(1);
        pieceDao.archivePiece(toArchive);
        assertEquals(2, pieceDao.getByColumn("archived", true).size());
    }

    /**
     * Verifies archives a piece
     */
    @Test
    void unarchivePieceSuccess() {
        Piece toPublish = pieceDao.getById(8);
        pieceDao.publishPiece(toPublish);
        assertEquals(9, pieceDao.getByColumn("archived",false).size());
    }
}
