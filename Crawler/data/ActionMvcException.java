2
https://raw.githubusercontent.com/marcoseduardoss/mini-mvc/master/mini-mvc/src/main/java/br/me/mvc/excecoes/ActionMvcException.java
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
public class ActionMvcException extends ServletException {

    public ActionMvcException(String message) {
        super(message);
    }
    
    public ActionMvcException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

   
}
