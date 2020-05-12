6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/LibraryData.java
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** 
 * Class responsible for handling currently loaded
 * book data and loading additional data from file.
 */
public class LibraryData {

    /** Currently loaded book data. */
    private final List<BookEntry> books;

    /** Create a new and empty book library. */
    public LibraryData() {
        books = new ArrayList<>();
    }

    /** 
     * Get all available book entries.
     * @return available book entries
     */
    public List<BookEntry> getBookData() {
        return books;
    }

    /**
     * Initiate book data loading for the given path.
     * @param libraryFile specified path to book data file
     * @return true if loading was successful, false otherwise
     * @throws NullPointerException if the given path is null
     */
    public boolean loadData(Path libraryFile) {
        Objects.requireNonNull(libraryFile, "Given file path must not be null.");
               
        LibraryFileLoader loader = new LibraryFileLoader();
        boolean success = loader.loadFileContent(libraryFile);

        if (success) {
            List<BookEntry> loaded = loader.parseFileContent();
            int added = mergeEntries(loaded);
            System.out.println(added + " new book entries added.");
        } else {
            System.err.println("ERROR: Loading book data failed for file: " + libraryFile);
        }

        return success;
    }

    /**
     * Merge the list of book entries with the
     * entries already loaded.
     * 
     * Duplicate entries will be discarded (This requires .equals to be
     * implemented for BookEntry).
     * 
     * @param loaded list of book entries to be merged with data already loaded
     * @return number of book entries added to the library
     */
    private int mergeEntries(List<BookEntry> loaded) {
        int count = 0;

        for (BookEntry entry : loaded) {
            if (!books.contains(entry)) {
                books.add(entry);
                count++;
            } else {
                System.out.println("Duplicate entry found for book: " + entry);
            }
        }
        return count;
    }
}
