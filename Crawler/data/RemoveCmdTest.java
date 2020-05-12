6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/RemoveCmdTest.java
import static org.junit.Assert.assertFalse;

public abstract class RemoveCmdTest extends CommandTest {

    protected static final String TITLE_VALUE_ARGUMENT = "TitleB";
    protected static final String AUTHOR_VALUE_ARGUMENT = "AuthorB";
    protected static final String GENERIC_VALUE_ARGUMENT = "some unknown value";

    protected static final String TITLE_REMOVE_MESSAGE = "%s: removed successfully.";
    protected static final String TITLE_NOT_FOUND_MESSAGE = "%s: not found.";
    protected static final String AUTHOR_REMOVE_MESSAGE = "%d books removed for author: %s";

    @Override
    protected CommandType getCmdType() {
        return CommandType.REMOVE;
    }

    // ------------------------- helpers ---------------------------------

    protected void checkRemoveTitleExecute(LibraryCommand cmd, LibraryData library, String removeValue) {
        cmd.execute(library);

        boolean found = false;
        for (BookEntry book : library.getBookData()) {
            String currentTitle = book.getTitle();
            if (currentTitle.equals(removeValue)) {
                found = true;
                break;
            }
        }

        assertFalse("Title: " + removeValue + " not removed as expected.", found);
    }

    protected void checkRemoveAuthorExecute(LibraryCommand cmd, LibraryData library, String removeValue) {
        cmd.execute(library);

        boolean found = false;
        for (BookEntry book : library.getBookData()) {
            String[] currentAuthors = book.getAuthors();
            for (String author : currentAuthors) {
                if (author.equals(removeValue)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        assertFalse("Title: " + removeValue + " not removed as expected.", found);
    }

    protected void checkEntryNotFound() {
        testCommand = new RemoveCmd(TITLE_ARGUMENT + " " + GENERIC_VALUE_ARGUMENT);
        String expectedConsoleOutput = String.format(TITLE_NOT_FOUND_MESSAGE, GENERIC_VALUE_ARGUMENT);
        CommandTestUtils.checkExecuteConsoleOutput(testCommand, testLibrary, expectedConsoleOutput);

        testCommand = new RemoveCmd(AUTHOR_ARGUMENT + " " + GENERIC_VALUE_ARGUMENT);
        int removedAuthors = 0;
        expectedConsoleOutput = String.format(AUTHOR_REMOVE_MESSAGE, removedAuthors, GENERIC_VALUE_ARGUMENT);
        CommandTestUtils.checkExecuteConsoleOutput(testCommand, testLibrary, expectedConsoleOutput);
    }

}