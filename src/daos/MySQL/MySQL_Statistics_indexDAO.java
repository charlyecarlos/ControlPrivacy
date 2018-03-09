package daos.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import daos.interfaces.Statistics_indexDAO;
import domain.Canvas_files;
import domain.Statistics_index;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class MySQL_Statistics_indexDAO implements Statistics_indexDAO{

	private Connection con;
	private int firstMonth;
	private int firstYear;
	
	public MySQL_Statistics_indexDAO(Connection con) {
		this.con=con;
		readStatisticsUser();
	}
	
	private static final String DB_ERR = "Database error";
	
	public static final int ORACLE_DUPLICATE_PK = 1;
	
	public List<Statistics_index> readStatisticsUser(){
		List<Statistics_index> statisticsUser=new ArrayList<>();
		
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getReadstatisticsuser());
			rs=st.executeQuery();
			while (rs.next())
				statisticsUser.add(new Statistics_index(
										rs.getInt(1),
										rs.getInt(2),
										rs.getLong(3)
								));
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);	
		}
		firstMonth=statisticsUser.get(0).getMonth();
		firstYear=statisticsUser.get(0).getYear();
		
		List<Statistics_index> months=new ArrayList<Statistics_index>();
		int month=firstMonth;
		int year=firstYear;
		boolean exit=false;
		while(!exit)
			if (year<=Calendar.getInstance().get(Calendar.YEAR)){
				if (month<=Calendar.getInstance().get(Calendar.MONTH)+1 || year<Calendar.getInstance().get(Calendar.YEAR)) {
					if (statisticsUser.size()!=0)
						if(statisticsUser.get(0).getMonth()==month && statisticsUser.get(0).getYear()==year) {
							months.add(new Statistics_index(month, year, statisticsUser.get(0).getCont()));
							statisticsUser.remove(0);
						}else
							months.add(new Statistics_index(month, year, 0));
					else
						months.add(new Statistics_index(month, year, 0));
					month++;
					if (month==13) {
						month=1;
						year++;
					}
				}else
					exit=true;
			}else
				exit=true;
		
		return months;
	}
	
	public List<Statistics_index> readStatistics(String module){
		List<Statistics_index> statisticsUser=new ArrayList<>();
		
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getReadstatistics());
			st.setString(1, module);
			rs=st.executeQuery();
			while (rs.next())
				statisticsUser.add(new Statistics_index(
										rs.getInt(1),
										rs.getInt(2),
										rs.getLong(3)
								));
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);	
		}
		List<Statistics_index> months=new ArrayList<Statistics_index>();
		
		int month=firstMonth;
		int year=firstYear;
		boolean exit=false;
		while(!exit)
			if (year<=Calendar.getInstance().get(Calendar.YEAR)){
				if (month<=Calendar.getInstance().get(Calendar.MONTH)+1 || year<Calendar.getInstance().get(Calendar.YEAR)){
					if (statisticsUser.size()!=0)
						if(statisticsUser.get(0).getMonth()==month && statisticsUser.get(0).getYear()==year) {
							months.add(new Statistics_index(month, year, statisticsUser.get(0).getCont()));
							statisticsUser.remove(0);
						}else
							months.add(new Statistics_index(month, year, 0));
					else
						months.add(new Statistics_index(month, year, 0));
					month++;
					if (month==13) {
						month=1;
						year++;
					}
				}else
					exit=true;
			}else
				exit=true;
		
		return months;
	}
	
	public List<Canvas_files> readTypeFiles(){
		List<Canvas_files> typeFiles=new ArrayList<Canvas_files>();
		
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getReadtypefiles());
			rs=st.executeQuery();
			while (rs.next())
				typeFiles.add(new Canvas_files(
									rs.getString(1),
									rs.getLong(2)
								));
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);	
		}
		
		return typeFiles;
	}
}
