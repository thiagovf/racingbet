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
import br.com.racingbet.servico.CategoriaServico;
import br.com.racingbet.util.ConversacaoUtil;
//import br.com.racingbet.entidade.Categoria;

@Named("manterCategoriaMB")
@SessionScoped
public class ManterCategoriaMB implements Serializable {

	@Inject
	private Conversation conversacao;

	@Inject
	private Categoria categoria;

	@Inject
	private CategoriaServico categoriaServico;
	
	private List<Categoria> categorias;

	private Boolean temCategoria;
	
	public Categoria getCategoria(){
		return this.categoria;
	}
	
	public void setCategoria(Categoria categoria){
		this.categoria = categoria;
	}
	
	public CategoriaServico getCategoriaSevico(){
		return this.categoriaServico ;
	}
	
	public void  setCategoriaServico(CategoriaServico categoriaServico){
		this.categoriaServico = categoriaServico;
	}
	
	
	public Conversation getConversacao(){
		return this.conversacao;
	}
	
	public void setConversacao(Conversation conversacao){
		this.conversacao= conversacao;
	}

	public Boolean getTemCatgoria() {

		Boolean temCategoria;
		if ((categoria == null) || (categoria.size() == 0))
			temCategoria = false;
		else
			temCategoria = true;

		return temCategoria;
	}

	

	public ManterCategoriaMB() {
		//categoria = new Categoria();
	}

	@PostConstruct
	public void init() {
		categoria = new Categoria();
		categorias = categoriaServico.recuperarTodos();
	}

	private void limparCategoria() {
		categoria = new Categoria();
	}

	public String iniciar() {

		limparCategoria();

		//categoria = (Categoria) CategoriaServico.recuperarTodos();
		System.out.println("passei no iniciar");

		return "manterCategoria";
	}

	public String salvar() throws ParseException {

		if (categoria.getId() == null) {
			categoriaServico.incluir(categoria);
		} else {
			categoriaServico.alterar(categoria);
		}

		//categorias = CategoriaServico.recuperarTodos();

		limparCategoria();
		this.categorias = categoriaServico.recuperarTodos();
		ConversacaoUtil.terminar(conversacao);

		return "manterCategoria";
	}

	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

	//	categoria = CategoriaServico.recuperarPorId(getIdCategoria());
		//categoria = CategoriaServico.recuperarTodos();

		System.out.println("passei no editar");
		return "manterCategoria";
	}

	public String excluir() {

		Categoria categoria = new Categoria();
	//	categoria.setId(idcategoria);
	//	CategoriaServico.remover(categoria);

		//categoria = CategoriaServico.recuperarTodos();
		limparCategoria();

		System.out.println("passei no excluir");

		return "manterCategoria";
	}

	public List<Categoria> getCategorias() {
		
		categoriaServico.recuperarTodos();
		
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Boolean getTemCategoria() {
		return temCategoria;
	}

	public void setTemCategoria(Boolean temCategoria) {
		this.temCategoria = temCategoria;
	}

}
