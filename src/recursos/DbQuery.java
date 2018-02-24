package recursos;

public class DbQuery {
	
	private static final String AllTimeImage="SELECT TIME_MINUTES,DESCRIPTION FROM TIME_IMAGES ORDER BY TIME_MINUTES";

	private static final String RecoverTypeUser="SELECT TYPE,DESCRIPTION FROM TYPEUSER WHERE TYPE=?";
	
	// USER
	
	private static final String CreateUser="INSERT INTO USERS (ID_USER,NAME,EMAIL,PWD,TYPE,ACCESS_FAIL,DATE_CREATION,DATE_LAST_ACCESS,LOCKED,ACTIVE) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String RecoverUser="SELECT ID_USER,NAME,EMAIL,PWD,TYPE,ACCESS_FAIL,DATE_CREATION,DATE_LAST_ACCESS,LOCKED,ACTIVE FROM USERS WHERE EMAIL=?";
	private static final String DeleteUser="DELETE FROM USERS WHERE ID_USER=?";
	
	// IMAGE
	
	private static final String CreateImage="INSERT INTO IMAGES (URL_REDIRECT,URL_IMAGE,OWNER,DATE_CREATION,EXPIRATION_DATE,VISITS) VALUES (?,?,?,?,?,?)";
	private static final String RecoverImage="SELECT URL_REDIRECT,URL_IMAGE,OWNER,DATE_CREATION,EXPIRATION_DATE,VISITS FROM IMAGES WHERE URL_REDIRECT=?";
	
	
	public static String getAlltimeimage() {
		return AllTimeImage;
	}

	public static String getRecovertypeuser() {
		return RecoverTypeUser;
	}

	public static String getCreateuser() {
		return CreateUser;
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
}
