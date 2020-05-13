2
https://raw.githubusercontent.com/marcoseduardoss/mini-mvc/master/mini-mvc/src/main/java/br/me/mvc/excecoes/ControllerMvcException.java
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.me.mvc.excecoes;

import javax.servlet.ServletException;

/**
 *
 * @author marcos.eduardo
 */
@SuppressWarnings("serial")
public class ControllerMvcException extends ServletException {

    public ControllerMvcException(String message) {
        super(message);
    }
    
    public ControllerMvcException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

   
}
