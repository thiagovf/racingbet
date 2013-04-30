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
import br.com.racingbet.entidade.Piloto;
import br.com.racingbet.servico.GrandePremioServico;
import br.com.racingbet.servico.PilotoServico;
import br.com.racingbet.util.ConversacaoUtil;

@Named("manterPilotoMB")
@SessionScoped
public class ManterPilotoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Conversation conversacao;

	@Inject
	private Piloto piloto;

	private List<Piloto> pilotos;
	
	private Boolean temPiloto;
	
	private Long idPiloto;
	
	@Inject
	private PilotoServico pilotoServico;
	
	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public List<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	public Boolean getTemPiloto() {
		return temPiloto;
	}

	public void setTemPiloto(Boolean temPiloto) {
		this.temPiloto = temPiloto;
	}

	public Long getIdPiloto() {
		return idPiloto;
	}

	public void setIdPiloto(Long idPiloto) {
		this.idPiloto = idPiloto;
	}

	public ManterPilotoMB() {
		piloto = new Piloto();
	}

	@PostConstruct
	public void init() {
		piloto = new Piloto();
		pilotos = pilotoServico.recuperarTodos();
	}
	
	private void limparGrandePremio() {
		piloto = new Piloto();
	}
	
	public String iniciar() {
		
		limparGrandePremio();
		
		pilotos = pilotoServico.recuperarTodos();
		System.out.println("passei no iniciar");
		
		return "manterAgenda";
	}

	public String salvar() throws ParseException {
		
		if (piloto.getId() == null) {
			pilotoServico.incluir(piloto);
		} else {
			pilotoServico.alterar(piloto);
		}
		
		pilotos = pilotoServico.recuperarTodos();
		
		limparGrandePremio();
		
		ConversacaoUtil.terminar(conversacao);
		
		System.out.println("passei no salvar");
		
		return "manterAgenda";
	}

	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

		piloto = pilotoServico.recuperarPorId(getIdPiloto());
		pilotos = pilotoServico.recuperarTodos();

		System.out.println("passei no editar");
		return "manterGrandePremio";
	}

	public String excluir() {
		
//		Piloto p = new GrandePremio();
//		gpremio.setId(idGrandePremio);
//		grandePremioServico.remover(gpremio);
//
//		grandesPremios = grandePremioServico.recuperarTodos();
//		limparGrandePremio();
//
//		System.out.println("passei no excluir");

//		return "manterGrandePremio";
		return null;
	}
	
}
