package services;

import daos.TransactionManager;
import daos.MySQL.MySQL_Statistic_fileDAO;
import domain.Counter;
import domain.Statistic_file;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServiceStatistic_file {

	public void create(Statistic_file statistic_file) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		try{
			MySQL_Statistic_fileDAO daostatistic=trans.getStatistic_fileDAO();
			
			ServiceCounter scounter=new ServiceCounter();
			Counter counter=scounter.recover(new Counter("2"));
			statistic_file.setId_file(String.valueOf(counter.getCount()));	
			
			daostatistic.create(statistic_file);
			
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
	
}
