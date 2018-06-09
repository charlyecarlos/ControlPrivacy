package daos.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daos.interfaces.Type_UserDAO;
import domain.Type_User;
import exceptions.DAOException;
import resources.DbQuery;
import resources.Recursos;

public class MySQL_Type_UserDAO implements Type_UserDAO{

	private Connection con;
	
	public MySQL_Type_UserDAO(Connection con) {
		this.con=con;
	}
	
	private static final String DB_ERR = "Database error";
	
	
	@Override
	public void create(Type_User type_user) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(Type_User type_user) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Type_User recover(Type_User type_user) throws DAOException {		PreparedStatement st=null;
	ResultSet rs=null;
	try {
		st = con.prepareStatement(DbQuery.getRecovertypeuser());
		st.setInt(1,type_user.getType());
		rs=st.executeQuery();
		if (rs.next())
			type_user=new Type_User(rs.getInt(1), rs.getString(2));
	} catch (SQLException e) {
		throw new DAOException(DB_ERR, e);
	} finally {
		Recursos.closeResultSet(rs);
		Recursos.closePreparedStatement(st);	
	}
	return type_user;
	}

	@Override
	public int delete(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Type_User> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
