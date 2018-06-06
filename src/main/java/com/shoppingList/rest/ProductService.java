package com.shoppingList.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shoppingList.controllers.ControllerRegistry;
import com.shoppingList.controllers.ProductController;
import com.shoppingList.entity.Product;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductService {

	private ProductController productCtrl = ControllerRegistry.getProductCtrl();
	
	@GET
	public Product[] getProducts() {
		return productCtrl.getProducts();
	}
	
	@POST
	public void saveProduct(Product newProduct) {
		productCtrl.saveProduct(newProduct);
	}
	
	@DELETE
	@Path("/{productID}")
	public void deleteProduct(@PathParam("productID") String productID) {
		productCtrl.deleteProduct(productID);
	}
	
	
}
