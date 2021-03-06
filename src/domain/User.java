package domain;

import java.sql.Timestamp;

import exceptions.DomainException;
import util.Validator;

public class User {
	private static final String ERM_email = "invalid email";
	private static final int email_MIN = 1;
	private static final int email_MAX = 100;
	
	private static final String ERM_name = "invalid name";
	private static final int name_MIN = 1;
	private static final int name_MAX = 50;
	
	private static final String ERM_password = "invalid password";
	private static final int password_MIN = 1;
	private static final int password_MAX = 64;
	
	private static final String ERM_accessfail = "number of failed accesses maximum 3";
	
	private static final String MSG_active = "active can only be 'Y' or 'N'";
	private static final String ERM_active = "invalid active";
	private static final int active_MIN = 1;
	private static final int active_MAX = 1;
	
	
	private static final String MSG_locked = "Locked can only be 'Y' or 'N'";;
	private static final String ERM_locked = "invalid locked";
	private static final int locked_MIN = 1;
	private static final int locked_MAX = 1;
	
	private String id_user;
	private String email;
	private String name;
	private String pwd;
	private Type_User type;
	private int access_fail=0;
	private	Timestamp date_creation;
	private Timestamp date_last_access;
	private String locked="N";
	private String active="N";
	
	// para crear un us con la PK
	
	public User (String id_user,String email){
		this.id_user=id_user;
		this.email=email;
	}
		
	public  User(String email)  {
		this.email=email;
		}
	// para crear un us
	public User(String name,String email,String password, Type_User type,Timestamp date_creation,Timestamp date_last_access) {
		setPassword(password);
		setName(name);
		setType(type);
		setEmail(email);
		setDate_Creation(date_creation);
		setDate_last_access(date_last_access);
		setAccessfail(0);
		setLocked("N");
		setActive("Y");
	}
		/**
	 * @param pwd
	 * @param type
	 * @param email
	 * @param accessfail
	 * @param date_creation
	 * @param date_last_access
	 * @param locked
	 * @param active
	 */
	public User(String id_user, String email,String name, String pwd, Type_User type,int access_fail,Timestamp date_creation,Timestamp date_last_access,String locked, String active) {
		this.id_user=id_user;
		this.name=name;
		this.pwd = pwd;
		this.type = type;
		this.email = email;
		this.access_fail = access_fail;
		this.date_creation=date_creation;
		this.date_last_access=date_last_access;
		this.locked = locked;
		this.active = active;
	}
	public User() {}
	
	public static User createUserWithId_user(String id_user){
		User user=new User();
		user.setId_user(id_user);
		return user;
	}
	
	/**
	 * @return id user
	 */
	public String getId_user(){
		return id_user;
	}
	
	/**
	 * @param id user to set
	 */
	public void setId_user(String id_user){
		this.id_user=id_user;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return pwd;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		if (Validator.length(password, password_MIN, password_MAX)) {
			this.pwd = password.trim();
		} else {
			throw new DomainException(ERM_password);
		}
	}
	
	/**
	 * @return the type
	 */
	public Type_User getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(Type_User type) {
		this.type = type;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		if (Validator.email(email, email_MIN, email_MAX)) {
			this.email = email.trim();
		} else {
			throw new DomainException(ERM_email);
		}	
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param email the name to set
	 */
	public void setName(String name) {
		if (Validator.length(name, name_MIN, name_MAX))
			this.name = name.trim();
		else
			throw new DomainException(ERM_name);
	}
	
	/**
	 * @return Timestamp_creation
	 */
	public Timestamp getDate_creation() {
		return date_creation;
	}
	
	/**
	 * @param Timestamp_creation
	 */
	public void setDate_Creation(Timestamp date_creation) {
		this.date_creation=date_creation;
	}
	
	/**
	 * @return date_last_access
	 */
	public Timestamp getDate_last_access() {
		return date_last_access;
	}
	
	/**
	 * @param date_last_access to set
	 */
	public void setDate_last_access(Timestamp date_last_access) {
		this.date_last_access=date_last_access;
	}
	
	/**
	 * @return the accessfail
	 */
	public int getAccess_fail() {
		return access_fail;
	}
	
	/**
	 * @param accessfail the accessfail to set
	 */
	public void setAccessfail(int access_fail) {
		if (access_fail >= 0 && access_fail<=3) {
			this.access_fail = access_fail;
		} else {
			throw new DomainException(ERM_accessfail);
		}	
	}
	
	/**
	 * @return the locked
	 */
	public String getLocked() {
		return locked;
	}
	
	/**
	 * @param locked the locked to set
	 */
	public void setLocked(String locked) {
		if (Validator.length(locked, locked_MIN, locked_MAX)) {
			if (locked.compareTo("Y")==0||locked.compareTo("N")==0)
				this.locked = locked;
			else 
				throw new DomainException(MSG_locked);
		} else {
			throw new DomainException(ERM_locked);
		}
		
	}
	
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		if (Validator.length(active, active_MIN, active_MAX)) {
			if (active.compareTo("Y")==0||active.compareTo("N")==0)
			    this.active = active.trim();
			else 
				throw new DomainException(MSG_active);
		} else {
			throw new DomainException(ERM_active);
		}	
	}
	
}
