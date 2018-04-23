package com.jprens.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jprens.controllers.ControllerRegistry;
import com.jprens.controllers.Util;

public class Subscription {
	
	private String subscriptionID, description, startDate, costumerID;
	private int intervalMonths;
	private float price;
	
	@JsonCreator
	public Subscription(@JsonProperty("costumerID") String costumerID, @JsonProperty("description") String description, @JsonProperty("startDate") String startDate, @JsonProperty("intervalMonths") int intervalMonths, @JsonProperty("price") float price){
		this.costumerID = costumerID;
		this.description = description;
		this.startDate = startDate;
		this.intervalMonths = intervalMonths;
		this.price = price;
		this.subscriptionID = intervalMonths + "" + new Date().getTime();
	}
	
	
	public Subscription(String costumerID, String description, String startDate, int intervalMonths, double price, String subscriptionID){
		this.costumerID = costumerID;
		this.description = description;
		this.startDate = startDate;
		this.intervalMonths = intervalMonths;
		this.price = (float) price;
		this.subscriptionID = subscriptionID;
	}
	
	public String getSubscriptionID() {
		return subscriptionID;
	}
	public String getCostumerID() {
		return costumerID;
	}


	public String getDescription() {
		return description;
	}


	public String getStartDate() {
		return startDate;
	}


	public int getIntervalMonths() {
		return intervalMonths;
	}

	public String getCostumer() {
		return ControllerRegistry.getCostumerCtrl().getCostumer(costumerID).getName();
	}
	
	public String getAdress() {
		return ControllerRegistry.getCostumerCtrl().getCostumer(costumerID).getAdress();
	}
	
	public String getNextOrder() {
		return ControllerRegistry.getSubscriptionCtrl().getNextAppointment(startDate, intervalMonths);
	}

	public String getPriceString() {
		return Util.decimalFormatter(price);
	}
	public float getPrice() {
		return price;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setIntervalMonths(int intervalMonths) {
		this.intervalMonths = intervalMonths;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
