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

import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.Equipe;
import br.com.racingbet.servico.CategoriaServico;
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
	
	/* -------- Atributos necessarios para o ListBox de Categoria -------*/
	private List<SelectItem> listaCategorias;
	private String codigoCategoriaSelecionada;
	@Inject
	private CategoriaServico categoriaServico;
	private Categoria categoriaSelecionada;
	/* -------- Fim dos Atributos necessarios para o ListBox de Categoria -------*/
	
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
		return "manterEquipe";
	}

	public String salvar() throws ParseException {
		
		//categoriaSelecionada = categoriaServico.recuperarPorId(Long.valueOf(codigoCategoriaSelecionada));
		
		if (equipe.getId() == null) {
			equipeServico.incluir(categoriaSelecionada, equipe);
		} else {
			equipeServico.alterar(equipe);
		}
		
		equipes = equipeServico.recuperarEquipes(categoriaSelecionada);
		
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
		equipes = equipeServico.recuperarEquipes(categoriaSelecionada);
		//limparEquipe();

		
		return "manterEquipe";
	}

	public String excluir() {

		Equipe eqp = new Equipe();
		eqp.setId(idEquipe);
		equipeServico.remover(eqp);

		//categoriaSelecionada = categoriaServico.recuperarPorId(Long.valueOf(codigoCategoriaSelecionada));
		equipes = equipeServico.recuperarEquipes(categoriaSelecionada);
		limparEquipe();

		

		return "manterEquipe";
	}
	
	public String pesquisar() {

		//categoriaSelecionada = categoriaServico.recuperarPorId(Long.valueOf(codigoCategoriaSelecionada));
		equipes = equipeServico.recuperarEquipes(categoriaSelecionada);
		limparEquipe();

		return "manterEquipe";
	}
	
	/* -------- Metodos necessarios para o ListBox de Categoria -------*/

	public List<SelectItem> getListaCategorias() {
		
		List<Categoria> categorias = categoriaServico.recuperarTodos();
		Categoria categ;
		
		listaCategorias = new ArrayList<SelectItem>();
		for (int i=0; i< categorias.size(); i++) {
			categ = (Categoria) categorias.get(i);
			listaCategorias.add(new SelectItem(categ.getId(), categ.getNome()));
		}
		
		if ((codigoCategoriaSelecionada == null) || (codigoCategoriaSelecionada.equals(""))) {
			if ((categorias != null) && (categorias.size() > 0)) {
				categoriaSelecionada = categorias.get(0);
				codigoCategoriaSelecionada = categoriaSelecionada.getId().toString();
			}
		}
		
		return listaCategorias;
	}
	
	public void recuperarCategoria() {
		categoriaSelecionada = categoriaServico.recuperarPorId(Long.valueOf(codigoCategoriaSelecionada));
		System.out.println("Categoria="+categoriaSelecionada.getNome());
	}

	public void setListaCategorias(List<SelectItem> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public String getCodigoCategoriaSelecionada() {
		return codigoCategoriaSelecionada;
	}

	public void setCodigoCategoriaSelecionada(String codigoCategoriaSelecionada) {
		this.codigoCategoriaSelecionada = codigoCategoriaSelecionada;
	}

	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}
	
	/* -------- Fim dos Metodos necessarios para o ListBox de Categoria -------*/

}
