package ua.shpp.fenuik;

public class InvalidPropertyException extends Exception {
    public InvalidPropertyException(String message) {
        super(message);
    }

    public InvalidPropertyException(String message, Throwable cause) {
        super(message, cause);
    }
}
