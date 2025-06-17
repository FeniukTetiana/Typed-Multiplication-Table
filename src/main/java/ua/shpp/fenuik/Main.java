package ua.shpp.fenuik;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String PROPERTIES_FILE_NAME = "application.properties";

    public static void main(String[] args) {
        LOGGER.info("The program is running");

        try {
            PropertiesLoader propertiesLoader = new PropertiesLoader(PROPERTIES_FILE_NAME);
            ProgramParameters params = loadAndValidateParameters(propertiesLoader);

            if (areValuesWithinRange(params)) {
                LOGGER.info("All values are in a range of type {}", params.type());
                LOGGER.info("Table of multiplication from {} to {} with a step {} for type {} (stopOnOverflow = {})",
                        params.min(), params.max(), params.incrementRaw(), params.type(), params.stopOnOverflow());

                MultiplicationTableGenerator.generate(
                        params.min(), params.max(), params.increment(), params.type(), params.stopOnOverflow()
                );
                LOGGER.info("The program has ended");
            } else {
                LOGGER.error("min={}, max={}, increment={} are not all in valid range for {}",
                        params.min(), params.max(), params.increment(), params.type());
                throw new InvalidPropertyException("Values are out of range");
            }

        } catch (InvalidPropertyException e) {
            LOGGER.error("Invalid properties: ", e);
        } catch (Exception e) {
            LOGGER.error("An error occurred:", e);
        }
    }

    private static ProgramParameters loadAndValidateParameters(PropertiesLoader loader)
            throws InvalidPropertyException {
        DataType type = loader.getTypeProperty();
        boolean stopOnOverflow = loader.getStopOnOverflowProperty();
        String minStr = loader.getProperty("min");
        String maxStr = loader.getProperty("max");
        String incrementStr = loader.getProperty("increment");

        validateNotEmpty(minStr, maxStr, incrementStr);

        Number min = convertToType(minStr, type);
        Number max = convertToType(maxStr, type);
        Number increment = convertToType(incrementStr, type);

        return new ProgramParameters(min, max, increment, incrementStr, type, stopOnOverflow);
    }

    private static void validateNotEmpty(String... values) throws InvalidPropertyException {
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                LOGGER.error("min, max or increment cannot be empty");
                throw new InvalidPropertyException("min, max or increment cannot be empty");
            }
        }
    }

    private static boolean areValuesWithinRange(ProgramParameters params) {
        return RangeChecker.isWithinRange(params.min(), params.type()) &&
                RangeChecker.isWithinRange(params.max(), params.type()) &&
                RangeChecker.isWithinRange(params.increment(), params.type());
    }

    protected static Number convertToType(String value, DataType dataType) {
        return TypeConverter.convertToType(value, dataType);
    }
}
