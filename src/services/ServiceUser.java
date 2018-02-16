package services;

import daos.TypeUserDAO;
import daos.TransactionManager;
import domain.User;
import encrypt.EncryptMD5;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServiceUser {
	
	public void insertUser(User user) throws ServiceException {

	}
	
	public int modificarUser(User user) throws ServiceException {
		return 0;
	}

	public int deleteUser(User user) throws ServiceException {
		return 0;
	}

	public User recoverUser(User user) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		return user;
	}

	public User recoverUserComplete(User user) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		return user;
	}
	
	public int unlockedUser(User user) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		int rows = 0;
		return rows;
	}
	
	public int incrementFail(User user) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		int rows = 0;
		return rows;
	}

}
