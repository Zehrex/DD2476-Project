2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/service/ProdutoService.java
package br.com.geranotas.service;

import br.com.geranotas.domain.Produto;

import java.util.List;

public interface ProdutoService {

    void salvar(Produto produto);

    void salvarNaTabela(Produto produto, Integer tabelaId);

    List<Produto> recuperarPorTabela(Integer tabelaId);

    List<Produto> recuperar();

    Produto recuperarPorId(Integer produtoId);

    Produto recuperarPorTabelaIdEProdutoId(Integer tabelaId, Integer produtoId);

    void atualizar(Produto produto);

    void atualizarNaTabela(Produto produto, Integer tabelaId);

    void excluir(Integer produtoId);

    void excluirNaTabela(Integer produtoId, Integer tabelaId);

}
