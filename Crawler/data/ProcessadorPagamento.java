9
https://raw.githubusercontent.com/asouza/implementacao-teste-ifood-pagamento-ddd-da-massa/master/src/main/java/com/deveficiente/testepagamentoifood/pagamento/processadores/ProcessadorPagamento.java
package com.deveficiente.testepagamentoifood.pagamento.processadores;

import java.util.Optional;

import com.deveficiente.testepagamentoifood.pagamento.TentativaPagamento;

/*
 * Responsável pelo aceite e geração de um pagador para uma determinada forma de pagamento 
 */
public interface ProcessadorPagamento {

	/**
	 * 
	 * @param tentativaPagamento
	 * @return caso possa processar, retorna a Transacao
	 */
	Optional<Pagador> aceita(TentativaPagamento tentativaPagamento);
	
	
}
