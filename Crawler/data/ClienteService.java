2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/service/ClienteService.java
package br.com.geranotas.service;

import br.com.geranotas.domain.Cliente;

import java.util.List;

public interface ClienteService {

    void salvar(Cliente cliente);

    List<Cliente> recuperar();

    Cliente recuperarPorId(Integer clienteId);

    void atualizar(Cliente cliente);

    void excluir(Integer clienteId);

}
