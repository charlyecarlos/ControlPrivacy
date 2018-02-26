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
	
	// IMAGES
	
	private static final String CreateImage="INSERT INTO IMAGES (URL_REDIRECT,URL_IMAGE,OWNER,DATE_CREATION,EXPIRATION_DATE,VISITS) VALUES (?,?,?,?,?,?)";
	private static final String RecoverImage="SELECT URL_REDIRECT,URL_IMAGE,OWNER,DATE_CREATION,EXPIRATION_DATE,VISITS FROM IMAGES WHERE URL_REDIRECT=?";
	private static final String RecoverImageForUser="SELECT URL_REDIRECT,URL_IMAGE,OWNER,DATE_CREATION,EXPIRATION_DATE,VISITS FROM IMAGES WHERE OWNER=?";
	
	// COUNTERS
	
	private static final String RecoverCounters="SELECT ID_COUNTER,TABLE_COUNT,COUNT FROM COUNTERS WHERE ID_COUNTER=?";
	private static final String UpdateCounters="UPDATE COUNTERS SET TABLE_COUNT=?,COUNT=? WHERE ID_COUNTER=?";
	
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
	
	public static String getUpdateUser(){
		return UpdateUser;
	}

	public static String getRecoveruser() {
		return RecoverUser;
	}

	public static String getDeleteuser() {
		return DeleteUser;
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
	
}
