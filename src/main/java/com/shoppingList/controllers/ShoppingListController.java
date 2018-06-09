package com.shoppingList.controllers;

import com.shoppingList.entity.Participant;
import com.shoppingList.entity.ShoppingList;

public class ShoppingListController {
	
	private DataController dataCtrl = ControllerRegistry.getDataCtrl();
	
	public ShoppingList[] getShoppingLists() {
		return dataCtrl.getShoppingLists();
	}
	
	public ShoppingList[] getOwnedShoppingLists(String username) {
		return dataCtrl.getOwnedShoppingLists(username);
	}
	
	public ShoppingList getShoppingList(String shoppingListID) {
		return dataCtrl.getShoppingList(shoppingListID);
	}
	
	public void saveShoppingList(ShoppingList newShoppingList) {
		dataCtrl.saveShoppingList(newShoppingList);
	}
	
	public void addParticipant(Participant newParticipant) {
		dataCtrl.addParticipant(newParticipant);
	}

}
