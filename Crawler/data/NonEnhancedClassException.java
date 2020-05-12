3
https://raw.githubusercontent.com/jroutine/jroutine/master/src/main/java/org/coral/jroutine/exception/NonEnhancedClassException.java
package org.coral.jroutine.exception;

/**
 * Thrown to indicate that a {@link Runnable} is not enhanced.
 * 
 * @author lihao
 * @date 2020-05-11
 */
public class NonEnhancedClassException extends IllegalArgumentException {

    private static final long serialVersionUID = 3084170063439264232L;

    public NonEnhancedClassException() {
        super();
    }

    public NonEnhancedClassException(String s) {
        super(s);
    }
}
