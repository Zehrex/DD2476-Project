2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/domain/Tabela.java
package br.com.geranotas.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tabela")
public class Tabela implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "tabela_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tabela_seq", sequenceName = "tabela_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "QUANTIDADE")
    //@NotBlank(message = "O campo QUANTIDADE é obrigatório.")
    private Integer quantidade;

    @Column(name = "VALOR_VENDA")
    //@NotBlank(message = "O campo VALOR DE VENDA é obrigatório.")
    private BigDecimal valorVenda;

    @Column(name = "PERIODO")
    private String periodo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Tabela() {
    }

    public Tabela(Integer id, Integer quantidade, BigDecimal valorVenda, String periodo, Cliente cliente) {
        this.id = id;
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
        this.periodo = periodo;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
