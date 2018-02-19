package services;

import java.util.List;

import daos.TransactionManager;
import daos.MySQL.MySQL_TimeImageDAO;
import domain.TimeImage;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServiceTimeImage {
	
	public void insertTimeImage(TimeImage timeimage) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		MySQL_TimeImageDAO daotimeimage=trans.getTimeImageDAO();
		try{
			daotimeimage.create(timeimage);
		}catch(DAOException e){
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	public int updateTimeImage(TimeImage timeimage) throws ServiceException {
		return 0;
	}

	public int deleteTimeImage(TimeImage timeimage) throws ServiceException {
		return 0;
	}

	public List<TimeImage> recoverAllTimeImage() throws ServiceException {
		TransactionManager trans = new TransactionManager();
		MySQL_TimeImageDAO daotimeimage=trans.getTimeImageDAO();
		List<TimeImage> timeimages=null;
		try{
			timeimages=daotimeimage.findAll();
		}catch(DAOException e){
			throw new ServiceException(e.getMessage());
		}
		return timeimages;
	}
}
