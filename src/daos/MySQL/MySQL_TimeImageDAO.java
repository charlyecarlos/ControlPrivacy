package daos.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.interfaces.TimeImageDAO;

import domain.TimeImage;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class MySQL_TimeImageDAO implements TimeImageDAO{
	private static final String DB_ERR = "Error de la base de datos";
	
	private Connection con;
	
	public MySQL_TimeImageDAO(Connection con) {
		this.con=con;
	}

	@Override
	public void create(TimeImage timeImage) throws DAOException {
		
	}

	@Override
	public int update(TimeImage timeImage) throws DAOException {
		return 0;
	}

	@Override
	public TimeImage recover(TimeImage timeImage) throws DAOException {
		return timeImage;
	}

	@Override
	public int delete(Integer id) throws DAOException {
		return 0;
	}

	@Override
	public List<TimeImage> findAll() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		List<TimeImage> timeimage=new ArrayList<TimeImage>();
		try {
			st = con.prepareStatement(DbQuery.getAlltimeimage());
			rs=st.executeQuery();
			while (rs.next())
				timeimage.add(new TimeImage(rs.getInt(1),rs.getString(2)));
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return timeimage;
	}
}
