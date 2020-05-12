2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/service/TabelaService.java
package br.com.geranotas.service;

import br.com.geranotas.domain.Tabela;

import java.util.List;

public interface TabelaService {

    void salvar(Tabela tabela);

    List<Tabela> recuperar();

    Tabela recuperarPorId(Integer id);

    void atualizar(Tabela tabela);

    void excluir(Integer id);

}
