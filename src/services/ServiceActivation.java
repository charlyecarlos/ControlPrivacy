package services;

import daos.TransactionManager;
import daos.MySQL.MySQL_ActivationDAO;
import domain.Activation;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServiceActivation {

	public void create(Activation activation) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		try{
			MySQL_ActivationDAO daoactivation=trans.getActivationDAO();
			
			daoactivation.create(activation);
			
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
	}
	
	public Activation recover(Activation activation) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		try{
			MySQL_ActivationDAO daoactivation=trans.getActivationDAO();
			activation=daoactivation.recover(activation);
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return activation;
	}
	
	public int delete(Activation activation) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		int num;
		try{
			MySQL_ActivationDAO daoactivation=trans.getActivationDAO();
			num=daoactivation.delete(activation.getToken());
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return num;
	}
	
}
