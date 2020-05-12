2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/domain/ProdutoTabela.java
package br.com.geranotas.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto_tabela")
public class ProdutoTabela implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @Column(name = "id")
    private ProdutoTabelaKey id;

    @ManyToOne
    @JoinColumn(name = "IDTABELA", insertable = false, updatable = false)
    private Tabela tabela;

    @ManyToOne
    @JoinColumn(name = "IDPRODUTO", insertable = false, updatable = false)
    private Produto produto;

    public ProdutoTabela(Tabela tabela, Produto produto) {
        super();
        this.tabela = tabela;
        this.produto = produto;
        this.id = new ProdutoTabelaKey(tabela.getId(), produto.getId());
    }

    public ProdutoTabelaKey getId() {
        return id;
    }

    public void setId(ProdutoTabelaKey id) {
        this.id = id;
    }

    public Tabela getTabela() {
        return tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
