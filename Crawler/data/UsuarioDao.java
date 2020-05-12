1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/dao/UsuarioDao.java
package br.senac.sc.meuspedidos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.senac.sc.meuspedidos.model.Produto;
import br.senac.sc.meuspedidos.model.Usuario;
import br.senac.sc.meuspedidos.util.JPAUtil;

public class UsuarioDao {

	public Usuario verificaUsuario(String email, String senha) {
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Usuario> query = em.createQuery("from Usuario u where u.email = :email " + "and u.senha = :senha", Usuario.class);
		query.setParameter("email", email);
		query.setParameter("senha", senha);

		try {
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	public Usuario salvar(Usuario usuario) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();

		trx.begin();
		usuario = em.merge(usuario);
		trx.commit();
		em.close();

		return usuario;

	}

	public List<Usuario> buscarTodos() {
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Usuario> query = em.createQuery("from Usuario", Usuario.class);
		return query.getResultList();
	}

	public void excluir(Usuario usuario) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();

		trx.begin();

		em.remove(em.find(Produto.class, usuario.getId()));

		trx.commit();

		em.close();

	}

	public Usuario buscarPorId(int id) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.find(Usuario.class, id);

		} finally {
			em.close();
		}

	}

}
