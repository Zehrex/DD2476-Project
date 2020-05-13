1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/dao/ProdutoDao.java
package br.senac.sc.meuspedidos.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


import br.senac.sc.meuspedidos.model.Produto;
import br.senac.sc.meuspedidos.util.JPAUtil;

public class ProdutoDao {

	public Produto salvar(Produto produto) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();

		trx.begin();
		produto = em.merge(produto);
		trx.commit();
		em.close();

		return produto;

	}

	public List<Produto> buscarTodos() {
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Produto> query = em.createQuery("from Produto", Produto.class);
		return query.getResultList();
	}

	public void excluir(Produto produto) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();

		trx.begin();

		em.remove(em.find(Produto.class, produto.getId()));

		trx.commit();

		em.close();
	
	}
	
	public Produto buscarPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.find(Produto.class, id);

		} finally {
			em.close();
		}

	}

}


