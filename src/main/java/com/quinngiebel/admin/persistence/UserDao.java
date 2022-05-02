package com.quinngiebel.admin.persistence;

import com.quinngiebel.admin.entities.Piece;
import com.quinngiebel.admin.entities.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type User dao.
 */
public class UserDao extends GenericDao<User> {

    /**
     * Default constructor for a User dao.
     */
    public UserDao() {
        this.setType(User.class);
    }

    /**
     * Returns a JSON representation of the list of pieces.
     * @param resultList The list of pieces.
     * @return A JSON representation of the list
     */
    public String toJSON(List<User> resultList) {
        return String.format("{\"users\":[%s]}",
                resultList.stream().map(User::toJSON).collect(Collectors.joining(",")));
    }
}
