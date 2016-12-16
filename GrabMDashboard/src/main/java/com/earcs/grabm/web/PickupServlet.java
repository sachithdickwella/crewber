package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.Pickup;
import com.earcs.grabm.util.GrabmDashboardConstant;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes.SESSION_ADMIN;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import org.apache.log4j.Logger;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "PickupServlet", urlPatterns = {"/pickupweb"})
public class PickupServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes, GrabmDashboardConstant.Bundle {

    private final Logger logger = Logger.getLogger(PickupServlet.class);
    private boolean isValid = true;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stat = request.getParameter("stat");

        GrabmRESTClient client = new GrabmRESTClient();
        Invocation.Builder builder = client
                .getTarget().path("/pickup/future")
                .request()
                .headers(GrabmDashboardConstant.Bundle.getHeaders());
        List<Pickup> pickups = builder.get(new GenericType<List<Pickup>>() {
        });
        getServletContext().setAttribute(CONTEXT_FUTUREPICKUPDS, pickups);
        response.sendRedirect(GrabmDashboardConstant.Bundle.context() + "schedule-pick-up?stat=" + stat);
        client.getClient().close();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("null")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String flightNo = request.getParameter("add_schedule_flight_number"),
                departureDateTime = request.getParameter("add_schedule_depature_date_time"),
                note = request.getParameter("it_admin_rg_note");

        Date flightDateTime = null;
        if (flightNo == null || flightNo.equals("")) {
            isValid = false;
        }
        if (departureDateTime == null || departureDateTime.equals("")) {
            isValid = false;
        } else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                flightDateTime = formatter.parse(departureDateTime.trim());
            } catch (ParseException ex) {
                logger.error(ex.getMessage(), ex);
                isValid = false;
            }
        }
        if (note != null) {
            note = note.trim();
        }
        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                Pickup pickup = new Pickup();
                pickup.setFlightNo(flightNo.trim());
                pickup.setFlightDateTime(flightDateTime);
                pickup.setNote(note);
                pickup.setCreateUser(admin.getId());
                pickup.setLastupdateUser(admin.getId());
                
                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/pickup/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());

                String result = builder.put(Entity.json(pickup), String.class);
                if (result != null) {
                    response.sendRedirect(GrabmDashboardConstant.Bundle.context() + "pickupweb?stat=" + result);
                }
                client.getClient().close();
            } else {
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect(GrabmDashboardConstant.Bundle.context() + "schedule-pick-up?stat=-3");
        }
    }
}
