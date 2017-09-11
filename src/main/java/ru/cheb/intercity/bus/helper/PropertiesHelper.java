package ru.cheb.intercity.bus.helper;


import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class contain methods for helping to work with properties.
 */
public class PropertiesHelper {



    final static Logger logger = Logger.getLogger(PropertiesHelper.class);

    /**
     * Function return value by key in properties file.
     * @param fileName - name of "*.properties" file.
     * @param propertyKey - key string of particular property.
     * @return - host website url.
     */
    public static String getPropByKeyInProperties(String fileName, String propertyKey)
    {
        Properties properties = new Properties();

        try(InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
            return properties.getProperty(propertyKey);
        }
        catch (IOException ex)
        {
            logger.error(ex.getMessage());
        }

        throw new IllegalStateException();
    }


}
