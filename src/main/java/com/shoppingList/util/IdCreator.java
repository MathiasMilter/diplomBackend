package com.shoppingList.util;

import java.util.Date;

import com.shoppingList.entity.User;

public class IdCreator {
	
	public static String createProductID() {
		return new Date().getTime() + "";
	}
	
	public static String createShoppingListID(String ownerUsername) {
		return ownerUsername.substring(0, 1)+new Date().getTime();
	}
}
