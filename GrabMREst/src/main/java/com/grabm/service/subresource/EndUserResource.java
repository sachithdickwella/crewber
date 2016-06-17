package com.grabm.service.subresource;

import com.grabm.entity.EndUser;
import com.grabm.factory.AbstractFacade;
import com.grabm.util.EmailHandler;
import com.grabm.util.RandomPinGenerator;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sachith Dickwella
 */
public class EndUserResource extends AbstractFacade<EndUser> {

    public EndUserResource() {
        super(EndUser.class);
    }

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public long create(EndUser endUser) {
        Date now = new Date();
        endUser.setCreateDatetime(now);
        endUser.setLastupdateDatetime(now);
        return super.create(endUser);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public int update(EndUser endUser) {
        endUser.setLastupdateDatetime(new Date());
        return super.update(endUser);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<EndUser> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/all/{status}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<EndUser> getAll(@PathParam("status") Status status) {
        return super.getAll(status);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public EndUser get(@PathParam("id") long id) {
        return super.get(id);
    }

    @POST
    @Path("/validate/member/{memno}")
    @Produces(MediaType.TEXT_PLAIN)
    public int validateMemberNo(@PathParam("memno") String memno) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq("membershipNumber", memno));
            List<EndUser> list = criteria.list();
            if (list.size() > 0) {
                Transaction tx = session.beginTransaction();
                try {
                    String pin = RandomPinGenerator.getPIN();
                    EndUser endUser = list.get(0);
                    endUser.setPin(pin);
                    endUser.setPinDateTime(new Date());

                    session.update(endUser);
                    tx.commit();

                    EmailHandler emailHandler = new EmailHandler();
                    emailHandler.sendMail(endUser.getEmail(), "Identity verification PIN", endUser.getFirstName(), pin);
                    return 0;
                } catch (Exception ex) {
                    tx.rollback();
                    logger.error(ENTITY_EXCEPTION_UPDATE, ex);
                }
            }
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_VALIDATE, ex);
        } finally {
            session.close();
        }
        return -1;
    }

    @POST
    @Path("/validate/pin/{pin}")
    @Produces(MediaType.TEXT_PLAIN)
    public int validatePIN(@PathParam("pin") String pin, @QueryParam("memno") String memno) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq("membershipNumber", memno))
                    .add(Restrictions.eq("pin", pin));
            List<EndUser> list = criteria.list();
            if (list.size() > 0) {
                return 0;
            }
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_VALIDATE, ex);
        } finally {
            session.close();
        }
        return -1;
    }

    @POST
    @Path("/token/{memno}")
    @Produces(MediaType.TEXT_PLAIN)
    public int updateUserToken(@PathParam("memno") String memno,
            @QueryParam("token") String token) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq("membershipNumber", memno));
            List<EndUser> users = criteria.list();
            if (users.size() > 0) {
                EndUser user = users.get(0);
                user.setGcmToken(token);

                session.update(user);
                tx.commit();
            }
        } catch (Exception ex) {
            tx.rollback();
            logger.error(ENTITY_EXCEPTION_UPDATE, ex);
            return -1;
        } finally {
            session.close();
        }
        return 0;
    }
}
