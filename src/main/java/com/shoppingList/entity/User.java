package com.shoppingList.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoppingList.util.IdCreator;

public class User {
	
	private String email, name, username, password;
	
	@JsonCreator
	public User(@JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("username") String username, @JsonProperty("password") String password) {
		this.name=name;
		this.email=email;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	

}
