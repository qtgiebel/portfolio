package com.quinngiebel.admin.persistence;

import com.quinngiebel.admin.entities.Category;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryDaoTest {

    CategoryDao categoryDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Create the DAO.
     */
    @BeforeEach
    void setUp() {
        categoryDao = new CategoryDao();
    }

    /* SUCCESS */

    /**
     * Verifies dao type is set correctly.
     */
    @Test
    void setTypeSuccess() {
        assertEquals(Category.class, categoryDao.getType());
    }

    /* FAILURE */

}



