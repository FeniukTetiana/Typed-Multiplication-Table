package ua.shpp.fenuik;

public class RangeChecker {
    private RangeChecker() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static boolean isWithinRange(Number value, DataType dataType) {
        double val = value.doubleValue();
        return switch (dataType) {
            case BYTE -> val >= Byte.MIN_VALUE && val <= Byte.MAX_VALUE;
            case SHORT -> val >= Short.MIN_VALUE && val <= Short.MAX_VALUE;
            case INT -> val >= Integer.MIN_VALUE && val <= Integer.MAX_VALUE;
            case LONG -> val >= Long.MIN_VALUE && val <= Long.MAX_VALUE;
            case FLOAT -> val >= -Float.MAX_VALUE && val <= Float.MAX_VALUE;
            case DOUBLE -> val >= -Double.MAX_VALUE && val <= Double.MAX_VALUE;
        };
    }
}