6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/AddCmdBasicTest.java
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddCmdBasicTest extends CommandTest {

    private static final String TEST_PATH = "booksTestData01.csv";

    @Override
    protected CommandType getCmdType() {
        return CommandType.ADD;
    }

    @Before
    public void setup() {
        testCommand = new AddCmd(TEST_PATH);

        testLibrary = new LibraryData();
        List<BookEntry> bookData = new ArrayList<>();
        bookData.add(new BookEntry("TitleA", new String[] { "AuthorA" }, 3.2f, "ISBNA", 500));
        FieldTestUtils.setPrivateField(testLibrary, testLibrary.getClass(), "books", bookData);
    }

    // ------------------------- parseArguments tests --------------------

    @Test
    public void testParseArgumentsIllegalArgument() {
        String blankArg = "";
        CommandTestUtils.checkArgumentInput(testCommand, false, blankArg);
        String invalidPath = "notACsvfile.dat";
        CommandTestUtils.checkArgumentInput(testCommand, false, invalidPath);
        invalidPath = "this/is/not/a/csv/file.txt";
        CommandTestUtils.checkArgumentInput(testCommand, false, invalidPath);
    }

    @Test
    public void testParseArgumentsLegalArgument() {
        CommandTestUtils.checkArgumentInput(testCommand, true, TEST_PATH);
        CommandTestUtils.checkArgumentInput(testCommand, true, "this/is/some/test/path/for/books.csv");
    }

    // ------------------------- execute tests --------------------

    @Test
    public void testExecuteLoadData() {
        testCommand.execute(testLibrary);

        List<BookEntry> books = testLibrary.getBookData();
        int expectedBookAmount = 3;
        assertEquals("Unexpected amount of books in library after loading file.", expectedBookAmount, books.size());

        List<Object[]> expectedBookValues = new ArrayList<>();
        expectedBookValues.add(new Object[] { "TitleA", new String[] { "AuthorA" }, 3.2f, "ISBNA", 500 });
        expectedBookValues.add(
                new Object[] { "The Changeling", new String[] { "Zilpha Keatley Snyder" }, 4.17f, "595321801", 228 });
        expectedBookValues.add(
                new Object[] { "Animal Farm", new String[] { "George Orwell" }, 3.91f, "452284244", 122 });

        for (int i = 0; i < books.size(); i++) {
            BookEntryTestUtils.checkBookFieldValues(books.get(i), BookEntryBasicTest.BOOK_ENTRY_FIELD_NAMES,
                    expectedBookValues.get(i));
        }
    }
}
