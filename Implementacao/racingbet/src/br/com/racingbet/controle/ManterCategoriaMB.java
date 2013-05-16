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

	private Boolean temCategoria;
	
	public Categoria getCategoria(){
		return this.categoria;
	}
	
	public void setCategoria(Categoria categoria){
		this.categoria = categoria;
	}
	
	public CategoriaServico getCategoriaSevico(){
		return this.categoria ;
	}
	
	public void CategoriaServico setCategoriaServico(CategoriaServico categoriaServico){
		this.categoriaServico = categoriaServico;
	}
	
	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}
	
	public Conversation getConversacao(){
		return this.conversacao
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
		categoria = CategoriaServico.recuperarTodos();
	}

	private void limparCategoria() {
		categoria = new Categoria();
	}

	public String iniciar() {

		limparCategoria();

		categoria = (Categoria) CategoriaServico.recuperarTodos();
		System.out.println("passei no iniciar");

		return "manterCategoria";
	}

	public String salvar() throws ParseException {

		if (categoria.getId() == null) {
			categoriaServico.incluir(categoria);
		} else {
			categoriaServico.alterar(categoria);
		}

		categoria = CategoriaServico.recuperarTodos();

		limparCategoria();

		ConversacaoUtil.terminar(conversacao);

		System.out.println("passei no salvar");

		return "manterCategoria";
	}

	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

		categoria = CategoriaServico.recuperarPorId(getIdCategoria());
		categoria = CategoriaServico.recuperarTodos();

		System.out.println("passei no editar");
		return "manterCategoria";
	}

	public String excluir() {

		Categoria categoria = new Categoria();
		categoria.setId(idcategoria);
		CategoriaServico.remover(categoria);

		categoria = CategoriaServico.recuperarTodos();
		limparCategoria();

		System.out.println("passei no excluir");

		return "manterCategoria";
	}

}
