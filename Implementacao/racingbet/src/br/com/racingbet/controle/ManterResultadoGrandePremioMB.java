package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.ResultadoGrandePremio;
import br.com.racingbet.servico.CategoriaServico;
import br.com.racingbet.servico.ResultadoGrandePremioServico;
import br.com.racingbet.util.ConversacaoUtil;

@SuppressWarnings("serial")
@Named("manterResultadoGrandePremioMB")
@SessionScoped
public class ManterResultadoGrandePremioMB implements Serializable {


	@Inject
	private Conversation conversacao;
	
	@Inject
	private ResultadoGrandePremio resultadoGrandePremio;
	
	@Inject
	private CategoriaServico catServico;
	
	private static Map<String, String> categorias;
	
	public static Map<String, String> grandePremios;
	
	public static Map<String, String> pilotos;
	
	private String categoria = "";
	
	private String grandePremio = "";
	
	private String piloto1 = "";
	
	private String piloto2 = "";
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getGrandePremio() {
		return grandePremio;
	}
	
	public String getPiloto1() {
		return piloto1;
	}

	public void setPiloto1(String piloto1) {
		this.piloto1 = piloto1;
	}

	public String getPiloto2() {
		return piloto2;
	}

	public void setPiloto2(String piloto2) {
		this.piloto2 = piloto2;
	}

	public void setGrandePremio(String grandePremio) {
		this.grandePremio = grandePremio;
	}

	public Map<String, String> getCategoriasInMap() {
		Map<String, String> countries = new LinkedHashMap<String, String>();
		List<Categoria> lsCategorias = catServico.recuperarTodos();
		for (Categoria categoria : lsCategorias) {
			countries.put(categoria.getDescricao(), categoria.getId().toString());
		}
		categorias = countries;
		return categorias;
	}
	
	public Map<String, String> getGrandePremiosInMap() {
		if(grandePremios == null) {
			grandePremios = new LinkedHashMap<String, String>();
		}
		return grandePremios;
	}
	
	public Map<String, String> getPilotosInMap() {
		if(pilotos == null) {
			pilotos = new LinkedHashMap<String, String>();
		}
		return pilotos;
	}
	
	
//	@SuppressWarnings("static-access")
//	public void setGrandePremios(Map<String, String> grandePremios) {
//		this.grandePremios = grandePremios;
//	}

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
		if ((resultadosGrandesPremios == null) || (resultadosGrandesPremios.size() == 0))
			temResultadosGrandesPremios = false;
		else
			temResultadosGrandesPremios = true;
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

	@PostConstruct
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
