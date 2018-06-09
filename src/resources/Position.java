package resources;

public enum Position {
	CENTERED("CENTERED",5,1.8),
	UPPER_RIGHT("UPPER RIGHT",2.6,6.3),
	UPPER_LEFT("UPPER LEFT",90,6.3),
	BOTTOM_RIGHT("BOTTOM RIGHT",2.6,1.01),
	BOTTOM_LEFT("BOTTOM LEFT",90,1.01);
	
	private String description;
	private double x;
	private double y;
	
	private Position(String description,double x,double y){
		this.description=description;
		this.x=x;
		this.y=y;
	}

	public String getDescription() {
		return description;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
}
