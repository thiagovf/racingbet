package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.ResultadoGrandePremio;
import br.com.racingbet.servico.ResultadoGrandePremioServico;
import br.com.racingbet.util.ConversacaoUtil;

@Named("manterResultadoGrandePremioMB")
@SessionScoped
public class ManterResultadoGrandePremioMB implements Serializable {


	@Inject
	private Conversation conversacao;
	
	@Inject
	private ResultadoGrandePremio resultadoGrandePremio;

	private List<ResultadoGrandePremio> resultadosGrandesPremios;
	
	private Boolean temResultadosGrandesPremios;
	
	private Long idResultadoGrandePremio;

	@Inject
	private ResultadoGrandePremioServico resultadoGrandePremioServico;
	
	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}

	public ResultadoGrandePremio getResultadoGrandePremio() {
		return resultadoGrandePremio;
	}

	public void setResultadoGrandePremio(ResultadoGrandePremio resultadoGrandePremio) {
		this.resultadoGrandePremio = resultadoGrandePremio;
	}

	public List<ResultadoGrandePremio> getResultadosGrandesPremios() {
		return resultadosGrandesPremios;
	}

	public void setResultadosGrandesPremios(
			List<ResultadoGrandePremio> resultadosGrandesPremios) {
		this.resultadosGrandesPremios = resultadosGrandesPremios;
	}

	public Boolean getTemResultadosGrandesPremios() {
		return temResultadosGrandesPremios;
	}

	public void setTemResultadosGrandesPremios(Boolean temResultadosGrandesPremios) {
		this.temResultadosGrandesPremios = temResultadosGrandesPremios;
	}

	public Long getIdResultadoGrandePremio() {
		return idResultadoGrandePremio;
	}

	public void setIdResultadoGrandePremio(Long idResultadoGrandePremio) {
		this.idResultadoGrandePremio = idResultadoGrandePremio;
	}

	public ResultadoGrandePremioServico getResultadoGrandePremioServico() {
		return resultadoGrandePremioServico;
	}

	public void setResultadoGrandePremioServico(
			ResultadoGrandePremioServico resultadoGrandePremioServico) {
		this.resultadoGrandePremioServico = resultadoGrandePremioServico;
	}

	public void init() {
		resultadoGrandePremio = new ResultadoGrandePremio();
		resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
	}

	private void limparResultadoGrandePremio() {
		resultadoGrandePremio = new ResultadoGrandePremio();
	}
	
	public String iniciar() {
		limparResultadoGrandePremio();
		resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
		return "manterResultadoGrandePremio";
	}
	
	public String salvar() throws ParseException { 
		if (resultadoGrandePremio.getId() == null) {
			resultadoGrandePremioServico.incluir(resultadoGrandePremio);
		} else {
			resultadoGrandePremioServico.alterar(resultadoGrandePremio);
		}
		
		resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
		
		limparResultadoGrandePremio();
		
		ConversacaoUtil.terminar(conversacao);
		
		return "manterResultadoGrandePremio";
	}
	
	public String editar() {
		ConversacaoUtil.iniciar(conversacao);
		
		resultadoGrandePremio = resultadoGrandePremioServico.recuperarPorId(getIdResultadoGrandePremio());
		resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
		
		return "manterResultadoGrandePremio";
	}
	
	public String excluir() {
		
		ResultadoGrandePremio resultadoGP = new ResultadoGrandePremio();
		resultadoGP.setId(idResultadoGrandePremio);
		resultadoGrandePremioServico.remover(resultadoGP);
		
		resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
		limparResultadoGrandePremio();
		
		return "manterResultadoGrandePremio";
		
	}

}
