package com.grabm.filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Sachith Dickwella
 */
@Provider
public class ResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        
        responseContext.getHeaders().add("X-Powered-By", "Jersey 2.22.1/Servlet 3.1(GrabM(v0.3.5.1) REST API provider)");
        responseContext.getHeaders().add("Server", "JSP/Servlet Container Java 1.8_U80");
    }
}
