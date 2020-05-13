6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/SearchCmd.java
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchCmd extends LibraryCommand {

    /**
     * Field for regex of all alphabets
     */
    public static final String ALL_ALPHA_REGEX = "^[a-zA-Z-]*$";

    /**
     * Field for saving the arg
     */
    protected String searchWord;

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public SearchCmd(String argumentInput) {
        super(CommandType.SEARCH, argumentInput);
    }

    /**
     * To execute the search command
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, LegalityCheck.LIBRARY_DATA_NULL_MESSAGE);

        List<BookEntry> books = data.getBookData();
        List<BookEntry> booksFound = Utils.searchBooksByTitle(books, searchWord);

        if (booksFound.size() == 0) {
            System.out.println("No hits found for search term: " + this.searchWord);
        }
        else {
            for (BookEntry book : booksFound) {
                System.out.println(book.getTitle());
            }
        }
    }

    /**
     * To parse the search name
     *
     * @param argumentInput argument input for this command
     * @return whether the parse is successful
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, LegalityCheck.INPUT_ARGUMENT_NULL_MESSAGE);

        if (argumentInput.matches(ALL_ALPHA_REGEX) && !argumentInput.isBlank()) {
            this.searchWord = argumentInput;
            return true;
        }
        else {
            return false;
        }
    }
}
