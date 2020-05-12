1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/bean/CadastroPedidoBean.java
package br.senac.sc.meuspedidos.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.senac.sc.meuspedidos.dao.ClienteDao;
import br.senac.sc.meuspedidos.dao.PedidoDao;
import br.senac.sc.meuspedidos.dao.ProdutoDao;
import br.senac.sc.meuspedidos.model.Cliente;
import br.senac.sc.meuspedidos.model.Endereco;
import br.senac.sc.meuspedidos.model.FormaPagamento;
import br.senac.sc.meuspedidos.model.ItemPedido;
import br.senac.sc.meuspedidos.model.Pedido;
import br.senac.sc.meuspedidos.model.Produto;
import br.senac.sc.meuspedidos.model.StatusPedido;

@ViewScoped
@ManagedBean
public class CadastroPedidoBean {

	private Pedido pedido;
	private PedidoDao pedidoDao;
	private List<Pedido> pedidos;
	private Produto produto;
	private List<Produto> produtos;
	private ProdutoDao produtoDao;

	private ClienteDao clienteDao;
	private Cliente cliente;
	private List<Cliente> clientes;
	private ItemPedido itemPedido;
	private List<ItemPedido> itensPedido;

	private Double valorTotalPedido;
	private Double valorTotal;
	private Double valorSubTotal;
	private List<Endereco> enderecos;
	private Endereco endereco;

	public void inicializar() {
		pedido = new Pedido();
		pedidoDao = new PedidoDao();
		cliente = new Cliente();
		clientes = new ArrayList<>();
		itensPedido = new ArrayList<>();
		itemPedido = new ItemPedido();
		produto = new Produto();
		clienteDao = new ClienteDao();
		produtoDao = new ProdutoDao();
		endereco = new Endereco();
		enderecos = new ArrayList<>();
		pedidos = new ArrayList<>();
		pedidoDao = new PedidoDao();
		produtos = produtoDao.buscarTodos();
		clientes = clienteDao.buscarTodos();
//        cliqueEmitir(); 

		atualizarDataCriacao();
		pedido.gerarNumeroPedido();

	}

	public void adicionarPedidoNaLista() {
		itemPedido.setProduto(produto);
		itensPedido.add(itemPedido);
		itemPedido = new ItemPedido();

	}

	public void clienteSelecionado() {

		Long id = 0l;
//		for (Cliente cliente : clientes) {
//			pedido.setCliente(cliente);
		pedido.getCliente().getId();
//			cliente.setId(id);
		clienteDao.buscarEnderecoPorIdCliente(id);
//			pedido.getCliente().setId(id);
//			cliente.setEnderecos(enderecos);
		enderecos.add(endereco);
//			pedido.setEnderecoEntrega(endereco);
//			pedido.setEnderecoEntrega((Endereco) enderecos);

	}

//			

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public PedidoDao getPedidoDao() {
		return pedidoDao;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setPedidoDao(PedidoDao pedidoDao) {
		this.pedidoDao = pedidoDao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

	public ClienteDao getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public ItemPedido getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}

	public FormaPagamento[] getForma() {
		return FormaPagamento.values();

	}

	
	public Double getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(Double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public double somarValorDoFreteAoTotalPedido() {
//		if (valorTotalPedido == null) {
//			return 0d;
//		}
		
		double valorFrete = 0l;
		pedido.setValorFrete(valorFrete);
		pedido.setValorTotal(valorFrete);
		
		double auxTotalPed = 0l;
		if(itemPedido.getPedido() != null) {
			
			for (ItemPedido itemPedido : itensPedido) {
				itemPedido.setPedido(pedido);
				valorTotalPedido = pedido.getValorFrete() + valorSubTotal;
			}
			valorTotalPedido = auxTotalPed;
			pedido.setValorTotal(valorTotalPedido);
			return valorTotalPedido;
		}
		return valorTotalPedido;
	
		

	
}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void valorTotalAdd() {
		valorTotal = itemPedido.valorTotal();

	}

	public void produtoSelecionado() {

	}

	public void atualizarDataCriacao() {
		pedido.setDataCriacao(new Date());
	}

	public Double getValorSubTotal() {
		return valorSubTotal;
	}

	public void setValorSubTotal(Double valorSubTotal) {
		this.valorSubTotal = valorSubTotal;

	}

	public double totalSubTotal() {
		if (valorSubTotal == null) {
			return 0d;
		}
		double aux = 0l;

		for (ItemPedido itemPedido : itensPedido) {
			itemPedido.setPedido(pedido);
			valorSubTotal = itemPedido.getQuantidade() * itemPedido.getValorUnitario();
		}
		valorSubTotal = aux;
		return valorSubTotal;

	}

	public void cliqueEmitir() {
		StatusPedido status = StatusPedido.EMITIDO;
		pedido.setStatus(status);

	}

	// PRA PEGAR O VALOR TOTAL DO PEDIDO E SETAR NO VALORTOTAL 
	public double valorTotalPedido() {
		pedido.setValorFrete(pedido.getValorFrete());
		
		if (valorTotalPedido == null) {
			return 0d;
		}
		double auxTotalPed = 0l;

		for (ItemPedido itemPedido : itensPedido) {
			itemPedido.setPedido(pedido);
			valorTotalPedido = pedido.getValorFrete() + valorSubTotal;
		}
		valorTotalPedido = auxTotalPed;
		pedido.setValorTotal(valorTotalPedido);
		return valorTotalPedido;

	}

}
