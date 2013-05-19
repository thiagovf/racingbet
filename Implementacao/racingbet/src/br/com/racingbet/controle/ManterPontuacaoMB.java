package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Pontuacao;
import br.com.racingbet.servico.PontuacaoServico;
import br.com.racingbet.util.ConversacaoUtil;



@Named("manterPontuacaoMB")
@SessionScoped
public class ManterPontuacaoMB implements Serializable {
	
	@Inject
	private Conversation conversacao;
	
	@Inject
	private Pontuacao pontuacao;
	
	private List<Pontuacao> pontuacoes;
	
	private Boolean temPontuacoes;

	private Long idPontuacao;
	
	@Inject
	private PontuacaoServico pontuacaoServico;

	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}

	public Pontuacao getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Pontuacao pontuacao) {
		this.pontuacao = pontuacao;
	}

	public List<Pontuacao> getPontuacoes() {
		return pontuacoes;
	}

	public void setPontuacoes(List<Pontuacao> pontuacoes) {
		this.pontuacoes = pontuacoes;
	}
	
	public Boolean getTemPontuacoes() {
		if ((pontuacoes == null) || (pontuacoes.size() == 0))
			temPontuacoes = false;
		else
			temPontuacoes = true;
		return temPontuacoes;
	}

	public void setTemPontuacoes(Boolean temPontuacoes) {
		this.temPontuacoes = temPontuacoes;
	}

	public Long getIdPontuacao() {
		return idPontuacao;
	}

	public void setIdPontuacao(Long idPontuacao) {
		this.idPontuacao = idPontuacao;
	}

	public PontuacaoServico getPontuacaoServico() {
		return pontuacaoServico;
	}

	public void setPontuacaoServico(PontuacaoServico pontuacaoServico) {
		this.pontuacaoServico = pontuacaoServico;
	}
	
	public ManterPontuacaoMB() {
		pontuacao = new Pontuacao();
	}
	
	@PostConstruct
	public void init() {
		pontuacao = new Pontuacao();
		pontuacoes = pontuacaoServico.recuperarTodos();
	}
	
	private void limparPontuacao() {
		pontuacao = new Pontuacao();
	}
	
	public String iniciar() {
		
		limparPontuacao();
		
		pontuacoes = pontuacaoServico.recuperarTodos();
		/*System.out.println("passei no iniciar");*/
		
		return "manterPontuacao";
	}

	public String salvar() throws ParseException {
		
		if (pontuacao.getId() == null) {
			pontuacaoServico.incluir(pontuacao);
		} else {
			pontuacaoServico.alterar(pontuacao);
		}
		
		pontuacoes = pontuacaoServico.recuperarTodos();
		
		limparPontuacao();
		
		ConversacaoUtil.terminar(conversacao);
		
		/*System.out.println("passei no salvar");*/
		
		return "manterPontuacao";
	}

	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

		pontuacao = pontuacaoServico.recuperarPorId(getIdPontuacao());
		pontuacoes = pontuacaoServico.recuperarTodos();

		
		return "manterPontuacao";
	}

	public String excluir() {

		Pontuacao u = new Pontuacao();
		u.setId(idPontuacao);
		pontuacaoServico.remover(u);

		pontuacoes = pontuacaoServico.recuperarTodos();
		limparPontuacao();

		

		return "manterPontuacao";
	}
	
	public String gerarDadosTeste()
	{
		pontuacaoServico.gerarDadosTeste();
		
		return "manterPontuacao";
	}
	
	public String gerarPontuacao()
	{
		pontuacaoServico.gerarPontuacao();
		
		return "manterPontuacao";
	}

}
