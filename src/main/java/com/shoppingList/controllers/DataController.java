package com.shoppingList.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.shoppingList.entity.Participant;
import com.shoppingList.entity.Product;
import com.shoppingList.entity.ShoppingList;
import com.shoppingList.entity.User;

public class DataController {

	private MongoClientURI uri;
	private MongoClient client;
	private MongoDatabase db;


	private MongoCollection<Document> products;
	private MongoCollection<Document> users;
	private MongoCollection<Document> shoppingList;
	public DataController() {

		try {
			uri  = new MongoClientURI("mongodb://"+System.getenv("MONGOUSER")+":"+System.getenv("MONGOPASS")+"@ds147180.mlab.com:47180/shoppingmilter");
			client = new MongoClient(uri);
			db = client.getDatabase(uri.getDatabase());

			products = db.getCollection("products");
			users = db.getCollection("users");
			shoppingList = db.getCollection("shoppingList");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	
//----------------------------------------------------------------------------------------------	
//	
//	
//	Products methods!
//	
//	
//----------------------------------------------------------------------------------------------
	
	
	public Product[] getProducts() {
		MongoCursor<Document> cursor = products.find().iterator();

		Product[] output = new Product[(int) products.count()];
		int count = 0;

		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();

				output[count] = new Product(doc.getString("shoppingListID"), doc.getString("productID"), doc.getString("name"), doc.getString("count"));
				count++;
			}
		} finally {
			cursor.close();
		}
		return output;
	}

	public void saveProduct(Product newProduct) {
		products.insertOne(new Document("shoppingListID", newProduct.getShoppingListID())
				.append("productID", newProduct.getProductID())
				.append("name", newProduct.getName())
				.append("count", newProduct.getCount()));
	}

	public void deleteProduct(String productID) {
		BasicDBObject document = new BasicDBObject();
		document.put("productID", productID);
		products.deleteOne(document);
	}
	
//----------------------------------------------------------------------------------------------	
//	
//	
//	User methods!
//	
//	
//----------------------------------------------------------------------------------------------
	
	
	
	public User[] getUsers() {
		MongoCursor<Document> cursor = users.find().iterator();

		User[] output = new User[(int) users.count()];
		int count = 0;

		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();

				output[count] = new User(doc.getString("username"), doc.getString("password"));

				count++;
			}
		} finally {
			cursor.close();
		}
		return output;
	}
	
	public User getUser(String username) {
		User[] all = getUsers();

		for (int i = 0; i < all.length; i++) 
			if (all[i].getUsername().equals(username)) 
				return all[i];

		return null;
	}
	
	public void saveUser(User newUser) {
		users.insertOne(new Document("username", newUser.getUsername())
				.append("password", newUser.getPassword()));
	}
	
//----------------------------------------------------------------------------------------------	
//	
//	
//	Shoppinglist methods!
//	
//	
//----------------------------------------------------------------------------------------------
	
	
	public ShoppingList[] getShoppingLists() {
		MongoCursor<Document> cursor = shoppingList.find().iterator();

		ShoppingList[] output = new ShoppingList[(int) shoppingList.count()];
		int count = 0;

		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				
				ArrayList<String> participants = (ArrayList<String>) doc.get("participants");
				
				output[count] = new ShoppingList(doc.getString("name"), doc.getString("shoppingListID"), doc.getString("ownerUsername"), participants);
				count++;
			}
		} finally {
			cursor.close();
		}
		return output;
	}
	
	public ShoppingList getShoppingList(String shoppingListID) {
		return null;
	}
	
	public void saveShoppingList(ShoppingList newShoppingList) {
		shoppingList.insertOne(new Document("shoppingListID", newShoppingList.getShoppingListID())
				.append("name", newShoppingList.getName())
				.append("ownerUsername", newShoppingList.getOwnerUsername())
				.append("participants", newShoppingList.getParticipants()));
		
		
	}

	public void addParticipant(Participant newParticipant) {
		ShoppingList[] all = getShoppingLists();
		for (int i = 0; i < all.length; i++) 
			if (all[i].getShoppingListID().equals(newParticipant.getShoppingListID())){
				all[i].addparticipant(newParticipant.getUsername());
				BasicDBObject newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("participants", all[i].getParticipants()));
				shoppingList.updateOne(new BasicDBObject().append("shoppingListID", all[i].getShoppingListID()), newDocument);
			}
	}
	
	
	
	
	
	
	

}
