package services;

import daos.TransactionManager;
import daos.MySQL.MySQL_CounterDAO;
import domain.Counter;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServiceCounter {

	public int update(Counter counter) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		int rows=0;
		try{
			MySQL_CounterDAO daocounter=trans.getCounterDAO();
			rows=daocounter.update(counter);
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
	
	public Counter recover(Counter counter) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		try{
			MySQL_CounterDAO daocounter=trans.getCounterDAO();
			counter=daocounter.recover(counter);
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return counter;
	}
	
}
