package com.jprens.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jprens.controllers.ControllerRegistry;
import com.jprens.controllers.impl.CostumerController;
import com.jprens.entity.Costumer;

@Path("/costumers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CostumerService {

	private CostumerController costumerCtrl = ControllerRegistry.getCostumerCtrl();
	
	// GET
	
	@GET
	public Costumer[] getCostumers() {
		return costumerCtrl.getCostumers();
	}
	
	@GET
	@Path("/{id}")
	public Costumer getCostumer(@PathParam("id") String id) {
		return costumerCtrl.getCostumer(id);
	}
	
	// POST
	
	@POST
	public void saveCostumer(Costumer newCostumer) {
		costumerCtrl.saveCostumer(newCostumer);
	}
	
	// PUT
	
	@PUT
	@Path("/{costumerID}")
	public void changeCostumer(@PathParam("costumerID") String costumerID, Costumer newCostumer) {
		costumerCtrl.changeCostumer(costumerID, newCostumer);
	}
	
	
	// DELETE
	
	@DELETE
	@Path("/{costumerID}")
	public void deleteCostumer(@PathParam("costumerID") String costumerID) {
		costumerCtrl.deleteCostumer(costumerID);
	}
	
}
