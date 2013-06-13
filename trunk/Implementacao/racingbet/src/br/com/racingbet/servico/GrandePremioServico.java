package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.racingbet.dao.CategoriaDAO;
import br.com.racingbet.dao.GrandePremioDAO;
import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.GrandePremio;

@Stateless
@LocalBean
public class GrandePremioServico implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private GrandePremioDAO grandePremioDAO;

	@Inject
	private CategoriaDAO categoriaDAO;

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(GrandePremio grandePremio, Categoria categoria) {
		Categoria categoriaDoContexto = categoriaDAO.recuperar(categoria.getId());
		
		grandePremio.setCategoria(categoriaDoContexto);
		grandePremioDAO.incluir(grandePremio);
		categoriaDoContexto.getGrandespremios().add(grandePremio);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(GrandePremio grandePremio) {
		grandePremioDAO.alterar(grandePremio);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(GrandePremio grandePremio) {
		grandePremioDAO.remover(grandePremio.getId());
	}
	
	public List<GrandePremio> recuperarTodos() {
		return grandePremioDAO.recuperarTodos();
	}
	
	public GrandePremio recuperarPorId(Long id) {
		return grandePremioDAO.recuperar(id);
	}
}