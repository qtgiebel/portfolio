package com.quinngiebel.admin.persistence;

import com.quinngiebel.admin.entities.User;
import com.quinngiebel.test.utilities.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the generic dao methods.
 */
public class GenericDaoTest {

    GenericDao dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Create the DAO.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        if (database.runSQL("create_art_test.sql")) {
            logger.info("Database reset success");
        }
        dao = new GenericDao(User.class);
    }

    /* SUCCESS */

    /**
     * Verifies that an entity is added.
     */
     @Test
     void insertSuccess() {
         User user = new User("asdf@gmail.com", "password");
         User output = (User) dao.getById(dao.insert(user));
         assertEquals(user, output);
     }

    /**
     * Verifies gets all entities successfully.
     */
    @Test
    void getAllSuccess() {
        List<User> users = (List<User>) dao.getAll();
        assertEquals(2, users.size());
    }

    /**
     * Verifies gets an entity by its id
     */
    @Test
    void getByIdSuccess() {
        User user = (User) dao.getById(1);
        assertEquals("qtgiebel@gmail.com", user.getEmail());
    }

    /**
     * Verifies gets an entity by a column.
     */
    @Test
    void getByColumnSuccess() {
        User user = (User) dao.getByColumn("email", "qtgiebel@gmail.com").get(0);
        assertEquals(1, user.getId());
    }

    /**
     * Verifies an entity is successfully deleted.
     */
    @Test
    void deletePieceSuccess() {
        User toDelete = (User) dao.getAll().get(1);
        dao.delete(toDelete);
        assertEquals(1, dao.getAll().size());
    }

    /* FAILURE */
}
