package com.shoppingList.auth;

import java.security.Key;
import java.util.Calendar;

import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingList.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;


public class JWTHandler {
	private static final int TOKEN_EXPIRY = 2880;
	public static final String JWT_SECRET_KEY = System.getenv("JWT_SECRET_KEY");

	public static class AuthException extends Exception {
		public AuthException(String string) {
			super(string);
		}

	}
	public static class ExpiredLoginException extends Exception {
		public ExpiredLoginException(String string) { super(string);
		}
}
	
	
	
	private static Key key;
	private static Key getKey(){
		if (key==null) {
			if (JWT_SECRET_KEY != null && JWT_SECRET_KEY != "") {
				String string = JWT_SECRET_KEY;
				key = new SecretKeySpec(string.getBytes(), 0, string.length(), "HS512");
			} else {
				key = MacProvider.generateKey(SignatureAlgorithm.HS512);
			}
		}
		return key;
	}

	public String generateJwtToken(User user){
		Calendar expiry = Calendar.getInstance();
		expiry.add(Calendar.MINUTE, TOKEN_EXPIRY);
		return Jwts.builder()
				.setIssuer("Milter")
				.claim("user", user)
				.signWith(SignatureAlgorithm.HS512, getKey())
				.setExpiration(expiry.getTime())
				.compact();
	}


	public Jws<Claims> validateToken(String tokenString) throws AuthException, ExpiredLoginException {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(tokenString).getBody();
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.convertValue((claims.get("user")), User.class);
			return Jwts.parser().setSigningKey(getKey()).parseClaimsJws(tokenString);
		} catch (ExpiredJwtException e) {
			throw new ExpiredLoginException("Token too old!");
		} catch (UnsupportedJwtException e) {
			throw new AuthException("UnsupportedToken");
		} catch (MalformedJwtException e) {
			throw new AuthException("Malformed Token");
		} catch (SignatureException e) {
			throw new AuthException("Token signature invalid");
		} catch (IllegalArgumentException e) {
			throw new AuthException("Illegal Argument: " + e.getMessage());
		}

	}
}
