6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/SearchCmdBasicTest.java
import org.junit.Test;

public class SearchCmdBasicTest extends SearchCmdTest {

    // ------------------------- parseArguments tests --------------------

    @Test
    public void testParseArgumentsIllegalArgument() {
        String blankArg = "";
        CommandTestUtils.checkArgumentInput(testCommand, false, blankArg);

        String argWithSpaces = "invalid search query";
        CommandTestUtils.checkArgumentInput(testCommand, false, argWithSpaces);
    }

    @Test
    public void testParseArgumentsLegalArgument() {
        CommandTestUtils.checkArgumentInput(testCommand, true, SINGLE_WORD_TITLE);

        String argWithHyphen = "Hundred-Dollar";
        CommandTestUtils.checkArgumentInput(testCommand, true, argWithHyphen);
    }

    // ------------------------- execute tests --------------------

    @Test
    public void testExecuteFindExactMatch() {
        String expectedConsoleOutput = SINGLE_WORD_TITLE;
        CommandTestUtils.checkExecuteConsoleOutput(testCommand, testLibrary, expectedConsoleOutput);
    }

    @Test
    public void testExecuteFindWordMatch() {
        String expectedConsoleOutput = MULTI_WORD_TITLE_A;
        testCommand = new SearchCmd(MULTI_WORD_SEARCH_TERM_SINGLE_HIT);
        CommandTestUtils.checkExecuteConsoleOutput(testCommand, testLibrary, expectedConsoleOutput);
    }

    @Test
    public void testExecuteFindNoMatch() {
        String searchTerm = "Unknown";
        String expectedConsoleOutput = NO_HITS_FOUND_MESSAGE + searchTerm;
        testCommand = new SearchCmd(searchTerm);
        CommandTestUtils.checkExecuteConsoleOutput(testCommand, testLibrary, expectedConsoleOutput);
    }
}
