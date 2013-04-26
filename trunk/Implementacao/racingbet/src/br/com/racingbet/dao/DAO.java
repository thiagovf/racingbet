package br.com.racingbet.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T> extends Serializable {

    void incluir(T instance);

    void alterar(T instance);
	
    void remover(Long id);

    T recuperar(Long id);
    
    T recuperar(String id);
    
    List<T> recuperarTodos();
    
    List<T> recuperarPorObj(T entity);
    
    void descarregar();
}

