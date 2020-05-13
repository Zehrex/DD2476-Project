1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/bean/CadastroClienteBean.java
package br.senac.sc.meuspedidos.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.senac.sc.meuspedidos.dao.ClienteDao;
import br.senac.sc.meuspedidos.model.Cliente;
import br.senac.sc.meuspedidos.model.Endereco;
import br.senac.sc.meuspedidos.model.TipoPessoa;
import br.senac.sc.meuspedidos.util.FacesUtil;

@ViewScoped
@ManagedBean
public class CadastroClienteBean {
	private Cliente cliente;
	private ClienteDao dao;
	private Cliente clienteSelecionado;
	private List<Cliente> clientes;
	private List<Endereco> enderecos;
	private Endereco endereco;

	public void inicializar() {
		enderecos = new ArrayList<>();
		dao = new ClienteDao();
		cliente = new Cliente();
		endereco = new Endereco();
		
		
//	    clientes = new ArrayList<>();
//		buscarClientes();

	}

	public void salvar() {
		cliente.setEnderecos(enderecos);
		dao.salvar(cliente);
		limpar();

		FacesUtil.addInfoMessage("Cliente salvo com sucesso");
	}

	public void buscarClientes() {
		clientes = dao.buscarTodos();
	}

	public void limpar() {
		cliente = new Cliente();

	}

	public void adicionarEndereco() {
		enderecos.add(endereco);
		cliente.setEnderecos(enderecos);
		endereco = new Endereco();

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteDao getDao() {
		return dao;
	}

	public void setDao(ClienteDao dao) {
		this.dao = dao;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	/* Aqui método que acesso no selectOneMenu */
	public TipoPessoa[] getTipo() {
		return TipoPessoa.values();

	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

//	public void excluir() {
//		dao.excluir(clienteSelecionado);
//		buscarClientes();
//		
//		FacesUtil.addInfoMessage("Cliente excluido com sucesso");
//		

//	public void pesquisarPorNome(String nome) {
//		categorias = dao.pesquisarPorNome(nome);

}