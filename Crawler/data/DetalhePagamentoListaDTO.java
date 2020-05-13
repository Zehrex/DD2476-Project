9
https://raw.githubusercontent.com/asouza/implementacao-teste-ifood-pagamento-ddd-da-massa/master/src/main/java/com/deveficiente/testepagamentoifood/listapagamentos/DetalhePagamentoListaDTO.java
package com.deveficiente.testepagamentoifood.listapagamentos;

import com.deveficiente.testepagamentoifood.TipoPagamento;

public class DetalhePagamentoListaDTO {


	private TipoPagamento tipoPagamento;

	public DetalhePagamentoListaDTO(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}
	
	
}
