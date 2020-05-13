6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/HelpCmd.java
/**
 * Help command used to print usage information of available commands.
 */
public class HelpCmd extends LibraryCommand {

    /** Generated help output with information on command usage. */
    private String helpOutput;

    /**
     * Create a help command.
     * 
     * @param argumentInput argument input is expected to be blank
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null.
     */
    public HelpCmd(String argumentInput) {
        super(CommandType.HELP, argumentInput);
        
        helpOutput = buildHelpOutput();
    }

    /**
     * Execute the help command. This prints the available commands
     * and corresponding usage to the console.
     *
     * @param data book data to be considered for command execution.
     */    
    @Override
    public void execute(LibraryData data) {       
        System.out.println(helpOutput);
    }

    private String buildHelpOutput() {
        final String padding = "\n  ";
        StringBuilder bld = new StringBuilder("The following commands are available:");

        bld.append(padding).append(CommandType.HELP);
        bld.append(padding).append(CommandType.EXIT);
        bld.append(padding).append(CommandType.ADD).append(" path/to/book/data.csv");
        bld.append(padding).append(CommandType.LIST).append(" [short|long]");
        bld.append(padding).append(CommandType.SEARCH).append(" <value>");
        bld.append(padding).append(CommandType.REMOVE).append(" TITLE|AUTHOR <value>");
        bld.append(padding).append(CommandType.GROUP).append(" TITLE|AUTHOR");

        return bld.toString();
    }
    
}
