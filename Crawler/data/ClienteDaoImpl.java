2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/dao/ClienteDaoImpl.java
package br.com.geranotas.dao;

import br.com.geranotas.domain.Cliente;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClienteDaoImpl implements ClienteDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public List<Cliente> recuperar() {
        return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    @Override
    public Cliente recuperarPorId(Integer id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public void atualizar(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public void excluir(Integer id) {
        em.remove(em.getReference(Cliente.class, id));
    }
}
