6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/LibraryFileLoaderTest.java
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class LibraryFileLoaderTest {

    protected static final String DATA_HEADER = "title,authors,average_rating,isbn,# num_pages";
    protected static final String DATA_SAMPLE1 = "The Changeling,Zilpha Keatley Snyder,4.17,595321801,228";
    protected static final String DATA_SAMPLE2 = "Chester,Syd Hoff,3.75,64440958,64";

    protected static final Object[] BOOK_SAMPLE1_VALUES = { "The Changeling", new String[] { "Zilpha Keatley Snyder" },
            4.17f, "595321801", 228 };
    protected static final Object[] BOOK_SAMPLE2_VALUES = { "Chester", new String[] { "Syd Hoff" }, 3.75f, "64440958",
            64 };

    protected static final String FILE_CONTENT_FIELD_NAME = "fileContent";

    protected LibraryFileLoader testFileLoader;
    protected List<String> testBookData;

    public LibraryFileLoaderTest() {
        testFileLoader = null;
        testBookData = null;
    }

    @Before
    public void setup() {
        testFileLoader = new LibraryFileLoader();
        testBookData = new ArrayList<>();
        testBookData.add(DATA_HEADER);
        testBookData.add(DATA_SAMPLE1);
        testBookData.add(DATA_SAMPLE2);

        setBookData(testBookData);
    }

    // -------------------------- test helper -------------------------------------

    protected void setBookData(List<String> data) {
        // circumvent file loading by directly setting
        // the corresponding field using reflection
        FieldTestUtils.setPrivateField(testFileLoader, testFileLoader.getClass(), FILE_CONTENT_FIELD_NAME, data);
    }

    protected void checkBookCollectionResult(List<String> bookData, List<Object[]> expectedValues) {
        List<BookEntry> books = testFileLoader.parseFileContent();
        assertNotNull("Resulting book collection is not expected to be null.", books);
        assertEquals("Resulting book data has unexpected size.", expectedValues.size(), books.size());

        for (int i = 0; i < books.size(); i++) {
            BookEntryTestUtils.checkBookFieldValues(books.get(i), BookEntryTest.BOOK_ENTRY_FIELD_NAMES,
                    expectedValues.get(i));
        }
    }
}