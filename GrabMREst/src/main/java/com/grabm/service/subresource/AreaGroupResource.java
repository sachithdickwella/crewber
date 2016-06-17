package com.grabm.service.subresource;

import com.grabm.entity.AreaGroup;
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
public class AreaGroupResource
        extends AbstractFacade<AreaGroup> {

    public AreaGroupResource() {
        super(AreaGroup.class);
    }
    
    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(AreaGroup areaGroup) {
        Date now = new Date();
        areaGroup.setCreateDateTime(now);
        areaGroup.setLastUpdateDateTime(now);
        return super.create(areaGroup);
    }
    
    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(AreaGroup areaGroup) {
        areaGroup.setLastUpdateDateTime(new Date());
        return super.update(areaGroup);
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<AreaGroup> getAll() {
        return super.getAll();
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public AreaGroup get(@PathParam("id") long id) {
        return super.get(id);
    }
    
    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public int delete(AreaGroup areaGroup) {
        return super.delete(areaGroup);
    }
}
