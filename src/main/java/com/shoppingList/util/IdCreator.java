package com.shoppingList.util;

import java.util.Date;

public class IdCreator {
	
	public static String createID() {
		return new Date().getTime() + "";
	}
	
	public static String createUserID(String username) {
		return username.substring(0, 1) + new Date().getTime();
	}
}
