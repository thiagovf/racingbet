package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
		return dao.recuperarTodos("id_usuario =" + usuario.getId());
	}
	
	public Palpite recuperarPorId(Long idPalpite) {
		
		return dao.recuperar(idPalpite);
		
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(Palpite palpite) {
		
		dao.incluir(palpite);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(Palpite palpite) {
		dao.alterar(palpite);		
	}

	public void excluir(Palpite palpite) {
		
		dao.remover(palpite.getId());
		
	}


}
