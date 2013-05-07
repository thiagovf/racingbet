package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Palpite;
import br.com.racingbet.entidade.ResultadoGrandePremio;
import br.com.racingbet.servico.PalpiteServico;
import br.com.racingbet.servico.ResultadoGrandePremioServico;
import br.com.racingbet.util.ConversacaoUtil;

@SuppressWarnings("serial")
@Named("consultarPalpiteMB")
@SessionScoped
public class ConsultarPalpiteMB implements Serializable {

	@Inject
	private Conversation conversacao;
	
	private List<Palpite> palpites;

	@Inject
	private PalpiteServico palpiteServico;
	
	public Conversation getConversacao() {
		return conversacao;
	}
	
	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}
	
	@PostConstruct
	public void init() {
		palpites = palpiteServico.recuperarTodos();
	}
	
	public String iniciar() {
		return "consultarPalpite";
	}

	public PalpiteServico getPalpiteServico() {
		return palpiteServico;
	}

	public void setPalpiteServico(PalpiteServico palpiteServico) {
		this.palpiteServico = palpiteServico;
	}

	public List<Palpite> getPalpites() {
		return palpites;
	}

	public void setPalpites(List<Palpite> palpites) {
		this.palpites = palpites;
	}
}
