6
https://raw.githubusercontent.com/LJacksonPan/Y1-OOP-CW3/master/tests/StdStreamIntercept.java
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class StdStreamIntercept {

    private boolean stdCapStarted;
    private OutputStream tmpStdOut;
    private OutputStream tmpStdErr;
    private PrintStream originalStdOut;
    private PrintStream originalStdErr;

    public StdStreamIntercept() {
        stdCapStarted = false;
        tmpStdOut = null;
        tmpStdErr = null;
        originalStdOut = null;
        originalStdErr = null;
    }

    /**
     * Reroute standard output and standard error into temporary output streams.
     * 
     * @throws RuntimeException if capturing has already started.
     */
    public void stdCaptureStart() {
        if (stdCapStarted)
            throw new RuntimeException("Capture needs to be stopped before it can be started");

        stdCapStarted = true;
        tmpStdOut = new ByteArrayOutputStream();
        originalStdOut = System.out;
        System.setOut(new PrintStream(tmpStdOut));

        tmpStdErr = new ByteArrayOutputStream();
        originalStdErr = System.err;
        System.setErr(new PrintStream(tmpStdErr));
    }

    /**
     * Reset the standard output and standard error to their original streams.
     * 
     * @throws RuntimeException if capturing has not yet started.
     */
    public void stdCaptureStop() {
        if (!stdCapStarted)
            throw new RuntimeException("Capture needs to be started before it can be stopped");

        stdCapStarted = false;
        System.setOut(originalStdOut);
        originalStdOut = null;
        tmpStdOut = null;

        System.setErr(originalStdErr);
        originalStdErr = null;
        tmpStdErr = null;
    }

    /**
     * Return the result of the captured standard output.
     * 
     * @return the standard output since rerouting started.
     * @throws RuntimeException if capturing is not activated.
     */
    public String getCapturedStdOut() {
        if (tmpStdOut == null)
            throw new RuntimeException("Nothing captured.");

        return tmpStdOut.toString();
    }

    /**
     * Return the result of the captured standard error.
     * 
     * @return the standard error since rerouting started.
     * @throws RuntimeException if capturing is not activated.
     */
    public String getCapturedStdErr() {
        if (tmpStdErr == null)
            throw new RuntimeException("Nothing captured.");

        return tmpStdErr.toString();
    }

}
