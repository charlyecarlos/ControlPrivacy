package daos.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daos.interfaces.ActivationDAO;
import domain.Activation;
import domain.User;
import exceptions.DAOException;
import resources.DbQuery;
import resources.ErrOracle;
import resources.Recursos;

public class MySQL_ActivationDAO implements ActivationDAO{
	
	private Connection con;
	
	public MySQL_ActivationDAO(Connection con) {
		this.con=con;
	}

	private static final String DB_ERR = "Database error";

	public void create(Activation activation){
		PreparedStatement stat = null;
		try {
			stat=con.prepareStatement(DbQuery.getCreateactivation());
			stat.setString(1, activation.getToken());
			stat.setString(2, activation.getUser().getId_user());
			stat.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode()== ErrOracle.ORACLE_DUPLICATE_PK.getCod_err())
				throw new DAOException("the activation already exists.");
			else if(e.getErrorCode() == ErrOracle.ORACLE_FAIL_FK.getCod_err())
				throw new DAOException("Operation out of service, try again later.");
			else
				throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(stat);
		}
	}
	
	public Activation recover(Activation activation){
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getRecoveractivation());
			st.setString(1,activation.getToken());
			rs=st.executeQuery();
			activation=null;
			if (rs.next())
				activation=new Activation(
								rs.getString(1),
								new User(rs.getString(2))
							);
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);	
		}
		return activation;
	}

	@Override
	public int update(Activation activation) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) throws DAOException {
		PreparedStatement st = null;
		int delete = 0;
		try {
			st = con.prepareStatement(DbQuery.getDeleteactivation());
			st.setString(1, id);
			delete = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ErrOracle.ORACLE_DELETE_FK.getCod_err())
				throw new DAOException("Not allowed to delete activation");
			else
				throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return delete;
	}

	@Override
	public List<Activation> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
