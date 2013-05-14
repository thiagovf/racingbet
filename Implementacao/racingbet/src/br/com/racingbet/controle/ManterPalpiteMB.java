package br.com.racingbet.controle;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Palpite;
import br.com.racingbet.servico.PalpiteServico;

@SuppressWarnings("serial")
@Named("manterPalpiteMB")
@SessionScoped
public class ManterPalpiteMB implements Serializable {

	@Inject
	private Conversation conversacao;

	@Inject
	private PalpiteServico palpiteServico;
	
	@Inject
	private Palpite palpite;
	

	public String salvar(){
		
		palpiteServico.salvar(palpite);
		
		return "manterPalpite";
	}
	
	public Conversation getConversacao() {
		return conversacao;
	}
	
	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
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

	public Palpite getPalpite() {
		return palpite;
	}

	public void setPalpite(Palpite palpite) {
		this.palpite = palpite;
	}

}
