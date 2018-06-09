package resources;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import exceptions.DAOException;



public class Recursos {

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Error de la base de datos", e);
		}
	}

	public static void closePreparedStatement(PreparedStatement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Error de la base de datos", e);
		}
	}
	public static void closeCallableStatement(CallableStatement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Error de la base de datos", e);
		}
	}

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String randomString(int len) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
	static final String AB1 = "0123456789";

	public static int randomEntero(int len) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB1.charAt(rnd.nextInt(AB1.length())));
		return Integer.valueOf(sb.toString());
	}
	static final String AB2 = "0123456789";

	public static String randomStringNumero(int len) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB2.charAt(rnd.nextInt(AB2.length())));
		return sb.toString();
	}
	// redondea un numero al int decinal
	public static double redondeoADecimal(double numero,int decimal) {
	return Math.round((numero)*Math.pow(10,decimal))/Math.pow(10,decimal);
	}
	
}
