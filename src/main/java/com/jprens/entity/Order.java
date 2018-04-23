package com.jprens.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jprens.controllers.ControllerRegistry;
import com.jprens.controllers.Util;

public class Order {
	
	private String orderID, time, description, costumerID;
	private boolean done, payed;

	private float prize;
	
	
	@JsonCreator
	public Order(@JsonProperty("costumerID") String costumerID,@JsonProperty("time") String time,@JsonProperty("description") String description, @JsonProperty("done")boolean done,@JsonProperty("payed") boolean payed,@JsonProperty("prize") float prize) {
		this.costumerID = costumerID;
		this.time = time; 
		this.description = description;
		this.done = done;
		this.payed = payed;
		this.prize = prize;
		this.orderID = costumerID.substring(0, costumerID.length()-1) + new Date().getTime();
	}
	
	
	public Order(String orderID, String costumerID, String time, String description, boolean done, boolean payed, float prize) {
		this.costumerID = costumerID;
		this.time = time; 
		this.description = description;
		this.done = done;
		this.payed = payed;
		this.prize = prize;
		this.orderID = orderID;
	}
	
	
	
	public Order(String orderID, String costumerID, String time, String description, boolean done, boolean payed, double prize) {
		this.costumerID = costumerID;
		this.time = time; 
		this.description = description;
		this.done = done;
		this.payed = payed;
		this.prize = (float) prize;
		this.orderID = orderID;
	}


	public void setDone(boolean done) {
		this.done = done;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	public String getCostumerID() {
		return costumerID;
	}
	
	public String getOrderID() {
		return orderID;
	}

	public String getTime() {
		return time;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDone() {
		return done;
	}

	public boolean isPayed() {
		return payed;
	}

	public float getPrize() {
		return prize;
	}
	
	public String getPrizeString() {
		return Util.decimalFormatter(prize);
	}
	
	public String getCostumer() {
		return ControllerRegistry.getCostumerCtrl().getCostumer(costumerID).getName();
	}
	
	public String getAdress() {
		return ControllerRegistry.getCostumerCtrl().getCostumer(costumerID).getAdress();
	}
	
	public void setTime(String time) {
		this.time = time;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public void setPrize(float prize) {
		this.prize = prize;
	}

}
