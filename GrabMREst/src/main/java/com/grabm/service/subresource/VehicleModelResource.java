package com.grabm.service.subresource;

import com.grabm.entity.VehicleModel;
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
public class VehicleModelResource
        extends AbstractFacade<VehicleModel> {

    public VehicleModelResource() {
        super(VehicleModel.class);
    }
    
    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(VehicleModel vehicleModel) {
        Date now = new Date();
        vehicleModel.setCreateDateTime(now);
        vehicleModel.setLastUpdateDateTime(now);
        return super.create(vehicleModel);
    }
    
    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(VehicleModel vehicleModel) {
        vehicleModel.setLastUpdateDateTime(new Date());
        return super.update(vehicleModel);
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<VehicleModel> getAll() {
        return super.getAll();
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public VehicleModel get(@PathParam("id") long id) {
        return super.get(id);
    }
    
    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int delete(VehicleModel vehicleModel) {
        return super.delete(vehicleModel);
    }
}
