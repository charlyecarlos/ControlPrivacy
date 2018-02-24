package services;

import daos.TransactionManager;
import daos.MySQL.MySQL_UsersDAO;
import domain.User;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServiceUser {

	
	public User recover(User user) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		try{
			MySQL_UsersDAO daouser=trans.getUsersDAO();
			user=daouser.recover(user);
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return user;
	}
	
	public int incrementFail(User user) throws ServiceException{	
		TransactionManager trans = new TransactionManager();
		int rows=0;
		try{
			MySQL_UsersDAO daouser=trans.getUsersDAO();
			user.setAccessfail(user.getAccess_fail()+1);
			if (user.getAccess_fail()==3)
				user.setLocked("S");
			rows=daouser.update(user);
			
			trans.closeCommit();
		} catch (DAOException e) {
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return rows;
	}
	
}
