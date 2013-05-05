package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.racingbet.dao.ResultadoGrandePremioDAO;
import br.com.racingbet.entidade.ResultadoGrandePremio;

@Stateless
@LocalBean
public class ResultadoGrandePremioServico implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ResultadoGrandePremioDAO resultadoGrandePremioDAO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(ResultadoGrandePremio resultadoGrandePremio) {
		
		resultadoGrandePremioDAO.incluir(resultadoGrandePremio);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(ResultadoGrandePremio resultadoGrandePremio) {
		resultadoGrandePremioDAO.alterar(resultadoGrandePremio);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(ResultadoGrandePremio resultadoGrandePremio) {
		resultadoGrandePremioDAO.remover(resultadoGrandePremio.getId());
	}
	
	public List<ResultadoGrandePremio> recuperarTodos() {
		return resultadoGrandePremioDAO.recuperarTodos();
	}
	
	public ResultadoGrandePremio recuperarPorId(Long id) {
		return resultadoGrandePremioDAO.recuperar(id);
	}

}
