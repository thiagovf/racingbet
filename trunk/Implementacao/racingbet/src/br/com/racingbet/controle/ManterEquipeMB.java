package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.Equipe;
import br.com.racingbet.entidade.GrandePremio;
import br.com.racingbet.servico.EquipeServico;
import br.com.racingbet.util.ConversacaoUtil;



@Named("manterEquipeMB")
@SessionScoped
public class ManterEquipeMB implements Serializable {
	
	@Inject
	private Conversation conversacao;
	
	@Inject
	private Equipe equipe;
	
	private List<Equipe> equipes;
	
	private Boolean temEquipes;

	private Long idEquipe;
	
	@Inject
	private EquipeServico equipeServico;
	
	@Inject
	private ManterCategoriaMB manterCategoriaMB;

	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}
	
	public Boolean getTemEquipes() {
		if ((equipes == null) || (equipes.size() == 0))
			temEquipes = false;
		else
			temEquipes = true;
		return temEquipes;
	}

	public void setTemEquipes(Boolean temEquipes) {
		this.temEquipes = temEquipes;
	}

	public Long getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(Long idEquipe) {
		this.idEquipe = idEquipe;
	}

	public EquipeServico getEquipeServico() {
		return equipeServico;
	}

	public void setEquipeServico(EquipeServico equipeServico) {
		this.equipeServico = equipeServico;
	}
	
	public ManterEquipeMB() {
		equipe = new Equipe();
	}
	
	@PostConstruct
	public void init() {
		equipe = new Equipe();
		equipes = equipeServico.recuperarTodos();
	}
	
	private void limparEquipe() {
		equipe = new Equipe();
	}
	
	public String iniciar() {
		
		limparEquipe();
		
		equipes = equipeServico.recuperarEquipes(manterCategoriaMB.getCategoria());
		/*System.out.println("passei no iniciar");*/
		
		return "manterEquipe";
	}

	public String salvar() throws ParseException {
		
		if (equipe.getId() == null) {
			equipeServico.incluir(manterCategoriaMB.getCategoria(), equipe);
		} else {
			equipeServico.alterar(equipe);
		}
		
		equipes = equipeServico.recuperarEquipes(manterCategoriaMB.getCategoria());
		
		limparEquipe();
		
		ConversacaoUtil.terminar(conversacao);
		
		/*System.out.println("passei no salvar");*/
		
		return "manterEquipe";
	}

	public ManterCategoriaMB getManterCategoriaMB() {
		return manterCategoriaMB;
	}

	public void setManterCategoriaMB(ManterCategoriaMB manterCategoriaMB) {
		this.manterCategoriaMB = manterCategoriaMB;
	}

	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

		equipe = equipeServico.recuperarPorId(getIdEquipe());
		equipes = equipeServico.recuperarEquipes(manterCategoriaMB.getCategoria());

		
		return "manterEquipe";
	}

	public String excluir() {

		Equipe eqp = new Equipe();
		eqp.setId(idEquipe);
		equipeServico.remover(eqp);

		equipes = equipeServico.recuperarEquipes(manterCategoriaMB.getCategoria());
		limparEquipe();

		

		return "manterEquipe";
	}

}
