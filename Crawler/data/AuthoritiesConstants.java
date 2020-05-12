2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/security/AuthoritiesConstants.java
package com.gardle.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
