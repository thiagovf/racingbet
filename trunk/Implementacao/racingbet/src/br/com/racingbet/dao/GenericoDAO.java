package br.com.racingbet.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Example;

@Stateless
public class GenericoDAO<T> implements DAO<T> {
        
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
    private EntityManager entityManager;

    public GenericoDAO() {
    }

    @SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @SuppressWarnings("unchecked")
	public List<T> recuperarTodos() {
        return getEntityManager().createQuery(
                "from " + getEntityClass().getName()).getResultList();
    }

    public T recuperar(Long id) {
    	
    	System.out.println("tentando recuperar no generico DAO ->");
        
        return getEntityManager().find(getEntityClass(), id);
    }
    
    public T recuperar(String id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    public void remover(Long id) {
    	System.out.println("tentando remover no generico DAO ->");
        getEntityManager().remove(getEntityManager().getReference(getEntityClass(), id));
        
    }

    public void incluir(T instance) {    
    	System.out.println("tentando Incluir no generico DAO ->");
    	getEntityManager().persist(instance);
    }

    public void alterar(T instance) {
        getEntityManager().merge(instance);
    }

    public List<T> recuperarPorObj(T entity) {
        Example example = Example.create(entity);
        example.excludeZeroes()
                .excludeNone();
        Session session = (Session) getEntityManager().getDelegate();

        List<T> list = session.createCriteria(entity.getClass())
                .add(example)
                .list();

        return list;
    }
    
	@SuppressWarnings("unchecked")
	public String add_and(String clausula_where) {

		if ((clausula_where != null) && (!clausula_where.equals(""))) {
			clausula_where = clausula_where + " and ";
		} else {
			if (clausula_where == null)
				clausula_where = "";
		}

		return clausula_where;
	}

	@SuppressWarnings("unchecked")
	public String add_or(String clausula_where) {

		if ((clausula_where != null) && (!clausula_where.equals(""))) {
			clausula_where = clausula_where + " or ";
		} else {
			if (clausula_where == null)
				clausula_where = "";
		}

		return clausula_where;
	}

	@SuppressWarnings("unchecked")
	public List<T> recuperarTodos(String clausula_where) {
		
		String statment = "from " + getEntityClass().getName();
		if (clausula_where != null)
			statment += " where " + clausula_where;
		
		return getEntityManager().createQuery(statment).getResultList();
	}
	
	public void descarregar() {
		getEntityManager().flush();
	}
    
}