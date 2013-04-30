package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.racingbet.dao.PilotoDAO;
import br.com.racingbet.entidade.Piloto;

@Stateless
@LocalBean
public class PilotoServico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PilotoDAO pilotoDAO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(Piloto piloto) {
		
		pilotoDAO.incluir(piloto);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(Piloto piloto) {
		pilotoDAO.alterar(piloto);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Piloto piloto) {
		pilotoDAO.remover(piloto.getId());
	}
	
	public List<Piloto> recuperarTodos() {
		return pilotoDAO.recuperarTodos();
	}
	
	public Piloto recuperarPorId(Long id) {
		return pilotoDAO.recuperar(id);
	}

}
