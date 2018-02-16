package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import recursos.DbQuery;
import recursos.Recursos;

import domain.TypeUser;

import exceptions.DAOException;

public class TypeUserDAO {
	private static final String DB_ERR = "Error de la base de datos";

	public static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	
	private Connection con;

	public TypeUserDAO(Connection con) {
		this.con = con;
	}
	public TypeUser recoverTypeUser(TypeUser tipousuario) {
		PreparedStatement st = null;
		ResultSet rs =null ;
		TypeUser usu=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarTypeUser());
				st.setInt(1,tipousuario.getType() );
				rs=st.executeQuery();
				if (rs.next()){
				 usu=new TypeUser(rs.getInt(1),
							     rs.getString(2)
							      ); 
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return usu;
		
		
	}

}
