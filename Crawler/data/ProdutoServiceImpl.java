2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/service/ProdutoServiceImpl.java
package br.com.geranotas.service;

import br.com.geranotas.dao.ProdutoDao;
import br.com.geranotas.domain.Produto;
import br.com.geranotas.domain.ProdutoTabela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoDao produtoDao;

    @Autowired
    private TabelaService tabelaService;

    ProdutoTabela produtoTabela;

    @Override
    public void salvar(Produto produto) {
        produtoDao.salvar(produto);
    }

    @Override
    public void salvarNaTabela(Produto produto, Integer tabelaId) {
        produtoDao.salvarNaTabela(produto, tabelaId);
    }

    @Override
    public List<Produto> recuperarPorTabela(Integer tabelaId) {
        return produtoDao.recuperarPorTabela(tabelaId);
    }

    @Override
    public List<Produto> recuperar() {
        return produtoDao.recuperar();
    }

    @Override
    public Produto recuperarPorId(Integer produtoId) {
        return produtoDao.recuperarPorId(produtoId);
    }

    @Override
    public Produto recuperarPorTabelaIdEProdutoId(Integer tabelaId, Integer produtoId) {
        return produtoDao.recuperarPorTabelaIdEProdutoId(tabelaId, produtoId);
    }

    @Override
    public void atualizar(Produto produto) {
        produtoDao.atualizar(produto);
    }

    @Override
    public void atualizarNaTabela(Produto produto, Integer tabelaId) {
        produtoDao.atualizarNaTabela(produto, tabelaId);
    }

    @Override
    public void excluir(Integer produtoId) {
        produtoDao.excluir(produtoId);
    }

    @Override
    public void excluirNaTabela(Integer tabelaId, Integer produtoId) {
        produtoDao.excluir(recuperarPorTabelaIdEProdutoId(tabelaId, produtoId).getId());
    }
}
