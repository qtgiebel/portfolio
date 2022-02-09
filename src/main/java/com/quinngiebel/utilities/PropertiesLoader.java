package com.quinngiebel.utilities;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Eric Knapp
 *
 */
public interface PropertiesLoader {

    default Properties loadProperties(String propertiesFilePath) throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (Exception ioException) {
            ioException.printStackTrace();
            throw ioException;
        }
        return properties;
    }
}