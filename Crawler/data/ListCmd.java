6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/ListCmd.java
import java.util.List;
import java.util.Objects;

public class ListCmd extends LibraryCommand {

    /**
     * The enum type for options, short or long
     */
    public enum ListCommandOptions {SHORT, LONG}

    /**
     * The field for saved argument, with default short
     */
    protected ListCommandOptions savedOption;

    /**
     * The fields for user option strings
     */
    protected static final String LIST_COMMAND_OPTION_EMPTY = "";
    protected static final String LIST_COMMAND_OPTION_SHORT = "short";
    protected static final String LIST_COMMAND_OPTION_LONG = "long";

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public ListCmd(String argumentInput) {
        super(CommandType.LIST, argumentInput);
    }

    /**
     * To execute the list command
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, LegalityCheck.LIBRARY_DATA_NULL_MESSAGE);

        List<BookEntry> bookData = data.getBookData();
        if (bookData.isEmpty()) {
            System.out.println("The library has no book entries.");
            return;
        }
        System.out.println(bookData.size() + " books in library:");

        for (BookEntry book : bookData) {
            if (this.savedOption == ListCommandOptions.SHORT) {
                System.out.println(book.getTitle());
            }
            if (this.savedOption == ListCommandOptions.LONG) {
                // Print title
                System.out.println(book.toString());
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * To parse the argument for list command
     *
     * @param argumentInput argument input for this command
     * @return whether the parse is successful
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, LegalityCheck.INPUT_ARGUMENT_NULL_MESSAGE);

        switch (argumentInput) {

            case LIST_COMMAND_OPTION_EMPTY:
            case LIST_COMMAND_OPTION_SHORT:
                this.savedOption = ListCommandOptions.SHORT;
                return true;

            case LIST_COMMAND_OPTION_LONG:
                this.savedOption = ListCommandOptions.LONG;
                return true;

            default:
                return false;
        }
    }
}
