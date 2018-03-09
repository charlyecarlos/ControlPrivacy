package recursos;

public class DbQuery {
	
	private static final String AllTimeImage="SELECT TIME_MINUTES,DESCRIPTION FROM TIME_IMAGES ORDER BY TIME_MINUTES";

	// TYPE_USERS
	
	private static final String RecoverTypeUser="SELECT TYPE,DESCRIPTION FROM TYPE_USERS WHERE TYPE=?";
	
	// USERS
	
	private static final String CreateUser="INSERT INTO USERS (ID_USER,NAME,EMAIL,PWD,TYPE,ACCESS_FAIL,DATE_CREATION,DATE_LAST_ACCESS,LOCKED,ACTIVE) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String UpdateUser="UPDATE USERS SET EMAIL=?,NAME=?,PWD=?,TYPE=?,ACCESS_FAIL=?,DATE_CREATION=?,DATE_LAST_ACCESS=?,LOCKED=?,ACTIVE=? WHERE ID_USER=?";
	private static final String RecoverUser="SELECT ID_USER,NAME,EMAIL,PWD,TYPE,ACCESS_FAIL,DATE_CREATION,DATE_LAST_ACCESS,LOCKED,ACTIVE FROM USERS WHERE EMAIL=?";
	private static final String DeleteUser="DELETE FROM USERS WHERE ID_USER=?";
	private static final String FindAllOrderByDateCreation="SELECT ID_USER,NAME,EMAIL,PWD,TYPE,ACCESS_FAIL,DATE_CREATION,DATE_LAST_ACCESS,LOCKED,ACTIVE FROM USERS ORDER BY DATE_CREATION";
	
	// IMAGES
	
	private static final String CreateImage="INSERT INTO IMAGES (URL_REDIRECT,URL_IMAGE,OWNER,DATE_CREATION,EXPIRATION_DATE,VISITS) VALUES (?,?,?,?,?,?)";
	private static final String RecoverImage="SELECT URL_REDIRECT,URL_IMAGE,OWNER,DATE_CREATION,EXPIRATION_DATE,VISITS FROM IMAGES WHERE URL_REDIRECT=?";
	private static final String RecoverImageForUser="SELECT URL_REDIRECT,URL_IMAGE,OWNER,DATE_CREATION,EXPIRATION_DATE,VISITS FROM IMAGES WHERE OWNER=?";
	
	// COUNTERS
	
	private static final String RecoverCounters="SELECT ID_COUNTER,TABLE_COUNT,COUNT FROM COUNTERS WHERE ID_COUNTER=?";
	private static final String UpdateCounters="UPDATE COUNTERS SET TABLE_COUNT=?,COUNT=? WHERE ID_COUNTER=?";
	
	// STATISTIC_FILE
	
	private static final String CreateStatistic_file="INSERT INTO statistics_file(ID_FILE, MODULE, TYPE_FILE, DATE_ANALYSE) VALUES (?,?,?,?)";
	
	private static final String RecoverType_file="SELECT TYPE_FILE,COUNT(*) FROM statistics_file GROUP BY TYPE_FILE";
	
	// STATISTICS_INDEX
	
	private static final String ReadStatisticsUser="SELECT DATE_FORMAT(DATE_CREATION,'%m') MONTH,DATE_FORMAT(DATE_CREATION,'%Y') YEAR,COUNT(*) CONT FROM USERS GROUP BY DATE_FORMAT(DATE_CREATION,'%m %Y') ORDER BY YEAR,MONTH";
	private static final String ReadStatistics="SELECT DATE_FORMAT(DATE_ANALYSE,'%m') MONTH,DATE_FORMAT(DATE_ANALYSE,'%Y') YEAR ,COUNT(*) COUNT FROM statistics_file WHERE MODULE=? GROUP BY DATE_FORMAT(DATE_ANALYSE,'%m %Y')";
	
	private static final String ReadTypeFiles="SELECT TYPE_FILE, COUNT(*) FROM statistics_file GROUP BY TYPE_FILE ORDER BY 2 DESC";
	
	// SET AND GET
	
	public static String getAlltimeimage() {
		return AllTimeImage;
	}

	public static String getRecovertypeuser() {
		return RecoverTypeUser;
	}

	public static String getCreateuser() {
		return CreateUser;
	}

	public static String getUpdateuser() {
		return UpdateUser;
	}

	public static String getRecoveruser() {
		return RecoverUser;
	}

	public static String getDeleteuser() {
		return DeleteUser;
	}

	public static String getFindallorderbydatecreation() {
		return FindAllOrderByDateCreation;
	}

	public static String getCreateimage() {
		return CreateImage;
	}

	public static String getRecoverimage() {
		return RecoverImage;
	}
	
	public static String getRecoverimageforuser(){
		return RecoverImageForUser;
	}

	public static String getRecovercounters() {
		return RecoverCounters;
	}

	public static String getUpdatecounters() {
		return UpdateCounters;
	}

	public static String getCreatestatisticFile() {
		return CreateStatistic_file;
	}

	public static String getRecovertypeFile() {
		return RecoverType_file;
	}

	public static String getReadstatisticsuser() {
		return ReadStatisticsUser;
	}

	public static String getReadstatistics() {
		return ReadStatistics;
	}

	public static String getReadtypefiles() {
		return ReadTypeFiles;
	}
	
}
