package services;

import java.util.ArrayList;
import java.util.List;

import daos.TransactionManager;
import daos.MySQL.MySQL_Statistics_indexDAO;
import domain.Canvas_files;
import domain.Statistics_index;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServiceStatistics_index {

	public List<Statistics_index> readStatisticsUser() throws ServiceException {
		TransactionManager trans = new TransactionManager();
		List<Statistics_index> statisticsIndex=new ArrayList<Statistics_index>();
		try{
			MySQL_Statistics_indexDAO daosStatistics=trans.getStatistics_indexDAO();
			statisticsIndex=daosStatistics.readStatisticsUser();
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return statisticsIndex;
	}
	
	public List<Statistics_index> readStatistics(String module) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		List<Statistics_index> statisticsIndex=new ArrayList<Statistics_index>();
		try{
			MySQL_Statistics_indexDAO daosStatistics=trans.getStatistics_indexDAO();
			statisticsIndex=daosStatistics.readStatistics(module);
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return statisticsIndex;
	}
	
	public List<Canvas_files> readTypeFiles() throws ServiceException {
		TransactionManager trans = new TransactionManager();
		List<Canvas_files> typeFiles=new ArrayList<Canvas_files>();
		try{
			MySQL_Statistics_indexDAO daosStatistics=trans.getStatistics_indexDAO();
			typeFiles=daosStatistics.readTypeFiles();
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return typeFiles;
	}
	
}
