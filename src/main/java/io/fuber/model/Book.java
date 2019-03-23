package io.fuber.model;

import javax.validation.constraints.NotNull;

public class Book {

	@NotNull(message = "UserId should not be null")
	private int userId;

	@NotNull(message = "Latitude value should not be null")
	private float latitude;

	@NotNull(message = "Longitude value should not be null")
	private float longitude;

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

}
