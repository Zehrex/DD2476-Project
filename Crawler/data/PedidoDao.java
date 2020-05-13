1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/dao/PedidoDao.java
package br.senac.sc.meuspedidos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.senac.sc.meuspedidos.model.Pedido;
import br.senac.sc.meuspedidos.model.Usuario;
import br.senac.sc.meuspedidos.util.JPAUtil;

public class PedidoDao {
private Pedido pedido;

public Pedido salvar(Pedido pedido) {
	EntityManager em = JPAUtil.getEntityManager();
	EntityTransaction trx = em.getTransaction();

	trx.begin();
	pedido = em.merge(pedido);
	trx.commit();
	em.close();

	return pedido;

}

public List<Pedido> buscarTodos() {
	EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Pedido> query = em.createQuery("from Pedido", Pedido.class);

	try {
		return query.getResultList();

	} finally {
		em.close();
	}
}

//public int gerarNumero() {
//	EntityManager em = JPAUtil.getEntityManager();
//	TypedQuery<Integer> query = em.createQuery("select max(numero) from Pedido", Integer.class);
//	query.getSingleResult("numero" + 1);
//
//	if (!query.getResultList().isEmpty()) {
//		return query.getResultList();
//	} else {
//		return buscarTodos();
//	}

	
	

}
