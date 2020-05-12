6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/LibraryFileLoader.java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** 
 * Class responsible for loading
 * book data from file.
 */
public class LibraryFileLoader {

    /**
     * Contains all lines read from a book data file using
     * the loadFileContent method.
     * 
     * This field can be null if loadFileContent was not called
     * for a valid Path yet.
     * 
     * NOTE: Individual line entries do not include line breaks at the 
     * end of each line.
     */
    private List<String> fileContent;

    /**
     * The regex for line format of content
     */
    protected static final String BOOK_DATA_FILE_LINE_REGEX = ",";
    protected static final String BOOK_DATA_FILE_AUTHOR_REGEX = "-";

    /** Create a new loader. No file content has been loaded yet. */
    public LibraryFileLoader() { 
        fileContent = null;
    }

    /**
     * Load all lines from the specified book data file and
     * save them for later parsing with the parseFileContent method.
     * 
     * This method has to be called before the parseFileContent method
     * can be executed successfully.
     * 
     * @param fileName file path with book data
     * @return true if book data could be loaded successfully, false otherwise
     * @throws NullPointerException if the given file name is null
     */
    public boolean loadFileContent(Path fileName) {
        Objects.requireNonNull(fileName, "Given filename must not be null.");
        boolean success = false;

        try {
            fileContent = Files.readAllLines(fileName);
            success = true;
        } catch (IOException | SecurityException e) {
            System.err.println("ERROR: Reading file content failed: " + e);
        }

        return success;
    }

    /**
     * Has file content been loaded already?
     * @return true if file content has been loaded already.
     */
    public boolean contentLoaded() {
        return fileContent != null;
    }

    /**
     * Parse file content loaded previously with the loadFileContent method.
     * 
     * @return books parsed from the previously loaded book data or an empty list
     * if no book data has been loaded yet.
     */
    public List<BookEntry> parseFileContent() {
        if (this.fileContent == null) {
            System.err.println("ERROR: No content loaded before parsing.");
            return new ArrayList<BookEntry>();
        }

        // remove the header
        this.fileContent.remove(0);
        List<BookEntry> books = new ArrayList<BookEntry>();

        // Iterate the strings
        for (String content : this.fileContent) {
            // split by comma
            String[] strItems = content.split(BOOK_DATA_FILE_LINE_REGEX);

            // create a new book entry using the content line
            BookEntry book = new BookEntry(
                    strItems[0],
                    strItems[1].split(BOOK_DATA_FILE_AUTHOR_REGEX),
                    Float.parseFloat(strItems[2]),
                    strItems[3],
                    Integer.parseInt(strItems[4])
            );
            books.add(book);
        }

        return books;
    }
}
