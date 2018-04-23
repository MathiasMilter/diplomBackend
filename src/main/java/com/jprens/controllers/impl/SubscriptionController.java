package com.jprens.controllers.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.jprens.controllers.ControllerRegistry;
import com.jprens.controllers.Util;
import com.jprens.entity.Subscription;

public class SubscriptionController {
	
//	private DataController dataCtrl = ControllerRegistry.getDataCtrl();
	private MongoDBController dataCtrl = ControllerRegistry.getDataCtrl();
	
	//GET
	
	public Subscription[] getSubscriptions() {
		return dataCtrl.getSubscriptions();
	}
	
	public Subscription getSubscription(String subID) {
		return dataCtrl.getSubscription(subID);
	}
	
	public Subscription getCostumersSubscription(String costumerID) {
		Subscription[] allSubs = dataCtrl.getSubscriptions();
		for (int i = 0; i < allSubs.length; i++) 
			if (allSubs[i].getCostumerID().equals(costumerID)) 
				return allSubs[i];
			
		return null;
	}
	
	
	//POST
	
	public void saveSubscription(Subscription newSubscription) {
		
		if(ControllerRegistry.getCostumerCtrl().hasSubscription(newSubscription.getCostumerID())) {
			dataCtrl.changeSubscription(newSubscription);
		}else {
			dataCtrl.saveSubscription(newSubscription);
		}
		
	}
	
	
	//Service
	
	/*
	 * Return the next appointment as a string.
	 */
	public String getNextAppointment(String formattetDate, int intervalMonths) {
		String[] split = formattetDate.split("-");
		GregorianCalendar cal = new GregorianCalendar(Integer.parseInt((split[0])), Integer.parseInt((split[1])), Integer.parseInt((split[2])));
		Calendar now = Calendar.getInstance();
		GregorianCalendar cal_now = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH)+1, now.get(Calendar.DAY_OF_MONTH));
		
		
		
		while(cal.getTimeInMillis()<cal_now.getTimeInMillis()) {
			cal.add((GregorianCalendar.MONTH), intervalMonths);
		};
		
		
		
		
		String output = cal.get(GregorianCalendar.YEAR) +"-"+ Util.dayMonthFormatter(cal.get((GregorianCalendar.MONTH))) +"-"+ Util.dayMonthFormatter(cal.get((GregorianCalendar.DAY_OF_MONTH)));
		return output;
	}

}
