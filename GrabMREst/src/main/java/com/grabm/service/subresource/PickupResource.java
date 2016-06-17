package com.grabm.service.subresource;

import com.grabm.entity.Pickup;
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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sachith Dickwella
 */
public class PickupResource
        extends AbstractFacade<Pickup> {

    public PickupResource() {
        super(Pickup.class);
    }

    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(Pickup pickup) {
        Date now = new Date();
        pickup.setCreateDatetime(now);
        pickup.setLastupdateDatetime(now);
        return super.create(pickup);
    }

    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(Pickup pickup) {
        pickup.setLastupdateDatetime(new Date());
        return super.update(pickup);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<Pickup> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/future")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pickup> getFuture() {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.gt("flightDateTime", new Date()));
            return criteria.list();
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Pickup get(@PathParam("id") long id) {
        return super.get(id);
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int delete(Pickup pickup) {
        return super.delete(pickup);
    }
}
