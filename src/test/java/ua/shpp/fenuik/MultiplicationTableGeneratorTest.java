package ua.shpp.fenuik;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MultiplicationTableGeneratorTest {

    @Test
    void testGenerateWithZeroIncrementThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                MultiplicationTableGenerator.generate(1, 5, 0,
                        DataType.INT, false));
        assertEquals("The increment cannot be equal to 0", exception.getMessage());
    }

    @Test
    void testGenerateWithWrongDirectionThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                MultiplicationTableGenerator.generate(10, 1, 1,
                        DataType.INT, false));
        assertEquals("The increment direction does not allow traversal from min to max",
                exception.getMessage());
    }

    @Test
    void testGenerateIntTableWithoutOverflow() {
        assertDoesNotThrow(() ->
                MultiplicationTableGenerator.generate(1, 3, 1,
                        DataType.INT, true));
    }

    @Test
    void testGenerateDoubleTableWithoutOverflow() {
        assertDoesNotThrow(() ->
                MultiplicationTableGenerator.generate(1.0, 2.0, 0.5,
                        DataType.DOUBLE, true));
    }

    @Test
    void testOverflowWithStopOnOverflowThrowsException() {
        assertThrows(ArithmeticException.class, () ->
                MultiplicationTableGenerator.generate(30000, 40000, 5000,
                        DataType.SHORT, true));
    }

    @Test
    void testOverflowWithStopOnOverflowFalseDoesNotThrow() {
        assertDoesNotThrow(() ->
                MultiplicationTableGenerator.generate(30000, 40000, 5000,
                        DataType.SHORT, false));
    }

    @Test
    void testNegativeIncrementDirectionAllowed() {
        assertDoesNotThrow(() ->
                MultiplicationTableGenerator.generate(5, 1, -1,
                        DataType.INT, false));
    }

    @Test
    void testDecimalWithNegativeIncrement() {
        assertDoesNotThrow(() ->
                MultiplicationTableGenerator.generate(2.0, 1.0, -0.5,
                        DataType.FLOAT, false));
    }
}