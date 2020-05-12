9
https://raw.githubusercontent.com/asouza/implementacao-teste-ifood-pagamento-ddd-da-massa/master/src/main/java/com/deveficiente/testepagamentoifood/pagamento/validadores/CCSoEhValidoParaCartaoOnlineValidator.java
package com.deveficiente.testepagamentoifood.pagamento.validadores;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CCSoEhValidoParaCartaoOnlineValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoPagamentoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NovoPagamentoForm form = (NovoPagamentoForm) target;
		form.validaCc(errors);		
	}

}
