package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.EndUser;
import com.earcs.grabm.util.GrabmDashboardConstant;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes.SESSION_ADMIN;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "PushCrewListServlet", urlPatterns = {"/pushcrewdataweb"})
public class PushCrewListServlet extends HttpServlet {

    private static final long serialVersionUID = -5594689848333187955L;
    private final Logger logger = Logger.getLogger(PushCrewListServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String flightNumber = request.getParameter("upload_crew_schedule_flight_number"),
                selected_crew = request.getParameter("selected-crew");

        if (selected_crew != null) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                List<EndUser> endUsers = EndUser.fromJson(selected_crew);
                
                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/pickupschedule/create/bulk/" + flightNumber)
                        .queryParam("userID", admin.getId())
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());

                String result = builder.put(Entity.entity(new GenericEntity<List<EndUser>>(endUsers) {
                }, MediaType.APPLICATION_JSON_TYPE), String.class);
                if (result != null) {
                    response.sendRedirect(GrabmDashboardConstant.Bundle.context() + "schedule-pick-up?stat=" + result + "#crew-schedule");
                }
                client.getClient().close();
            } else {
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect(GrabmDashboardConstant.Bundle.context() + "schedule-pick-up?stat=-1#crew-schedule");
        }
    }
}
