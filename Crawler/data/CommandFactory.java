6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/CommandFactory.java
import java.util.Objects;

/**
 * Static factory for creating library commands.
 */
public final class CommandFactory { // the final keyword prevents subclassing of this utility class

    /** Not to be used. */
    private CommandFactory() {
        // Utility classes do not need constructors and this is a way of preventing them 
        // from being instantiated accidentally.
        throw new UnsupportedOperationException("This constructor should never be used.");
    }

    /**
     * Create library command for the given type and argument.
     * 
     * @param cmdType Type of the command to be created
     * @param argumentInput command argument to be used during initialisation of the command
     * @return Command of the given type initialised for the given argument. If command creation 
     * failed due to an illegal argument, null will be returned.
     * @throws NullPointerException If one of the given parameters is null.
     */
    public static LibraryCommand createCommand(CommandType cmdType, String argumentInput) {
        Objects.requireNonNull(cmdType, "Given command type must not be null.");
        Objects.requireNonNull(argumentInput, "Given argument input must not be null.");

        try {
            switch(cmdType) {
                /* TODO Implement individual commands and comment
                 * corresponding line back in once you have a class in place
                 * Lastly, get rid of the unsupported Operation exception.
                 */

                case HELP: return new HelpCmd(argumentInput);
                case EXIT: return new ExitCmd(argumentInput);
                case ADD: return new AddCmd(argumentInput);
                case LIST: return new ListCmd(argumentInput);
                case SEARCH: return new SearchCmd(argumentInput);
                case REMOVE: return new RemoveCmd(argumentInput);
                case GROUP: return new GroupCmd(argumentInput);
                default:
                    throw new IllegalArgumentException("Command type not supported: " + cmdType);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        return null;
    }
}
