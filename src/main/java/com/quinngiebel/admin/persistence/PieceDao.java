package com.quinngiebel.admin.persistence;

import com.quinngiebel.admin.entities.Piece;

/**
 * The type Piece dao.
 */
public class PieceDao extends GenericDao<Piece> {

    /**
     * Default constructor for a Piece DAO.
     */
    public PieceDao() {
        this.setType(Piece.class);
    }

    /**
     * Archives a piece.
     * @param toArchive The piece being archived
     */
    public void archivePiece(Piece toArchive) {
        toArchive.setArchived(true);
        update(toArchive);
    }

    /**
     * Unarchives a piece.
     * @param toPublish The piece being unarchived.
     */
    public void publishPiece(Piece toPublish) {
        toPublish.setArchived(false);
        update(toPublish);
    }


}
