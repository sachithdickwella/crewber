package com.grabm.util;

import com.grabm.DownstreamHTTPMessages;
import com.grabm.service.client.GCMClient;
import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sachith Dickwella
 */
public class NotificationHandler {

    /**
     * Notification types.
     */
    public static enum Type {
        SCHEDULED, DISPATCHED, NEXT_PICKUP, ARRIVED;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Send notification to the mobile user.
     *
     * @param to Destination mobile device id. Typically this would be a hash
     * token that registered when first login from mobile device.
     * @param title Notification title.
     * @param message Notification body content.
     * @param type Message type that has been sent.
     *
     * @return The JSON response from the Google cloud messaging server.
     */
    public String notify(@NotNull String to, @NotNull String title, String message, @NotNull Type type) {

        Map<String, String> data = new HashMap<>();
        data.put("title", title);
        data.put("message", message);
        data.put("type", type.toString());

        final DownstreamHTTPMessages.Payload payload = new DownstreamHTTPMessages.Payload();
        payload.setTo(to)
                .setPriority(DownstreamHTTPMessages.Priorities.HIGH.toString().toLowerCase())
                .setData(data);

        GCMClient client = new GCMClient();
        Response response = client.request(Entity.entity(payload.toJson().toString(), MediaType.APPLICATION_JSON));

        return response.readEntity(String.class);
    }
}
