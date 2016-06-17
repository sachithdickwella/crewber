package com.grabm.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.apache.log4j.Logger;

/**
 *
 * @author Sachith Dickwella
 */
@Provider
public class ContextExceptionMapper 
        implements ExceptionMapper<Throwable>, GrabMConstant {

    private final Logger logger = Logger.getLogger(ContextExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {
        logger.error(EXCEPTION_JERSEY, exception);
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse();
        }
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }
}
