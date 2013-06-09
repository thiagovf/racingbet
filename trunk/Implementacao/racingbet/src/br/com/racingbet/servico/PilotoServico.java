package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.racingbet.dao.EquipeDAO;
import br.com.racingbet.dao.PilotoDAO;
import br.com.racingbet.entidade.Equipe;
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
	
	@Inject
	private EquipeDAO equipeDAO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(Equipe equipe,Piloto piloto) {
		Equipe equipeDoContexto = equipeDAO.recuperar(equipe.getId());
		
		piloto.setEquipe(equipeDoContexto);
		pilotoDAO.incluir(piloto);
		equipeDoContexto.getPilotos().add(piloto);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(Piloto piloto) {
		pilotoDAO.alterar(piloto);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Piloto piloto) {
		pilotoDAO.remover(piloto.getId());
	}
	
	public Piloto recuperarPorId(Long id) {
		return pilotoDAO.recuperar(id);
	}
	
	public List<Piloto> recuperarTodos() {
		return pilotoDAO.recuperarTodos();
	}
	
	public List<Piloto> recuperarPilotos(Equipe equipe) {
		
		Equipe equipeDoContexto = equipeDAO.recuperar(equipe.getId());
		
		List<Piloto> pilotos = equipeDoContexto.getPilotos();
		
		System.out.println("recperando as Equipes");
		
		if ((pilotos == null) || (pilotos.size() == 0)) {
			return null;
		} else {
			return pilotos;
		}
	}

}
