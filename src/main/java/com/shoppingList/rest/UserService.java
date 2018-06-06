package com.shoppingList.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shoppingList.controllers.ControllerRegistry;
import com.shoppingList.controllers.UserController;
import com.shoppingList.entity.User;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {

	private UserController userCtrl = ControllerRegistry.getUserCtrl();
	
	@GET
	public User[] getUsers() {
		return userCtrl.getUsers();
	}
	
	@GET
	@Path("/{username}")
	public User getUser(@PathParam("username") String username) {
		return userCtrl.getUser(username);
	}
	
	@POST
	public void saveUser(User newUser) {
		userCtrl.saveUser(newUser);
	}
	
	
	
	
	
}
