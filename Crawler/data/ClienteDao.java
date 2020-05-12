1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/dao/ClienteDao.java
package br.senac.sc.meuspedidos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.senac.sc.meuspedidos.model.Cliente;
import br.senac.sc.meuspedidos.model.Endereco;
import br.senac.sc.meuspedidos.util.JPAUtil;

public class ClienteDao {
	private Cliente cliente;
	
	public Cliente salvar(Cliente cliente) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();

		trx.begin();
		cliente = em.merge(cliente);
		trx.commit();
		em.close();

		return cliente;

	}

	public List<Cliente> buscarTodos() {
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Cliente> query = em.createQuery("from Cliente", Cliente.class);

		try {
			return query.getResultList();

		} finally {
			em.close();
		}
	}

	public void excluir(Cliente categoria) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();

		trx.begin();

		em.remove(em.find(Cliente.class, categoria.getId()));

		trx.commit();

		em.close();
	}

	public Cliente buscarPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.find(Cliente.class, id);

		} finally {
			em.close();
		}
	}

	public List<Cliente> pesquisarPorNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		String consulta = "select nome from Cliente where nome = :nome";
		TypedQuery<Cliente> query = em.createQuery(consulta, Cliente.class);
		query.setParameter("nome", "%" + nome + "%");

		if (!query.getResultList().isEmpty()) {
			return query.getResultList();
		} else {
			return buscarTodos();
		}
	}

	public Cliente buscarEnderecoPorIdCliente(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		String consulta = "from Cliente c JOIN fetch c.enderecos where c.id = "+ id;

		TypedQuery<Cliente> query = em.createQuery(consulta, Cliente.class);

		try {
			return query.getSingleResult();
		} finally {
			em.close();
		}

	}

}
