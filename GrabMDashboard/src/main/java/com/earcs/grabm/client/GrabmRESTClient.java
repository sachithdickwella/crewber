package com.earcs.grabm.client;

import com.earcs.grabm.util.ContextExceptionMapper;
import com.earcs.grabm.util.GrabmDashboardConstant;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

/**
 *
 * @author Sachith Dickwella
 */
public class GrabmRESTClient implements GrabmDashboardConstant {

    private final Client client;
    private final WebTarget target;

    public GrabmRESTClient() {
        this.client = ClientBuilder.newClient()
                .register(MoxyJsonFeature.class)
                .register(ContextExceptionMapper.class);
        this.target = client.target(Bundle.getValue(Bundle.API_URL));
    }

    public Client getClient() {
        return this.client;
    }

    public WebTarget getTarget() {
        return this.target;
    }
}
