1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/bean/PesquisaCategoriaBean.java
package br.senac.sc.meuspedidos.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.senac.sc.meuspedidos.dao.CategoriaDao;
import br.senac.sc.meuspedidos.model.Categoria;
import br.senac.sc.meuspedidos.util.FacesUtil;

@ViewScoped
@ManagedBean
public class PesquisaCategoriaBean {

	private CategoriaDao dao;

	private Categoria categoriaSelecionada;

	private List<Categoria> categorias;

	public void inicializar() {
		dao = new CategoriaDao();
		buscarCategorias();

	}

	public void buscarCategorias() {
		categorias = dao.buscarTodos();
	}

	public void excluir() {
		dao.excluir(categoriaSelecionada);
		buscarCategorias();
		
		FacesUtil.addInfoMessage("Categoria excluida com sucesso");
		
	}
	public void pesquisarPorNome(String nome) {
		categorias = dao.pesquisarPorNome(nome);
		
		
	}
	

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public CategoriaDao getDao() {
		return dao;
	}

	public void setDao(CategoriaDao dao) {
		this.dao = dao;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
