package com.shoppingList.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoppingList.util.IdCreator;

public class Product {
	
	private String name, productID, count, shoppingListID;
	
	


	@JsonCreator
	public Product(@JsonProperty("shoppingListID") String shoppingListID, @JsonProperty("name") String name, @JsonProperty("count") String count) {
		this.shoppingListID = shoppingListID;
		this.productID = IdCreator.createProductID();
		this.name = name;
		this.count = count;
	}
	
	public Product(String shoppingListID, String productID, String name, String count) {
		this.shoppingListID = shoppingListID;
		this.productID = productID; 
		this.name = name;
		this.count = count;
	}

	public String getShoppingListID() {
		return shoppingListID;
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
