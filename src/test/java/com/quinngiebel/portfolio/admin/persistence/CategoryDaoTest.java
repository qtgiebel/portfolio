package com.quinngiebel.portfolio.admin.persistence;

import com.quinngiebel.test.utilities.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;

public class CategoryDaoTest {
    CategoryDao categoryDao;
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
        categoryDao = new CategoryDao();
    }


}
