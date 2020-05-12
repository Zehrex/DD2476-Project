6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/BookEntry.java
import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable class encapsulating data for a single book entry.
 */
public class BookEntry {

    // --------------------------------- Fields of constants ---------------------------------

    /** the book's title **/
    private final String title;

    /** the authors' names **/
    private final String[] authors;

    /** the rating of the book **/
    private final float rating;

    /** the ISBN of the book **/
    private final String ISBN;

    /** the number of pages **/
    private final int pages;




    // --------------------------------- Constructor ---------------------------------

    /***
     * The constructor of class BookEntry
     * @param title the title of the book
     * @param authors the name of the authors of the book
     * @param rating the rating of the book
     * @param ISBN the ISBN of the book
     * @param pages the number of pages of the book
     * @throws NullPointerException if the args are null
     * @throws IllegalArgumentException if the rating and page is illegal
     */
    public BookEntry(String title, String[] authors, float rating, String ISBN, int pages) {

        /**
         * legality check
         * use methods, in situation of complex rules added later
         */
        LegalityCheck.titleLegalityCheck(title);
        LegalityCheck.authorsLegalityCheck(authors);
        LegalityCheck.ratingLegalityCheck(rating);
        LegalityCheck.ISBNLegalityCheck(ISBN);
        LegalityCheck.pagesLegalityCheck(pages);

        /**
         * to assign the values
         */
        this.title = title;
        this.authors = authors.clone();
        this.rating = rating;
        this.ISBN = ISBN;
        this.pages = pages;
    }


    // --------------------------------- Methods ---------------------------------

    /*
        The getters
     */

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public float getRating() {
        return rating;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getPages() {
        return pages;
    }

    /**
     * Overrides toString()
     * @return the format generated for BookEntry output
     */
    @Override
    public String toString() {

        String output = this.title;
        output += "\nby ";

        for (int i = 0; i < this.authors.length; i++) {
            output += authors[i];
            if (i != this.authors.length - 1) {
                output += ", ";
            }
        }

        output += "\nRating: " + String.format("%.2f", this.rating);
        output += "\nISBN: " + this.ISBN;
        output += "\n" + Integer.toString(this.pages) + " pages";

        return output;
    }

    /**
     * Overrides equals
     * @param obj an BookEntry object
     * @return return whether the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BookEntry)) {
            return false;
        }

        BookEntry book = (BookEntry) obj;

        // Value equality check
        if (this.title.equals(book.getTitle()) &&
            this.ISBN.equals(book.getISBN()) &&
            Arrays.deepEquals(this.authors, book.getAuthors()) &&
            this.rating == book.getRating() &&
            this.pages == book.getPages()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, rating, ISBN, pages);
        result = 31 * result + Arrays.hashCode(authors);
        return result;
    }
}
