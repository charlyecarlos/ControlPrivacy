package recursos;

public class DbQuery {
	
	private static final String AllTimeImage="SELECT TIME_MINUTES,DESCRIPTION FROM TIME_IMAGE ORDER BY TIME_MINUTES";

	public static String getAllTimeImage() {
		return AllTimeImage;
	}
}
