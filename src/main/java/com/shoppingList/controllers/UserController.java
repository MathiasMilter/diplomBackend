package com.shoppingList.controllers;

import com.shoppingList.entity.User;

public class UserController {
	
	private DataController dataCtrl = ControllerRegistry.getDataCtrl();
	
	
	public User[] getUsers() {
		return dataCtrl.getUsers();
	}
	
	public User getUser(String username) {
		return dataCtrl.getUser(username);
	}
	
	public void saveUser(User newUser) {
		
		if (dataCtrl.getUser(newUser.getUsername()) == null) {
			dataCtrl.saveUser(newUser);
		} else {
			System.out.println("user already exists");
			//THROW EXCPETION :)
		}
		
	}

}
