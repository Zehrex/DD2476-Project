2
https://raw.githubusercontent.com/marcoseduardoss/mini-mvc/master/mini-mvc/src/main/java/br/me/mvc/controle/ServletControlador.java
package br.me.mvc.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.me.mvc.controle.acoes.Action;
import br.me.mvc.excecoes.ControllerMvcException;

@WebServlet(name = "ServletControlador", urlPatterns = { "/public/controllerServlet", "/restrict/controllerServlet" })
@SuppressWarnings("serial")
public class ServletControlador extends HttpServlet {

	private void controlModelAccess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// update encondig
		req.setCharacterEncoding("UTF-8");

		try {

			String className = req.getParameter("controle");

			String realPath = req.getServletContext().getRealPath("/WEB-INF/classes");

			Action command = Action.newInstance(className, realPath);

			// execute business logic
			String redirectedPage = command.executa(req, res);

			String url = "/" + redirectedPage;

			// update encoding of the response to utf-8
			res.setCharacterEncoding("UTF-8");

			res.setContentType("text/html;charset=UTF-8");

			// redireciona
			if (command.isRedirect())
				res.sendRedirect(res.encodeRedirectURL(url));
			else
				req.getRequestDispatcher(url).forward(req, res);

		} catch (Exception e) {

			String msg = "ServletContrador Error";

			msg += e.getMessage();

			throw new ControllerMvcException(msg, e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		controlModelAccess(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		controlModelAccess(request, response);

	}

}