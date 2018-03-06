package domain;

import java.sql.Timestamp;

import exceptions.DAOException;
import util.Validator;

public class Statistic_file {

	private static final int type_file_MIN = 1;
	private static final int type_file_MAX = 50;
	private static final String ERM_type_file= "type file is not valid";
	
	private String id_file;
	private String module;
	private String type_file;
	private Timestamp date_analyse;
	
	
	
	public Statistic_file(String module, String type_file, Timestamp date_analyse) {
		this.module = module;
		this.type_file = type_file;
		this.date_analyse = date_analyse;
	}

	public Statistic_file(String id_file, String module, String type_file, Timestamp date_analyse) {
		this.id_file = id_file;
		this.module = module;
		this.type_file = type_file;
		this.date_analyse = date_analyse;
	}

	public String getId_file() {
		return id_file;
	}

	public void setId_file(String id_file) {
		this.id_file = id_file;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getType_file() {
		return type_file;
	}

	public void setType_file(String type_file) {
		if (Validator.length(type_file, type_file_MIN, type_file_MAX))
			this.type_file = type_file;
		else
			throw new DAOException(ERM_type_file);
	}

	public Timestamp getDate_analyse() {
		return date_analyse;
	}

	public void setDate_analyse(Timestamp date_analyse) {
		this.date_analyse = date_analyse;
	}
}
