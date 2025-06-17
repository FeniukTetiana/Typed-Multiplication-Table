package ua.shpp.fenuik;

public enum DataType {
    BYTE, SHORT, INT, LONG, FLOAT, DOUBLE;

    public static DataType from(String typeStr) {
        try {
            return DataType.valueOf(typeStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown data type: " + typeStr, e);
        }
    }
}
