package domain;

import exceptions.DomainException;
import util.Validator;



public class Type_User {
	private static final int description_MIN = 1;
	private static final int description_MAX = 15;
	private static final String ERM_description = "description of the user type is not valid";
	
	int type;
	String description ;
	
	public static Type_User creartypeUsuario (int type) {
		Type_User typeusuario =new Type_User(); 
		typeusuario.setType(type);
		return typeusuario;
	}
	public Type_User(int type) {
		this.type = type;
	}
	public Type_User(int type, String description) {
		this.type = type;
		this.description = description;
	}
	public Type_User() {
	}
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		if (Validator.length(description,description_MIN, description_MAX)) {
			this.description = description.trim();
		} else {
			throw new DomainException(ERM_description);
		}
		
	}
	

}
