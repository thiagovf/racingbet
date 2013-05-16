package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.racingbet.dao.CategoriaDAO;
import br.com.racingbet.entidade.Categoria;

@Stateless
@LocalBean
public class CategoriaServico implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(Categoria categoria) {
		
		categoriaDAO.incluir(categoria);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(Categoria categoria) {
		categoriaDAO.alterar(categoria);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Categoria categoria) {
		categoriaDAO.remover(categoria.getId());
	}
	
//	public static List<Categoria> recuperarTodos() {
//		return categoriaDAO.recuperarTodos();
//	}
	
	public Categoria recuperarPorId(Long id) {
		return categoriaDAO.recuperar(id);
	}

}