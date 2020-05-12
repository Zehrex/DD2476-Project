1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/filter/filtroAutorizacao.java
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "FiltroAutorizacao", urlPatterns = { "*.xhtml" })
public class filtroAutorizacao implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
		
        String reqURI = req.getRequestURI();
        
        
        if(reqURI.endsWith("/login.xhtml") || reqURI.contains("javax.faces.resource") || (session != null && session.getAttribute("UsuarioLogado") != null)) {
        	
		chain.doFilter(request, response);
		
        }else {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
		}
	}

	@Override
	public void destroy() {

	}

}
