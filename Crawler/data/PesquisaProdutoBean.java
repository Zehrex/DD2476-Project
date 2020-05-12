1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/bean/PesquisaProdutoBean.java
package br.senac.sc.meuspedidos.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.senac.sc.meuspedidos.dao.ProdutoDao;
import br.senac.sc.meuspedidos.model.Produto;
import br.senac.sc.meuspedidos.util.FacesUtil;

@ViewScoped
@ManagedBean
public class PesquisaProdutoBean {
	private ProdutoDao dao;

	private Produto produtoSelecionado;

	private List<Produto> produtos;

	public void inicializar() {
		dao = new ProdutoDao();
		buscarProdutos();

	}

	public void buscarProdutos() {
		produtos = dao.buscarTodos();
	}

	public void excluir() {
		dao.excluir(produtoSelecionado);
		buscarProdutos();

		FacesUtil.addInfoMessage("Produto excluido com sucesso");

	}

	public ProdutoDao getDao() {
		return dao;
	}

	public void setDao(ProdutoDao dao) {
		this.dao = dao;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

}
