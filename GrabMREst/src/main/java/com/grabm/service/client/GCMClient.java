package com.grabm.service.client;

import com.grabm.util.ContextExceptionMapper;
import com.grabm.util.GrabMConstant;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sachith Dickwella
 */
public class GCMClient extends GrabMConstant.GoogleCloudMessagingClient {

    private final Client client;
    private final WebTarget target;

    public GCMClient() {
        client = ClientBuilder.newClient();
        target = client.register(ContextExceptionMapper.class)
                .target(GOOGLE_MESSAGING_API);
    }
    
    public Client getClient() {
        return this.client;
    }

    public Response request(Entity<?> entity) {
        return target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "key=" + GOOGLE_MESSEAGING_API_KEY)
                .post(entity);
    }
}
