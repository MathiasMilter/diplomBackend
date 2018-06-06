package com.shoppingList.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoppingList.util.IdCreator;

public class ShoppingList {
	private String name;
	private String shoppingListID;
	private String ownerUsername;
	private ArrayList<String> participants;
	


	@JsonCreator
	public ShoppingList(@JsonProperty("name") String name, @JsonProperty("ownerUsername") String ownerUsername) {
		this.name = name;
		this.ownerUsername = ownerUsername;
		this.shoppingListID=IdCreator.createShoppingListID(ownerUsername);
		this.participants = new ArrayList<String>();
	}
	
	public ShoppingList(String name, String shoppingListID, String ownerUsername, ArrayList<String> participants) {
		this.name = name;
		this.shoppingListID = shoppingListID;
		this.ownerUsername = ownerUsername;
		this.participants = participants;
	}
	
	public ArrayList<String> getParticipants() {
		return participants;
	}
	public String getName() {
		return name;
	}
	public String getShoppingListID() {
		return shoppingListID;
	}
	public String getOwnerUsername() {
		return ownerUsername;
	}
	
	public void addparticipant(String participantUsername) {
		this.participants.add(participantUsername);
	}
	
}
