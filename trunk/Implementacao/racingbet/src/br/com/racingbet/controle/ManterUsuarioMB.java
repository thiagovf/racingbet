package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Usuario;
import br.com.racingbet.servico.UsuarioServico;
import br.com.racingbet.util.ConversacaoUtil;



@Named("manterUsuarioMB")
@SessionScoped
public class ManterUsuarioMB implements Serializable {
	
	@Inject
	private Conversation conversacao;
	
	@Inject
	private Usuario usuario;
	
	private List<Usuario> usuarios;
	
	private Boolean temUsuarios;

	private Long idUsuario;
	
	@Inject
	private UsuarioServico usuarioServico;

	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Boolean getTemUsuarios() {
		if ((usuarios == null) || (usuarios.size() == 0))
			temUsuarios = false;
		else
			temUsuarios = true;
		return temUsuarios;
	}

	public void setTemUsuarios(Boolean temUsuarios) {
		this.temUsuarios = temUsuarios;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public UsuarioServico getUsuarioServico() {
		return usuarioServico;
	}

	public void setUsuarioServico(UsuarioServico usuarioServico) {
		this.usuarioServico = usuarioServico;
	}
	
	public ManterUsuarioMB() {
		usuario = new Usuario();
	}
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuarios = usuarioServico.recuperarTodos();
	}
	
	private void limparUsuario() {
		usuario = new Usuario();
	}
	
	public String iniciar() {
		
		limparUsuario();
		
		usuarios = usuarioServico.recuperarTodos();
		/*System.out.println("passei no iniciar");*/
		
		return "manterUsuario";
	}

	public String salvar() throws ParseException {
		
		if (usuario.getId() == null) {
			usuarioServico.incluir(usuario);
		} else {
			usuarioServico.alterar(usuario);
		}
		
		usuarios = usuarioServico.recuperarTodos();
		
		limparUsuario();
		
		ConversacaoUtil.terminar(conversacao);
		
		/*System.out.println("passei no salvar");*/
		
		return "manterUsuario";
	}

	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

		usuario = usuarioServico.recuperarPorId(getIdUsuario());
		usuarios = usuarioServico.recuperarTodos();

		
		return "manterUsuario";
	}

	public String excluir() {

		Usuario u = new Usuario();
		u.setId(idUsuario);
		usuarioServico.remover(u);

		usuarios = usuarioServico.recuperarTodos();
		limparUsuario();

		

		return "manterUsuario";
	}

}
