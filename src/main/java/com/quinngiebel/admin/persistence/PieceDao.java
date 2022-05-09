package com.quinngiebel.admin.persistence;

import com.quinngiebel.admin.entities.Piece;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Returns a JSON representation of the list of pieces.
     * @param resultList The list of pieces.
     * @return A JSON representation of the list
     */
    public String toJSON(List<Piece> resultList) {
        return String.format("{\"images\":[%s]}",
                resultList.stream().map(Piece::toJSON).collect(Collectors.joining(",")));
    }

}
