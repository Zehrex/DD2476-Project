6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/AddCmd.java
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AddCmd extends LibraryCommand {

    // --------------------------------- Fields ---------------------------------

    /**
     * Allowed extension name, only .csv
     */
    public static final String FILE_NAME_EXTENSION = ".csv";

    /**
     * To save the argument for path
     */
    private Path filePath;


    // --------------------------------- Constructors ---------------------------------

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public AddCmd(String argumentInput) {
        super(CommandType.ADD, argumentInput);
    }


    // --------------------------------- Methods ---------------------------------

    /**
     * To execute the add operation
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, LegalityCheck.LIBRARY_DATA_NULL_MESSAGE);

        data.loadData(filePath);
    }

    /**
     * parse a path argument
     *
     * @param argumentInput argument (the string of path) input for this command
     * @return whether the path is valid
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        // Meaningless check, just for probable tests
        Objects.requireNonNull(argumentInput, LegalityCheck.INPUT_ARGUMENT_NULL_MESSAGE);

        try {
            // Check the file name extension
            if (argumentInput.endsWith(FILE_NAME_EXTENSION)) {
                filePath = Paths.get(argumentInput);
                return true;
            }
            else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
}
