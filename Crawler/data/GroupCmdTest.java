6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/GroupCmdTest.java
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class GroupCmdTest extends CommandTest {

    protected static final String GROUP_HEADER_OUTPUT = "Grouped data by %s";
    protected static final String GROUP_TITLE_PREFIX = "## ";

    @Override
    protected CommandType getCmdType() {
        return CommandType.GROUP;
    }

    @Before
    public void setup() {
        testCommand = new GroupCmd(TITLE_ARGUMENT);

        testLibrary = new LibraryData();
        List<BookEntry> bookData = new ArrayList<>();
        bookData.add(new BookEntry("B Title", new String[] { "B Author" }, 3.2f, "ISBNB", 500));
        bookData.add(new BookEntry("A Title", new String[] { "A Author" }, 4.3f, "ISBNA", 400));
        bookData.add(new BookEntry("B Title 2", new String[] { "B Author" }, 4.3f, "ISBNB2", 400));
        bookData.add(new BookEntry("C Title", new String[] { "C Author" }, 1.3f, "ISBNC", 300));
        bookData.add(new BookEntry("A Title 2", new String[] { "A Author" }, 1.3f, "ISBNA2", 300));
        bookData.add(new BookEntry("D Title", new String[] { "D Author" }, 1.3f, "ISBND", 300));
        bookData.add(new BookEntry("A Title 3", new String[] { "A Author" }, 1.3f, "ISBNA3", 300));
        bookData.add(new BookEntry("F Title", new String[] { "F Author" }, 1.3f, "ISBNF", 300));
        bookData.add(new BookEntry("E Title", new String[] { "E Author" }, 1.3f, "ISBNE", 300));
        FieldTestUtils.setPrivateField(testLibrary, testLibrary.getClass(), "books", bookData);
    }

    // ------------------------- test helpers ----------------------------

    protected void checkOutputLineCount(int expectedLines) {
        String[] executeStdOutLines = CommandTestUtils.captureExecuteStdOutputLines(testCommand, testLibrary);
        assertEquals("Unexpected amount of output lines for group output.", expectedLines, executeStdOutLines.length);
    }

    protected void checkOutputLinesIgnoringOrder(String[] expectedOutputLines) {
        CommandTestUtils.checkExecuteConsoleOutputLines(expectedOutputLines, testCommand, testLibrary);
        checkOutputLineCount(expectedOutputLines.length);
    }

    protected void checkGroupOutputOrder(String[] executeStdOutLines, List<String> expectedGroups) {
        List<String> actualGroups = new ArrayList<>();

        for (int i = 0; i < executeStdOutLines.length; i++) {
            String currentLine = executeStdOutLines[i].strip();
            if (currentLine.startsWith(GROUP_TITLE_PREFIX)) {
                actualGroups.add(currentLine.substring(GROUP_TITLE_PREFIX.length()));
            }
        }

        assertEquals("Groups not printed in expected order.", expectedGroups, actualGroups);
    }
}