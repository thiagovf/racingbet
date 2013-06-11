package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;


import br.com.racingbet.dao.CategoriaDAO;
import br.com.racingbet.dao.EquipeDAO;
import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.Equipe;


@Stateless
@LocalBean
public class EquipeServico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EquipeDAO equipeDAO;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(Categoria categoria, Equipe equipe) {
		Categoria categoriaDoContexto = categoriaDAO.recuperar(categoria.getId());
		
		equipe.setCategoria(categoriaDoContexto);
		equipeDAO.incluir(equipe);
		categoriaDoContexto.getEquipes().add(equipe);
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(Equipe equipe) {
		equipeDAO.alterar(equipe);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Equipe equipe) {
		equipeDAO.remover(equipe.getId());
	}
	
	public List<Equipe> recuperarTodos() {
		return equipeDAO.recuperarTodos();
	}
	
	public Equipe recuperarPorId(Long id) {
		return equipeDAO.recuperar(id);
	}
	
	public List<Equipe> recuperarEquipes(Categoria categoria) {
		
		Categoria categoriaDoContexto = categoriaDAO.recuperar(categoria.getId());
		
		List<Equipe> equipes = categoriaDoContexto.getEquipes();
		
		System.out.println("recperando as Equipes");
		
		if ((equipes == null) || (equipes.size() == 0)) {
			return null;
		} else {
			return equipes;
		}
	}
	public List<Equipe> recuperarTodos(String clausula_where) {
		return equipeDAO.recuperarTodos(clausula_where);
	}
	

}
