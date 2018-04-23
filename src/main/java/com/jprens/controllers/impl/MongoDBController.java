package com.jprens.controllers.impl;

import org.bson.Document;

import com.jprens.entity.Adress;
import com.jprens.entity.Costumer;
import com.jprens.entity.Order;
import com.jprens.entity.Subscription;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBController {

	private MongoClientURI uri;
	private MongoClient client;
	private MongoDatabase db;


	private MongoCollection<Document> costumers;
	private MongoCollection<Document> orders;
	private MongoCollection<Document> subscriptions;

	public MongoDBController() {
		try {
			uri  = new MongoClientURI(System.getenv("MONGODB_URI"));
			client = new MongoClient(uri);
			db = client.getDatabase(uri.getDatabase());

			costumers = db.getCollection("costumers");
			orders = db.getCollection("orders");
			subscriptions = db.getCollection("subscriptions");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	//GET
	public Costumer[] getCostumers() {

		MongoCursor<Document> cursor = costumers.find().iterator();

		Costumer[] output = new Costumer[(int) costumers.count()];
		int count = 0;

		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();

				output[count] = new Costumer(doc.getString("costumerID"), doc.getString("name"), doc.getString("email"), doc.getString("phoneNumber"), new Adress(doc.getString("streetName"), doc.getString("houseNumber"), doc.getString("zipCode"), doc.getString("city")));

				count++;
			}
		} finally {
			cursor.close();
		}
		return output;
	}

	public Costumer getCostumer(String costumerID) {
		Costumer[] all = getCostumers();

		for (int i = 0; i < all.length; i++) 
			if (all[i].getCostumerID().equals(costumerID)) 
				return all[i];

		return null;
	}


	public Subscription[] getSubscriptions() {

		MongoCursor<Document> cursor = subscriptions.find().iterator();
		Subscription[] output = new Subscription[(int) subscriptions.count()];
		int count = 0;

		try {
			while(cursor.hasNext()) {
				Document doc = cursor.next();
				output[count] = new Subscription(
						doc.getString("costumerID"),
						doc.getString("description"),
						doc.getString("startDate"),
						doc.getInteger("intervalMonths"),
						doc.getDouble("price"),
						doc.getString("subscriptionID"));

			} 
		} finally {
			cursor.close();
		}

		return output;

	}

	public Subscription getSubscription(String subscriptionID) {
		Subscription[] all = getSubscriptions();

		for (int i = 0; i < all.length; i++) 
			if(all[i].getSubscriptionID().equals(subscriptionID))
				return all[i];

		return null;
	} 


	public Order[] getOrders() {
		MongoCursor<Document> cursor = orders.find().iterator();

		Order[] output = new Order[(int) orders.count()];
		int count = 0;

		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();

				output[count] = new Order(doc.getString("orderID"), doc.getString("costumerID"), doc.getString("time"), doc.getString("description"), doc.getBoolean("done"), doc.getBoolean("payed"), doc.getDouble("price"));
				count++;
			}
		} finally {
			cursor.close();
		}
		return output;
	}	


	public Order[] getCostumersOrders(String costumerID) {
		Order[] allOrder = getOrders();
		int count = 0;
		for (int i = 0; i < allOrder.length; i++) 
			if (allOrder[i].getCostumerID().equals(costumerID)) 
				count++;

		Order[] output = new Order[count];
		count = 0;
		for (int i = 0; i < allOrder.length; i++) 
			if (allOrder[i].getCostumerID().equals(costumerID)) {
				output[count] = allOrder[i];
				count++;
			}

		return output;
	}


	public Order getOrder(String orderID) {
		Order[] allOrder = getOrders();
		for (int i = 0; i < allOrder.length; i++) 
			if (allOrder[i].getOrderID().equals(orderID)) 
				return allOrder[i];
		return null;
	}



	// POST
	public void saveCostumer(Costumer newCostumer) {
		costumers.insertOne(new Document("costumerID", newCostumer.getCostumerID())
				.append("name", newCostumer.getName())
				.append("email", newCostumer.getEmail())
				.append("phoneNumber", newCostumer.getPhoneNumber())
				.append("streetName", newCostumer.getAdressObject().getStreetName())
				.append("houseNumber", newCostumer.getAdressObject().getHouseNumber())
				.append("city", newCostumer.getAdressObject().getCity())
				.append("zipCode", newCostumer.getAdressObject().getZipCode())
				.append("country", newCostumer.getAdressObject().getCountry()));
	}

	public void saveOrder(Order newOrder) {
		orders.insertOne(new Document("orderID", newOrder.getOrderID())
				.append("costumerID", newOrder.getCostumerID())
				.append("time", newOrder.getTime())
				.append("description", newOrder.getDescription())
				.append("done", newOrder.isDone())
				.append("payed", newOrder.isPayed())
				.append("price", newOrder.getPrize())
				);

	}


	public void saveSubscription(Subscription newSubscription) {
		subscriptions.insertOne(new Document("subscriptionID", newSubscription.getSubscriptionID())
				.append("costumerID", newSubscription.getCostumerID())
				.append("description", newSubscription.getDescription())
				.append("startDate", newSubscription.getStartDate())
				.append("intervalMonths", newSubscription.getIntervalMonths())
				.append("price", newSubscription.getPrice()));
	}


	// PUT

	public void payOrder(String orderID) {
		Order[] allorders = getOrders();
		BasicDBObject newDocument = new BasicDBObject().append("$set", new BasicDBObject().append("payed", true));

		for (int i = 0; i < allorders.length; i++) 
			if (allorders[i].getOrderID().equals(orderID)) 
				if (allorders[i].isPayed()) 
					newDocument = new BasicDBObject().append("$set", new BasicDBObject().append("payed", false));

		orders.updateOne(new BasicDBObject().append("orderID", orderID), newDocument);
	}

	public void doneOrder(String orderID) {
		Order[] allorders = getOrders();
		BasicDBObject newDocument = new BasicDBObject().append("$set", new BasicDBObject().append("done", true));

		for (int i = 0; i < allorders.length; i++) 
			if (allorders[i].getOrderID().equals(orderID)) 
				if (allorders[i].isDone()) 
					newDocument = new BasicDBObject().append("$set", new BasicDBObject().append("done", false));

		orders.updateOne(new BasicDBObject().append("orderID", orderID), newDocument);
	}

	public void changeOrder(String orderID, Order newOrder) {
		Order[] allOrders = getOrders();
		for (int i = 0; i < allOrders.length; i++) 
			if (allOrders[i].getOrderID().equals(orderID)) {
				BasicDBObject newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("description", newOrder.getDescription()));
				orders.updateOne(new BasicDBObject().append("orderID", allOrders[i].getOrderID()), newDocument);
				
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("price", newOrder.getPrize()));
				orders.updateOne(new BasicDBObject().append("orderID", allOrders[i].getOrderID()), newDocument);
				
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("time", newOrder.getTime()));
				orders.updateOne(new BasicDBObject().append("orderID", allOrders[i].getOrderID()), newDocument);

				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("payed", newOrder.isPayed()));
				orders.updateOne(new BasicDBObject().append("orderID", allOrders[i].getOrderID()), newDocument);

				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("done", newOrder.isDone()));
				orders.updateOne(new BasicDBObject().append("orderID", allOrders[i].getOrderID()), newDocument);
			}
	}
	
	public void changeCostumer(String costumerID, Costumer newCostumer) {
		Costumer[] all = getCostumers();
		for (int i = 0; i < all.length; i++) 
			if (all[i].getCostumerID().equals(costumerID)){
				BasicDBObject newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("name", newCostumer.getName()));
				costumers.updateOne(new BasicDBObject().append("costumerID", all[i].getCostumerID()), newDocument);
				
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("email", newCostumer.getEmail()));
				costumers.updateOne(new BasicDBObject().append("costumerID", costumerID), newDocument);
				
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("phoneNumber", newCostumer.getPhoneNumber()));
				costumers.updateOne(new BasicDBObject().append("costumerID", costumerID), newDocument);
				
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("streetName", newCostumer.getAdressObject().getStreetName()));
				costumers.updateOne(new BasicDBObject().append("costumerID", costumerID), newDocument);
				
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("houseNumber", newCostumer.getAdressObject().getHouseNumber()));
				costumers.updateOne(new BasicDBObject().append("costumerID", costumerID), newDocument);
			
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("city", newCostumer.getAdressObject().getCity()));
				costumers.updateOne(new BasicDBObject().append("costumerID", costumerID), newDocument);
				
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("zipCode", newCostumer.getAdressObject().getZipCode()));
				costumers.updateOne(new BasicDBObject().append("costumerID", costumerID), newDocument);

				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("country", newCostumer.getAdressObject().getCountry()));
				costumers.updateOne(new BasicDBObject().append("costumerID", costumerID), newDocument);
			}
	}
	
	
	public void changeSubscription(Subscription newSubscription) {
		Subscription[] allSubscription = getSubscriptions();
		for (int i = 0; i < allSubscription.length; i++) 
			if (allSubscription[i].getCostumerID().equals(newSubscription.getCostumerID())) {
				BasicDBObject newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("description", newSubscription.getDescription()));
				subscriptions.updateOne(new BasicDBObject().append("subscriptionID", allSubscription[i].getSubscriptionID()), newDocument);
				
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("intervalMonths", newSubscription.getIntervalMonths()));
				subscriptions.updateOne(new BasicDBObject().append("subscriptionID", allSubscription[i].getSubscriptionID()), newDocument);
				
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("price", newSubscription.getPrice()));
				subscriptions.updateOne(new BasicDBObject().append("subscriptionID", allSubscription[i].getSubscriptionID()), newDocument);
				
				newDocument = new BasicDBObject()
						.append("$set", new BasicDBObject().append("startDate", newSubscription.getStartDate()));
				subscriptions.updateOne(new BasicDBObject().append("subscriptionID", allSubscription[i].getSubscriptionID()), newDocument);
				
			}
	}
	
	
	//DELETE
	
	public void deleteOrder(String orderID) {

		BasicDBObject document = new BasicDBObject();
		document.put("orderID", orderID);
		orders.deleteOne(document);
		
		
	}
	
	public void deleteCostumer(String costumerID) {

		BasicDBObject document = new BasicDBObject();
		document.put("costumerID", costumerID);
		costumers.deleteOne(document);
		
	}
	
	public void deleteSubscription(String costumerID) {
		
		BasicDBObject document = new BasicDBObject();
		document.put("costumerID", costumerID);
		subscriptions.deleteOne(document);
		
	}

}
