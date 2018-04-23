package com.jprens.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jprens.controllers.ControllerRegistry;
import com.jprens.controllers.impl.OrderController;
import com.jprens.entity.Order;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {
	
	private OrderController orderCtrl = ControllerRegistry.getOrderCtrl();
	
	//GET
	@GET
	public Order[] getOrders() {
		return orderCtrl.getOrders();
	}
	
	
	@GET
	@Path("/costumer/{costumerID}")
	public Order[] getOrders(@PathParam("costumerID") String costumerID) {
		return orderCtrl.getCostumersOrders(costumerID);
	}
	
	@GET
	@Path("/{orderID}")
	public Order getOrder(@PathParam("orderID") String orderID) {
		return orderCtrl.getOrder(orderID); 
	}
	
	
	//POST
	
	@POST
	public void saveOrder(Order newOrder) {
		orderCtrl.saveOrder(newOrder);
	}
	
	//PUT
	
	@PUT
	@Path("/{orderID}")
	public void updateOrder(@PathParam("orderID") String orderID, Order newOrder) {
		orderCtrl.updateOrder(orderID, newOrder);
	}
	
	@PUT
	@Path("/pay/{orderID}")
	public void payOrder(@PathParam("orderID") String orderID) {
		orderCtrl.payOrder(orderID);
	}
	
	@PUT
	@Path("/done/{orderID}")
	public void doneOrder(@PathParam("orderID") String orderID) {
		orderCtrl.doneOrder(orderID);
	}
	
	//DELETE
	
	@DELETE
	@Path("{orderID}")
	public void deleteOrder(@PathParam("orderID") String orderID) {
		orderCtrl.deleteOrder(orderID);
	}
	

}
