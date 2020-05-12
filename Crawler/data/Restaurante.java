9
https://raw.githubusercontent.com/asouza/implementacao-teste-ifood-pagamento-ddd-da-massa/master/src/main/java/com/deveficiente/testepagamentoifood/Restaurante.java
package com.deveficiente.testepagamentoifood;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Size(min = 1)
	@ElementCollection
	private Set<TipoPagamento> tiposPagamento = new HashSet<>();
	
	@Deprecated
	public Restaurante() {

	}

	public Restaurante(String nome, TipoPagamento... possiveisTiposPagamento) {
		this.nome = nome;
		this.tiposPagamento.addAll(Stream.of(possiveisTiposPagamento).collect(Collectors.toSet()));
	}
	
	public boolean aceita(TipoPagamento tipoPagamento) {
		return this.tiposPagamento.contains(tipoPagamento);
	}

}
