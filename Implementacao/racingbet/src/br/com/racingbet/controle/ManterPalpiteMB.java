package br.com.racingbet.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.Equipe;
import br.com.racingbet.entidade.GrandePremio;
import br.com.racingbet.entidade.Palpite;
import br.com.racingbet.entidade.Piloto;
import br.com.racingbet.entidade.ResultadoGrandePremio;
import br.com.racingbet.entidade.Usuario;
import br.com.racingbet.servico.CategoriaServico;
import br.com.racingbet.servico.EquipeServico;
import br.com.racingbet.servico.GrandePremioServico;
import br.com.racingbet.servico.PalpiteServico;
import br.com.racingbet.servico.PilotoServico;
import br.com.racingbet.servico.ResultadoGrandePremioServico;
import br.com.racingbet.servico.UsuarioServico;
import br.com.racingbet.util.ConversacaoUtil;

@Named("manterPalpiteMB")
@SessionScoped
public class ManterPalpiteMB implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Conversation conversacao;
	
	@Inject
	private ResultadoGrandePremio resultadoGrandePremio;
	
	@Inject
	private Palpite palpite;
	
	@Inject
	private ResultadoGrandePremioServico resultadoGrandePremioServico;
	
	@Inject
	private PalpiteServico palpiteServico;
	
	@Inject
	private UsuarioServico usuarioServico;

	@Inject
	private CategoriaServico categoriaServico;
	
	@Inject
	private GrandePremioServico grandePremioServico;
	
	@Inject
	private EquipeServico equipeServico;
	
	@Inject
	private PilotoServico pilotoServico;
	
	@Inject
	private GerenciarUsuarioMB gerenciarUsuarioMB;

	private Long categoriaId;
	
	private Long idGrandePremio;
	
	private Long idPilotoPole;
	
	private Long idPilotoPrimeiro;
	
	private List<Piloto> pilotos;
	
	private List<GrandePremio> grandesPremios;
	
	private List<ResultadoGrandePremio> resultadosGrandesPremios;
	
	private Boolean temResultadosGrandesPremios;
	
	private Long idResultadoGrandePremio;

//	public ManterResultadoGrandePremioMB() {
//		resultadoGrandePremio = new ResultadoGrandePremio();
//	}

	@PostConstruct
	public void init() {
		resultadoGrandePremio = new ResultadoGrandePremio();
		resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
	}

	public String selecionarCategoria() {
		//ResultadoGrandePremio rgp = (ResultadoGrandePremio) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("country");
		System.out.println(categoriaId);
		preencheSelectBox();

		return "manterResultadoGrandePremio";
	}
	
	private void preencheSelectBox(){
		String clausula_where = "id_categoria=" + categoriaId;
		grandesPremios = grandePremioServico.recuperarTodos(clausula_where);
		List<Equipe> equipes = equipeServico.recuperarTodos(clausula_where);
		pilotos = new ArrayList<Piloto>();
		for(Equipe equipe : equipes) {
			pilotos.addAll(pilotoServico.recuperarPilotos(equipe));
		}
	}

	private void limparResultados() {
		resultadoGrandePremio = new ResultadoGrandePremio();
		categoriaId = null;
		idGrandePremio = null;
		idPilotoPole = null;
		idPilotoPrimeiro = null;
		grandesPremios = null;
		pilotos = null;
		palpite = null;
	}
	
	public String iniciar() {
		limparResultados();
		resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
		return "manterResultadoGrandePremio";
	}
	
	public String salvar() throws ParseException {
		
		if(palpite == null) palpite = new Palpite();
		
		getPalpite().setGrandePremio(grandePremioServico.recuperarPorId(idGrandePremio));
		getPalpite().setRepostaPerguntaPole(pilotoServico.recuperarPorId(idPilotoPole));
		getPalpite().setRepostaPerguntaPrimeiro(pilotoServico.recuperarPorId(idPilotoPrimeiro));
		getPalpite().setUsuario(usuarioServico.recuperarPorId(new Long(2)));
		//getPalpite().setUsuario(getGerenciarUsuarioMB().getUsuario());
		getPalpite().setData(new Date());
		
		// TODO comentar esse codigo jaja
		
//		if(getPalpite().getUsuario() == null){
//			getPalpite().setUsuario(usuarioServico.recuperarPorId(new Long(1)));
//		}
		
		if (getPalpite().getId() == null) {
			palpiteServico.incluir(getPalpite());
		} else {
			palpiteServico.alterar(getPalpite());
		}
		
		//resultadosGrandesPremios = resultadoGrandePremioServico.recuperarTodos();
		
		limparResultados(); 
		
		ConversacaoUtil.terminar(conversacao);
		
		return "consultarPalpite";
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
		limparResultados();
		
		return "manterResultadoGrandePremio";
		
	}	
	
	public String cancelar() {
		resultadoGrandePremio.setId(null);
		limparResultados();
		return "principal";
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

	public PalpiteServico getPalpiteServico() {
		return palpiteServico;
	}

	public void setPalpiteServico(PalpiteServico palpiteServico) {
		this.palpiteServico = palpiteServico;
	}

	public Palpite getPalpite() {
		return palpite;
	}

	public void setPalpite(Palpite palpite) {
		this.palpite = palpite;
	}

	public GerenciarUsuarioMB getGerenciarUsuarioMB() {
		return gerenciarUsuarioMB;
	}

	public void setGerenciarUsuarioMB(GerenciarUsuarioMB gerenciarUsuarioMB) {
		this.gerenciarUsuarioMB = gerenciarUsuarioMB;
	}
}
