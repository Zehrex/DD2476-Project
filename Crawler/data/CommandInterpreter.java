6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/CommandInterpreter.java
import java.util.Objects;

/**
 * Class responsible for creating library commands
 * from given user input and executing them.
 */
public class CommandInterpreter {

    /** Delimiter between command keyword and corresponding argument. */
    private static final String COMMAND_ARGUMENT_DELIMITER = " ";

    /** Create a CommandInterpreter instance. */
    public CommandInterpreter() {
        // nothing to do
    }

    /**
     * Parse given user input and create a corresponding library command.
     * 
     * @param inputLine The input is expected to be a single line starting with a command
     * keyword followed by corresponding arguments.
     * @return Command as specified in the given input line. If command creation 
     * failed due to an illegal argument or command keyword, null will be returned.
     * @throws NullPointerException If the given input line is null.
     */
    public LibraryCommand parseCommand(String inputLine) {
        Objects.requireNonNull(inputLine, "Given input must not be null.");

        // split command keyword from arguments
        int firstSpaceIdx = inputLine.indexOf(COMMAND_ARGUMENT_DELIMITER);
        String commandInput, argumentInput;
        if (firstSpaceIdx == -1) {
            commandInput = inputLine.strip();
            argumentInput = "";
        } else {
            commandInput = inputLine.substring(0, firstSpaceIdx);
            argumentInput = inputLine.substring(firstSpaceIdx + 1);
        }

        return setupCommand(commandInput, argumentInput);
    }

    /**
     * Execute the given command.
     * 
     * @param command Command to be executed.
     * @param data book data to be considered for command execution.
     * @throws NullPointerException If one of the given parameters is null.
     */
    public void executeCommand(LibraryCommand command, LibraryData data) {
        Objects.requireNonNull(command, "Given command must not be null.");
        Objects.requireNonNull(data, "Given data must not be null.");

        command.execute(data);
    }

    /**
     * Create a command from given keyword and argument input.
     * @param commandInput command keyword
     * @param argumentInput command argument
     * @return Command as specified in the given input line. If command creation 
     * failed due to an illegal argument or command keyword, null will be returned.
     */
    private LibraryCommand setupCommand(String commandInput, String argumentInput) {

        CommandType cmdType = parseCommandType(commandInput);
        LibraryCommand command = null;
        if (cmdType != null) {
            command = CommandFactory.createCommand(cmdType, argumentInput);
        }

        return command;
    }
    
    /**
     * Translate given command keyword to corresponding CommandType.
     * @param cmd command keyword
     * @return CommandType associated with given keyword or null if no
     * association was found.
     */
    private CommandType parseCommandType(String cmd) {
        for (CommandType type : CommandType.values()) {
            if (type.name().equals(cmd)) {
                return type;
            }
        }
        return null;
    }

}
