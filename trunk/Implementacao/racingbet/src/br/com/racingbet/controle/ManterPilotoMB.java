package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Equipe;
import br.com.racingbet.entidade.Piloto;
import br.com.racingbet.servico.EquipeServico;
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
	
	@Inject
	private Equipe equipe;

	private List<Piloto> pilotos;
	
	private Boolean temPiloto;
	
	private Long idPiloto;
	
	/* -------- Atributos necessarios para o ListBox de Equipe -------*/
	private List<SelectItem> listaEquipes;
	private String codigoEquipeSelecionada;
	@Inject
	private EquipeServico equipeServico;
	private Equipe equipeSelecionada;
	/* -------- Fim dos Atributos necessarios para o ListBox de Equipe -------*/
	
	private ManterEquipeMB manterEquipeMB;
	
	public ManterEquipeMB getManterEquipeMB() {
		return manterEquipeMB;
	}

	public void setManterEquipeMB(ManterEquipeMB manterEquipeMB) {
		this.manterEquipeMB = manterEquipeMB;
	}

	@Inject
	private PilotoServico pilotoServico;
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
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
		if ((pilotos == null) || (pilotos.size() == 0))
			temPiloto = false;
		else
			temPiloto = true;
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
	
	private void limparPiloto() {
		piloto = new Piloto();
	}
	
	public String iniciar() {
		
		limparPiloto();
		
		
		return "manterPiloto";
	}

	public String salvar() throws ParseException {
		
		if (piloto.getId() == null) {
			pilotoServico.incluir(equipeSelecionada,piloto);
		} else {
			pilotoServico.alterar(piloto);
		}
		
		pilotos = pilotoServico.recuperarTodos();
		
		limparPiloto();
		
		ConversacaoUtil.terminar(conversacao);
		
//		System.out.println("passei no salvar");
		
		return "manterPiloto";
	}

	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

		piloto = pilotoServico.recuperarPorId(getIdPiloto());
		pilotos = pilotoServico.recuperarPilotos(equipeSelecionada);

//		System.out.println("passei no editar");
		return "manterPiloto";
	}

	public String excluir() {
		
		Piloto piloto = new Piloto();
		piloto.setId(idPiloto);
		pilotoServico.remover(piloto);

		pilotos = pilotoServico.recuperarPilotos(equipeSelecionada);
		limparPiloto();

		System.out.println("passei no excluir");

		return "manterPiloto";
	}
	
	public String pesquisar() {

		pilotos = pilotoServico.recuperarPilotos(equipeSelecionada);
		limparPiloto();

		return "manterPiloto";
	}
	
	/* -------- Metodos necessarios para o ListBox de Equipe -------*/

	public List<SelectItem> getListaEquipes() {
		
		List<Equipe> equipes = equipeServico.recuperarTodos();
		Equipe eqpe;
		
		listaEquipes = new ArrayList<SelectItem>();
		for (int i=0; i< equipes.size(); i++) {
			eqpe = (Equipe) equipes.get(i);
			listaEquipes.add(new SelectItem(eqpe.getId(), eqpe.getNome()));
		}
		
		if ((codigoEquipeSelecionada == null) || (codigoEquipeSelecionada.equals(""))) {
			if ((equipes != null) && (equipes.size() > 0)) {
				equipeSelecionada = equipes.get(0);
				codigoEquipeSelecionada = equipeSelecionada.getId().toString();
			}
		}
		
		return listaEquipes;
	}
	
	public void recuperarEquipe() {
		equipeSelecionada = equipeServico.recuperarPorId(Long.valueOf(codigoEquipeSelecionada));
		System.out.println("Equipe="+equipeSelecionada.getNome());
	}

	public void setListaEquipes(List<SelectItem> listaEquipes) {
		this.listaEquipes = listaEquipes;
	}

	public String getCodigoEquipeSelecionada() {
		return codigoEquipeSelecionada;
	}

	public void setCodigoEquipeSelecionada(String codigoEquipeSelecionada) {
		this.codigoEquipeSelecionada = codigoEquipeSelecionada;
	}

	public EquipeServico getEquipeServico() {
		return equipeServico;
	}

	public void setEquipeServico(EquipeServico equipeServico) {
		this.equipeServico = equipeServico;
	}

	public Equipe getEquipeSelecionada() {
		return equipeSelecionada;
	}

	public void setEquipeSelecionada(Equipe equipeSelecionada) {
		this.equipeSelecionada = equipeSelecionada;
	}
	
	/* -------- Fim dos Metodos necessarios para o ListBox de Equipe -------*/
	
}
