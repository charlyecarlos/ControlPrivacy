package daos.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import daos.interfaces.Statistic_fileDAO;
import domain.Statistic_file;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.ErrOracle;
import recursos.Recursos;

public class MySQL_Statistic_fileDAO implements Statistic_fileDAO{

	private Connection con;
	
	public MySQL_Statistic_fileDAO(Connection con) {
		this.con=con;
	}
	
	private static final String DB_ERR = "Database error";
	
	@Override
	public void create(Statistic_file statistic_file) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat=con.prepareStatement(DbQuery.getCreatestatisticFile());
			stat.setString(1, statistic_file.getId_file());
			stat.setString(2, statistic_file.getModule());
			stat.setString(3, statistic_file.getType_file());
			stat.setTimestamp(4, statistic_file.getDate_analyse());
			stat.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode()== ErrOracle.ORACLE_DUPLICATE_PK.getCod_err())
				throw new DAOException("the id_file already exists.");
			else if(e.getErrorCode() == ErrOracle.ORACLE_FAIL_FK.getCod_err())
				throw new DAOException("Operation out of service, try again later.");
			else
				throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(stat);
		}
	}

	@Override
	public int update(Statistic_file statistic_file) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Statistic_file recover(Statistic_file statistic_file) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Statistic_file> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


}
