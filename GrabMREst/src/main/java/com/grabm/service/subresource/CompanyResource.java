package com.grabm.service.subresource;

import com.grabm.entity.Company;
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
public class CompanyResource
        extends AbstractFacade<Company> {

    public CompanyResource() {
        super(Company.class);
    }

    @PUT
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public long create(Company company) {
        Date now = new Date();
        company.setCreateDateTime(now);
        company.setLastUpdateDateTime(now);
        return super.create(company);
    }

    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int update(Company company) {
        company.setLastUpdateDateTime(new Date());
        return super.update(company);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<Company> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Company get(@PathParam("id") long id) {
        return super.get(id);
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public int delete(Company company) {
        return super.delete(company);
    }
}
