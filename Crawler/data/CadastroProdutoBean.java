1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/bean/CadastroProdutoBean.java
package br.senac.sc.meuspedidos.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.senac.sc.meuspedidos.dao.CategoriaDao;
import br.senac.sc.meuspedidos.dao.ProdutoDao;
import br.senac.sc.meuspedidos.model.Categoria;
import br.senac.sc.meuspedidos.model.FormaPagamento;
import br.senac.sc.meuspedidos.model.Produto;
import br.senac.sc.meuspedidos.util.FacesUtil;

@ViewScoped
@ManagedBean
public class CadastroProdutoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private ProdutoDao produtoDao;
	private CategoriaDao categoriaDao;
	private List<Categoria> categorias;
  
	public void inicializar() {
		produto = new Produto();
		produtoDao = new ProdutoDao();
		
		categoriaDao = new CategoriaDao();
		
		categorias = categoriaDao.buscarTodos();
		
	}
	
	public void categoriaSelecionada() {
		FacesUtil.addInfoMessage("categoria selecionada");
	}

	public void salvar() {
		System.out.println("Salvando a categoria: " + produto.getNome());
		produtoDao.salvar(produto);
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

	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public FormaPagamento[] getForma() {
		return FormaPagamento.values();

	}
	
}
