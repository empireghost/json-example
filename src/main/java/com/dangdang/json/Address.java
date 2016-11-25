package com.dangdang.json;

public class Address {

	private String zipCode;
	
	private String location;
	
	public Address() {
	}

	public Address(String zipCode, String location) {
		super();
		this.zipCode = zipCode;
		this.location = location;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
