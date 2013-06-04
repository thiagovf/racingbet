package br.com.racingbet.controle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.GrandePremio;
import br.com.racingbet.servico.GrandePremioServico;

@SuppressWarnings("serial")
@Named("consultarGrandePremioMB")
@SessionScoped
public class ConsultarGrandePremioMB implements Serializable {

	@Inject
	private Conversation conversacao;

	private List<GrandePremio> grandesPremios;

	@Inject
	private GrandePremioServico grandePremioServico;

	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;

	}

	@PostConstruct
	public void init() {
		grandesPremios = grandePremioServico.recuperarTodos();
	}

	public String inciar() {
		return "consultarGrandePremio";
	}

	public GrandePremioServico getGrandePremioServico() {
		return grandePremioServico;
	}

	public void setGrandePremioServico(GrandePremioServico grandePremioServico) {
		this.grandePremioServico = grandePremioServico;
	}

	public List<GrandePremio> getGrandesPremios() {
		return grandesPremios;
	}

	public void setGrandesPremios(List<GrandePremio> grandesPremios) {
		this.grandesPremios = grandesPremios;
	}

}
