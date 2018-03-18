package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.TransactionManager;
import daos.MySQL.MySQL_ImageDAO;
import domain.Image;
import domain.User;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServiceImage {

	public void createImage(Image image) throws ServiceException, SQLException {
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
	
	public void updateImage(Image image) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		try{
			MySQL_ImageDAO daoimage=trans.getImageDAO();
			daoimage.update(image);
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
	}
	
	public List<Image> recoverImageForUser(User user) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		List<Image> images=new ArrayList<Image>();
		try{
			MySQL_ImageDAO daoimage=trans.getImageDAO();
			images=daoimage.recoverForUser(user);
			trans.closeCommit();
		}catch(DAOException e){
			trans.closeRollback();
			if (e.getCause() == null)
				throw new ServiceException(e.getMessage()); //Logical error
			else
				throw new ServiceException(e.getMessage(), e); //Internal error
		}
		return images;
	}
	
	public Image recoverImageForUrl(String url_image) throws ServiceException {
		TransactionManager trans = new TransactionManager();
		Image image;
		try{
			MySQL_ImageDAO daoimage=trans.getImageDAO();
			image=daoimage.recoverImageForUrl(url_image);
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
