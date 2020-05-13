1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/bean/LoginBean.java
package br.senac.sc.meuspedidos.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.senac.sc.meuspedidos.dao.UsuarioDao;
import br.senac.sc.meuspedidos.model.Usuario;
import br.senac.sc.meuspedidos.util.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginBean {
	private String email;
	private String senha;
	private UsuarioDao usuarioDao;

	public LoginBean() {
		usuarioDao = new UsuarioDao();
	}

	public String login() {
		Usuario usuario = usuarioDao.verificaUsuario(email, senha);
		if (usuario == null) {
			FacesUtil.addErrorMessage("Usuário ou senha invalidos");
			return "login?faces-redirect=true";
		} else {
			// criando uma sessao e setando o usuario na sessao
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("UsuarioLogado", usuario);

			return "dashboard?faces-redirect=true";
		}

	}
	
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);
		
		session.invalidate();
		return "/login?faces-redirect=true";
		
		
	}
	public String getUsuarioLogado() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);
		
		Usuario usuario = (Usuario) session.getAttribute("UsuarioLogado");
		
		return usuario.getNome();
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
