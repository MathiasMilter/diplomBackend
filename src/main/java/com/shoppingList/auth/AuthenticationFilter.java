package com.shoppingList.auth;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import com.shoppingList.auth.JWTHandler.AuthException;
import com.shoppingList.auth.JWTHandler.ExpiredLoginException;
import com.shoppingList.controllers.ControllerRegistry;


@Provider
public class AuthenticationFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
	private static final String SECURED_URL_PREFIX = "login";
	private JWTHandler jwthandler = ControllerRegistry.getJwtHandler();
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (!(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX))) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				
				try {
					jwthandler.validateToken(authToken);
					return;
				} catch (AuthException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExpiredLoginException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			Response unauthorizedStatus = Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("User cannot acces this resource")
					.build();
			requestContext.abortWith(unauthorizedStatus);
			
			
		}
		

		
	}

}
