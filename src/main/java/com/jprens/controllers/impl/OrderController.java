package com.jprens.controllers.impl;

import com.jprens.controllers.ControllerRegistry;
import com.jprens.entity.Order;

public class OrderController {

//	private DataController dataCtrl = ControllerRegistry.getDataCtrl();
	private MongoDBController dataCtrl = ControllerRegistry.getDataCtrl();


	//GET

	public Order[] getOrders() {
		return dataCtrl.getOrders();
	}

	public Order[] getCostumersOrders(String costumerID) {
		return dataCtrl.getCostumersOrders(costumerID);
	}

	public Order getOrder(String id) {
		return dataCtrl.getOrder(id);
	}

	//POST

	public void saveOrder(Order newOrder) {
			dataCtrl.saveOrder(newOrder);
	}

	// PUT

	public void payOrder(String orderID) {
		dataCtrl.payOrder(orderID);
	}

	public void updateOrder(String orderID, Order newOrder) {
		dataCtrl.changeOrder(orderID, newOrder);
	}
	
	public void doneOrder(String orderID) {
		dataCtrl.doneOrder(orderID);
	}
	
	//DELETE
	
	public void deleteOrder(String orderID) {
		dataCtrl.deleteOrder(orderID);
	}


}
