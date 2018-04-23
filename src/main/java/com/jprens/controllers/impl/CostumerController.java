package com.jprens.controllers.impl;

import com.jprens.controllers.ControllerRegistry;
import com.jprens.entity.Costumer;
import com.jprens.entity.Subscription;

public class CostumerController {
	
//	private DataController dataCtrl = ControllerRegistry.getDataCtrl();
	private MongoDBController dataCtrl = ControllerRegistry.getDataCtrl();
	
	
	//GET
	
	public Costumer[] getCostumers() {
		return dataCtrl.getCostumers();
	}
	
	public Costumer getCostumer(String id) {
		return dataCtrl.getCostumer(id);
	}
	
	
	//POST
	
	public void saveCostumer(Costumer newCostumer) {
		dataCtrl.saveCostumer(newCostumer);
	}

	
	// service
	
	public boolean hasSubscription(String costumerID) {
		Subscription[] subscriptions = dataCtrl.getSubscriptions();
		for (int i = 0; i < subscriptions.length; i++) 
			if (subscriptions[i].getCostumerID().equals(costumerID))
				return true;
			
		return false;
	}
	
	// PUT
	
	public void changeCostumer(String costumerID, Costumer newCostumer) {
		dataCtrl.changeCostumer(costumerID, newCostumer);
	}
	
	//DELETE
	
	public void deleteCostumer(String costumerID) {
		dataCtrl.deleteSubscription(costumerID);
		dataCtrl.deleteCostumer(costumerID);
	}
	
}
