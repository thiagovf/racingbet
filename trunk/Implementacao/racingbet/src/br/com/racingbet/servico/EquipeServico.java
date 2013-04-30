package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;


import br.com.racingbet.dao.EquipeDAO;
import br.com.racingbet.entidade.Equipe;


@Stateless
@LocalBean
public class EquipeServico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EquipeDAO equipeDAO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(Equipe equipe) {
		
		equipeDAO.incluir(equipe);
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

}
