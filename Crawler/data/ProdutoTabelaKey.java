2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/domain/ProdutoTabelaKey.java
package br.com.geranotas.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProdutoTabelaKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "IDPRODUTO")
    Integer produtoId;

    @Column(name = "IDTABELA")
    Integer tabelaId;

    public ProdutoTabelaKey(Integer produtoId, Integer tabelaId) {
        this.produtoId = produtoId;
        this.tabelaId = tabelaId;
    }
}
