package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.racingbet.dao.ConsultarGrandePremioDAO;
import br.com.racingbet.entidade.ConsultarGrandePremio;

@Stateless
@LocalBean
public class ConsultarGrandePremioServico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private ConsultarGrandePremioDAO dao;
	
	public List<ConsultarGrandePremio> recuperarTodos() {
		return dao.recuperarTodos();
	}

	public ConsultarGrandePremioDAO getDao() {
		return dao;
	}

	public void setDao(ConsultarGrandePremioDAO dao) {
		this.dao = dao;
	}
}