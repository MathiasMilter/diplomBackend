package com.jprens.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jprens.controllers.ControllerRegistry;

public class Costumer {
	
	private String name, email, phoneNumber, costumerID;
	private Adress adress;
	
	public Costumer(String name, String email, String phoneNumber, Adress adress) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.costumerID = phoneNumber+""+adress.getZipCode();
	}
	
	public Costumer(String costumerID, String name, String email, String phoneNumber, Adress adress) {
		this.costumerID = costumerID;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.costumerID = costumerID;
	}
	
	
	@JsonCreator
	public Costumer(@JsonProperty("name") String name, @JsonProperty("email") String email,@JsonProperty("phoneNumber") String phoneNumber,@JsonProperty("streetName") String streetName,@JsonProperty("houseNumber") String houseNumber,@JsonProperty("zipCode") String zipCode,@JsonProperty("city") String city) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.adress = new Adress(streetName, houseNumber, zipCode, city);
		this.costumerID = phoneNumber+""+houseNumber+""+zipCode;
	}
	
	public String getCostumerID() {
		return costumerID;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAdress() {
		return adress.toString();
	}
	
	public Adress getAdressObject() {
		return this.adress;
	}
	
	public Boolean getHasSubscription() {
		return ControllerRegistry.getCostumerCtrl().hasSubscription(this.costumerID);
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}
}

