13
https://raw.githubusercontent.com/RikkaApps/SaveCopy/master/app/src/main/java/app/rikka/savecopy/SaveException.java
package app.rikka.savecopy;

public class SaveException extends Exception {

    public SaveException(String message) {
        super(message);
    }

    public SaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
