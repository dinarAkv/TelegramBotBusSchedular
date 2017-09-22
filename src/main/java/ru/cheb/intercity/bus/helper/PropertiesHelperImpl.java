package ru.cheb.intercity.bus.helper;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class PropertiesHelperImpl implements PropertiesHelper {



    final static Logger logger = Logger.getLogger(PropertiesHelperImpl.class);

    /**
     * Function return value by key in properties file.
     * @param fileName - name of "*.properties" file.
     * @param propertyKey - key string of particular property.
     * @return - host website url.
     */
    @Override
    public String getPropByKeyInProperties(String fileName, String propertyKey)
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
