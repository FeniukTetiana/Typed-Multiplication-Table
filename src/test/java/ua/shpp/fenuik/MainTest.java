package ua.shpp.fenuik;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testConvertToTypeInt() {
        assertEquals(42, Main.convertToType("42", DataType.INT));
    }

    @Test
    void testConvertToTypeLong() {
        assertEquals(42L, Main.convertToType("42", DataType.LONG));
    }

    @Test
    void testConvertToTypeFloat() {
        assertEquals(42.0f, Main.convertToType("42", DataType.FLOAT));
    }

    @Test
    void testConvertToTypeDouble() {
        assertEquals(42.0, Main.convertToType("42", DataType.DOUBLE));
    }

    @Test
    void testConvertToTypeInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Main.convertToType("abc", DataType.INT));
    }
}