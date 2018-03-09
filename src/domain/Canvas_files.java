package domain;

public class Canvas_files {

	private String type_file;
	private long cont;
	
	public Canvas_files(String type_file, long cont) {
		this.type_file = type_file;
		this.cont = cont;
	}

	public String getType_file() {
		return type_file;
	}

	public void setType_file(String type_file) {
		this.type_file = type_file;
	}

	public long getCont() {
		return cont;
	}

	public void setCont(long cont) {
		this.cont = cont;
	}
}
