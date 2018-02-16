package domain;



import exceptions.DomainException;
import util.Validator;

public class User {
	private static final String ERM_us = "us no valido";
	private static final int us_MIN = 1;
	private static final int us_MAX = 15;
	private static final String ERM_password = "contraseña no valida";
	private static final int password_MIN = 1;
	private static final int password_MAX = 32;
	private static final String ERM_email = "Email no valido";
	private static final int email_MIN = 1;
	private static final int email_MAX = 50;
	
	private static final String ERM_question = "la question no  es valida es muy larga ó es obligatoria ";
	private static final int question_MIN = 1;
	private static final int question_MAX = 50;
	
	private static final String ERM_answer = "recuerdo no valido";
	private static final int answer_MIN = 1;
	private static final int answer_MAX = 25;
	
	private static final String ERM_accessfail = "numero de accesos permitidos maximo 3";
	
	private static final String MSG_active = "active solo puede tomar valores S/N";
	private static final String ERM_active = "active no valido";
	private static final int active_MIN = 1;
	private static final int active_MAX = 1;
	
	
	private static final String MSG_locked = "Locked solo puede tomar valores S/N";;
	private static final String ERM_locked = "Locked no valido";
	private static final int locked_MIN = 1;
	private static final int locked_MAX = 1;
	
	
	
	private  String email;
	private  String pwd;
	private  TypeUser type;
	private  String question;
	private  String answer;
	private  int accessfail=0;
	private  String locked="N";
	private  String active="S";
	
// para crear un us con la PK	
public  User(String email)  {
	this.email=email;
		
	}
// para crear un us
public User(String password, TypeUser type,
		        String email,String question,String answer) {
	setPassword(password);
	setType(type);
	setEmail(email);
	setQuestion(question);
	setAnswer(answer);
	
}

	


	
	/**
 * @param pwd
 * @param type
 * @param email
 * @param question
 * @param answer
 * @param accessfail
 * @param locked
 * @param active
 */
public User(String us, String pwd, TypeUser type, String email,
		String question, String answer, int accessfail,
		String locked, String active) {
	this.pwd = pwd;
	this.type = type;
	this.email = email;
	this.question = question;
	this.answer = answer;
	this.accessfail = accessfail;
	this.locked = locked;
	this.active = active;
}
	public User() {}
	
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
	public TypeUser getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(TypeUser type) {
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
		//System.out.println(email);
		if (Validator.email(email, email_MIN, email_MAX)) {
			this.email = email.trim();
		} else {
			throw new DomainException(ERM_email);
		}
		
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		if (Validator.length(question, question_MIN, question_MAX)) {
			this.question = question;
		} else {
			throw new DomainException(ERM_question);
		}
		
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		if (Validator.length(answer, answer_MIN, answer_MAX)) {
			this.answer = answer.trim();
		} else {
			throw new DomainException(ERM_answer);
		}
		
	}
	/**
	 * @return the accessfail
	 */
	public int getAccessfail() {
		
		return accessfail;
	}
	/**
	 * @param accessfail the accessfail to set
	 */
	public void setAccessfail(int accessfail) {
		if (accessfail >= 0 && accessfail<=3) {
			this.accessfail = accessfail;
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
			if (locked.compareTo("S")==0||locked.compareTo("N")==0)
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
			if (active.compareTo("S")==0||active.compareTo("N")==0)
			    this.active = active.trim();
			else 
				throw new DomainException(MSG_active);
		} else {
			throw new DomainException(ERM_active);
		}
		
	}
	
}
