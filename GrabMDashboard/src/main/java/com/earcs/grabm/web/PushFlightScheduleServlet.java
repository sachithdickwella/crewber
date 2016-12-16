package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.util.GrabmDashboardConstant;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes.SESSION_ADMIN;
import com.earcs.grabm.util.GrabmDashboardConstant.Bundle;
import com.earcs.grabm.util.pojo.Flight;
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

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "PushExcelDataServlet", urlPatterns = {"/pushexceldata"})
public class PushFlightScheduleServlet extends HttpServlet {

    private static final long serialVersionUID = -6603915852786459392L;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selected_flights = request.getParameter("selected-flights");

        if (selected_flights != null) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                List<Flight> flights = Flight.fromJson(selected_flights);

                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/pickup/create/bulk/" + admin.getId())
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());

                String result = builder.put(Entity.entity(new GenericEntity<List<Flight>>(flights) {
                }, MediaType.APPLICATION_JSON_TYPE), String.class);
                if (result != null) {
                    int stat = Integer.parseInt(result);
                    response.sendRedirect(Bundle.context() + "schedule-pick-up?stat=" + stat);
                }
                client.getClient().close();
            } else {
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect(Bundle.context() + "schedule-pick-up?stat=-1");
        }
    }
}
