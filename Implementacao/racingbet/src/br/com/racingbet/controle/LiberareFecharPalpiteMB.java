
public class LiberareFecharPalpite {

}

package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.LiberareFecharPalpite;
import br.com.racingbet.servico.LiberareFecharPalpiteServico;
import br.com.racingbet.util.ConversacaoUtil;
//import br.com.racingbet.entidade.Categoria;

@Named("LiberareFecharPalpiteMB")
@SessionScoped
public class LiberareFecharPalpiteMB implements Serializable {

	@Inject
	private Conversation conversacao;

	@Inject
	private LiberareFecharPalpite liberareFecharPalpite;

	private List<LiberareFecharPalpite> liberareFecharPalpite;

//	@Inject
//	private Categoria categoria;
//
//	private List<Categoria> categorias;

	private Boolean liberareFecharPalpite;

	private Long idliberareFecharPalpite;
=======
//	private Boolean categotia;
//
//	private Long idcategoria;
>>>>>>> .r79

	@Inject
	private LiberareFecharPalpiteServico liberareFecharPalpiteServico;

	private Boolean temLiberareFecharPalpite;

	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}

	public Boolean getTemLiberareFecharPalpit() {

		Boolean temLiberareFecharPalpite;
		if ((liberareFecharPalpit == null) || (liberareFecharPalpite.size() == 0))
			temLiberareFecharPalpite = false;
		else
			temLiberareFecharPalpite = true;

		return temLiberareFecharPalpite;
	}

	public void setTemLiberareFecharPalpite(Boolean temLiberareFecharPalpite) {
		this.temLiberareFecharPalpite = temLiberareFecharPalpite;
	}

	public Long getIdLiberareFecharPalpite() {
		return idliberareFecharPalpite;
	}

	public void setIdLiberareFecharPalpite(Long idLiberareFecharPalpite) {
		this.idLiberareFecharPalpite = idLiberareFecharPalpite;
	}

	public LiberareFecharPalpiteServico getLiberareFecharPalpiteServico() {
		return LiberareFecharPalpiteServico;
	}

	public void setLiberareFecharPalpiteServico(LiberareFecharPalpiteServico LiberareFecharPalpiteServico) {
		this.LiberareFecharPalpiteServico = LiberareFecharPalpiteServico;
	}

	publicLiberareFecharPalpiteMBMB() {
		liberareFecharPalpite= new LiberareFecharPalpite();
	}

	@PostConstruct
	public void init() {
		LiberareFecharPalpite = new LiberareFecharPalpite();
		liberareFecharPalpite = LiberareFecharPalpiteServico.recuperarTodos();
	}

	private void limparLiberareFecharPalpite() {
		LiberareFecharPalpitea = new LiberareFecharPalpite();
	}

	public String iniciar() {

		limparLiberareFecharPalpite();

		liberareFecharPalpite = (LiberareFecharPalpite) LiberareFecharPalpiteServico.recuperarTodos();
		System.out.println("passei no iniciar");

		return "LiberareFecharPalpite";
	}

	public String salvar() throws ParseException {

		if (LiberareFecharPalpite.getId() == null) {
			LiberareFecharPalpiteServico.incluir(liberareFecharPalpite);
		} else {
			LiberareFecharPalpiteServico.alterar(liberareFecharPalpite);
		}

		liberareFecharPalpite = LiberareFecharPalpiteServico.recuperarTodos();

		limparLiberareFecharPalpite();

		ConversacaoUtil.terminar(conversacao);

		System.out.println("passei no salvar");

		return "LiberareFecharPalpite";
	}

	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

		liberareFecharPalpite = LiberareFecharPalpiteServico.recuperarPorId(getIdLiberareFecharPalpite());
		liberareFecharPalpite = LiberareFecharPalpiteServico.recuperarTodos();

		System.out.println("passei no editar");
		return "manterLiberareFecharPalpite";
	}

	public String excluir() {

		LiberareFecharPalpite liberareFecharPalpite = new LiberareFecharPalpite();
		LiberareFecharPalpite.setId(idliberareFecharPalpite);
		LiberareFecharPalpiteServico.remover(liberareFecharPalpite);

		liberareFecharPalpite = LiberareFecharPalpiteServico.recuperarTodos();
		limparLiberareFecharPalpite();

		System.out.println("passei no excluir");

		return "LiberareFecharPalpite";
	}

}
