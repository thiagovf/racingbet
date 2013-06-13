package br.com.racingbet.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.GrandePremio;
import br.com.racingbet.servico.CategoriaServico;
import br.com.racingbet.servico.GrandePremioServico;

@SuppressWarnings("serial")
@Named("consultarGrandePremioMB")
@SessionScoped
public class ConsultarGrandePremioMB implements Serializable {
	

    private List<SelectItem> listaCategorias;
    private String codigoCategoriaSelecionada;
    @Inject
    private CategoriaServico categoriaServico;
    private Categoria categoriaSelecionada;    
    private List<Categoria> categorias;
    private List<GrandePremio> grandesPremios;
    private String errorMsg;
    private Long categoriaSelecionadaId;
    
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Long getCategoriaSelecionadaId() {
		return categoriaSelecionadaId;
	}

	public void setCategoriaSelecionadaId(Long categoriaSelecionadaId) {
		this.categoriaSelecionadaId = categoriaSelecionadaId;
	}

	

    private Long grandePremioSelecionadoId;
	public Long getGrandePremioSelecionadoId() {
		return grandePremioSelecionadoId;
	}

	public void setGrandePremioSelecionadoId(Long grandePremioSelecionadoId) {
		this.grandePremioSelecionadoId = grandePremioSelecionadoId;
	}

	public List<GrandePremio> getGrandesPremios() {
		return grandesPremios;
	}

	public void setGrandePremios(List<GrandePremio> grandePremios) {
		this.grandesPremios = grandePremios;
	}	


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
    
    public String atualizarGrandesPremios(){
		String clausula_where = "id_grandePremio=" + grandePremioSelecionadoId;
		grandesPremios = getGrandePremioServico().recuperarTodos(clausula_where);
	
		if(grandesPremios.isEmpty()){
			errorMsg = "Nao ha Grande Premio cadastrado para a categoria selecionada";
		}else{
			errorMsg = "";
		}
			
		return "consultarGrandePremio";
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
   

	@Inject
	private Conversation conversacao;

	@Inject
	private GrandePremioServico grandePremioServico;

	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;

	}

	@PostConstruct
	public void init() {
		grandesPremios = grandePremioServico.recuperarTodos();
	}

	public String inciar() {
		return "consultarGrandePremio";
	}

	public GrandePremioServico getGrandePremioServico() {
		return grandePremioServico;
	}

	public void setGrandePremioServico(GrandePremioServico grandePremioServico) {
		this.grandePremioServico = grandePremioServico;
	}

	public void setGrandesPremios(List<GrandePremio> grandesPremios) {
		this.grandesPremios = grandesPremios;
	}

}
