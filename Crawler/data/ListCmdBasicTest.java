6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/ListCmdBasicTest.java
import org.junit.Test;

public class ListCmdBasicTest extends ListCmdTest {

    // ------------------------- parseArguments tests --------------------

    @Test
    public void testParseArgumentsIllegalArgument() {
        CommandTestUtils.checkArgumentInput(testCommand, false, "nonsense");
    }

    @Test
    public void testParseArgumentsLegalArgument() {
        CommandTestUtils.checkArgumentInput(testCommand, true, SHORT_ARGUMENT);
        CommandTestUtils.checkArgumentInput(testCommand, true, LONG_ARGUMENT);
        CommandTestUtils.checkArgumentInput(testCommand, true, BLANK_ARGUMENT);
    }

    // ------------------------- execute tests --------------------

    @Test
    public void testExecuteShortList() {
        String expectedConsoleOutput = "3 books in library:\nTitleA\nTitleB\nTitleC";
        CommandTestUtils.checkExecuteConsoleOutput(testCommand, testLibrary, expectedConsoleOutput);
    }

    @Test
    public void testExecuteLongList() {
        testCommand = new ListCmd(LONG_ARGUMENT);

        String expectedConsoleOutput =
        "3 books in library:\n" +
        "TitleA\n" +
        "by AuthorA\n" +
        "Rating: 3.20\n" +
        "ISBN: ISBNA\n" +
        "500 pages\n\n" +
        "TitleB\n" +
        "by AuthorB\n" +
        "Rating: 4.30\n" +
        "ISBN: ISBNB\n" +
        "400 pages\n\n" +
        "TitleC\n" +
        "by AuthorC\n" +
        "Rating: 1.30\n" +
        "ISBN: ISBNC\n" +
        "300 pages";

        CommandTestUtils.checkExecuteConsoleOutput(testCommand, testLibrary, expectedConsoleOutput);
    }
}
