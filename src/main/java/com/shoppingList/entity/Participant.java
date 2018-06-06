package com.shoppingList.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Participant {
	
	private String username;
	private String shoppingListID;

	@JsonCreator
	public Participant(@JsonProperty("username") String username, @JsonProperty("shoppingListID") String shoppingListID) {
		this.username = username;
		this.shoppingListID = shoppingListID;
	}

	
	public String getUsername() {
		return username;
	}

	public String getShoppingListID() {
		return shoppingListID;
	}
	
}
