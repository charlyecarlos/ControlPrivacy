package domain;

import java.sql.Timestamp;

import exceptions.DomainException;
import util.Validator;

public class Image {
	
	private static final String ERM_url_redirect = "invalid url_redirect";
	private static final int url_recirect_MIN = 1;
	private static final int url_redirect_MAX = 300;
	

	private static final String ERM_url_image = "invalid url_direct";
	private static final int url_image_MIN = 1;
	private static final int url_image_MAX = 300;
	

	private static final String ERM_visits = "invalid visits";
	private static final int visits_DECIMAL = 10;
	private static final int visits_ESCALE = 1;
	
	private String url_redirect;
	private String url_image;
	private User owner;
	private Timestamp date_creation;
	private Timestamp expiration_date;
	private int visits;
	
	public Image(String url_redirect){
		this.url_redirect=url_redirect;
	}
	
	//	Creation Image
	public Image(String url_redirect,String url_image,User owner,Timestamp date_creation,Timestamp expiration_date){
		setUrl_redirect(url_redirect);
		setUrl_image(url_image);
		setOwner(owner);
		setDate_creation(date_creation);
		setExpiration_date(expiration_date);
	}
	
	public Image(String url_redirect,String url_image,User owner,Timestamp date_creation,Timestamp expiration_date,int visits){
		this.url_redirect=url_redirect;
		this.url_image=url_image;
		this.owner=owner;
		this.date_creation=date_creation;
		this.expiration_date=expiration_date;
		this.visits=visits;
	}
	
	public String getUrl_redirect() {
		return url_redirect;
	}
	
	public void setUrl_redirect(String url_redirect) {
		if (Validator.length(url_redirect, url_recirect_MIN, url_redirect_MAX))
			this.url_redirect = url_redirect;
		else
			throw new DomainException(ERM_url_redirect);
	}
	
	public String getUrl_image() {
		return url_image;
	}
	
	public void setUrl_image(String url_image) {
		if (Validator.length(url_image, url_image_MIN, url_image_MAX))
			this.url_image = url_image;
		else
			throw new DomainException(ERM_url_image);
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public Timestamp getDate_creation() {
		return date_creation;
	}
	
	public void setDate_creation(Timestamp date_creation) {
		this.date_creation = date_creation;
	}
	
	public Timestamp getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Timestamp expiration_date) {
		this.expiration_date = expiration_date;
	}

	public int getVisits() {
		return visits;
	}
	
	public void setVisits(int visits) {
		if(Validator.lengthDecimal(visits,visits_DECIMAL,visits_ESCALE))
			this.visits = visits;
		else
			throw new DomainException(ERM_visits);
	}
}
