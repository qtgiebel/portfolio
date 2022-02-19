package com.quinngiebel.portfolio.admin.persistence;

import com.quinngiebel.portfolio.admin.entities.Category;
import com.quinngiebel.portfolio.admin.entities.Piece;
import com.quinngiebel.test.utilities.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceDaoTest {

    PieceDao pieceDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Create the DAO.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        if(database.runSQL("create_art_test.sql")) {
            logger.info("Database reset success");
        }
        pieceDao = new PieceDao();
    }

    /**
     * Verifies that a piece is added.
     */
    /*@Test
    void addPieceSuccess() {

        Piece piece = new Piece("asdf", "asdf", );
        pieceDao.addPiece(piece);
        assertEquals(10, pieceDao.getAllPieces().size());
    }*/

    /**
     * Verifies gets all pieces successfully.
     */
    @Test
    void getAllPiecesSuccess() {
        List<Piece> pieces = pieceDao.getAllPieces();
        assertEquals(9, pieces.size());
    }

    /**
     * Verifies gets a piece by its id
     */
    @Test
    void getPieceByIdSuccess() {
        Piece piece = pieceDao.getPieceById(1);
        assertEquals("California Hills 2020", piece.getTitle());
    }

    /**
     * Verifies gets a piece by its title
     */
    @Test
    void getPieceByTitleSuccess() {
        Piece piece = pieceDao.getPieceByTitle("California Hills 2020");
        assertEquals(1, piece.getId());
    }

    /**
     * Verifies gets all the pieces in a category
     */
    @Test
    void getPiecesByCategorySuccess() {
        List<Piece> results = pieceDao.getPiecesByCategory(2);
        assertEquals(2, results.size());
    }

    /**
     * Verifies gets the pieces that are archived
     */
    @Test
    void getPiecesByArchivedArchived() {
        List<Piece> results = pieceDao.getPiecesByArchived(true);
        assertEquals(1, results.size());
    }

    /**
     * Verfies gets the pieces that are not archived
     */
    @Test
    void getPiecesByArchivedUnarchived() {
        List<Piece> results = pieceDao.getPiecesByArchived(false);
        assertEquals(8, results.size());
    }

    /**
     * Verifies archives a piece
     */
    @Test
    void archivePieceSuccess() {
        Piece toArchive = pieceDao.getPieceById(1);
        pieceDao.archivePiece(toArchive);
        assertEquals(2, pieceDao.getPiecesByArchived(true).size());
    }

    @Test
    void deletePieceSuccess() {
        Piece toDelete = pieceDao.getPieceById(1);
        pieceDao.deletePiece(toDelete);
        assertEquals(8, pieceDao.getAllPieces().size());
    }
}
