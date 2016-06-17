package com.grabm.service.subresource;

import com.grabm.entity.Profile;
import com.grabm.factory.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Sachith Dickwella
 */
public class ProfileResource extends AbstractFacade<Profile> {

    public ProfileResource() {
        super(Profile.class);
    }

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public long create(Profile profile) {
        Date date = new Date();
        profile.setCreateDatetime(date);
        profile.setLastupdateDatetime(date);
        return super.create(profile);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public int update(Profile profile) {
        profile.setLastupdateDatetime(new Date());
        return super.update(profile);
    }

    @POST
    @Path("/update/weblist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int updateWebPageList(Profile profile) {
        Session session = sessionFactory.openSession();
        Profile profile_from_db = null;                
        try {
            Criteria criteria = session.createCriteria(Profile.class);
            criteria.add(Restrictions.eq("id", profile.getId()));
            List<Profile> profiles = criteria.list();
            if (profiles.size() > 0) {
                profile_from_db = profiles.get(0);
                profile_from_db.setWebPageList(profile.getWebPageList());
                profile_from_db.setLastupdateDatetime(new Date());                
            } else {
                throw new NullPointerException(ENTITY_EXCEPTION_FETCH);
            }
        } catch (HibernateException | NullPointerException ex) {
            logger.error(ENTITY_EXCEPTION_UPDATE, ex);
            return -1;
        } finally {
            session.close();
        }
        return super.update(profile_from_db);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<Profile> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Profile get(@PathParam("id") long id) {
        return super.get(id);
    }

    @GET
    @Path("/idname/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Profile getIdName(@PathParam("id") long id) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(Profile.class);
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("id"), "id")
                    .add(Projections.property("name"), "name"))
                    .add(Restrictions.eq("id", id))
                    .setResultTransformer(Transformers.aliasToBean(Profile.class));
            List<Profile> profiles = criteria.list();
            if (profiles.size() > 0) {
                return profiles.get(0);
            }
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }

    @GET
    @Path("/idname")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Profile> getIdNameAll() {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(Profile.class);
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("id"), "id")
                    .add(Projections.property("name"), "name"))
                    .setResultTransformer(Transformers.aliasToBean(Profile.class));
            return criteria.list();
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }
}
