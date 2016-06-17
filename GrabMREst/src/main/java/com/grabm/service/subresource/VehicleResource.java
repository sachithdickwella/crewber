package com.grabm.service.subresource;

import com.grabm.entity.Vehicle;
import com.grabm.factory.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Sachith Dickwella
 */
public class VehicleResource 
        extends AbstractFacade<Vehicle> {
    
    public VehicleResource() {
        super(Vehicle.class);
    }
    
    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(Vehicle vehicle) {
        Date now = new Date();
        vehicle.setCreateDateTime(now);
        vehicle.setLastupdateDateTime(now);
        return super.create(vehicle);
    }
    
    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(Vehicle vehicle) {
        vehicle.setLastupdateDateTime(new Date());
        return super.update(vehicle);
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<Vehicle> getAll() {
        return super.getAll();
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Vehicle get(@PathParam("id") long id) {
        return super.get(id);
    }
    
    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int delete(Vehicle vehicle) {
        return super.delete(vehicle);
    }
}
