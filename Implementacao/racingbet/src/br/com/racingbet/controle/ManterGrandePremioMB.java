package br.com.racingbet.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.GrandePremio;

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

	public ManterGrandePremioMB() {
		grandePremio = new GrandePremio();
	}
	
	@PostConstruct
	public void init() {
		grandePremio = new GrandePremio();
		grandesPremios = new ArrayList<GrandePremio>();
	}
	
	public String salvar() {
		System.out.println("Estou no salvar");
		return "manterGrandePremio";
	}

}
