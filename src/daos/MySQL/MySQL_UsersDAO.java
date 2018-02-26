package daos.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import daos.interfaces.UsersDAO;
import domain.Type_User;
import domain.User;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class MySQL_UsersDAO implements UsersDAO{
	
	private Connection con;
	
	public MySQL_UsersDAO(Connection con) {
		this.con=con;
	}
	
	private static final String DB_ERR = "Database error";
	
	public static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FAIL_FK = 2291;
	
	@Override
	public void create(User user) throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs=null;
		try {
			stat=con.prepareStatement(DbQuery.getRecovertypeuser());
			stat.setInt(1, user.getType().getType());
			rs= stat.executeQuery();
			if(!rs.next())
				throw new DAOException("The type of user does not exist");
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(stat);
			
			stat=con.prepareStatement(DbQuery.getCreateuser());
			stat.setString(1, user.getId_user());
			stat.setString(2, user.getName());
			stat.setString(3, user.getEmail());
			stat.setString(4, user.getPassword());
			stat.setInt(5, user.getType().getType());
			stat.setString(6,String.valueOf(user.getAccess_fail()));
			stat.setLong(7, user.getDate_creation().getTime());
			stat.setLong(8, user.getDate_last_access().getTime());
			stat.setString(9, user.getLocked());
			stat.setString(10, user.getActive());
			stat.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode()== ORACLE_DUPLICATE_PK)
				throw new DAOException("the email already exists.");
			else if(e.getErrorCode() == ORACLE_FAIL_FK)
				throw new DAOException("Operation out of service, try again later.");
			else
				throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(stat);
		}
	}

	@Override
	public int update(User user) throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs=null;
		int num=0;
		try {
			stat=con.prepareStatement(DbQuery.getRecovertypeuser());
			stat.setInt(1, user.getType().getType());
			rs= stat.executeQuery();
			if(!rs.next())
				throw new DAOException("The type of user does not exist");
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(stat);
			
			stat=con.prepareStatement(DbQuery.getRecoveruser());
			stat.setString(1, user.getEmail());
			rs= stat.executeQuery();
			if(rs.next())
				throw new DAOException("The user already exist");
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(stat);
			
			stat=con.prepareStatement(DbQuery.getUpdateUser());
			stat.setString(1, user.getName());
			stat.setString(2, user.getEmail());
			stat.setString(3, user.getPassword());
			stat.setInt(4, user.getType().getType());
			stat.setInt(5, user.getAccess_fail());
			stat.setLong(6, user.getDate_creation().getTime());
			stat.setLong(7, user.getDate_last_access().getTime());
			stat.setString(8, user.getLocked());
			stat.setString(9, user.getActive());
			stat.setString(10, user.getId_user());
			num=stat.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode() == ORACLE_FAIL_FK)
				throw new DAOException("Operation out of service, try again later.");
			else
				throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(stat);
		}
		return num;
	}

	@Override
	public User recover(User user) throws DAOException {
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getRecoveruser());
			st.setString(1,user.getEmail());
			rs=st.executeQuery();
			user=null;
			if (rs.next())
				user=new User(rs.getString(1),
							  rs.getString(2),
							  rs.getString(3),
							  rs.getString(4),
							  new Type_User(rs.getInt(5)),
							  rs.getInt(6),
							  new Timestamp(rs.getLong(7)),
							  new Timestamp(rs.getLong(8)),
							  rs.getString(9),
							  rs.getString(10)
							);
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);	
		}
		return user;
	}
	
	@Override
	public int delete(String id_user) throws DAOException {
		PreparedStatement st = null;
		int delete = 0;
		try {
			st = con.prepareStatement(DbQuery.getDeleteuser());
			st.setString(1, id_user);
			delete = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK)
				throw new DAOException("Not allowed to delete user");
			else
				throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return delete;
	}

	@Override
	public List<User> findAll() throws DAOException {
		return null;
	}

}
