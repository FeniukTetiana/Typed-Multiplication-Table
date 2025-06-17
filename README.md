# Typed Multiplication Table Generator
This is a Java-based command-line utility designed to generate a multiplication table. The main feature of the project is its ability to work with various numeric data types, flexible configuration of the range and step, and a built-in system for checking type overflow.

# Key Features
- Table Generation: Creates a multiplication table for a specified range of numbers (min, max).
- Custom Step: Allows specifying an increment for iterating through the range.
- Data Type Support: Works with various Java primitive data types:
  . BYTE, SHORT, INT, LONG (integer types)
  . FLOAT, DOUBLE (floating-point types)
- Overflow Control: The program checks if the result of a multiplication exceeds the permissible range for the selected data type.
- Flexible Error Handling: The application can be configured to either stop execution upon the first overflow (stopOnOverflow=true) or simply log an error and continue running.
- File-Based Configuration: All parameters are set via an application.properties file, allowing for easy changes without recompiling the code.
- High Precision: For FLOAT and DOUBLE types, calculations are performed using BigDecimal to ensure the accuracy of floating-point operations.

# Configuration
To run the program, you need to create an application.properties file in the src/main/resources directory.

1. The application.properties File
This file contains the primary settings for generating the table.

```properties
# The minimum value of the range
min=1

# The maximum value of the range
max=10

# The step for iterating through the range
increment=1

# Stop execution on data type overflow
# true - stop the program with an error
# false - log the error and continue execution
stopOnOverflow=true
```
2. Data Type (System Property)
The data type for which the table will be generated is specified via the type System Property when launching the program. If this property is not provided, INT is used by default.

Available values for type: byte, short, int, long, float, double (case-insensitive).

# Build and Run
Requirements
 - Java Development Kit (JDK) 21 or newer
 - Apache Maven 3.9.9 or newer

1. Build the Project
Navigate to the project's root directory and execute the Maven command to build the JAR file:

```bash
mvn clean package
```
After a successful build, an executable *.jar file will be created in the target/ directory.

2. Run the Application
Use the java -Dtype=${type} -jar command to run the program. Remember to specify the data type using the -Dtype flag.

Launch Examples:

```bash
java -Dtype=double -jar target/typed-multiplication-table-${project.version}.jar
Generate for the DOUBLE type:
```

```bash
java -Dtype=byte -jar target/typed-multiplication-table-${project.version}.jar
Generate for the BYTE type (which can cause a quick overflow):
```

```bash
# In application.properties: min=1, max=20, stopOnOverflow=false
java -Dtype=byte -jar target/typed-multiplication-table-${project.version}.jar
In this case, you will see overflow error messages in the logs, but the program will continue its execution.
```

# Project Architecture
- Main.java: The main class and entry point of the application. Responsible for loading the configuration, validating parameters, and launching the generator.
- MultiplicationTableGenerator.java: The core business logic. Contains methods for generating the table for both integer and decimal types.
- PropertiesLoader.java: Responsible for loading and parsing the application.properties file and system properties.
- DataType.java: An enum that defines the supported data types.
- RangeChecker.java: A utility class for checking if a number is within the valid range of the selected data type.
- TypeConverter.java: A utility class for converting string values from the configuration into their corresponding numeric types.
- ProgramParameters.java: A record for conveniently storing and passing around the program's parameters.
- InvalidPropertyException.java: A custom exception for handling configuration errors.
  
# License
This project is intended for demonstration purposes in my portfolio only. All code is protected by copyright.

You are free to view and clone the repository to review the code. However, you are not permitted to modify, distribute, or use this code or any part of it in any other project (commercial or non-commercial) without my explicit written consent.
