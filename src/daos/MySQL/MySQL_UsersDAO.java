package daos.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.interfaces.UsersDAO;
import domain.Type_User;
import domain.User;
import exceptions.DAOException;
import resources.DbQuery;
import resources.ErrOracle;
import resources.Recursos;

public class MySQL_UsersDAO implements UsersDAO{
	
	private Connection con;
	
	public MySQL_UsersDAO(Connection con) {
		this.con=con;
	}
	
	private static final String DB_ERR = "Database error";
	
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
			

			stat=con.prepareStatement(DbQuery.getRecoveruser());
			stat.setString(1, user.getEmail());
			rs= stat.executeQuery();
			if(rs.next())
				throw new DAOException("The email already exist.");
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(stat);
			
			stat=con.prepareStatement(DbQuery.getCreateuser());
			stat.setString(1, user.getId_user());
			stat.setString(2, user.getEmail());
			stat.setString(3, user.getName());
			stat.setString(4, user.getPassword());
			stat.setInt(5, user.getType().getType());
			stat.setString(6,String.valueOf(user.getAccess_fail()));
			stat.setTimestamp(7, user.getDate_creation());
			stat.setTimestamp(8, user.getDate_last_access());
			stat.setString(9, user.getLocked());
			stat.setString(10, user.getActive());
			stat.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode()== ErrOracle.ORACLE_DUPLICATE_PK.getCod_err())
				throw new DAOException("the email already exists.");
			else if(e.getErrorCode() == ErrOracle.ORACLE_FAIL_FK.getCod_err())
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
			
			stat=con.prepareStatement(DbQuery.getUpdateuser());
			stat.setString(1, user.getEmail());
			stat.setString(2, user.getName());
			stat.setString(3, user.getPassword());
			stat.setInt(4, user.getType().getType());
			stat.setString(5, String.valueOf(user.getAccess_fail()));
			stat.setTimestamp(6, user.getDate_creation());
			stat.setTimestamp(7, user.getDate_last_access());
			stat.setString(8, user.getLocked());
			stat.setString(9, user.getActive());
			stat.setString(10, user.getId_user());
			num=stat.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode() == ErrOracle.ORACLE_FAIL_FK.getCod_err())
				throw new DAOException("Operation out of service, try again later.");
			else
				throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(stat);
		}
		return num;
	}
	
	@Override
	public int updateEmail(User user) throws DAOException {
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
			
			stat=con.prepareStatement(DbQuery.getUpdateuser());
			stat.setString(1, user.getEmail());
			stat.setString(2, user.getName());
			stat.setString(3, user.getPassword());
			stat.setInt(4, user.getType().getType());
			stat.setString(5, String.valueOf(user.getAccess_fail()));
			stat.setTimestamp(6, user.getDate_creation());
			stat.setTimestamp(7, user.getDate_last_access());
			stat.setString(8, user.getLocked());
			stat.setString(9, user.getActive());
			stat.setString(10, user.getId_user());
			num=stat.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode() == ErrOracle.ORACLE_FAIL_FK.getCod_err())
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
							  rs.getTimestamp(7),
							  rs.getTimestamp(8),
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
			if (e.getErrorCode() == ErrOracle.ORACLE_DELETE_FK.getCod_err())
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

	@Override
	public List<User> findAllOrderByDateCreation() throws DAOException {
		PreparedStatement st=null;
		ResultSet rs=null;
		List<User> users=new ArrayList<User>();
		try {
			st = con.prepareStatement(DbQuery.getFindallorderbydatecreation());
			rs=st.executeQuery();
			while (rs.next())
				users.add(new User(rs.getString(1),
							  rs.getString(2),
							  rs.getString(3),
							  rs.getString(4),
							  new Type_User(rs.getInt(5)),
							  rs.getInt(6),
							  rs.getTimestamp(7),
							  rs.getTimestamp(8),
							  rs.getString(9),
							  rs.getString(10)
							));
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);	
		}
		return users;
	}

}
