1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/bean/CadastroUsuarioBean.java
package br.senac.sc.meuspedidos.bean;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.senac.sc.meuspedidos.dao.UsuarioDao;

import br.senac.sc.meuspedidos.model.Usuario;
import br.senac.sc.meuspedidos.util.FacesUtil;

@ViewScoped
@ManagedBean
public class CadastroUsuarioBean {
	private Usuario usuario;
	private UsuarioDao usuarioDao;
  
    private List<Usuario> usuarios;
    
    public void inicializar() {
		usuario = new Usuario();
		usuarioDao = new UsuarioDao();  
        
    }

	public void salvar() {
		limpar();
		
		usuarioDao.salvar(usuario);
		FacesUtil.addInfoMessage("Usuario salvo com sucesso");
	
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void limpar() {
		usuario = new Usuario();

	}

}






