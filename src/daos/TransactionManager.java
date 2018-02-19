package daos;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import daos.MySQL.MySQL_TimeImageDAO;
import exceptions.ServiceException;


public class TransactionManager {

	private static final String DB_ERR = "Database error";
	private static final String DB_CON_ERR = "Failed to connect to database";

	private Connection con;

	// para  octener la conexion del pool de conexiones, ver WEB.xml
	  public TransactionManager() throws ServiceException {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/PoolDeConexiones");
			con = ds.getConnection();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			throw new ServiceException(DB_ERR, e);
		} catch (NamingException e) {
			throw new ServiceException(DB_CON_ERR, e);
		}
	}
//	// la conexion la establezco con la clase ConexionOracle
//	public TransaccionesManager() throws ServiceException  {
//		
//		try {
//			ConexionOracle conn=new ConexionOracle();
//			con=conn.getConexion();
//			con.setAutoCommit(false);
//		} catch (SQLException e) {
//			throw new ServiceException("Error en la  bbdd",e);	
//		} catch (DAOException e) {
//			throw new ServiceException(e.getMessage(),e);	
//		}	
//	}
	  
	public void closeCommit() throws ServiceException {
		try {
			if(con!=null){
			con.commit();
			con.close();
			}
		} catch (SQLException e) {
			throw new ServiceException(DB_ERR, e);
		}
	}
	public void closeRollback() throws ServiceException {
		try {
			if(con!=null){
				con.rollback();
				con.close();
				}
		} catch (SQLException e) {
			throw new ServiceException(DB_ERR, e);
		}
	}
	public void commit() throws ServiceException {
		try {
			if(con!=null){
				con.commit();
			}
		} catch (SQLException e) {
			throw new ServiceException(DB_ERR, e);
		}
	}
	public void rollback() throws ServiceException {
		try {
			if(con!=null)
				con.rollback();
		} catch (SQLException e) {
			throw new ServiceException(DB_ERR, e);
		}
	}
	
	public MySQL_TimeImageDAO getTimeImageDAO(){
		return new MySQL_TimeImageDAO(con);
	}
	
	
	public Connection getConexion() {
		return con;
	}
	

}
