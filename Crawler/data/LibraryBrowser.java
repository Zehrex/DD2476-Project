6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/src/LibraryBrowser.java
import java.util.Scanner;

/**
 * The main library browser module which 
 * runs the main loop and handles user input
 * and high level command creation and execution.
 */
public class LibraryBrowser {

    /** Book data currently loaded. */
    private final LibraryData data;
    /** Create and handle commands created via user input. */
    private final CommandInterpreter cmdIntrp;
    /** 
     * Used to read user input from StdIO. 
     * Only close shortly before program exit.
     */
    private final Scanner stdInScan;

    /**
     * Create library browser.
     */
    public LibraryBrowser() {
        data = new LibraryData();
        cmdIntrp = new CommandInterpreter();
        stdInScan = new Scanner(System.in);
    }

    /** Run the library browser and start the corresponding main loop. */
    public void run() {
        mainLoop();
        stdInScan.close();
    }

    /**
     * Main loop of the library browser programme.
     * 
     * It asks for user input via a command prompt, creates a 
     * corresponding command and executes it.
     */
    private void mainLoop() {
        boolean exit = false; 

        System.out.println("\nEnter a library command or type " + CommandType.HELP + " for command overview.");
        
        while(!exit) {
            String inputLine = promptUser();
            if (inputLine == null) {
                System.err.println("ERROR: User input could not be read successfully.");
                continue;
            }
            
            LibraryCommand command = cmdIntrp.parseCommand(inputLine);
            if (command == null) {
                System.err.println("ERROR: Given command input is invalid: " + inputLine);
            } else {
                if (command instanceof ExitCmd) {
                    exit = true;
                } else {
                    cmdIntrp.executeCommand(command, data);
                }
            }           
        }
    }

    /**
     * Display user command prompt and 
     * read corresponding input from StdIn.
     * @return provided user input or null if stream problem
     */
    private String promptUser() {
        System.out.print("> ");
        if (stdInScan.hasNextLine()) {
            return stdInScan.nextLine();
        } else {
            return null;
        }
    }
}
