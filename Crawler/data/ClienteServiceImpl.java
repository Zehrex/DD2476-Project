2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/service/ClienteServiceImpl.java
package br.com.geranotas.service;

import br.com.geranotas.dao.ClienteDao;
import br.com.geranotas.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public void salvar(Cliente cliente) {
        clienteDao.salvar(cliente);
    }

    @Override
    public List<Cliente> recuperar() {
        return clienteDao.recuperar();
    }

    @Override
    public Cliente recuperarPorId(Integer clienteId) {
        return clienteDao.recuperarPorId(clienteId);
    }

    @Override
    public void atualizar(Cliente cliente) {
        clienteDao.atualizar(cliente);
    }

    @Override
    public void excluir(Integer clienteId) {
        clienteDao.excluir(clienteId);
    }
}
