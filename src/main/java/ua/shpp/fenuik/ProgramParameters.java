package ua.shpp.fenuik;

public record ProgramParameters(
        Number min,
        Number max,
        Number increment,
        String incrementRaw,
        DataType type,
        boolean stopOnOverflow
) {}
