package daos.interfaces;

import java.io.Serializable;
import java.util.List;

import exceptions.DAOException;

public interface GenericDAO <T,ID extends Serializable> {
	
	void create(T entity) throws DAOException;
	
	int update(T entity) throws DAOException;
	
	T recover(T entity) throws DAOException;
	
	int delete(ID id) throws DAOException;
	
	List<T> findAll() throws DAOException;
}
