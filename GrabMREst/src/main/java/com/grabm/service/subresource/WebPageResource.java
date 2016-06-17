package com.grabm.service.subresource;

import com.grabm.entity.WebPage;
import com.grabm.factory.AbstractFacade;
import static com.grabm.util.GrabMConstant.ENTITY_EXCEPTION_FETCH;
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
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Sachith Dickwella
 */
public class WebPageResource extends AbstractFacade<WebPage> {

    public WebPageResource() {
        super(WebPage.class);
    }

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public long create(WebPage webPage) {
        Date date = new Date();
        webPage.setCreateDatetime(date);
        webPage.setLastupdateDatetime(date);
        return super.create(webPage);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public int update(WebPage webPage) {
        webPage.setLastupdateDatetime(new Date());
        return super.update(webPage);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<WebPage> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public WebPage get(@PathParam("id") long id) {
        return super.get(id);
    }

    @GET
    @Path("/idnameurl")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<WebPage> getIdNameAll() {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(WebPage.class);
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("id"), "id")
                    .add(Projections.property("fileName"), "fileName")
                    .add(Projections.property("mappingUrl"), "mappingUrl"))
                    .setResultTransformer(Transformers.aliasToBean(WebPage.class));
            return criteria.list();
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }
}
