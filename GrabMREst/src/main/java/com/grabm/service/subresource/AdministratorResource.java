package com.grabm.service.subresource;

import com.grabm.entity.Administrator;
import com.grabm.factory.AbstractFacade;
import com.grabm.util.Encryptor;
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
public class AdministratorResource
        extends AbstractFacade<Administrator> {

    public AdministratorResource() {
        super(Administrator.class);
    }

    /**
     *
     * Create an administrator in database.
     *
     * @param admininstrator
     * @return
     */
    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(Administrator admininstrator) {
        String password = Encryptor.encrypt(Encryptor.Algorithm.SHA_256, admininstrator.getPassword());
        Date now = new Date();
        admininstrator.setPassword(password);
        admininstrator.setCreateDateTime(now);
        admininstrator.setLastUpdateDateTime(now);
        return super.create(admininstrator);
    }

    /**
     *
     * Update specific administrator from database.
     *
     * @param admininstrator
     * @return
     */
    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(Administrator admininstrator) {
        admininstrator.setLastUpdateDateTime(new Date());
        return super.update(admininstrator);
    }

    /**
     * Get specific administrator by id.
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Administrator get(@PathParam("id") long id) {
        return super.get(id);
    }

    /**
     * Get all administrators.
     *
     * @return
     */
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<Administrator> getAll() {
        return super.getAll();
    }

    /**
     * Update status of the administrator refer by Id.
     *
     * @param id
     * @param status
     * @return the status of update.
     */
    @POST
    @Path("/status/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public int status(@PathParam("id") long id, @QueryParam("status") Status status) {
        return super.status(id, status);
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Administrator login(@QueryParam("user") String user,
            @QueryParam("pass") String password) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.and(Restrictions.eq("userName", user),
                    Restrictions.eq("password", Encryptor.encrypt(Encryptor.Algorithm.SHA_256, password))));
            List<Administrator> list = criteria.list();
            if (list.size() > 0) {
                return list.get(0);
            }
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }

    /**
     * First login validation.
     *
     * @param user
     * @param tempPass
     * @param newPass
     * @param adminId
     * 
     * @return whether success or fail.
     */
    @POST
    @Path("/firstlogin")
    @Produces(MediaType.TEXT_PLAIN)
    public int firstLogin(@QueryParam("user") String user, @QueryParam("temppass") String tempPass,
            @QueryParam("newpass") String newPass, @QueryParam("adminId") long adminId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.and(Restrictions.eq("userName", user),
                    Restrictions.eq("password", Encryptor.encrypt(Encryptor.Algorithm.SHA_256, tempPass))));
            List<Administrator> list = criteria.list();
            if (list.size() > 0) {
                Administrator admin = list.get(0);
                admin.setLastUpdateUser(adminId);
                admin.setPassword(Encryptor.encrypt(Encryptor.Algorithm.SHA_256, newPass));
                admin.setLastUpdateDateTime(new Date());
                admin.setStatus('A');

                session.update(admin);
                tx.commit();
                return 0;
            }
        } catch (Exception ex) {
            tx.rollback();
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return -1;
    }
}
