6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/LibraryFileLoaderBasicTest.java
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryFileLoaderBasicTest extends LibraryFileLoaderTest {

    // ------------------------- check parseFileContent --------------------

    @Test
    public void testParseFileContentReturnTypeIsArrayList() {
        List<BookEntry> bookData = testFileLoader.parseFileContent();
        assertNotNull("Resulting book collection is not expected to be null.", bookData);
        assertTrue("Returned type for book collection is expected to be an ArrayList.", bookData instanceof ArrayList);
    }

    @Test
    public void testParseFileContentResult() {
        List<Object[]> expectedValues = new ArrayList<>();
        expectedValues.add(BOOK_SAMPLE1_VALUES);
        expectedValues.add(BOOK_SAMPLE2_VALUES);

        checkBookCollectionResult(testBookData, expectedValues);
    }

    @Test
    public void testParseFileContentNoContentLoadedConsoleOutput() {
        testFileLoader = new LibraryFileLoader();
        String expected = "ERROR: No content loaded before parsing.";

        StdStreamIntercept intercept = new StdStreamIntercept();

        intercept.stdCaptureStart();
        try {
            try {
                testFileLoader.parseFileContent();
            } catch (Exception e) {
                System.err.println("ERROR: executing parseFileContent for console output check:" + e);
            }
            // ignore leading and trailing white spaces
            assertEquals("Console output not as expected in parseFileContent.", expected.replaceAll("\r", "").trim(),
                    intercept.getCapturedStdErr().replaceAll("\r", "").trim());
        } finally {
            intercept.stdCaptureStop();
        }
    }
}
