package domain;

public class Metadata {

	private String name;
	private String data;
	private boolean sensitive;
	private boolean remove;
	
	public Metadata(String name, String data, boolean sensitive,boolean remove) {
		this.name = name;
		this.data = data;
		this.sensitive = sensitive;
		this.remove=remove;
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

	public boolean canRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	
}
