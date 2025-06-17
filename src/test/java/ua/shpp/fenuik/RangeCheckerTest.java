package ua.shpp.fenuik;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RangeCheckerTest {

    @Test
    void testByteRange() {
        assertTrue(RangeChecker.isWithinRange((byte)0, DataType.BYTE));
        assertTrue(RangeChecker.isWithinRange(Byte.MIN_VALUE, DataType.BYTE));
        assertTrue(RangeChecker.isWithinRange(Byte.MAX_VALUE, DataType.BYTE));
        assertFalse(RangeChecker.isWithinRange(Byte.MIN_VALUE - 1L, DataType.BYTE));
        assertFalse(RangeChecker.isWithinRange(Byte.MAX_VALUE + 1L, DataType.BYTE));
    }

    @Test
    void testShortRange() {
        assertTrue(RangeChecker.isWithinRange((short)0, DataType.SHORT));
        assertTrue(RangeChecker.isWithinRange(Short.MIN_VALUE, DataType.SHORT));
        assertTrue(RangeChecker.isWithinRange(Short.MAX_VALUE, DataType.SHORT));
        assertFalse(RangeChecker.isWithinRange(Short.MIN_VALUE - 1L, DataType.SHORT));
        assertFalse(RangeChecker.isWithinRange(Short.MAX_VALUE + 1L, DataType.SHORT));
    }

    @Test
    void testIntRange() {
        assertTrue(RangeChecker.isWithinRange(0, DataType.INT));
        assertTrue(RangeChecker.isWithinRange(Integer.MIN_VALUE, DataType.INT));
        assertTrue(RangeChecker.isWithinRange(Integer.MAX_VALUE, DataType.INT));
        assertFalse(RangeChecker.isWithinRange((long)Integer.MIN_VALUE - 1, DataType.INT));
        assertFalse(RangeChecker.isWithinRange((long)Integer.MAX_VALUE + 1, DataType.INT));
    }

    @Test
    void testLongRange() {
        assertTrue(RangeChecker.isWithinRange(0L, DataType.LONG));
        assertTrue(RangeChecker.isWithinRange(Long.MIN_VALUE, DataType.LONG));
        assertTrue(RangeChecker.isWithinRange(Long.MAX_VALUE, DataType.LONG));
    }

    @Test
    void testFloatRange() {
        assertTrue(RangeChecker.isWithinRange(0.0f, DataType.FLOAT));
        assertTrue(RangeChecker.isWithinRange(Float.MIN_VALUE, DataType.FLOAT));
        assertTrue(RangeChecker.isWithinRange(Float.MAX_VALUE, DataType.FLOAT));
        assertFalse(RangeChecker.isWithinRange(Float.MAX_VALUE * 2, DataType.FLOAT));
        assertFalse(RangeChecker.isWithinRange(-Float.MAX_VALUE * 2, DataType.FLOAT));
    }

    @Test
    void testDoubleRange() {
        assertTrue(RangeChecker.isWithinRange(0.0, DataType.DOUBLE));
        assertTrue(RangeChecker.isWithinRange(Double.MIN_VALUE, DataType.DOUBLE));
        assertTrue(RangeChecker.isWithinRange(Double.MAX_VALUE, DataType.DOUBLE));
        assertFalse(RangeChecker.isWithinRange(Double.MAX_VALUE * 2, DataType.DOUBLE));
        assertFalse(RangeChecker.isWithinRange(-Double.MAX_VALUE * 2, DataType.DOUBLE));
    }
}
