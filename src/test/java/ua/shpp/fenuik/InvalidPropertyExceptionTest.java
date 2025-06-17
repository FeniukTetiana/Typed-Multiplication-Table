package ua.shpp.fenuik;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class InvalidPropertyExceptionTest {

    @Test
    void testInvalidPropertyExceptionWithMessage() {
        String errorMessage = "This is an error message";

        InvalidPropertyException exception = new InvalidPropertyException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testInvalidPropertyExceptionWithMessageAndCause() {
        String errorMessage = "This is an error message";
        Throwable cause = new IllegalArgumentException("Illegal argument");

        InvalidPropertyException exception = new InvalidPropertyException(errorMessage, cause);

        assertEquals(errorMessage, exception.getMessage());

        assertEquals(cause, exception.getCause());
    }
}
