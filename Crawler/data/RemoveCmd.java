6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/RemoveCmd.java
import java.util.List;
import java.util.Objects;

public class RemoveCmd extends LibraryCommand {

    /**
     * Enum for remove choice
     */
    public enum RemoveChoice {AUTHOR, TITLE}

    /**
     * The field for saved choice and string
     */
    protected RemoveChoice savedChoice;
    protected String savedString;

    /**
     * The field for choice name
     */
    public static final String REMOVE_BY_AUTHOR = "AUTHOR";
    public static final String REMOVE_BY_TITLE = "TITLE";

    /**
     * The field for arg regex
     */
    protected static final String REMOVE_ARG_REGEX = " ";

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public RemoveCmd(String argumentInput) {
        super(CommandType.REMOVE, argumentInput);
    }

    /**
     * Execute the remove command.
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, LegalityCheck.LIBRARY_DATA_NULL_MESSAGE);

        List<BookEntry> books = data.getBookData();
        List<BookEntry> booksToRemove;

        switch (this.savedChoice) {

            case AUTHOR:
                // Find books with the author name
                booksToRemove = Utils.getBooksWithAuthor(books, this.savedString);
                // remove the books
                if (booksToRemove.isEmpty()) {
                    System.out.println("0 books removed for author: " + this.savedString);
                    return;
                }
                Utils.removeBooks(books, booksToRemove);
                System.out.println(booksToRemove.size() + " books removed for author: " + this.savedString);
                break;

            case TITLE:
                booksToRemove = Utils.getBooksWithTitle(books, savedString);

                if (booksToRemove.isEmpty()) {
                    System.out.println(this.savedString + ": not found.");
                    return;
                }
                Utils.removeBooks(books, booksToRemove);
                System.out.println(this.savedString + ": removed successfully.");
                break;
        }
    }

    /**
     * To parse the args for remove
     *
     * @param argumentInput argument input for this command
     * @return whether the parse is successful
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, LegalityCheck.INPUT_ARGUMENT_NULL_MESSAGE);

        // split the input arg by space
        String[] strs = argumentInput.split(REMOVE_ARG_REGEX);

        // must be more than two entries (option, string)
        if (strs.length < 2) {
            return false;
        }

        String option = strs[0];
        StringBuilder str = new StringBuilder(new String());

        // recombine the string
        for (int i = 1; i < strs.length; i++) {
            str.append(strs[i]).append(REMOVE_ARG_REGEX);
        }
        this.savedString = str.toString().trim();

        switch (option) {
            case REMOVE_BY_AUTHOR:
                this.savedChoice = RemoveChoice.AUTHOR;
                return true;

            case REMOVE_BY_TITLE:
                this.savedChoice = RemoveChoice.TITLE;
                return true;

            default:
                return false;
        }
    }
}
