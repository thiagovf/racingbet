package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.racingbet.dao.PalpiteDAO;
import br.com.racingbet.entidade.GrandePremio;
import br.com.racingbet.entidade.Palpite;
import br.com.racingbet.entidade.Usuario;

@Stateless
@LocalBean
public class PalpiteServico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private PalpiteDAO dao;
	
	public List<Palpite> recuperarPorUsuario(Usuario usuario){
		return dao.recuperarTodos("idUsuario =" + usuario.getId());
	}
	
	public List<Palpite> recuperarTodos(String where){
		return dao.recuperarTodos(where);
	}
	
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
