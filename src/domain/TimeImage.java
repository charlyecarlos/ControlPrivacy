package domain;

public class TimeImage {
	
	private int minutes;
	private String description;
	
	public TimeImage() {}
	
	public TimeImage(int minutes,String description){
		this.minutes=minutes;
		this.description=description;
	}
	
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
