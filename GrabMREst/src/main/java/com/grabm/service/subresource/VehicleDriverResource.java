package com.grabm.service.subresource;

import com.grabm.entity.VehicleDriver;
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
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sachith Dickwella
 */
public class VehicleDriverResource
        extends AbstractFacade<VehicleDriver> {

    public VehicleDriverResource() {
        super(VehicleDriver.class);
    }

    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(VehicleDriver vehicleDriver) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Criterion restriction1 = Restrictions.eq("vehicle", vehicleDriver.getVehicle()),
                    restriction2 = Restrictions.eq("driver", vehicleDriver.getDriver());
            Criteria criteria = session.createCriteria(VehicleDriver.class);
            criteria.add(Restrictions.or(restriction1, restriction2));
            List<VehicleDriver> vehicleDrivers = criteria.list();
            if (vehicleDrivers.size() > 0) {
                vehicleDrivers.parallelStream().forEach((VehicleDriver vd) -> {
                    if (vd.getStatus() == 'A') {
                        vd.setStatus('I');
                        vd.setLastupdateDateTime(new Date());
                        session.update(vd);
                    }
                });
                tx.commit();
            }
        } catch (Exception ex) {
            tx.rollback();
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        Date now = new Date();
        vehicleDriver.setCreateDateTime(now);
        vehicleDriver.setLastupdateDateTime(now);
        return super.create(vehicleDriver);
    }

    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(VehicleDriver vehicleDriver) {
        vehicleDriver.setLastupdateDateTime(new Date());
        return super.update(vehicleDriver);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<VehicleDriver> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/all/{status}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<VehicleDriver> getAll(@PathParam("status") Status status) {
        return super.getAll(status);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public VehicleDriver get(@PathParam("id") long id) {
        return super.get(id);
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int delete(VehicleDriver vehicleDriver) {
        return super.delete(vehicleDriver);
    }
}
