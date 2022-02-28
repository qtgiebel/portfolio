package com.quinngiebel.utilities;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Eric Knapp
 *
 */
public interface PropertiesLoader {

    default Properties loadProperties(String propertiesFilePath) throws Exception {
        Properties properties = new Properties();
        Logger logger = LogManager.getLogger(this.getClass());

        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (Exception ioException) {
            logger.error("Error loading properties...", ioException);
            throw ioException;
        }
        return properties;
    }
}