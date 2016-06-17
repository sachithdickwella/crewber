package com.grabm.filter;

import com.grabm.util.Encryptor;
import static com.grabm.util.Encryptor.Algorithm;
import com.grabm.util.GrabMConstant;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import org.apache.log4j.Logger;

/**
 *
 * @author Sachith Dickwella
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
@PreMatching
public class RequestFilter implements ContainerRequestFilter,
        GrabMConstant.AuthBundle {

    private final Logger logger = Logger.getLogger(RequestFilter.class);
    private final static String HEADER_AUTH = "Authorization";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String credentials = requestContext.getHeaderString(HEADER_AUTH);
        if (credentials == null) {
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }

        credentials = credentials.replaceFirst("Basic ", "");
        byte[] buffer = Base64.getDecoder().decode(credentials);
        String token = Encryptor.encrypt(Algorithm.SHA_384, new String(buffer, Charset.forName("UTF-8")));

        if (!BUNDLE.getString(PRIVATE_KEY).equals(token)) {
            WebApplicationException ex = new WebApplicationException(Status.UNAUTHORIZED);
            logger.error("Unauthorized Access", ex);
            throw ex;
        }
    }
}
