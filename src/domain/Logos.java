package domain;

import exceptions.DomainException;
import util.Validator;

public class Logos {

	private static final String ERM_url_image = "invalid url_direct";
	private static final int url_image_MIN = 1;
	private static final int url_image_MAX = 300;

	private User user;
	private String url_image;
	
	public Logos(User user, String url_image) {
		this.user = user;
		this.url_image = url_image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUrl_image() {
		return url_image;
	}

	public void setUrl_image(String url_image) {
		if(Validator.length(url_image, url_image_MIN,url_image_MAX))
			this.url_image = url_image;
		else
			throw new DomainException(ERM_url_image);
	}
	
	
	
}
