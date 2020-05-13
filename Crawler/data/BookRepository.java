2
https://raw.githubusercontent.com/marcoseduardoss/mini-mvc/master/demos/001-crud-books-mvc-servlets-jstl-jpa/src/main/java/br/me/crudbooks/model/repository/BookRepository.java
package br.me.crudbooks.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.me.crudbooks.model.domain.entity.Book;
import br.me.crudbooks.model.domain.exception.LibrarySystemException;

public class BookRepository {


	private final EntityManagerFactory entityManagerFactory = HibernateUtil.INSTANCE.getEntityManagerFactory();

	public void save(Book book) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		if (entityManager == null)
			throw new LibrarySystemException("Repository Exception: EntityManager is null!");

		try {
			
			entityManager.getTransaction().begin();
			
			entityManager.persist(book);
			entityManager.flush();
			
			entityManager.getTransaction().commit();

		}  catch (Exception e) {
			
			if (entityManager.isOpen())
				entityManager.getTransaction().rollback();
			
			throw new LibrarySystemException("Repository Exception: " + e.getMessage(), e);
			
		} finally {

			if (entityManager.isOpen()) 
				entityManager.close();
			
		}

	}

	public void update(Book book) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		if (entityManager == null)
			throw new LibrarySystemException("Repository Exception: EntityManager is null!");

		try {
			
			entityManager.getTransaction().begin();
			
			entityManager.merge(book);
			entityManager.flush();
			
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			
			if (entityManager.isOpen())
				entityManager.getTransaction().rollback();
			
			throw new LibrarySystemException("Repository Exception: " + e.getMessage(), e);
			
		} finally {

			if (entityManager.isOpen()) 
				entityManager.close();
			
		}

	}

	public void remove(Book book) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		if (entityManager == null)
			throw new LibrarySystemException("Repository Exception: EntityManager is null!");

		try {
			
			entityManager.getTransaction().begin();
			
			entityManager.remove(entityManager.getReference(Book.class, book.getId()));
			
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			
			if (entityManager.isOpen())
				entityManager.getTransaction().rollback();
			
			throw new LibrarySystemException("Repository Exception: " + e.getMessage(), e);
			
		} finally {

			if (entityManager.isOpen()) 
				entityManager.close();
			
		}
		
	}

	public List<Book> findAll() {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		List<Book> retorno = null;

		if (entityManager == null)
			throw new LibrarySystemException("Repository Exception: EntityManager is null!");

		try {
			
			retorno = entityManager.createQuery("select u from Book u", Book.class).getResultList();

		} catch (Exception e) {
			
			throw new LibrarySystemException("Repository Exception: " + e.getMessage(), e);
			
		} finally {

			if (entityManager.isOpen()) 
				entityManager.close();
			
		}
		
		return retorno;
	}

	public Book findById(Long id) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Book retorno = null;

		if (entityManager == null)
			throw new LibrarySystemException("Repository Exception: EntityManager is null!");

		try {
			
			String query = "select u from Book u where u.id = :id";
			
			TypedQuery<Book> createQuery = entityManager.createQuery(query, Book.class);
			
			createQuery.setParameter("id", id);
			
			retorno = createQuery.getSingleResult();

		} catch (Exception e) {
			
			throw new LibrarySystemException("Repository Exception: " + e.getMessage(), e);
			
		} finally {

			if (entityManager.isOpen()) 
				entityManager.close();
			
		}
		
		return retorno;

	}

}