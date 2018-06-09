package com.shoppingList.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shoppingList.controllers.ControllerRegistry;
import com.shoppingList.controllers.ShoppingListController;
import com.shoppingList.entity.Participant;
import com.shoppingList.entity.ShoppingList;

@Path("/shoppingList")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShoppingListService {

	private ShoppingListController shopListContrl = ControllerRegistry.getShopListCtrl();
	
	
	@GET
	public ShoppingList[] getShoppingLists() {
		return shopListContrl.getShoppingLists();
	}
	
	@GET
	@Path("/ownedBy/{username}")
	public ShoppingList[] getOwnedShoppingLists(@PathParam("username") String username) {
		return shopListContrl.getOwnedShoppingLists(username);
	}
	
	@GET
	@Path("/{shoppingListID}")
	public ShoppingList getShoppingList(@PathParam("shoppingListID") String shoppingListID) {
		return shopListContrl.getShoppingList(shoppingListID);
	}
	
	@POST
	public void saveShoppingList(ShoppingList newShoppingList) {
		shopListContrl.saveShoppingList(newShoppingList);
	}
	
	@PUT
	@Path("/addParticipant")
	public void addParticipant(Participant newParticipant) {
		shopListContrl.addParticipant(newParticipant);
	}
	
}
