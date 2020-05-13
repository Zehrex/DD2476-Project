2
https://raw.githubusercontent.com/marcoseduardoss/mini-mvc/master/demos/001-crud-books-mvc-servlets-jstl-jpa/src/main/java/br/me/crudbooks/web/control/actions/BookAction.java
package br.me.crudbooks.web.control.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.me.crudbooks.model.domain.entity.Book;
import br.me.crudbooks.model.domain.exception.LibrarySystemException;
import br.me.crudbooks.model.repository.BookRepository;
import br.me.mvc.controle.acoes.Action;
//import br.me.agenda.modelo.persistencia.lista.BookDao;

/**
 * Classe de acesso a logica de negocio
 * 
 * @author marcos.eduardo
 *
 */
@SuppressWarnings("serial")
public class BookAction extends Action {
	
	private BookRepository bookDAO = new BookRepository();

	public String exibirPaginaPrincipal(HttpServletRequest request, HttpServletResponse response) {
		return "restrict/home.jsp";
	}

	public String prepararAlteracao(HttpServletRequest request, HttpServletResponse response) {

		try {

			String idStr = request.getParameter("id");

			Long idLong = new Long((idStr == null ? "-1" : idStr));

			Book u = (Book) bookDAO.findById(idLong);

			request.setAttribute("book", u);

			return "restrict/book/edit.jsp";

		} catch (Exception e) {
			request.setAttribute("msgexception", isNull(e) ? "Erro com valor nulo" : e.getMessage() ) ;
			throw new LibrarySystemException(e);
		}
	}

	public String prepararCadastro(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("book", new Book());

		return "restrict/book/edit.jsp";
	}

	public String cadastrar(HttpServletRequest request, HttpServletResponse response) {
		try {

			Book book = new Book();

	        book.setAuthor(request.getParameter("author"));
	        
	        book.setTitle(request.getParameter("title"));
	        
	        book.setSummary(request.getParameter("summary"));
	        
	        String ano = request.getParameter("ano");
	        book.setAno(ano != null ? Integer.parseInt(ano) : null );

			bookDAO.save(book);

			request.setAttribute("books", bookDAO.findAll());

			request.setAttribute("msgSucesso", "Book cadastrado com sucesso");

			return "restrict/book/list.jsp";

		} catch (Exception e) {
			request.setAttribute("msgexception", isNull(e) ? "Erro com valor nulo" : e.getMessage() ) ;
			throw new LibrarySystemException(e);
		}
	}



	public String cadastrarse(HttpServletRequest request, HttpServletResponse response) {

		try {
			Book book = new Book();

	        book.setAuthor(request.getParameter("author"));
	        
	        book.setTitle(request.getParameter("title"));
	        
	        book.setSummary(request.getParameter("summary"));
	        
	        String ano = request.getParameter("ano");
	        book.setAno(ano != null ? Integer.parseInt(ano) : null );


			bookDAO.save(book);

			request.setAttribute("books", bookDAO.findAll());

			request.setAttribute("msgSucesso", "Book cadastrado com sucesso");

			return new LoginAction().entrar(request, response);

		} catch (Exception e) {
			request.setAttribute("msgexception", isNull(e) ? "Erro com valor nulo" : e.getMessage() ) ;
			throw new LibrarySystemException(e);
		}
	}

	public String prepararCadastrarse(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("book", new Book());

		return "restrict/book/edit.jsp";
	}

	public String alterar(HttpServletRequest request, HttpServletResponse response) {

		try {

			String idStr = request.getParameter("id");

			Book book = new Book();

	        book.setId(new Long(idStr == null ? "-1" : idStr));
	        
	        book.setAuthor(request.getParameter("author"));
	        
	        book.setTitle(request.getParameter("title"));
	        
	        book.setSummary(request.getParameter("summary"));

	        String ano = request.getParameter("ano");
	        book.setAno(ano != null ? Integer.parseInt(ano) : null );

	        bookDAO.update(book);

			request.setAttribute("msgSucesso", "Book alterado com sucesso");

			request.setAttribute("books", bookDAO.findAll());

		} catch (Exception e) {

			request.setAttribute("msgexception", isNull(e) ? "Erro com valor nulo" : e.getMessage() ) ;
			throw new LibrarySystemException(e);

		}
		return "restrict/book/list.jsp";
	}

	public String listar(HttpServletRequest request, HttpServletResponse response) {

		try {

			request.setAttribute("books", bookDAO.findAll());

		} catch (Exception e) {

			request.setAttribute("msgexception", isNull(e) ? "Erro com valor nulo" : e.getMessage() ) ;
			throw new LibrarySystemException(e);

		}

		return "restrict/book/list.jsp";
	}

	public String listarSemLogar(HttpServletRequest request, HttpServletResponse response) {

		try {

			request.setAttribute("books", bookDAO.findAll());

		} catch (Exception e) {
			request.setAttribute("msgexception", isNull(e) ? "Erro com valor nulo" : e.getMessage() ) ;
			throw new LibrarySystemException(e);
		}
		
		return "public/lista_book_publico.jsp";
	}

	public String remover(HttpServletRequest request, HttpServletResponse response) {
		try {

			String idStr = request.getParameter("id");

			Book book = new Book();

			book.setId(new Long(idStr == null ? "-1" : idStr));

			bookDAO.remove(book);

			request.setAttribute("books", bookDAO.findAll());

			request.setAttribute("msgSucesso", "Book removido com sucesso");

		} catch (Exception e) {
			request.setAttribute("msgexception", isNull(e) ? "Erro com valor nulo" : e.getMessage() ) ;
			throw new LibrarySystemException(e);
		}
		return "restrict/book/list.jsp";
	}
	
	public boolean isNull(Exception e) {
		return e==null || e.getMessage ()== null || e.getMessage ().trim().contentEquals("null");
	}
}