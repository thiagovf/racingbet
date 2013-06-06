package br.com.racingbet.controle;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.GrandePremio;
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
	
	@Inject
	private Categoria categoria;
	
	@Inject
	private ManterCategoriaMB manterCategoriaMB;
	
	@Inject 
	private GrandePremio grandePremio;
	
	@Inject 
	private ManterGrandePremioMB manterGrandePremioMB;
	
	@Inject 
	private GerenciarUsuarioMB gerenciarUsuarioMB;
	

	public String salvar(){
		
		palpiteServico.salvar(palpite);
		 
		 FacesContext context = FacesContext.getCurrentInstance();
		 FacesMessage message = new FacesMessage("Palpite adicionado com sucesso");
		 context.addMessage("global", message);
		
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
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public ManterCategoriaMB getManterCategoriaMB() {
		return manterCategoriaMB;
	}

	public void setManterCategoriaMB(ManterCategoriaMB manterCategoriaMB) {
		this.manterCategoriaMB = manterCategoriaMB;
	}

	public GrandePremio getGrandePremio() {
		return grandePremio;
	}

	public void setGrandePremio(GrandePremio grandePremio) {
		this.grandePremio = grandePremio;
	}

	public ManterGrandePremioMB getManterGrandePremioMB() {
		return manterGrandePremioMB;
	}

	public void setManterGrandePremioMB(ManterGrandePremioMB manterGrandePremioMB) {
		this.manterGrandePremioMB = manterGrandePremioMB;
	}

	public GerenciarUsuarioMB getGerenciarUsuarioMB() {
		return gerenciarUsuarioMB;
	}

	public void setGerenciarUsuarioMB(GerenciarUsuarioMB gerenciarUsuarioMB) {
		this.gerenciarUsuarioMB = gerenciarUsuarioMB;
	}


}
