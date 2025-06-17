package ua.shpp.fenuik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesLoaderTest {

    @Test
    void testLoadPropertiesSuccessfully() throws InvalidPropertyException {
        PropertiesLoader loader = new PropertiesLoader("test.properties");
        assertEquals("1", loader.getProperty("min"));
        assertEquals("5", loader.getProperty("max"));
        assertEquals("1", loader.getProperty("increment"));
        assertTrue(loader.getStopOnOverflowProperty());
    }

}