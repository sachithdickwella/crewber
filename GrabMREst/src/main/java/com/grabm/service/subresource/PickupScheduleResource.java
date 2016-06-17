package com.grabm.service.subresource;

import com.grabm.entity.Driver;
import com.grabm.entity.MobilePickupSummery;
import com.grabm.entity.PickupSchedule;
import com.grabm.entity.Vehicle;
import com.grabm.factory.AbstractFacade;
import java.util.Collections;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Sachith Dickwella
 */
public class PickupScheduleResource
        extends AbstractFacade<PickupSchedule> {

    public PickupScheduleResource() {
        super(PickupSchedule.class);
    }

    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(PickupSchedule pickupSchedule) {
        Date now = new Date();
        pickupSchedule.setCreateDatetime(now);
        pickupSchedule.setLastupdateDatetime(now);
        return super.create(pickupSchedule);
    }

    @PUT
    @Path("/create/bulk")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int createBulk(List<PickupSchedule> pickupSchedules) {
        final Date now = new Date();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            pickupSchedules.stream().forEachOrdered((PickupSchedule pickupSchedule) -> {
                pickupSchedule.setCreateDatetime(now);
                pickupSchedule.setLastupdateDatetime(now);

                MobilePickupSummery summery = new MobilePickupSummery();
                summery.setPickupId(pickupSchedule.getPickupId().getId());

                /**
                 * Adding vehicle details.
                 */
                Vehicle vehicle = pickupSchedule.getVehicleDriver().getVehicle();
                summery.setVehicleRegistrationNumber(vehicle.getRegistrationNumber());
                summery.setVehicleColor1(vehicle.getColor1());
                summery.setVehicleColor2(vehicle.getColor2());
                /**
                 * Adding driver details.
                 */
                Driver driver = pickupSchedule.getVehicleDriver().getDriver();
                summery.setDriverFirstName(driver.getFirstName());
                summery.setDriverLastName(driver.getLastName());
                summery.setDriverPhoto(driver.getPhoto());
                summery.setRating(driver.getRating());

                summery.setPickupDateTime(pickupSchedule.getPickupDatetime());
                summery.setPickupPoint(pickupSchedule.getPickupPoint());
                summery.setDropoffPoint(pickupSchedule.getDropoffPoint());
                summery.setPickupStatus(pickupSchedule.getStatus());
                summery.setCreateUser(pickupSchedule.getCreateUser());
                summery.setLastUpdateUser(pickupSchedule.getLastupdateUser());
                summery.setCreateDateTime(now);
                summery.setLastUpdateDateTime(now);

                summery.setEndUserList(Collections.singletonList(pickupSchedule.getEnduserId()));

                session.save(pickupSchedule);
                session.save(summery);
            });
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            logger.error(ENTITY_EXCEPTION_CREATE, ex);
            return -1;
        } finally {
            session.close();
        }
        return 0;
    }

    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(PickupSchedule pickupSchedule) {
        pickupSchedule.setLastupdateDatetime(new Date());
        return super.update(pickupSchedule);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<PickupSchedule> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/all/{status}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<PickupSchedule> getAll(@PathParam("status") Status status) {
        return super.getAll(status);
    }

    @GET
    @Path("/all/imei/{status}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PickupSchedule> getAllIMEIs(@PathParam("status") Status status) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq("status", status.toString().charAt(0)))
                    .createAlias("vehicleDriver", "vd")
                    //.createAlias("vd.vehicle", "v")
                    .setProjection(Projections.projectionList()
                            .add(Projections.property("vd.vehicle"), "vehicle"))
                            //.add(Projections.property("v.trackerImei"), "v.trackerImei"))
                    .setResultTransformer(Transformers.aliasToBean(entityClass));
            return criteria.list();
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }

    @GET
    @Path("/active")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PickupSchedule> getActive() {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq("status", 'A'));
            return criteria.list();
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }

    @GET
    @Path("/future")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PickupSchedule> getFuture() {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.createAlias("pickupId", "p")
                    .add(Restrictions.gt("p.flightDateTime", new Date()));
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
    public PickupSchedule get(@PathParam("id") long id) {
        return super.get(id);
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int delete(PickupSchedule pickupSchedule) {
        return super.delete(pickupSchedule);
    }

    @POST
    @Path("/status/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public int status(@PathParam("id") long id, @QueryParam("status") Status status) {
        return super.status(id, status);
    }
}
