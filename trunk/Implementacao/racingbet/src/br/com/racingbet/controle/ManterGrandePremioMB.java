package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.GrandePremio;
import br.com.racingbet.servico.GrandePremioServico;
import br.com.racingbet.util.ConversacaoUtil;

@Named("manterGrandePremioMB")
@SessionScoped
public class ManterGrandePremioMB implements Serializable {

	@Inject
	private Conversation conversacao;

	@Inject
	private GrandePremio grandePremio;

	private List<GrandePremio> grandesPremios;

	private Boolean temGrandesPremios;

	private Long idGrandePremio;
	
	@Inject
	private GrandePremioServico grandePremioServico;

	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}

	public GrandePremio getGrandePremio() {
		return grandePremio;
	}

	public void setGrandePremio(GrandePremio grandePremio) {
		this.grandePremio = grandePremio;
	}

	public List<GrandePremio> getGrandesPremios() {
		return grandesPremios;
	}

	public void setGrandesPremios(List<GrandePremio> grandesPremios) {
		this.grandesPremios = grandesPremios;
	}

	public Boolean getTemGrandesPremios() {

		if ((grandesPremios == null) || (grandesPremios.size() == 0))
			temGrandesPremios = false;
		else
			temGrandesPremios = true;

		return temGrandesPremios;
	}

	public void setTemGrandesPremios(Boolean temGrandesPremios) {
		this.temGrandesPremios = temGrandesPremios;
	}

	public Long getIdGrandePremio() {
		return idGrandePremio;
	}

	public void setIdGrandePremio(Long idGrandePremio) {
		this.idGrandePremio = idGrandePremio;
	}

	public GrandePremioServico getGrandePremioServico() {
		return grandePremioServico;
	}

	public void setGrandePremioServico(GrandePremioServico grandePremioServico) {
		this.grandePremioServico = grandePremioServico;
	}

	public ManterGrandePremioMB() {
		grandePremio = new GrandePremio();
	}

	@PostConstruct
	public void init() {
		grandePremio = new GrandePremio();
		grandesPremios = grandePremioServico.recuperarTodos();
	}
	
	private void limparGrandePremio() {
		grandePremio = new GrandePremio();
	}
	
	public String iniciar() {
		
		limparGrandePremio();
		
		grandesPremios = grandePremioServico.recuperarTodos();
		System.out.println("passei no iniciar");
		
		return "manterAgenda";
	}

	public String salvar() throws ParseException {
		
		if (grandePremio.getId() == null) {
			grandePremioServico.incluir(grandePremio);
		} else {
			grandePremioServico.alterar(grandePremio);
		}
		
		grandesPremios = grandePremioServico.recuperarTodos();
		
		limparGrandePremio();
		
		ConversacaoUtil.terminar(conversacao);
		
		System.out.println("passei no salvar");
		
		return "manterAgenda";
	}

	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

		grandePremio = grandePremioServico.recuperarPorId(getIdGrandePremio());
		grandesPremios = grandePremioServico.recuperarTodos();

		System.out.println("passei no editar");
		return "manterGrandePremio";
	}

	public String excluir() {

		GrandePremio gpremio = new GrandePremio();
		gpremio.setId(idGrandePremio);
		grandePremioServico.remover(gpremio);

		grandesPremios = grandePremioServico.recuperarTodos();
		limparGrandePremio();

		System.out.println("passei no excluir");

		return "manterGrandePremio";
	}

}
