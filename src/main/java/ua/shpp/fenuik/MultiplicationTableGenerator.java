package ua.shpp.fenuik;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MultiplicationTableGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiplicationTableGenerator.class);
    private static final int DECIMAL_SCALE = 5;

    private MultiplicationTableGenerator() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static void generate(Number minValue, Number maxValue, Number incrementValue, DataType dataType,
                                boolean stopOnOverflow) {
        double min = minValue.doubleValue();
        double max = maxValue.doubleValue();
        double increment = incrementValue.doubleValue();

        if (increment == 0) {
            LOGGER.error("The increment cannot be equal to 0");
            throw new IllegalArgumentException("The increment cannot be equal to 0");
        }

        if ((increment > 0 && min > max) || (increment < 0 && min < max)) {
            LOGGER.error("Invalid increment direction: cannot reach from {} to {} with step {}", min, max, increment);
            throw new IllegalArgumentException("The increment direction does not allow traversal from min to max");
        }

        switch (dataType) {
            case BYTE, SHORT, INT, LONG -> generateLongTable(minValue.longValue(), maxValue.longValue(),
                    incrementValue.longValue(), dataType, stopOnOverflow);
            case FLOAT, DOUBLE -> generateDecimalTable(minValue.doubleValue(), maxValue.doubleValue(),
                    incrementValue.doubleValue(), dataType, stopOnOverflow);
        }
    }

    private static void generateLongTable(long min, long max, long increment, DataType dataType,
                                          boolean stopOnOverflow) {
        for (long i = min; compare(i, max, increment); i += increment) {
            generateLongRow(i, min, max, increment, dataType, stopOnOverflow);
        }
    }

    private static void generateLongRow(long i, long min, long max, long increment, DataType dataType,
                                        boolean stopOnOverflow) {
        for (long j = min; compare(j, max, increment); j += increment) {
            long result = i * j;
            if (RangeChecker.isWithinRange(result, dataType)) {
                LOGGER.info("{} x {} = {}", i, j, result);
            } else {
                LOGGER.error("{} x {} = {} is out of range for type {}", i, j, result, dataType);
                if (stopOnOverflow) {
                    throw new ArithmeticException("The result is out of range for " + dataType);
                }
            }
        }
    }

    private static boolean compare(long a, long b, long increment) {
        return increment > 0 ? a <= b : a >= b;
    }

    private static void generateDecimalTable(double min, double max, double increment, DataType dataType,
                                             boolean stopOnOverflow) {
        BigDecimal bigMin = BigDecimal.valueOf(min);
        BigDecimal bigMax = BigDecimal.valueOf(max);
        BigDecimal bigIncrement = BigDecimal.valueOf(increment);

        for (BigDecimal i = bigMin; compare(i, bigMax, increment); i = i.add(bigIncrement)) {
            generateDecimalRow(i, bigMin, bigMax, bigIncrement, dataType, stopOnOverflow, increment);
        }
    }

    private static void generateDecimalRow(BigDecimal i, BigDecimal bigMin, BigDecimal bigMax,
                                           BigDecimal bigIncrement, DataType dataType,
                                           boolean stopOnOverflow, double increment) {
        for (BigDecimal j = bigMin; compare(j, bigMax, increment); j = j.add(bigIncrement)) {
            BigDecimal result = i.multiply(j);
            if (RangeChecker.isWithinRange(result.doubleValue(), dataType)) {
                LOGGER.info("{} x {} = {}",
                        i.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP),
                        j.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP),
                        result.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
            } else {
                LOGGER.error("{} x {} = {} is out of range for type {}", i, j, result, dataType);
                if (stopOnOverflow) {
                    throw new ArithmeticException("The result is out of range for " + dataType);
                }
            }
        }
    }

    private static boolean compare(BigDecimal a, BigDecimal b, double increment) {
        return increment > 0 ? a.compareTo(b) <= 0 : a.compareTo(b) >= 0;
    }
}