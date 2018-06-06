package com.shoppingList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingList.auth.JWTHandler;
import com.shoppingList.auth.JWTHandler.AuthException;
import com.shoppingList.auth.JWTHandler.ExpiredLoginException;
import com.shoppingList.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class Main {

	public static void main(String[] args) {

		
		JWTHandler h = new JWTHandler();
		
		String key = h.generateJwtToken(new User("mathias", "123"));

		try {
			Jws<Claims> claimsJws = h.validateToken(key);
			
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.convertValue(claimsJws.getBody().get("user"),User.class);
			
			System.out.println(user.getUsername());
		} catch (AuthException e) {

		} catch (ExpiredLoginException e) {

		}
		
	}

}
