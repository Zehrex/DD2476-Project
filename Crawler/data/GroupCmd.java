6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/GroupCmd.java
import java.util.List;
import java.util.Objects;

public class GroupCmd extends LibraryCommand {

    /**
     * Enum for grouping choices
     */
    public enum GroupingChoice {AUTHOR, TITLE}

    /**
     * Field for saved option
     */

    protected GroupingChoice groupBy;

    /**
     * Fields for option arg strings
     */

    public static final String GROUP_BY_AUTHOR = "AUTHOR";
    public static final String GROUP_BY_TITLE = "TITLE";

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public GroupCmd(String argumentInput) {
        super(CommandType.GROUP, argumentInput);
    }

    /**
     * Execute the group command.
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, LegalityCheck.LIBRARY_DATA_NULL_MESSAGE);
        List<BookEntry> books = data.getBookData();

        if (books.size() == 0) {
            System.out.println("The library has no book entries.");
            return;
        }

        switch (groupBy) {
            case AUTHOR:
                // Uses Utils.listAllAuthorsSorted inside
                printGroupByAuthors(books);
                break;
            case TITLE:
                printBooksByTitleAlpha(books);
                printBooksByTitleNum(books);
                break;
        }
    }

    /**
     * To parse args for grouping
     *
     * @param argumentInput argument input for this command
     * @return whether the parse is successful
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, LegalityCheck.LIBRARY_DATA_NULL_MESSAGE);

        switch (argumentInput) {
            case GROUP_BY_AUTHOR:
                this.groupBy = GroupingChoice.AUTHOR;
                return true;

            case GROUP_BY_TITLE:
                this.groupBy = GroupingChoice.TITLE;
                return true;

            default:
                return false;
        }
    }


    /**
     * To print books grouped by authors
     *
     * @param books the list of books
     */
    protected static void printGroupByAuthors(List<BookEntry> books) {
        System.out.println("Grouped data by AUTHOR");

        // First list all authors
        List<String> authors = Utils.listAllAuthorsSorted(books);

        // Then print books of each author
        for (String author : authors) {

            // For all authors
            System.out.println("## " + author);

            for (BookEntry book : books) {
                for (String aut : book.getAuthors()) {
                    // Find the authors' book and print
                    if (aut.equals(author)) {
                        System.out.println("\t" + book.getTitle());
                    }
                }
            }
        }
    }

    /**
     * To print books that titles started with number
     *
     * @param books the book library
     */
    protected static void printBooksByTitleNum(List<BookEntry> books) {
        boolean ifNumGroupExist = false;
        StringBuilder outputNum = new StringBuilder();

        for (BookEntry book : books) {
            // 48 is the ASCII of 0 and 57 is the ASCII of 9
            // Check whether books begin with numbers
            if (48 <= book.getTitle().charAt(0) && book.getTitle().charAt(0) <= 57) {
                ifNumGroupExist = true;
                outputNum.append("\t").append(book.getTitle()).append("\n");
            }
        }

        if (ifNumGroupExist) {
            System.out.println("## [0-9]");
            System.out.println("\t" + outputNum.toString().trim());
        }
    }

    /**
     * To print books by title alpha
     *
     * @param books
     */
    protected static void printBooksByTitleAlpha(List<BookEntry> books) {
        System.out.println("Grouped data by TITLE");
        // To iterate all alphabets
        for (int i = 0; i < 26; i++) {

            StringBuilder outputAlpha = new StringBuilder();
            boolean ifAlphaGroupExist = false;

            // Iterate books
            for (BookEntry book : books) {
                // If is the current alphabetical group, add to string
                if (book.getTitle().startsWith(Character.toString('A' + i))) {
                    outputAlpha.append("\t").append(book.getTitle()).append("\n");
                    ifAlphaGroupExist = true;
                }
            }
            if (ifAlphaGroupExist) {
                System.out.println("## " + Character.toString('A' + i));
                System.out.println("\t" + outputAlpha.toString().trim());
            }
        }
    }
}
