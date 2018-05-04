package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encrypt {
	private static MessageDigest md;
	
	public static String encryptMD5(String password){	
		try { 
			md = MessageDigest.getInstance("MD5");
			byte[] passBytes = password.getBytes();
			md.reset();
			passBytes= md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			
			for(int i=0;i<passBytes.length;i++)
				sb.append(Integer.toHexString(0xff & passBytes[i]));
		
		return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null; 
	}
	
	public static String encryptSHA256(String password){
		try { 
			md = MessageDigest.getInstance("SHA-256");
			md.reset();
			byte[] hash = md.digest(password.getBytes());
			StringBuffer sb = new StringBuffer();
			
			for(byte b: hash)
				sb.append(Integer.toHexString(0xff & b));
		
		return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null; 
	}
}