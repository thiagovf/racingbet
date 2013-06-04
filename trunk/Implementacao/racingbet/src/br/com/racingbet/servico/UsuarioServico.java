package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.racingbet.dao.UsuarioDAO;
import br.com.racingbet.entidade.Usuario;
import br.com.racingbet.excecoes.LoginException;

@Stateless
@LocalBean
public class UsuarioServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(Usuario usuario) {

		usuarioDAO.incluir(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(Usuario usuario) {
		usuarioDAO.alterar(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Usuario usuario) {
		usuarioDAO.remover(usuario.getId());
	}

	public List<Usuario> recuperarTodos() {
		return usuarioDAO.recuperarTodos();
	}

	public Usuario recuperarPorId(Long id) {
		return usuarioDAO.recuperar(id);
	}

	public Usuario logar(Usuario usuario) throws LoginException {

		try {
			Usuario usu = usuarioDAO.recuperarPorEmail(usuario);
			if ((usu != null) && (usu.getSenha().equals(usuario.getSenha()))) {
				return usu;
			}
			return usu;
		} catch (Exception e) {
			throw new LoginException();
		}

	}

}
