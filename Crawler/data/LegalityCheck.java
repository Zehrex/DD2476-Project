6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/LegalityCheck.java
import java.util.Objects;

public class LegalityCheck {

    /**
     * This class is for legality check methods and constants
     */

    // --------------------------------- Fields of constants ---------------------------------

    // Max rating and minimum rating
    public static final float MAX_RATING = 5;
    public static final float MIN_RATING = 0;


    // Null book info pointer message
    public static final String NULL_BOOK_INFO_POINTER_MESSAGE = "The book information should not have null pointers.";

    // Illegal page number and rating
    public static final String ILLEGAL_PAGE_RATING_MESSAGE = "The rating should be between 0 and 5 and the page number should be positive.";

    // Illegal path
    public static final String ILLEGAL_PATH = "The path is not legal. Make sure the path is legal and the file name ends with .csv.";

    // Blank string message
    public static final String BLANK_STRING = "The book information should not contain blank strings";
    public static final String LIBRARY_DATA_NULL_MESSAGE = "Given LibraryData argument must not be null.";
    public static final String INPUT_ARGUMENT_NULL_MESSAGE = "Given input argument must not be null.";


    // --------------------------------- Methods ---------------------------------

    /**
     * the method to check authors' legality
     * this check is specific for authors string list, as there should not be empty entries in it
     *
     * @param authors the array of authors
     * @throws NullPointerException if object is null
     * @throws IllegalArgumentException if the argument is illegal
     */
    public static void authorsLegalityCheck(String[] authors) {
        Objects.requireNonNull(authors, INPUT_ARGUMENT_NULL_MESSAGE);
        for (String author : authors) {
            Objects.requireNonNull(author, INPUT_ARGUMENT_NULL_MESSAGE);
            if (author.isBlank()) {
                throw new IllegalArgumentException(BLANK_STRING);
            }
        }
    }

    /**
     * the method to check rating's legality
     *
     * @param rating the rating
     * @throws IllegalArgumentException if the argument is illegal
     */
    public static void ratingLegalityCheck(float rating) {
        if (rating > MAX_RATING || rating < MIN_RATING) {
            throw new IllegalArgumentException(ILLEGAL_PAGE_RATING_MESSAGE);
        }
    }

    /**
     * the method to check pages' legality
     *
     * @param pages the pages
     * @throws IllegalArgumentException if the argument is illegal
     */
    public static void pagesLegalityCheck(int pages) {
        if (pages < 0) {
            throw new IllegalArgumentException(ILLEGAL_PAGE_RATING_MESSAGE);
        }
    }

    /**
     * to check the legality of title
     *
     * @param title the title of book
     * @throws NullPointerException if object is null
     * @throws IllegalArgumentException if the argument is illegal
     */
    public static void titleLegalityCheck(String title) {
        Objects.requireNonNull(title, INPUT_ARGUMENT_NULL_MESSAGE);
        if (title.isBlank()) {
            throw new IllegalArgumentException(BLANK_STRING);
        }
    }

    /**
     * to check the legality of ISBN
     *
     * @param ISBN the ISBN of the book
     * @throws NullPointerException if object is null
     * @throws IllegalArgumentException if the argument is illegal
     */
    public static void ISBNLegalityCheck(String ISBN) {
        Objects.requireNonNull(ISBN, INPUT_ARGUMENT_NULL_MESSAGE);
        if (ISBN.isBlank()) {
            throw new IllegalArgumentException(BLANK_STRING);
        }
    }
}
