6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/GroupCmdBasicTest.java
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GroupCmdBasicTest extends GroupCmdTest {

    // ------------------------- test helpers ----------------------------

    private void checkOutputHeader(String expectedHeader) {
        String[] executeStdOutLines = CommandTestUtils.captureExecuteStdOutputLines(testCommand, testLibrary);
        assertEquals("Unexpected group output header.", expectedHeader, executeStdOutLines[0]);
    }

    // ------------------------- parseArguments tests --------------------

    @Test
    public void testParseArgumentsIllegalArgument() {
        String blankArg = "";
        CommandTestUtils.checkArgumentInput(testCommand, false, blankArg);
        CommandTestUtils.checkArgumentInput(testCommand, false, "nonsense");
    }

    @Test
    public void testParseArgumentsLegalArgument() {
        CommandTestUtils.checkArgumentInput(testCommand, true, TITLE_ARGUMENT);
        CommandTestUtils.checkArgumentInput(testCommand, true, AUTHOR_ARGUMENT);
    }

    // ------------------------- execute tests --------------------

    @Test
    public void testExecuteEmptyBookData() {
        testLibrary = new LibraryData();
        List<BookEntry> bookData = Collections.<BookEntry>emptyList();
        FieldTestUtils.setPrivateField(testLibrary, testLibrary.getClass(), "books", bookData);

        String expectedConsoleOutput = "The library has no book entries.";
        CommandTestUtils.checkExecuteConsoleOutput(testCommand, testLibrary, expectedConsoleOutput);
    }

    @Test
    public void testExecuteGroupByTitleLines() {
        checkOutputLineCount(16); // one for the header, one for each group and one for each title entry
    }

    @Test
    public void testExecuteGroupByAuthorLines() {
        testCommand = new GroupCmd(AUTHOR_ARGUMENT);
        checkOutputLineCount(16); // one for the header, one for each group and one for each title entry
    }

    @Test
    public void testExecuteGroupByTitleHeader() {
        checkOutputHeader(String.format(GROUP_HEADER_OUTPUT, TITLE_ARGUMENT));
    }

    @Test
    public void testExecuteGroupByAuthorHeader() {
        testCommand = new GroupCmd(AUTHOR_ARGUMENT);
        checkOutputHeader(String.format(GROUP_HEADER_OUTPUT, AUTHOR_ARGUMENT));
    }

    @Test
    public void testExecuteGroupByTitleGroups() {
        String[] executeStdOutLines = CommandTestUtils.captureExecuteStdOutputLines(testCommand, testLibrary);
        List<String> expectedGroups = List.of("A", "B", "C", "D", "E", "F");
        checkGroupOutputOrder(executeStdOutLines, expectedGroups);
    }

    @Test
    public void testExecuteGroupByAuthorGroups() {
        testCommand = new GroupCmd(AUTHOR_ARGUMENT);
        String[] executeStdOutLines = CommandTestUtils.captureExecuteStdOutputLines(testCommand, testLibrary);
        List<String> expectedGroups = List.of("A Author", "B Author", "C Author", "D Author", "E Author", "F Author");
        checkGroupOutputOrder(executeStdOutLines, expectedGroups);
    }
}