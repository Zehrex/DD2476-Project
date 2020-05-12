1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/bean/CategoriaBean.java
package br.senac.sc.meuspedidos.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.senac.sc.meuspedidos.dao.CategoriaDao;
import br.senac.sc.meuspedidos.model.Categoria;

@ViewScoped
@ManagedBean
public class CategoriaBean {

	private CategoriaDao dao;
	
	private List<Categoria> categorias;
	
	public CategoriaBean() {
		dao = new CategoriaDao();
		categorias = dao.buscarTodos();
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
}
