package ua.shpp.fenuik;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TypeConverterTest {

    @Test
    void testConvertToInt() {
        Number result = TypeConverter.convertToType("123", DataType.INT);
        assertEquals(123, result);
        assertInstanceOf(Integer.class, result);
    }

    @Test
    void testConvertToLong() {
        Number result = TypeConverter.convertToType("123456789", DataType.LONG);
        assertEquals(123456789L, result);
        assertInstanceOf(Long.class, result);
    }

    @Test
    void testConvertToFloat() {
        Number result = TypeConverter.convertToType("123.45", DataType.FLOAT);
        assertEquals(123.45f, result);
        assertInstanceOf(Float.class, result);
    }

    @Test
    void testConvertToDouble() {
        Number result = TypeConverter.convertToType("123.456", DataType.DOUBLE);
        assertEquals(123.456, result);
        assertInstanceOf(Double.class, result);
    }

    @Test
    void testConvertToByte() {
        Number result = TypeConverter.convertToType("100", DataType.BYTE);
        assertEquals((byte) 100, result);
        assertInstanceOf(Byte.class, result);
    }

    @Test
    void testConvertToShort() {
        Number result = TypeConverter.convertToType("30000", DataType.SHORT);
        assertEquals((short) 30000, result);
        assertInstanceOf(Short.class, result);
    }

    @Test
    void testInvalidNumberFormat() {
        assertThrows(IllegalArgumentException.class, () ->
                TypeConverter.convertToType("notNumber", DataType.INT));
    }

    @Test
    void testByteOutOfRange() {
        assertThrows(IllegalArgumentException.class, () ->
                TypeConverter.convertToType("200", DataType.BYTE)); // Byte.MAX_VALUE = 127
    }

    @Test
    void testShortOutOfRange() {
        assertThrows(IllegalArgumentException.class, () ->
                TypeConverter.convertToType("40000", DataType.SHORT)); // Short.MAX_VALUE = 32767
    }
}
