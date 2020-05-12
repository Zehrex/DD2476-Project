2
https://raw.githubusercontent.com/Virjid/Kartingjson/master/src/main/java/me/virjid/karting/json/exception/JSONTypeException.java
package me.virjid.karting.json.exception;

/**
 * @author Virjid
 */
public class JSONTypeException extends RuntimeException {
    private static final long serialVersionUID = 1757499705291807423L;

    public JSONTypeException(String message) {
        super(message);
    }
}
