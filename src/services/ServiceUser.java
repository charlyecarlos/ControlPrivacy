package services;

import java.util.ArrayList;
import java.util.List;

import daos.TransactionManager;
import daos.MySQL.MySQL_Type_UserDAO;
import daos.MySQL.MySQL_UsersDAO;
import domain.Counter;
import domain.User;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServiceUser {

	public void create(User user) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		try{
			MySQL_UsersDAO daouser=trans.getUsersDAO();
			
			ServiceCounter scounter=new ServiceCounter();
			Counter counter=scounter.recover(new Counter("1"));
			user.setId_user(String.valueOf(counter.getCount()));	
			
			daouser.create(user);
			
			counter.setCount(counter.getCount()+1);
			scounter.update(counter);
			
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
	}
	
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
	
	public User recoverComplete(User user) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		try{
			MySQL_UsersDAO daouser=trans.getUsersDAO();
			user=daouser.recover(user);
			if (user!=null) {
				MySQL_Type_UserDAO datotypeuser=trans.getType_User();
				user.setType(datotypeuser.recover(user.getType()));
			}
			
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
	
	public int update(User user) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		int rows=0;
		try{
			MySQL_UsersDAO daouser=trans.getUsersDAO();
			rows=daouser.update(user);
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return rows;
	}
	
	public List<User> findAllOrderByDateCreation() throws ServiceException {
		TransactionManager trans = new TransactionManager();
		List<User> users=new ArrayList<User>();
		try{
			MySQL_UsersDAO daouser=trans.getUsersDAO();
			users=daouser.findAllOrderByDateCreation();
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return users;
	}
	
	public int incrementFail(User user) throws ServiceException{	
		TransactionManager trans = new TransactionManager();
		int rows=0;
		try{
			MySQL_UsersDAO daouser=trans.getUsersDAO();
			user.setAccessfail(user.getAccess_fail()+1);
			if (user.getAccess_fail()==3)
				user.setLocked("Y");
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
