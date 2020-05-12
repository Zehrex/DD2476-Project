15
https://raw.githubusercontent.com/CodinGame/SpringChallenge2020/master/src/main/java/com/codingame/game/InvalidInputException.java
package com.codingame.game;

@SuppressWarnings("serial")
public class InvalidInputException extends Exception {

    public InvalidInputException(String expected, String got) {
        super("Invalid Input: Expected " + expected + " but got '" + got + "'");
    }
    
    public InvalidInputException(String error, String expected, String got) {
        super(error + ": Expected " + expected + " but got '" + got + "'");
    }

}
