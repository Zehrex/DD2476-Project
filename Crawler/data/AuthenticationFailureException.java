9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/security-support/src/main/java/engineering/everest/lhotse/security/AuthenticationFailureException.java
package engineering.everest.lhotse.security;

public class AuthenticationFailureException extends RuntimeException {

    public AuthenticationFailureException(String message) {
        super(message);
    }

    public AuthenticationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
