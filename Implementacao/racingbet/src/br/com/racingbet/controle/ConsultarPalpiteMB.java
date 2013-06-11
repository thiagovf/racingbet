package br.com.racingbet.controle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.GrandePremio;
import br.com.racingbet.entidade.Palpite;
import br.com.racingbet.servico.CategoriaServico;
import br.com.racingbet.servico.GrandePremioServico;
import br.com.racingbet.servico.PalpiteServico;

@SuppressWarnings("serial")
@Named("consultarPalpiteMB")
@SessionScoped
public class ConsultarPalpiteMB implements Serializable {

	@Inject
	private Conversation conversacao;
	
	@Inject
	private GerenciarUsuarioMB gerenciarUsuario;
	
	private List<Palpite> palpites;
	
	private List<Categoria> categorias;
	private Long categoriaSelecionadaId;
	
	private Long grandePremioSelecionadoId;
	private List<GrandePremio> grandePremios;
	
	@Inject
	private GrandePremioServico grandePremioServico;
	
	@Inject
	private CategoriaServico categoriaServico;
	
	@Inject
	private PalpiteServico palpiteServico;
	
	public String atualizarGrandePremios(){
		String clausula_where = "id_categoria=" + categoriaSelecionadaId;
		grandePremios = getGrandePremioServico().recuperarTodos(clausula_where);
	
		return "consultarPalpite";
	}

	public String atualizarPalpites(){
		String clausula_where = "idGrandePremio=" + grandePremioSelecionadoId;
		
		palpites = palpiteServico.recuperarTodos(clausula_where);
		
		return "consultarPalpite";
	}
	
	public Conversation getConversacao() {
		return conversacao;
	}
	
	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}
	
	@PostConstruct
	public void init() {
		categorias = categoriaServico.recuperarTodos();
	}
	
	public String iniciar() {
		return "consultarPalpite";
	}

	public List<Palpite> getPalpites() {
		return palpites;
	}

	public void setPalpites(List<Palpite> palpites) {
		this.palpites = palpites;
	}

	public GerenciarUsuarioMB getGerenciarUsuario() {
		return gerenciarUsuario;
	}

	public void setGerenciarUsuario(GerenciarUsuarioMB gerenciarUsuario) {
		this.gerenciarUsuario = gerenciarUsuario;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public List<GrandePremio> getGrandePremios() {
		return grandePremios;
	}

	public void setGrandePremios(List<GrandePremio> grandePremios) {
		this.grandePremios = grandePremios;
	}

	public Long getCategoriaSelecionadaId() {
		return categoriaSelecionadaId;
	}

	public void setCategoriaSelecionadaId(Long categoriaSelecionadaId) {
		this.categoriaSelecionadaId = categoriaSelecionadaId;
	}

	public Long getGrandePremioSelecionadoId() {
		return grandePremioSelecionadoId;
	}

	public void setGrandePremioSelecionadoId(Long grandePremioSelecionadoId) {
		this.grandePremioSelecionadoId = grandePremioSelecionadoId;
	}

	public GrandePremioServico getGrandePremioServico() {
		return grandePremioServico;
	}

	public void setGrandePremioServico(GrandePremioServico grandePremioServico) {
		this.grandePremioServico = grandePremioServico;
	}

	public PalpiteServico getPalpiteServico() {
		return palpiteServico;
	}

	public void setPalpiteServico(PalpiteServico palpiteServico) {
		this.palpiteServico = palpiteServico;
	}
}
