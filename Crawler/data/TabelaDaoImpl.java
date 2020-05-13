2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/dao/TabelaDaoImpl.java
package br.com.geranotas.dao;

import br.com.geranotas.domain.Tabela;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TabelaDaoImpl implements TabelaDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Tabela tabela) {
        em.persist(tabela);
    }

    @Override
    public List<Tabela> recuperar() {
        return em.createQuery("select t from Tabela t", Tabela.class).getResultList();
    }

    @Override
    public Tabela recuperarPorId(Integer id) {
        return em.find(Tabela.class, id);
    }

    @Override
    public void atualizar(Tabela tabela) {
        em.merge(tabela);
    }

    @Override
    public void excluir(Integer id) {
        em.remove(em.getReference(Tabela.class, id));
    }
}
