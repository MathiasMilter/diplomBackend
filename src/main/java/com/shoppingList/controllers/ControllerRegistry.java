package com.shoppingList.controllers;

import com.shoppingList.auth.JWTHandler;

public class ControllerRegistry {
	
	private static DataController dataCtrl;
	private static ProductController productCtrl;
	private static UserController userCtrl;
	private static loginController loginCtrl;
	
	private static JWTHandler jwthandler;

	
	public static synchronized DataController getDataCtrl() {
		if (dataCtrl == null) dataCtrl = new DataController();
		return dataCtrl;
	}
	
	public static synchronized ProductController getProductCtrl() {
		if (productCtrl == null) productCtrl = new ProductController();
		return productCtrl;
	}
	
	public static synchronized UserController getUserCtrl() {
		if (userCtrl == null) userCtrl = new UserController();
		return userCtrl;
	}
	
	public static synchronized JWTHandler getJwtHandler() {
		if (jwthandler == null) jwthandler =  new JWTHandler();
		return jwthandler;
	}
	
	public static synchronized loginController getLoginCtrl() {
		if (loginCtrl == null) loginCtrl = new loginController();
		return loginCtrl;
	}
}
