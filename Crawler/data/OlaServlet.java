2
https://raw.githubusercontent.com/marcoseduardoss/mini-mvc/master/mini-mvc/src/main/java/br/me/mvc/controle/OlaServlet.java
package br.me.mvc.controle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ola")
@SuppressWarnings("serial")
public class OlaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
    	res.setContentType("text/html");
        
        String message = "Projeto ServletPG - Versão 2";
        
        PrintWriter writer = res.getWriter();
        
        writer.println(
                "<html>" +
                    "<body>" +
                        "<h1>" + message + "</h1>" +
                    "</body>" +
                "</html>"
        );
    }

}
