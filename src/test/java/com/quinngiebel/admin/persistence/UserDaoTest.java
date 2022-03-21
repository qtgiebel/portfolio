package com.quinngiebel.admin.persistence;

import com.quinngiebel.admin.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {

    UserDao userDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Create the DAO.
     */
    @BeforeEach
    void setUp() {
        userDao = new UserDao();
    }

    /**
     * Verifies dao type is set correctly.
     */
    @Test
    void setTypeSuccess() {
        assertEquals(User.class, userDao.getType());
    }
}
