package com.grabm.service.subresource;

import com.grabm.entity.FuelType;
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
public class FuelTypeResource
        extends AbstractFacade<FuelType> {

    public FuelTypeResource() {
        super(FuelType.class);
    }

    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(FuelType fuelType) {
        Date now = new Date();
        fuelType.setCreateDateTime(now);
        fuelType.setLastUpdateDateTime(now);
        return super.create(fuelType);
    }

    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(FuelType fuelType) {
        fuelType.setLastUpdateDateTime(new Date());
        return super.update(fuelType);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<FuelType> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public FuelType get(@PathParam("id") long id) {
        return super.get(id);
    }
    
    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int delete(FuelType fuelType) {
        return super.delete(fuelType);
    }
}
