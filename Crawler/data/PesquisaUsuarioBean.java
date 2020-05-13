1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/bean/PesquisaUsuarioBean.java
package br.senac.sc.meuspedidos.bean;



import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.senac.sc.meuspedidos.dao.UsuarioDao;
import br.senac.sc.meuspedidos.model.Usuario;
import br.senac.sc.meuspedidos.util.FacesUtil;

@ViewScoped
@ManagedBean
public class PesquisaUsuarioBean {
	private UsuarioDao dao;

	private Usuario usuarioSelecionado;

	private List<Usuario> usuarios;

	public void inicializar() {
		dao = new UsuarioDao();
		buscarUsuarios();

	}

	public void excluir() {
		dao.excluir(usuarioSelecionado);
		buscarUsuarios();

		FacesUtil.addInfoMessage("Usuario excluido com sucesso");

	}

	public void buscarUsuarios() {
		usuarios = dao.buscarTodos();
	}

	public PesquisaUsuarioBean() {
		dao = new UsuarioDao();
		usuarios = dao.buscarTodos();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public UsuarioDao getDao() {
		return dao;
	}

	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	

}
