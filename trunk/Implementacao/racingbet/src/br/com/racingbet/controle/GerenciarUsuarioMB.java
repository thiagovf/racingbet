package br.com.racingbet.controle;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Usuario;
import br.com.racingbet.excecoes.LoginException;
import br.com.racingbet.servico.UsuarioServico;

@Named
@SessionScoped
public class GerenciarUsuarioMB implements Serializable {

	@Inject
	private Usuario usuario;

	@Inject
	private UsuarioServico usuarioServico;

	private String mensagemLogin;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioServico getUsuarioServico() {
		return usuarioServico;
	}

	public void setUsuarioServico(UsuarioServico usuarioServico) {
		this.usuarioServico = usuarioServico;
	}

	public String getMensagemLogin() {
		return mensagemLogin;
	}

	public void setMensagemLogin(String mensagemLogin) {
		this.mensagemLogin = mensagemLogin;
	}

	public GerenciarUsuarioMB() {

	}

	public String logar() {

		try {
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getSenha());
			usuario = usuarioServico.logar(usuario);
			return "principal";
		} catch (LoginException le) {
			mensagemLogin = le.getMessage();
			return "logar";
		}
	}

	public String cadastrar() {
		try {
			usuarioServico.incluir(usuario);
			return "principal";
		} catch (Exception e) {
			mensagemLogin = e.getMessage();
			return "logar";
		}
	}

	public String alterar() {
		try {
			usuarioServico.alterar(usuario);
			return "principal";
		} catch (Exception e) {
			mensagemLogin = e.getMessage();
			return "logar";
		}
	}

	public String sair() {
		usuario = new Usuario();
		return "logar";
	}

}
