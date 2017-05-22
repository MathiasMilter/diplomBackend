package rest;

import business.impl.LinkControllerImpl;
import business.interfaces.LinkController;
import data.dbDTO.Link;
import data.interfaces.PersistenceException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 09-05-2017.
 */
@Path("links")
@Produces(MediaType.APPLICATION_JSON)
public class LinkService {
    private LinkController linkController = new LinkControllerImpl();

    @GET
    @Path("default")
    public List<Link> getDefaultLinks() throws PersistenceException, ValidException {
        List<Link> links = linkController.getDefaultLinks();
        if(links.size()==0) {
            links.add(new Link("campusnet", "http://cn.dtu.dk"));
        }
        return links;
    }
    @POST
    @Path("default")
    public Link postDefaultLink(Link link) throws ValidException, PersistenceException {

        return linkController.saveDefaultLink(link);
    }
    @DELETE
    @Path("default/{id}")
    public String deleteDefaultLink(@PathParam("id") String id) throws ValidException, PersistenceException {
        System.out.println(id);
        return linkController.deleteDefaultLink(id);
    }

    @GET
    public List<Link> getLinks(@QueryParam("user") String userId, @QueryParam("course") String courseId){
        ArrayList<Link> links = new ArrayList<>();
        links.add(new Link("campusnet-Test2", "http://cn.dtu.dk"));
        return links;
    }

    @POST
    public Link postLink(Link link){
        return null;
    }


}
