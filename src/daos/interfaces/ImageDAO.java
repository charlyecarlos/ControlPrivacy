package daos.interfaces;

import java.util.List;
import domain.Image;
import domain.User;
import exceptions.DAOException;

public interface ImageDAO extends GenericDAO<Image,String>{

	public List<Image> recoverForUser(User user) throws DAOException;
	
	public Image recoverImageForUrl(String url_image) throws DAOException;
}
