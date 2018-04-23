package com.jprens.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jprens.controllers.ControllerRegistry;
import com.jprens.controllers.impl.SubscriptionController;
import com.jprens.entity.Subscription;

@Path("/subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubscriptionService {

	private SubscriptionController subscriptionCtrl = ControllerRegistry.getSubscriptionCtrl();
	
	// GET
	
	@GET
	public Subscription[] getSubscriptions() {
		return subscriptionCtrl.getSubscriptions();
	}
	
	@GET
	@Path("{subID}")
	public Subscription getSubscription(@PathParam("subID") String subID) {
		return subscriptionCtrl.getSubscription(subID);
	}
	
	@GET
	@Path("/costumer/{costumerID}")
	public Subscription getCostumersSubscription(@PathParam("costumerID") String costumerID) {
		return subscriptionCtrl.getCostumersSubscription(costumerID);
	}
	
	
	// POST
	
	@POST
	public void saveSubscription(Subscription newSubscription) {
		subscriptionCtrl.saveSubscription(newSubscription);
	}
}
