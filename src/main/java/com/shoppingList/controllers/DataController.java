package com.shoppingList.controllers;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.shoppingList.entity.Product;

public class DataController {

	private MongoClientURI uri;
	private MongoClient client;
	private MongoDatabase db;


	private MongoCollection<Document> products;
	public DataController() {

		try {
			uri  = new MongoClientURI("mongodb://"+System.getenv("MONGOUSER")+":"+System.getenv("MONGOPASS")+"@ds147180.mlab.com:47180/shoppingmilter");
			client = new MongoClient(uri);
			db = client.getDatabase(uri.getDatabase());

			products = db.getCollection("products");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Product[] getProducts() {
		MongoCursor<Document> cursor = products.find().iterator();

		Product[] output = new Product[(int) products.count()];
		int count = 0;

		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();

				output[count] = new Product(doc.getString("id"), doc.getString("name"), doc.getString("count"));

				count++;
			}
		} finally {
			cursor.close();
		}
		return output;
	}

	public void saveProduct(Product newProduct) {
		products.insertOne(new Document("id", newProduct.getId())
				.append("name", newProduct.getName())
				.append("count", newProduct.getCount()));
	}

	public void deleteProduct(String id) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		products.deleteOne(document);
	}

}
