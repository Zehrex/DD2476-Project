6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/SearchCmdTest.java
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchCmdTest extends CommandTest {

    protected static final String SINGLE_WORD_TITLE = "Harry";
    protected static final String MULTI_WORD_TITLE_A = "The Castle in the Sky";
    protected static final String MULTI_WORD_TITLE_B = "Edinburgh Castle";

    protected static final String MULTI_WORD_SEARCH_TERM_SINGLE_HIT = "Sky";

    protected static final String NO_HITS_FOUND_MESSAGE = "No hits found for search term: ";

    @Override
    protected CommandType getCmdType() {
        return CommandType.SEARCH;
    }

    @Before
    public void setup() {
        testCommand = new SearchCmd(SINGLE_WORD_TITLE);

        testLibrary = new LibraryData();
        List<BookEntry> bookData = new ArrayList<>();
        bookData.add(new BookEntry(MULTI_WORD_TITLE_A, new String[]{"AuthorA"}, 3.2f, "ISBNA", 500));
        bookData.add(new BookEntry(SINGLE_WORD_TITLE, new String[]{"AuthorB"}, 4.3f, "ISBNB", 400));
        bookData.add(new BookEntry(MULTI_WORD_TITLE_B, new String[]{"AuthorC"}, 1.3f, "ISBNC", 300));
        FieldTestUtils.setPrivateField(testLibrary, testLibrary.getClass(), "books", bookData);
    }
}