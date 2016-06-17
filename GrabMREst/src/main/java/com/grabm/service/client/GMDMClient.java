package com.grabm.service.client;

import com.grabm.util.ContextExceptionMapper;
import com.grabm.util.GrabMConstant;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Sachith Dickwella
 */
public class GMDMClient extends GrabMConstant.GoogleMapDistanceMatrixClient {

    private final Client client;
    private final WebTarget target;

    public GMDMClient() {
        client = ClientBuilder.newClient();
        target = client.register(ContextExceptionMapper.class)
                .target(GOOGLE_MAP_DISTANCE_MATRIX_API + "/"
                        + Output.JSON.toString().toLowerCase() + "?");
    }

    public Client getClient() {
        return this.client;
    }

    public WebTarget getTarget() {
        return target;
    }
}
