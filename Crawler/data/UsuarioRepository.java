9
https://raw.githubusercontent.com/asouza/implementacao-teste-ifood-pagamento-ddd-da-massa/master/src/main/java/com/deveficiente/testepagamentoifood/listapagamentos/UsuarioRepository.java
package com.deveficiente.testepagamentoifood.listapagamentos;

import org.springframework.data.repository.Repository;

import com.deveficiente.testepagamentoifood.Usuario;

public interface UsuarioRepository extends Repository<Usuario, Long>{
	
	public Usuario findByNome(String nome);

}
