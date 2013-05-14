package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.racingbet.dao.PalpiteDAO;
import br.com.racingbet.entidade.Palpite;

@Stateless
@LocalBean
public class PalpiteServico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private PalpiteDAO dao;
	
	public List<Palpite> recuperarTodos() {
		return dao.recuperarTodos();
	}

	public PalpiteDAO getDao() {
		return dao;
	}

	public void setDao(PalpiteDAO dao) {
		this.dao = dao;
	}

	public void salvar(Palpite palpite) {
		dao.incluir(palpite);
	}
}
