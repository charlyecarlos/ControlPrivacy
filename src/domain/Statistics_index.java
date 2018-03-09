package domain;

public class Statistics_index {

	private int month;
	private int year;
	private long cont;
	
	public Statistics_index(int month,int year, long cont) {
		this.month = month;
		this.year = year;
		this.cont = cont;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getYear(){
		return year;
	}
	
	public void setYear(int year){
		this.year=year;
	}
	
	public long getCont() {
		return cont;
	}
	
	public void setCont(long cont) {
		this.cont = cont;
	}
}
