package br.com.racingbet.dao;

import javax.persistence.TypedQuery;

import br.com.racingbet.entidade.Usuario;

public class UsuarioDAO extends GenericoDAO<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Usuario recuperarPorEmail(Usuario usuario) {
		
		TypedQuery<Usuario> query = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
		
		query = query.setParameter("email", usuario.getEmail());
		
		return query.getSingleResult();
	}
}
