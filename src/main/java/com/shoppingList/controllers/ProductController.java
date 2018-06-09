package com.shoppingList.controllers;

import java.util.ArrayList;

import com.shoppingList.entity.Product;

public class ProductController {


	private DataController dataCtrl = ControllerRegistry.getDataCtrl();

	// GETTERS

	public Product[] getProducts(String shoppingListID) {
		Product[] all = dataCtrl.getProducts();
		ArrayList<Product> output = new ArrayList<Product>();

		for (int i = 0; i < all.length; i++) {
			if (all[i].getShoppingListID().equals(shoppingListID)) {
				output.add(all[i]);
			}
		}

		return output.stream().toArray(Product[]::new);
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
