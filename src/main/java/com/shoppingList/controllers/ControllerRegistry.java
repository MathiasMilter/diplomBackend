package com.shoppingList.controllers;

public class ControllerRegistry {
	
	private static DataController dataCtrl;
	private static ProductController productCtrl;

	
	public static synchronized DataController getDataCtrl() {
		if (dataCtrl == null) dataCtrl = new DataController();
		return dataCtrl;
	}
	
	public static synchronized ProductController getProductCtrl() {
		if (productCtrl == null) productCtrl = new ProductController();
		return productCtrl;
	}
	
}
