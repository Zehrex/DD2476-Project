9
https://raw.githubusercontent.com/asouza/implementacao-teste-ifood-pagamento-ddd-da-massa/master/src/main/java/com/deveficiente/testepagamentoifood/listapagamentos/PossivelRestricaoPagamento.java
package com.deveficiente.testepagamentoifood.listapagamentos;

import com.deveficiente.testepagamentoifood.TipoPagamento;
import com.deveficiente.testepagamentoifood.Usuario;

public interface PossivelRestricaoPagamento {

	boolean aceita(Usuario usuario,TipoPagamento tipoPagamento);
}
