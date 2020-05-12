9
https://raw.githubusercontent.com/asouza/implementacao-teste-ifood-pagamento-ddd-da-massa/master/src/main/java/com/deveficiente/testepagamentoifood/pagamento/FakeGatewayAcquirerSubAcquirerController.java
package com.deveficiente.testepagamentoifood.pagamento;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeGatewayAcquirerSubAcquirerController {

	@PostMapping(value = "/pagador-fake")
	public void execute(int tempoExecucaoEmMilisegundos,boolean lancaException) throws InterruptedException {
		Thread.sleep(tempoExecucaoEmMilisegundos);
		
		if(lancaException) {					
			throw new RuntimeException("resista a esse problema!!!");
		}
	}

}
