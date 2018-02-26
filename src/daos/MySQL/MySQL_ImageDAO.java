package daos.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import daos.interfaces.ImageDAO;

import domain.Image;
import domain.User;

import exceptions.DAOException;

import recursos.DbQuery;
import recursos.Recursos;

public class MySQL_ImageDAO implements ImageDAO{

	private static final String DB_ERR = "Database error";
	
	public static final int ORACLE_DUPLICATE_PK = 1;
//	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FAIL_FK = 2291;

	
	private Connection con;
	
	public MySQL_ImageDAO(Connection con) {
		this.con=con;
	}
	
	@Override
	public void create(Image image) throws DAOException {
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
			stat.setLong(4, image.getDate_creation().getTime());
			stat.setLong(5, image.getExpiration_date().getTime());
			stat.setInt(6, image.getVisits());
			stat.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode()== ORACLE_DUPLICATE_PK)
				throw new DAOException("The image already exists.");
			else if(e.getErrorCode() == ORACLE_FAIL_FK)
				throw new DAOException("Operation out of service, try again later.");
			else
				throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(stat);
		}
	}

	@Override
	public int update(Image image) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
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
						  new User(rs.getString(3)),
						  new Timestamp(rs.getLong(4)),
						  new Timestamp(rs.getLong(5)),
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
							  new User(rs.getString(3)),
							  new Timestamp(rs.getLong(4)),
							  new Timestamp(rs.getLong(5)),
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

}
