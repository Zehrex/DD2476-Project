6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/LibraryCommand.java
import java.util.Objects;

/**
 * Super class for all commands which ties
 * command creation and initialisation using arguments together.
 */
public abstract class LibraryCommand {

	/** This commands specific type. */
	private CommandType type;

	/**
	 * Create the specified command and initialise it with 
	 * the given command argument.
	 * 
	 * @param type specific command type
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
	 * @throws NullPointerException if any of the given parameters are null.
	 */
	public LibraryCommand(CommandType type, String argumentInput) {
		Objects.requireNonNull(type, "Given type must not be null.");
		Objects.requireNonNull(argumentInput, "Given argument input must not be null.");
		this.type = type;

		if (!parseArguments(argumentInput)) {
		    throw new IllegalArgumentException("Invalid argument for " + type + " command: " + argumentInput);
		}
	}

	/**
	 * The specific type of this command.
	 * @return specific type of this command
	 */
	public CommandType getType() {
		return type;
	}

	/**
	 * Execute the specific command.
	 * 
	 * Subclasses must override this method to specify corresponding behaviour.
	 * 
	 * @param data book data to be considered for command execution.
	 */
	public abstract void execute(LibraryData data);

	/**
	 * Parses the given command arguments and initialised necessary
	 * parameters. In this default version, a blank argument is expected.
	 * 
	 * Subclasses should override this method for more specific argument
	 * parsing.
	 * 
	 * @param argumentInput argument input for this command
	 * @return true if the given argument is blank, false otherwise
     * @throws NullPointerException if the given argumentInput is null.
	 */
	protected boolean parseArguments(String argumentInput) {
		Objects.requireNonNull(argumentInput, "Given input argument must not be null.");

		return argumentInput.isBlank();
	}

}
