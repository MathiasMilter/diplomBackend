package com.shoppingList.controllers;

import com.shoppingList.entity.Product;

public class ProductController {
	
	
	private DataController dataCtrl = ControllerRegistry.getDataCtrl();
	
	// GETTERS
	
	public Product[] getProducts() {
		return dataCtrl.getProducts();
	}
	
	
	// POST
	
	public void saveProduct(Product newProduct) {
		dataCtrl.saveProduct(newProduct);
	}

	
	// DELETE
	
	public void deleteProduct(String productID) {
		dataCtrl.deleteProduct(productID);
	}
}
