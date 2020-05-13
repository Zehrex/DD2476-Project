9
https://raw.githubusercontent.com/asouza/implementacao-teste-ifood-pagamento-ddd-da-massa/master/src/main/java/com/deveficiente/testepagamentoifood/listapagamentos/PossivelFraudador.java
package com.deveficiente.testepagamentoifood.listapagamentos;

import org.springframework.stereotype.Service;

import com.deveficiente.testepagamentoifood.TipoPagamento;
import com.deveficiente.testepagamentoifood.Usuario;

/**
 * Analisa se um usuário é um possível fraudador
 * @author albertoluizsouza
 *
 */
@Service
public class PossivelFraudador implements PossivelRestricaoPagamento{

	@Override
	public boolean aceita(Usuario usuario, TipoPagamento tipoPagamento) {
		if(usuario.getNome().equals("alberto")) {
			return !tipoPagamento.aceitaOnline;			
		}
		return true;
	}

}
