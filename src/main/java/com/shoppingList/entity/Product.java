package com.shoppingList.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoppingList.util.IdCreator;

public class Product {
	
	private String sessionID, name, productID;


	private String count;
	
	
	@JsonCreator
	public Product(@JsonProperty("name") String name, @JsonProperty("count") String count) {
		this.productID = IdCreator.createID();
		this.name = name;
		this.count = count;
	}
	
	public Product(String id, String name, String count) {
		this.productID = id; 
		this.name = name;
		this.count = count;
	}
	
	public String getSessionID() {
		return sessionID;
	}

	public String getProductID() {
		return productID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCount() {
		return count;
	}
	
	public void setCount(String count) {
		this.count = count;
	}
	

}
