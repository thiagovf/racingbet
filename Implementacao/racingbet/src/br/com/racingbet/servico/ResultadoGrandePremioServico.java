package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.racingbet.dao.GrandePremioDAO;
import br.com.racingbet.dao.ResultadoGrandePremioDAO;
import br.com.racingbet.entidade.GrandePremio;
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
	
	@Inject
	private GrandePremioDAO grandePremioDAO;
	
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
	public List<ResultadoGrandePremio> recuperarTodos(String clausulaWhere) {
		return resultadoGrandePremioDAO.recuperarTodos(clausulaWhere);
	}
	
	public ResultadoGrandePremio recuperarPorId(Long id) {
		return resultadoGrandePremioDAO.recuperar(id);
	}
	
	public List<ResultadoGrandePremio> recuperaPorCategoria(Long categoriaId) {
		List<ResultadoGrandePremio> resultadosGrandesPremios = new ArrayList<ResultadoGrandePremio>();
		String clausulaWhere = " id_categoria =" + categoriaId;
		List<GrandePremio> gpList = grandePremioDAO.recuperarTodos(clausulaWhere);
		
		String idGPList = "";
		for (int i = 0; i < gpList.size(); i++) {
			if(gpList.size() > i+1){
				idGPList = idGPList + gpList.get(i).getId() + ",";
			} else {
				idGPList = idGPList + gpList.get(i).getId();				
			}
		}
		if (!"".equals(idGPList)) {
			clausulaWhere = " idGrandePremio in ( " + idGPList +")";
			resultadosGrandesPremios = resultadoGrandePremioDAO.recuperarTodos(clausulaWhere);
		} else {
			resultadosGrandesPremios = new ArrayList<ResultadoGrandePremio>();
		}
		return resultadosGrandesPremios;
	}

}
