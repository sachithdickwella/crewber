package com.grabm.service.subresource;

import com.grabm.entity.VehicleBrand;
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
public class VehicleBrandResource 
        extends AbstractFacade<VehicleBrand>{
    
    public VehicleBrandResource() {
        super(VehicleBrand.class);
    }
    
    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(VehicleBrand vehicleBrand) {
        Date now = new Date();
        vehicleBrand.setCreateDateTime(now);
        vehicleBrand.setLastUpdateDateTime(now);
        return super.create(vehicleBrand);
    }
    
    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(VehicleBrand vehicleBrand) {
        vehicleBrand.setLastUpdateDateTime(new Date());
        return super.update(vehicleBrand);
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<VehicleBrand> getAll() {
        return super.getAll();
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public VehicleBrand get(@PathParam("id") long id) {
        return super.get(id);
    }
    
    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int delete(VehicleBrand vehicleBrand) {
        return super.delete(vehicleBrand);
    }
}
