6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/ListCmdTest.java
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public abstract class ListCmdTest extends CommandTest {

    protected static final String SHORT_ARGUMENT = "short";
    protected static final String LONG_ARGUMENT = "long";

    @Override
    protected CommandType getCmdType() {
        return CommandType.LIST;
    }

    @Before
    public void setup() {
        testCommand = new ListCmd(SHORT_ARGUMENT);

        testLibrary = new LibraryData();
        List<BookEntry> bookData = new ArrayList<>();
        bookData.add(new BookEntry("TitleA", new String[]{"AuthorA"}, 3.2f, "ISBNA", 500));
        bookData.add(new BookEntry("TitleB", new String[]{"AuthorB"}, 4.3f, "ISBNB", 400));
        bookData.add(new BookEntry("TitleC", new String[]{"AuthorC"}, 1.3f, "ISBNC", 300));
        FieldTestUtils.setPrivateField(testLibrary, testLibrary.getClass(), "books", bookData);
    }
}