package br.com.racingbet.controle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Pontuacao;
import br.com.racingbet.servico.PontuacaoServico;

@SuppressWarnings("serial")
@Named("consultarPontuacaoMB")
@SessionScoped
public class ConsultarPontuacaoMB implements Serializable {

	@Inject
	private Conversation conversacao;
	
	private List<Pontuacao> pontuacoes;

	@Inject
	private PontuacaoServico pontuacaoServico;
	
	public Conversation getConversacao() {
		return conversacao;
	}
	
	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}
	
	@PostConstruct
	public void init() {
		pontuacoes = pontuacaoServico.recuperarTodos();
	}
	
	public String iniciar() {
		return "consultarPontuacao";
	}

	public PontuacaoServico getPontuacaoServico() {
		return pontuacaoServico;
	}

	public void setPontuacaoServico(PontuacaoServico pontuacaoServico) {
		this.pontuacaoServico = pontuacaoServico;
	}

	public List<Pontuacao> getPontuacoes() {
		return pontuacoes;
	}

	public void setPontuacoes(List<Pontuacao> pontuacoes) {
		this.pontuacoes = pontuacoes;
	}
}
