2
https://raw.githubusercontent.com/marcoseduardoss/mini-mvc/master/demos/001-crud-books-mvc-servlets-jstl-jpa/src/main/java/br/me/crudbooks/web/control/actions/UsuarioAction.java
package br.me.crudbooks.web.control.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.me.crudbooks.model.domain.entity.Usuario;
import br.me.crudbooks.model.domain.exception.LibrarySystemException;
import br.me.crudbooks.model.repository.UserRepository;
import br.me.mvc.controle.acoes.Action;
//import br.me.agenda.modelo.persistencia.lista.UsuarioDao;

/**
 * Classe de acesso a logica de negocio
 * 
 * @author marcos.eduardo
 *
 */
@SuppressWarnings("serial")
public class UsuarioAction extends Action {

	private final UserRepository userRepository = new UserRepository();

	public String exibirPaginaPrincipal(HttpServletRequest request, HttpServletResponse response) {
		return "restrict/home.jsp";
	}

	public String prepararAlteracao(HttpServletRequest request, HttpServletResponse response) {

		try {

			String idStr = request.getParameter("id");

			Long idLong = new Long((idStr == null ? "-1" : idStr));

			Usuario u = (Usuario) userRepository.findById(idLong);

			request.setAttribute("usuario", u);

			return "restrict/user/edit.jsp";

		} catch (Exception e) {
			request.setAttribute("msgexception", e.getMessage());
			throw new LibrarySystemException(e);
		}
	}

	public String prepararCadastro(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("usuario", new Usuario());

		return "restrict/user/edit.jsp";
	}

	public String cadastrar(HttpServletRequest request, HttpServletResponse response) {
		try {

			Usuario user = new Usuario();

			user.setNome(request.getParameter("nome"));
			user.setLogin(request.getParameter("login"));
			user.setSenha(request.getParameter("senha"));
			user.setTelefone(request.getParameter("telefone"));
			user.setEmail(request.getParameter("email"));

			userRepository.save(user);

			request.setAttribute("usuarios", userRepository.findAll());

			request.setAttribute("msgSucesso", "Usuario cadastrado com sucesso");

			return "restrict/user/list.jsp";

		} catch (Exception e) {
			request.setAttribute("msgexception", e.getMessage());
			throw new LibrarySystemException(e);
		}
	}

	public String register(HttpServletRequest request, HttpServletResponse response) {

		try {
			Usuario usuario = new Usuario();

			usuario.setNome(request.getParameter("nome"));
			usuario.setLogin(request.getParameter("login"));
			usuario.setSenha(request.getParameter("senha"));
			usuario.setTelefone(request.getParameter("telefone"));
			usuario.setEmail(request.getParameter("email"));

			userRepository.save(usuario);

			request.setAttribute("usuarios", userRepository.findAll());

			request.setAttribute("msgSucesso", "Usuario cadastrado com sucesso");

			return new LoginAction().entrar(request, response);

		} catch (Exception e) {
			request.setAttribute("msgexception", e.getMessage());
			throw new LibrarySystemException(e);
		}
	}

	public String prepareRegister(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("usuario", new Usuario());

		return "public/user/register.jsp";
	}

	public String alterar(HttpServletRequest request, HttpServletResponse response) {

		try {

			String idStr = request.getParameter("id");

			Usuario usuario = new Usuario();

			usuario.setId(new Long(idStr == null ? "-1" : idStr));
			usuario.setNome(request.getParameter("nome"));
			usuario.setLogin(request.getParameter("login"));
			usuario.setSenha(request.getParameter("senha"));
			usuario.setTelefone(request.getParameter("telefone"));
			usuario.setEmail(request.getParameter("email"));

			userRepository.update(usuario);

			request.setAttribute("msgSucesso", "Usuario alterado com sucesso");

			request.setAttribute("usuarios", userRepository.findAll());

		} catch (Exception e) {

			request.setAttribute("msgexception", e.getMessage());
			throw new LibrarySystemException(e);

		}
		return "restrict/user/list.jsp";
	}

	public String listar(HttpServletRequest request, HttpServletResponse response) {

		try {

			request.setAttribute("usuarios", userRepository.findAll());

		} catch (Exception e) {

			request.setAttribute("msgexception", e.getMessage());
			throw new LibrarySystemException(e);

		}

		return "restrict/user/list.jsp";
	}

	public String listarSemLogar(HttpServletRequest request, HttpServletResponse response) {

		try {

			request.setAttribute("usuarios", userRepository.findAll());

		} catch (Exception e) {
			request.setAttribute("msgexception", e.getMessage());
			throw new LibrarySystemException(e);
		}
		return "publico/lista_usuario_publico.jsp";
	}

	public String remover(HttpServletRequest request, HttpServletResponse response) {
		try {

			String idStr = request.getParameter("id");

			Usuario usuario = new Usuario();

			usuario.setId(new Long(idStr == null ? "-1" : idStr));

			userRepository.remove(usuario);

			request.setAttribute("usuarios", userRepository.findAll());

			request.setAttribute("msgSucesso", "Usuario removido com sucesso");

		} catch (Exception e) {
			request.setAttribute("msgexception", e.getMessage());
			throw new LibrarySystemException(e);
		}
		return "restrict/user/list.jsp";
	}
}