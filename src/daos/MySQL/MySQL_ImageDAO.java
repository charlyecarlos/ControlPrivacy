package daos.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.interfaces.ImageDAO;

import domain.Image;
import domain.User;

import exceptions.DAOException;
import resources.DbQuery;
import resources.ErrOracle;
import resources.Recursos;

public class MySQL_ImageDAO implements ImageDAO{

	private static final String DB_ERR = "Database error";
	

	
	private Connection con;
	
	public MySQL_ImageDAO(Connection con) {
		this.con=con;
	}
	
	@Override
	public void create(Image image) throws SQLException {
		PreparedStatement stat = null;
		ResultSet rs=null;
		try {
			stat=con.prepareStatement(DbQuery.getRecoveruser());
			stat.setString(1, image.getOwner().getEmail());
			rs= stat.executeQuery();
			if(!rs.next())
				throw new DAOException("The user does not exist");
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(stat);
			
			stat=con.prepareStatement(DbQuery.getCreateimage());
			stat.setString(1, image.getUrl_redirect());
			stat.setString(2, image.getUrl_image());
			stat.setString(3, image.getOwner().getId_user());
			stat.setTimestamp(4, image.getDate_creation());
			stat.setTimestamp(5, image.getExpiration_date());
			stat.setInt(6, image.getVisits());
			stat.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode()== ErrOracle.ORACLE_DUPLICATE_PK.getCod_err())
				throw new DAOException("The image already exists.");
			else if(e.getErrorCode() == ErrOracle.ORACLE_FAIL_FK.getCod_err())
				throw new DAOException("Operation out of service, try again later.");
			else if(e.getErrorCode()==1062)
				throw new SQLException(e.getMessage());
			else
				throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(stat);
		}
	}

	@Override
	public int update(Image image) throws DAOException {
		PreparedStatement stat = null;
		try {			
			stat=con.prepareStatement(DbQuery.getUpdateimage());
			stat.setString(1, image.getUrl_image());
			stat.setString(2, image.getOwner().getId_user());
			stat.setTimestamp(3, image.getDate_creation());
			stat.setTimestamp(4, image.getExpiration_date());
			stat.setInt(5, image.getVisits());
			stat.setString(6, image.getUrl_redirect());
			return stat.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode() == ErrOracle.ORACLE_FAIL_FK.getCod_err())
				throw new DAOException("Operation out of service, try again later.");
			else
				throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(stat);
		}
	}

	@Override
	public Image recover(Image image) throws DAOException {		
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat = con.prepareStatement(DbQuery.getRecoverimage());
			stat.setString(1,image.getUrl_redirect());
			rs=stat.executeQuery();
			if (rs.next()){
			image=new Image(rs.getString(1),
						  rs.getString(2),
						  User.createUserWithId_user(rs.getString(3)),
						  rs.getTimestamp(4),
						  rs.getTimestamp(5),
						  rs.getInt(6)
						 );
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(stat);	
		}
		return image;
	}
	

	@Override
	public List<Image> recoverForUser(User user) throws DAOException {
		PreparedStatement stat=null;
		ResultSet rs=null;
		List<Image> images=new ArrayList<Image>();
		try {
			stat = con.prepareStatement(DbQuery.getRecoverimageforuser());
			stat.setString(1,user.getId_user());
			rs=stat.executeQuery();
			while (rs.next()){
			images.add(new Image(rs.getString(1),
							  rs.getString(2),
							  User.createUserWithId_user(rs.getString(3)),
							  rs.getTimestamp(4),
							  rs.getTimestamp(5),
							  rs.getInt(6)
							 ));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(stat);	
		}
		return images;
	}

	@Override
	public int delete(String id) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Image> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image recoverImageForUrl(String url_image) throws DAOException {
		PreparedStatement stat=null;
		ResultSet rs=null;
		Image image=Image.createImageWithUrl(url_image);
		try {
			stat = con.prepareStatement(DbQuery.getRecoverimageforurl());
			stat.setString(1,image.getUrl_image());
			rs=stat.executeQuery();
			if (rs.next()){
			image=new Image(rs.getString(1),
						  rs.getString(2),
						  User.createUserWithId_user(rs.getString(3)),
						  rs.getTimestamp(4),
						  rs.getTimestamp(5),
						  rs.getInt(6)
						 );
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(stat);	
		}
		return image;
	}

}
