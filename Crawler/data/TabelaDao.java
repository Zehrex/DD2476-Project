2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/dao/TabelaDao.java
package br.com.geranotas.dao;

import br.com.geranotas.domain.Tabela;

import java.util.List;

public interface TabelaDao {

    void salvar(Tabela tabela);

    List<Tabela> recuperar();

    Tabela recuperarPorId(Integer id);

    void atualizar(Tabela tabela);

    void excluir(Integer id);

}
