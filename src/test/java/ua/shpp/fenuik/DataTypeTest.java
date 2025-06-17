package ua.shpp.fenuik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTypeTest {

    @Test
    void testValidTypesCaseInsensitive() {
        assertEquals(DataType.INT, DataType.from("int"));
        assertEquals(DataType.INT, DataType.from("InT"));

        assertEquals(DataType.LONG, DataType.from("LONG"));
        assertEquals(DataType.LONG, DataType.from("long"));

        assertEquals(DataType.FLOAT, DataType.from("float"));
        assertEquals(DataType.DOUBLE, DataType.from("DOUBLE"));

        assertEquals(DataType.BYTE, DataType.from("ByTe"));
        assertEquals(DataType.SHORT, DataType.from("short"));
    }

    @Test
    void testTrimmedInput() {
        assertEquals(DataType.INT, DataType.from(" int "));
        assertEquals(DataType.DOUBLE, DataType.from("  double\t"));
    }

    @Test
    void testInvalidTypeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                DataType.from("unknown"));
        assertTrue(exception.getMessage().contains("Unknown data type"));
    }

    @Test
    void testNullInputThrowsException() {
        assertThrows(NullPointerException.class, () -> DataType.from(null));
    }
}