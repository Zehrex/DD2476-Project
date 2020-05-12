1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/bean/CadastroCategoriaBean.java
package br.senac.sc.meuspedidos.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.senac.sc.meuspedidos.dao.CategoriaDao;
import br.senac.sc.meuspedidos.model.Categoria;
import br.senac.sc.meuspedidos.util.FacesUtil;

@ViewScoped
@ManagedBean
public class CadastroCategoriaBean {

	private Categoria categoria;
	private CategoriaDao categoriaDao;

	public void inicializar() {
		categoriaDao = new CategoriaDao();
		if (categoria == null) {
			limpar();
		}

	}

	public void salvar() {
		categoriaDao.salvar(categoria);
		limpar();

		FacesUtil.addInfoMessage("Categoria salvada com sucesso");
	}
	
	public void modificaTexto() {
		categoria.setDescricao(categoria.getDescricao().toUpperCase());
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void limpar() {
		categoria = new Categoria();

	}
	
	

}
