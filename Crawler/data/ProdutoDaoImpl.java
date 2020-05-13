2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/dao/ProdutoDaoImpl.java
package br.com.geranotas.dao;

import br.com.geranotas.domain.Produto;
import br.com.geranotas.domain.ProdutoTabela;
import br.com.geranotas.domain.ProdutoTabelaKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProdutoDaoImpl implements ProdutoDao {

    @PersistenceContext
    private EntityManager em;

    private ProdutoTabela produtoTabela;

    private ProdutoTabelaKey produtoTabelaKey;

    @Autowired
    private TabelaDao tabelaDao;

    @Override
    public void salvar(Produto produto) {
        em.persist(produto);
    }

    @Override
    public void salvarNaTabela(Produto produto, Integer tabelaId) {
        produtoTabelaKey = new ProdutoTabelaKey(produto.getId(), tabelaId);
        produtoTabela.setId(produtoTabelaKey);
        produtoTabela.setProduto(produto);
        produtoTabela.setTabela(tabelaDao.recuperarPorId(tabelaId));
        em.persist(produtoTabela);
    }

    @Override
    public List<Produto> recuperarPorTabela(Integer tabelaId) {
        return em.createQuery("select p from Produto p where Tabela.id = :tabelaId", Produto.class)
                .setParameter("tabelaId", tabelaId)
                .getResultList();
    }

    @Override
    public List<Produto> recuperar() {
        return em.createQuery("select p from Produto p", Produto.class).getResultList();
    }

    @Override
    public Produto recuperarPorId(Integer id) {
        return em.find(Produto.class, id);
    }

    @Override
    public Produto recuperarPorTabelaIdEProdutoId(Integer tabelaId, Integer produtoId) {
        return em.createQuery("select p from Produto p where Tabela.id = :tabelaId and p.id = :produtoId", Produto.class)
                .setParameter("tabelaId", tabelaId)
                .setParameter("produtoId", produtoId)
                .getSingleResult();
    }

    @Override
    public void atualizar(Produto produto) {
        em.merge(produto);
    }

    @Override
    public void atualizarNaTabela(Produto produto, Integer tabelaId) {
        produtoTabela.setProduto(produto);
        produtoTabela.setTabela(tabelaDao.recuperarPorId(tabelaId));
        em.merge(produtoTabela);
    }

    @Override
    public void excluir(Integer id) {
        em.remove(em.getReference(Produto.class, id));
    }

    @Override
    public void excluirNaTabela(Integer produtoId, Integer tabelaId) {
        em.remove(em.getReference(ProdutoTabela.class, produtoTabela.getProduto()));
    }
}
