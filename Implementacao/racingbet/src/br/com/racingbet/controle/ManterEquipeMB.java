package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

	private Long idEquipe;
	
	@Inject
	private EquipeServico equipeServico;

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
		
		equipes = equipeServico.recuperarTodos();
		System.out.println("passei no iniciar");
		
		return "manterEquipe";
	}

	public String salvar() throws ParseException {
		
		if (equipe.getId() == null) {
			equipeServico.incluir(equipe);
		} else {
			equipeServico.alterar(equipe);
		}
		
		equipes = equipeServico.recuperarTodos();
		
		limparEquipe();
		
		ConversacaoUtil.terminar(conversacao);
		
		System.out.println("passei no salvar");
		
		return "manterEquipe";
	}

	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

		equipe = equipeServico.recuperarPorId(getIdEquipe());
		equipes= equipeServico.recuperarTodos();

		System.out.println("passei no editar");
		return "manterEquipe";
	}

	public String excluir() {

		Equipe eqp = new Equipe();
		eqp.setId(idEquipe);
		equipeServico.remover(eqp);

		equipes = equipeServico.recuperarTodos();
		limparEquipe();

		System.out.println("passei no excluir");

		return "manterEquipe";
	}

}
