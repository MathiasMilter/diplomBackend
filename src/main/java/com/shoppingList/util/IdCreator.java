package com.shoppingList.util;

import java.util.Date;

public class IdCreator {
	
	public static String createID() {
		return new Date().getTime() + "";
	}
}
