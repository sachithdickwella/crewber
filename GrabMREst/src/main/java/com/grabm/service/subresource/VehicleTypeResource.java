package com.grabm.service.subresource;

import com.grabm.entity.VehicleType;
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
public class VehicleTypeResource
        extends AbstractFacade<VehicleType> {

    public VehicleTypeResource() {
        super(VehicleType.class);
    }
    
    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(VehicleType vehicleType) {
        Date now = new Date();
        vehicleType.setCreateDateTime(now);
        vehicleType.setLastUpdateDateTime(now);
        return super.create(vehicleType);
    }

    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(VehicleType vehicleType) {
        vehicleType.setLastUpdateDateTime(new Date());
        return super.update(vehicleType);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<VehicleType> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public VehicleType get(@PathParam("id") long id) {
        return super.get(id);
    }
    
    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int delete(VehicleType vehicleType) {
        return super.delete(vehicleType);
    }
}
