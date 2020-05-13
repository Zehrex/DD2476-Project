6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/ExitCmd.java
/**
 * Exit command indicating termination of the program.
 */
public class ExitCmd extends LibraryCommand {

    /**
     * Create an exit command.
     * 
     * @param argumentInput argument input is expected to be blank
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null.
     */
    public ExitCmd(String argumentInput) {
        super(CommandType.EXIT, argumentInput);
    }

    /**
     * Execute the exit command. This method is not actually
     * required for the exit command and does nothing.
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        // nothing to do here
    }
    
}
