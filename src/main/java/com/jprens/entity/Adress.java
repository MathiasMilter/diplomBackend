package com.jprens.entity;

public 	class Adress {
	String streetName, houseNumber, zipCode, city, country;
	
	public String getCity() {
		return city;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCountry() {
		return country;
	}

	public Adress(String streetName, String houseNumber, String zipCode, String city) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
		this.city = city;
		this.country = "Danmark";
	}
	
	@Override
	public String toString() {
		return city + " " + zipCode + " " + streetName + " " + houseNumber + " " + country;
	}
}
