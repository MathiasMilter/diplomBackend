package com.jprens.controllers;

import com.jprens.controllers.impl.CostumerController;
import com.jprens.controllers.impl.MongoDBController;
import com.jprens.controllers.impl.OrderController;
import com.jprens.controllers.impl.SubscriptionController;

public class ControllerRegistry {


	private static CostumerController costumerCtrl;
	private static OrderController orderCtrl;
	private static SubscriptionController subscriptionCtrl;
	
	private static MongoDBController dataCtrl;

	public static CostumerController getCostumerCtrl() {
		if (costumerCtrl == null) costumerCtrl = new CostumerController();
		return costumerCtrl;
	}

	public static OrderController getOrderCtrl() {
		if (orderCtrl == null) orderCtrl = new OrderController();
		return orderCtrl;
	}
	
	public static SubscriptionController getSubscriptionCtrl() {
		if (subscriptionCtrl == null) subscriptionCtrl = new SubscriptionController();
		return subscriptionCtrl;
	}
	public static MongoDBController getDataCtrl() {
		if (dataCtrl == null) dataCtrl = new MongoDBController();
		return dataCtrl;
	}

}
