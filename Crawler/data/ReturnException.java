2
https://raw.githubusercontent.com/nikhilbghodke/Simplex/master/src/com/github/nikhilbghodke/Interpreter/ReturnException.java
package com.github.nikhilbghodke.Interpreter;

public class ReturnException extends Exception{
    int num;
    public ReturnException(int num){
        super(num+"");
        this.num=num;
    }
}
