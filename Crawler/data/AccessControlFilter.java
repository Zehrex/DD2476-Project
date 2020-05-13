2
https://raw.githubusercontent.com/marcoseduardoss/mini-mvc/master/demos/001-crud-books-mvc-servlets-jstl-jpa/src/main/java/br/me/crudbooks/web/security/AccessControlFilter.java
package br.me.crudbooks.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe que controla o acesso de usuário não autorizados,
 * verificando, a cada solicitação de página, se existe
 * um usuário logado, ou seja, se existe um usuário na seção.
 * 
 * @author marcos.eduardo
 */
@WebFilter("/restrict/*")
public class AccessControlFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AccessControlFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

		  
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
        
	public void doFilter(ServletRequest request, ServletResponse response,

	FilterChain chain) throws IOException, ServletException {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            RequestDispatcher rd;

            HttpSession session = req.getSession();
            if (session == null || session.getAttribute("usuario") == null){
                
                //redirecionamento para pagina de aviso de sessão expirada
                String paginaDeAviso = "/WEB-INF/session.jsp";
                rd = request.getRequestDispatcher(paginaDeAviso);  
                rd.forward(request, response); 
                
            }
            else{
                
                chain.doFilter(req, res);
                
            }
	}

	public String getUrlBase(HttpServletRequest req) {
            String nomeAplic = req.getContextPath();
            String url = req.getRequestURL()+"";
            String urlSufixo = url.substring(url.indexOf(nomeAplic));
            return url.replace(urlSufixo, "")+nomeAplic;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
