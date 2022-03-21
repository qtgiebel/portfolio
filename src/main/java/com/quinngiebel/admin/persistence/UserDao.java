package com.quinngiebel.admin.persistence;

import com.quinngiebel.admin.entities.User;

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
}
