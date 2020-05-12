6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/CommandTestUtils.java
import java.lang.reflect.Method;
import java.util.Objects;

import static org.junit.Assert.*;

public final class CommandTestUtils {

    private static final String PARSE_ARGUMENTS_EXPECTED_SIGNATURE = "protected boolean %s.parseArguments(java.lang.String)";

    private CommandTestUtils() {
        throw new UnsupportedOperationException("Constructor is not to be used for static utils collection class.");
    }

    public static void checkIfParseArgumentsIsOverridden(LibraryCommand cmd) {
        Objects.requireNonNull(cmd, "Given command must not be null.");

        String expectedSignature = String.format(PARSE_ARGUMENTS_EXPECTED_SIGNATURE, cmd.getClass().getName());
        checkIfMethodExists(cmd, expectedSignature, "parseArguments", String.class);
    }

    private static void checkIfMethodExists(Object obj, String expectedSign, String methodName,
            Class<?>... parameterTypes) {
        try {
            Method m = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
            assertEquals(methodName + " signature not as expected.", expectedSign, m.toString());
        } catch (NoSuchMethodException e) {
            System.err.println("ERROR: Method not found in " + obj.getClass() + ": " + e);
            fail(obj.getClass() + " is expected to implement method " + methodName + ".");
        }
    }

    public static void checkExecuteConsoleOutput(LibraryCommand cmd, LibraryData library, String expectedOutput) {
        Objects.requireNonNull(cmd, "Given command must not be null.");
        Objects.requireNonNull(library, "Given library must not be null.");
        Objects.requireNonNull(expectedOutput, "Given expected output must not be null.");

        StdStreamIntercept intercept = new StdStreamIntercept();

        intercept.stdCaptureStart();
        try {
            try {
                cmd.execute(library);
            } catch (Exception e) {
                System.err.println("ERROR: executing execute for console output check:" + e);
            }
            // ignore leading and trailing white spaces
            assertEquals("Console output not as expected in execute.", expectedOutput.replaceAll("\r", "").trim(),
                    intercept.getCapturedStdOut().replaceAll("\r", "").trim());
        } finally {
            intercept.stdCaptureStop();
        }
    }

    public static void checkExecuteConsoleOutputLines(String[] expectedConsoleOutput, LibraryCommand cmd,
            LibraryData library) {
        Objects.requireNonNull(cmd, "Given command must not be null.");
        Objects.requireNonNull(library, "Given library must not be null.");
        Objects.requireNonNull(expectedConsoleOutput, "Given console output must not be null.");

        StdStreamIntercept intercept = new StdStreamIntercept();

        intercept.stdCaptureStart();
        try {
            try {
                cmd.execute(library);
            } catch (Exception e) {
                System.err.println("ERROR: executing execute for console output check:" + e);
            }

            // ignore leading and trailing white spaces
            String actualOutput = intercept.getCapturedStdOut().replaceAll("\r", "").trim();
            for (String expectedLine : expectedConsoleOutput) {
                boolean containsLine = actualOutput.contains(expectedLine);
                assertTrue("Given line not found in output as expected: " + expectedLine, containsLine);
            }
        } finally {
            intercept.stdCaptureStop();
        }
    }

    public static String captureExecuteStdOutput(LibraryCommand cmd, LibraryData library) {
        Objects.requireNonNull(cmd, "Given command must not be null.");
        Objects.requireNonNull(library, "Given library must not be null.");
        StdStreamIntercept intercept = new StdStreamIntercept();

        intercept.stdCaptureStart();
        String stdOut = null;
        try {
            try {
                cmd.execute(library);
            } catch (Exception e) {
                System.err.println("ERROR: executing execute for console output check:" + e);
            }
            stdOut = intercept.getCapturedStdOut();
        } finally {
            intercept.stdCaptureStop();
        }
        return stdOut;
    }

    public static String[] captureExecuteStdOutputLines(LibraryCommand cmd, LibraryData library) {
        Objects.requireNonNull(cmd, "Given command must not be null.");
        Objects.requireNonNull(library, "Given library must not be null.");

        String executeStdOut = CommandTestUtils.captureExecuteStdOutput(cmd, library);
        // ignore leading and trailing white spaces and correct for Windows line endings
        return executeStdOut.replaceAll("\r", "").trim().split("\n");
    }

    public static void checkCtorSuperclassCall(LibraryCommand cmd, CommandType type) {
        Objects.requireNonNull(cmd, "Given command must not be null.");
        Objects.requireNonNull(type, "Given type must not be null.");

        assertEquals("Type field in superclass not initialised as expected.", type, cmd.getType());
    }

    public static void checkArgumentInput(LibraryCommand cmd, boolean isLegal, String argument) {
        Objects.requireNonNull(cmd, "Given command must not be null.");
        Objects.requireNonNull(argument, "Given argument must not be null.");

        boolean result = cmd.parseArguments(argument);
        assertEquals("ParseArguments returned unexpected value for argument: " + argument, isLegal, result);
    }
}