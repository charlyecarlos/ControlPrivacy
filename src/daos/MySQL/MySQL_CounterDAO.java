package daos.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daos.interfaces.CounterDAO;
import domain.Counter;
import exceptions.DAOException;
import resources.DbQuery;
import resources.Recursos;

public class MySQL_CounterDAO implements CounterDAO{
	private static final String DB_ERR = "Error de la base de datos";

	
	private Connection con;
	
	public MySQL_CounterDAO(Connection con) {
		this.con=con;
	}
	
	@Override
	public void create(Counter counter) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(Counter counter) throws DAOException {
		PreparedStatement stat = null;
		int rows=0;
		try {
			stat = con.prepareStatement(DbQuery.getUpdatecounters());
			stat.setString(1, counter.getTable_count());
			stat.setInt(2, counter.getCount());
			stat.setString(3, counter.getId_count());
			rows=stat.executeUpdate();
			counter=null;
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closePreparedStatement(stat);
		}
		return rows;
	}

	@Override
	public Counter recover(Counter counter) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		try {
			st = con.prepareStatement(DbQuery.getRecovercounters());
			st.setString(1, counter.getId_count());
			rs=st.executeQuery();
			counter=null;
			if (rs.next())
				counter=new Counter(rs.getString(1),rs.getString(2),rs.getInt(3));
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return counter;
	}

	@Override
	public int delete(String id) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Counter> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
