package com.shoppingList.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

import com.shoppingList.auth.AccessDeniedException;
import com.shoppingList.controllers.ControllerRegistry;
import com.shoppingList.controllers.loginController;
import com.shoppingList.entity.User;

@Path("/login")
public class loginService {
	
	@Context 
	ContainerRequestContext requestContext;

	
	private loginController loginCtrl = ControllerRegistry.getLoginCtrl();

	
	
	
	@POST
	public String validateUser(User user) throws AccessDeniedException {
		return loginCtrl.validateUser(user, requestContext);
	}

}
