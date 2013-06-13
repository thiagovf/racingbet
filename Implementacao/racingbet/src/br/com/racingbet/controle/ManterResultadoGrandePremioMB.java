package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.Equipe;
import br.com.racingbet.entidade.GrandePremio;
import br.com.racingbet.entidade.Piloto;
import br.com.racingbet.entidade.ResultadoGrandePremio;
import br.com.racingbet.servico.CategoriaServico;
import br.com.racingbet.servico.EquipeServico;
import br.com.racingbet.servico.GrandePremioServico;
import br.com.racingbet.servico.PilotoServico;
import br.com.racingbet.servico.ResultadoGrandePremioServico;
import br.com.racingbet.util.ConversacaoUtil;

@Named("manterResultadoGrandePremioMB")
@SessionScoped
public class ManterResultadoGrandePremioMB implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Conversation conversacao;
	
	@Inject
	private ResultadoGrandePremio resultadoGrandePremio;
	
	@Inject
	private ResultadoGrandePremioServico resultadoGrandePremioServico;

	@Inject
	private CategoriaServico categoriaServico;
	
	@Inject
	private GrandePremioServico grandePremioServico;
	
	@Inject
	private EquipeServico equipeServico;
	
	@Inject
	private PilotoServico pilotoServico;

	private Long categoriaId;
	
	private Long idGrandePremio;
	
	private Long idPilotoPole;
	
	private Long idPilotoPrimeiro;
	
	private List<Piloto> pilotos;
	
	private List<GrandePremio> grandesPremios;
	
	private List<ResultadoGrandePremio> resultadosGrandesPremios;
	
	private Boolean temResultadosGrandesPremios;
	
	private Long idResultadoGrandePremio;

	public ManterResultadoGrandePremioMB() {
		resultadoGrandePremio = new ResultadoGrandePremio();
	}

	@PostConstruct
	public void init() {
		resultadoGrandePremio = new ResultadoGrandePremio();
		resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
	}

	public String selecionarCategoria() {
		preencheSelectBox();

		return "manterResultadoGrandePremio";
	}
	
	private void preencheSelectBox(){
		if (categoriaId != null) {
			String clausula_where = "id_categoria=" + categoriaId;
			grandesPremios = grandePremioServico.recuperarTodos(clausula_where);
			List<Equipe> equipes = equipeServico.recuperarTodos(clausula_where);
			pilotos = new ArrayList<Piloto>();
			for(Equipe equipe : equipes) {
				pilotos.addAll(pilotoServico.recuperarPilotos(equipe));
			}
		} else {
			limparResultadoGrandePremio();
		}
	}

	private void limparResultadoGrandePremio() {
		resultadoGrandePremio = new ResultadoGrandePremio();
		categoriaId = null;
		idGrandePremio = null;
		idPilotoPole = null;
		idPilotoPrimeiro = null;
		grandesPremios = null;
		pilotos = null;
	}
	
	public String iniciar() {
		limparResultadoGrandePremio();
		resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
		return "manterResultadoGrandePremio";
	}
	
	public String salvar() throws ParseException {
		String clausulaWhere = " idGrandePremio = " + idGrandePremio;
		List<ResultadoGrandePremio> resultadosTemp = resultadoGrandePremioServico.recuperarTodos(clausulaWhere);
		for(ResultadoGrandePremio rgp : resultadosTemp) {
			resultadoGrandePremio.setId(rgp.getId());
		}
		resultadoGrandePremio.setGrandePremio(grandePremioServico.recuperarPorId(idGrandePremio));
		resultadoGrandePremio.setRepostaPerguntaPole(pilotoServico.recuperarPorId(idPilotoPole));
		resultadoGrandePremio.setRepostaPerguntaPrimeiro(pilotoServico.recuperarPorId(idPilotoPrimeiro));
		
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
		categoriaId = resultadoGrandePremio.getGrandePremio().getCategoria().getId();
		preencheSelectBox();
		idGrandePremio = resultadoGrandePremio.getGrandePremio().getId();
		idPilotoPole = resultadoGrandePremio.getRepostaPerguntaPole().getId();
		idPilotoPrimeiro = resultadoGrandePremio.getRepostaPerguntaPrimeiro().getId();
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
	
	public String cancelar() {
		resultadoGrandePremio.setId(null);
		limparResultadoGrandePremio();
		return "manterResultadoGrandePremio";
	}
	
	public String pesquisar() {	
		if(categoriaId != null) {
			if (idGrandePremio == null && idPilotoPrimeiro == null && idPilotoPole == null){			
				resultadosGrandesPremios = resultadoGrandePremioServico.recuperaPorCategoria(categoriaId);
			} else {
				String clausulaWhere = "";
				List<String> auxClausulaWhere = new ArrayList<String>();
				if (idGrandePremio != null) {
					auxClausulaWhere.add(" idGrandePremio = " + idGrandePremio);
				}
				if (idPilotoPrimeiro != null) {
					auxClausulaWhere.add(" idPilotoPerguntaPrimeiro = " + idPilotoPrimeiro);
				}
				if (idPilotoPole != null) {					
					auxClausulaWhere.add(" idPilotoPerguntaPole = " + idPilotoPole);
				}
				for (int i=0; i < auxClausulaWhere.size(); i++) {
					if (!"".equals(clausulaWhere) && auxClausulaWhere.size() > i+1){
						clausulaWhere = clausulaWhere + " AND ";
					}
					clausulaWhere = clausulaWhere + auxClausulaWhere.get(i);
				}
				resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos(clausulaWhere);
			}
		} else {
			resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
		}
		limparResultadoGrandePremio();

		return "manterResultadoGrandePremio";
	}
	
	public void fazNada() {
		System.out.println("Coisas irão acontecer se vc alterar esse código!");
	}


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

	public List<GrandePremio> getGrandesPremios() {
		return grandesPremios;
	}

	public void setGrandesPremios(List<GrandePremio> grandesPremios) {
		this.grandesPremios = grandesPremios;
	}

	public List<Categoria> getCategorias() {
		return categoriaServico.recuperarTodos();
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public List<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	public Long getIdPilotoPole() {
		return idPilotoPole;
	}

	public void setIdPilotoPole(Long idPilotoPole) {
		this.idPilotoPole = idPilotoPole;
	}

	public Long getIdPilotoPrimeiro() {
		return idPilotoPrimeiro;
	}

	public void setIdPilotoPrimeiro(Long idPilotoPrimeiro) {
		this.idPilotoPrimeiro = idPilotoPrimeiro;
	}

	public Long getIdGrandePremio() {
		return idGrandePremio;
	}

	public void setIdGrandePremio(Long idGrandePremio) {
		this.idGrandePremio = idGrandePremio;
	}

	
	
}
