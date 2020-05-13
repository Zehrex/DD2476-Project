1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/dao/CategoriaDao.java
package br.senac.sc.meuspedidos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.senac.sc.meuspedidos.model.Categoria;
import br.senac.sc.meuspedidos.util.JPAUtil;

public class CategoriaDao {

	private static final Categoria String = null;

    private Categoria categoria;	
	
	public Categoria salvar(Categoria categoria) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();

		trx.begin();
		categoria = em.merge(categoria);
		trx.commit();
		em.close();

		return categoria;

	}

	public List<Categoria> buscarTodos() {
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Categoria> query = em.createQuery("from Categoria", Categoria.class);

		try {
			return query.getResultList();

		} finally {
			em.close();
		}
	}

	public void excluir(Categoria categoria) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();

		trx.begin();

		em.remove(em.find(Categoria.class, categoria.getId()));

		trx.commit();

		em.close();
	}

	public Categoria buscarPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.find(Categoria.class, id);

		} finally {
			em.close();
		}
	}
	
	public List<Categoria> pesquisarPorNome(String nome) {
	EntityManager em = JPAUtil.getEntityManager();
    String consulta = "select descricao from Categoria where descricao = :descricao";
    TypedQuery<Categoria> query = em.createQuery(consulta, Categoria.class);
    query.setParameter("descricao", "%" + nome + "%");
  
    if(!query.getResultList().isEmpty()) {
    	return query.getResultList();
	}else {
		return buscarTodos();
	}
}
	
//	public Categoria pesquisarPorNome(String nome) {
//		EntityManager em = JPAUtil.getEntityManager();
//
//		String sql;
//		sql = ("select * from tab_usuario where usuario_usuario=?");
//
//		return null;
		

	

}
