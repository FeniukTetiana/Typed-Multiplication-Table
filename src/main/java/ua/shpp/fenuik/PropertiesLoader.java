package ua.shpp.fenuik;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class PropertiesLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);
    private static final String TYPE = "type";
    private static final String STOP_ON_OVERFLOW = "stopOnOverflow";
    private final Properties properties = new Properties();

    public PropertiesLoader(String fileName) throws InvalidPropertyException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input != null) {
                properties.load(input);
                LOGGER.debug("Loaded properties from {}", fileName);
            } else {
                throw new InvalidPropertyException("Properties file not found: " + fileName);
            }
        } catch (IOException e) {
            throw new InvalidPropertyException("Error loading properties file: " + fileName, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public DataType getTypeProperty() {
        String systemTypeProperty = System.getProperty(TYPE);
        String typeString = systemTypeProperty != null ? systemTypeProperty : "int";
        return DataType.from(typeString);
    }

    public boolean getStopOnOverflowProperty() {
        String value = properties.getProperty(STOP_ON_OVERFLOW);
        return value != null && Boolean.parseBoolean(value.trim());
    }
}