9
https://raw.githubusercontent.com/asouza/implementacao-teste-ifood-pagamento-ddd-da-massa/master/src/main/java/com/deveficiente/testepagamentoifood/TipoPagamento.java
package com.deveficiente.testepagamentoifood;

public enum TipoPagamento {
	visa(true),master(true),hipercard(true),elo(true),dinheiro(false), cheque(false), maquininha(false);

	public final boolean aceitaOnline;

	TipoPagamento(boolean aceitaOnline) {
		this.aceitaOnline = aceitaOnline;
	}
	
	

	
}
