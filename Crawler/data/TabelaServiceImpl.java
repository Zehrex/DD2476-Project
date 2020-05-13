2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/service/TabelaServiceImpl.java
package br.com.geranotas.service;

import br.com.geranotas.dao.TabelaDao;
import br.com.geranotas.domain.Tabela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TabelaServiceImpl implements TabelaService {

    @Autowired
    private TabelaDao tabelaDao;

    @Override
    public void salvar(Tabela tabela) {
        tabelaDao.salvar(tabela);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tabela> recuperar() {
        return tabelaDao.recuperar();
    }

    @Override
    @Transactional
    public Tabela recuperarPorId(Integer id) {
        return tabelaDao.recuperarPorId(id);
    }

    @Override
    public void atualizar(Tabela tabela) {
        tabelaDao.atualizar(tabela);
    }

    @Override
    public void excluir(Integer id) {
        tabelaDao.excluir(id);
    }
}
