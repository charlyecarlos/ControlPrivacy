package domain;

public class Metadata {

	private String name;
	private String data;
	private boolean sensitive;
	
	public Metadata(String name, String data, boolean sensitive) {
		this.name = name;
		this.data = data;
		this.sensitive = sensitive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isSensitive() {
		return sensitive;
	}

	public void setSensitive(boolean sensitive) {
		this.sensitive = sensitive;
	}
	
	
}
