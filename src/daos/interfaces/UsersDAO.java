package daos.interfaces;

import java.util.List;

import domain.User;
import exceptions.DAOException;

public interface UsersDAO extends GenericDAO<User,String>{

	int updateEmail(User user) throws DAOException;
	
	List<User> findAllOrderByDateCreation() throws DAOException;
}
