package ua.shpp.fenuik;

public class TypeConverter {
    private TypeConverter() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static Number convertToType(String value, DataType dataType) {
        try {
            return switch (dataType) {
                case INT -> Integer.parseInt(value);
                case LONG -> Long.parseLong(value);
                case FLOAT -> Float.parseFloat(value);
                case DOUBLE -> Double.parseDouble(value);
                case BYTE -> Byte.parseByte(value);
                case SHORT -> Short.parseShort(value);
            };
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Value \"" + value + "\" does not match the type "
                    + dataType.name(), e);
        }
    }
}