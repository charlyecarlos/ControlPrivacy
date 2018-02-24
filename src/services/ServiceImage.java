package services;

import daos.TransactionManager;
import daos.MySQL.MySQL_ImageDAO;
import domain.Image;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServiceImage {

	public void createImage(Image image) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		try{
			MySQL_ImageDAO daoimage=trans.getImageDAO();
			daoimage.create(image);
			trans.closeCommit();
		} catch (DAOException e) {
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
	}
	
	public Image recoverImage(Image image) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		try{
			MySQL_ImageDAO daoimage=trans.getImageDAO();
			image=daoimage.recover(image);
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return image;
	}
}
