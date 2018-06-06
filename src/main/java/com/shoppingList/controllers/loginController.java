package com.shoppingList.controllers;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.shoppingList.auth.AccessDeniedException;
import com.shoppingList.auth.JWTHandler;
import com.shoppingList.entity.User;

public class loginController {
	private JWTHandler jwthandler = ControllerRegistry.getJwtHandler();
	private DataController dataCtrl = ControllerRegistry.getDataCtrl();

	
	public String validateUser(User user, ContainerRequestContext requestContext) throws AccessDeniedException {
		if (dataCtrl.getUser(user.getUsername()) != null) 
			if (dataCtrl.getUser(user.getUsername()).getPassword().equals(user.getPassword())) {
				return jwthandler.generateJwtToken(user);
			}
		
		Response unauthorizedStatus = Response
				.status(Response.Status.UNAUTHORIZED)
				.entity("Wrong Login")
				.build();
		requestContext.abortWith(unauthorizedStatus);
		
		throw new AccessDeniedException("Wrong login.");
		
	}

}
