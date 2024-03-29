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
import br.com.racingbet.entidade.GrandePremio;
import br.com.racingbet.servico.CategoriaServico;
import br.com.racingbet.servico.GrandePremioServico;
import br.com.racingbet.util.ConversacaoUtil;

@Named("manterGrandePremioMB")
@SessionScoped
public class ManterGrandePremioMB implements Serializable {

	@Inject
	private Conversation conversacao;

	@Inject
	private GrandePremio grandePremio;
	
	@Inject
	private ManterCategoriaMB manterCategoriaMB;
	
	/* -------- Atributos necessarios para o ListBox de Categoria -------*/
    private List<SelectItem> listaCategorias;
    private String codigoCategoriaSelecionada;
    @Inject
    private CategoriaServico categoriaServico;
    private Categoria categoriaSelecionada;
 /* -------- Fim dos Atributos necessarios para o ListBox de Categoria -------*/

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

	private List<GrandePremio> grandesPremios;

	private Boolean temGrandesPremios;

	private Long idGrandePremio;
	
	@Inject
	private GrandePremioServico grandePremioServico;

	public Conversation getConversacao() {
		return conversacao;
	}

	public void setConversacao(Conversation conversacao) {
		this.conversacao = conversacao;
	}

	public GrandePremio getGrandePremio() {
		return grandePremio;
	}

	public void setGrandePremio(GrandePremio grandePremio) {
		this.grandePremio = grandePremio;
	}

	public List<GrandePremio> getGrandesPremios() {
		return grandesPremios;
	}

	public void setGrandesPremios(List<GrandePremio> grandesPremios) {
		this.grandesPremios = grandesPremios;
	}

	public Boolean getTemGrandesPremios() {

		if ((grandesPremios == null) || (grandesPremios.size() == 0))
			temGrandesPremios = false;
		else
			temGrandesPremios = true;

		return temGrandesPremios;
	}

	public void setTemGrandesPremios(Boolean temGrandesPremios) {
		this.temGrandesPremios = temGrandesPremios;
	}

	public Long getIdGrandePremio() {
		return idGrandePremio;
	}

	public void setIdGrandePremio(Long idGrandePremio) {
		this.idGrandePremio = idGrandePremio;
	}

	public GrandePremioServico getGrandePremioServico() {
		return grandePremioServico;
	}

	public void setGrandePremioServico(GrandePremioServico grandePremioServico) {
		this.grandePremioServico = grandePremioServico;
	}

	public ManterGrandePremioMB() {
		grandePremio = new GrandePremio();
	}

	@PostConstruct
	public void init() {
		grandePremio = new GrandePremio();
		grandesPremios = grandePremioServico.recuperarTodos();
	}
	
	private void limparGrandePremio() {
		grandePremio = new GrandePremio();
	}
	
	public String iniciar() {
		
		limparGrandePremio();
		
		grandesPremios = grandePremioServico.recuperarTodos();
		System.out.println("passei no iniciar");
		
		return "manterGrandePremio";
	}

	public String salvar() throws ParseException {
		
		if (grandePremio.getId() == null) {
			categoriaSelecionada = categoriaServico.recuperarPorId(new Long(codigoCategoriaSelecionada));
			grandePremioServico.incluir(grandePremio, categoriaSelecionada);
		} else {
			categoriaSelecionada = categoriaServico.recuperarPorId(new Long(codigoCategoriaSelecionada));
			grandePremioServico.alterar(grandePremio);
		}
		
		grandesPremios = grandePremioServico.recuperarTodos();
		
		limparGrandePremio();
		
		ConversacaoUtil.terminar(conversacao);
		
		System.out.println("passei no salvar");
		
		return "manterGrandePremio";
	}

	public ManterCategoriaMB getManterCategoriaMB() {
		return manterCategoriaMB;
	}

	public void setManterCategoriaMB(ManterCategoriaMB manterCategoriaMB) {
		this.manterCategoriaMB = manterCategoriaMB;
	}
	public String editar() {

		ConversacaoUtil.iniciar(conversacao);

		grandePremio = grandePremioServico.recuperarPorId(getIdGrandePremio());
		grandesPremios = grandePremioServico.recuperarTodos();

		System.out.println("passei no editar");
		return "manterGrandePremio";
	}

	public String excluir() {

		GrandePremio gpremio = new GrandePremio();
		gpremio.setId(idGrandePremio);
		grandePremioServico.remover(gpremio);

		grandesPremios = grandePremioServico.recuperarTodos();
		limparGrandePremio();

		System.out.println("passei no excluir");

		return "manterGrandePremio";
	}
	
	public String pesquisar() {

		//categoriaSelecionada = categoriaServico.recuperarPorId(Long.valueOf(codigoCategoriaSelecionada));
		grandesPremios = grandePremioServico.recuperarGrandesPremios(categoriaSelecionada);
		limparGrandePremio();

		return "manterGrandePremio";
	}
}
