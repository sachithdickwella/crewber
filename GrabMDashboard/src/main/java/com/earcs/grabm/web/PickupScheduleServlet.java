package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.EndUser;
import com.earcs.grabm.pojo.Pickup;
import com.earcs.grabm.pojo.PickupSchedule;
import com.earcs.grabm.pojo.VehicleDriver;
import com.earcs.grabm.util.GrabmDashboardConstant;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes.SESSION_ADMIN;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
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
 * @deprecated
 * 
 * @author Roshin Perera
 */
@WebServlet(name = "PickupScheduleServlet", urlPatterns = {"/pickupscheduleweb"})
public class PickupScheduleServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes {

    private static final long serialVersionUID = -3442387185824296743L;

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
        String pickup = request.getParameter("schedule_user_flight_number"),
                endUser = request.getParameter("it_admin_rg_profile"),
                tran_count = request.getParameter("tran_count");

        String pdtParam = "user_sch_pickup_date_time_",
                vehicleParam = "user_sch_pick_vehicle_",
                piParam = "user_sch_pickup_index_",
                pipParam = "user_sch_pickup_point_",
                dopParam = "user_sch_drop_off_point_";
        
        response.getWriter().print(endUser);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        if (pickup == null || pickup.equals("")
                || endUser == null || endUser.equals("")) {
            isValid = false;
        }

        final List<PickupSchedule> pickupSchedules = new LinkedList<>();
        final Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
        if (admin != null) {
            try {
                long pickupId = Long.parseLong(pickup);
                long endUserId = Long.parseLong(endUser);

                int transitionCount = Integer.parseInt(tran_count);
                for (int it = 0; it < transitionCount; it++) {
                    try {
                        final int index = it;
                        PickupSchedule pickupSchedule = new PickupSchedule();
                        pickupSchedule.setPickupId(new Pickup(pickupId));

                        
                        pickupSchedule.setEnduserId(new EndUser(endUserId));

                        pickupSchedule.setPickupDatetime(formatter.parse(request.getParameter(pdtParam + (it + 1))));

                        List<VehicleDriver> vehicleDrivers = (List<VehicleDriver>) getServletContext().getAttribute(CONTEXT_VEHICLEDRIVER);
                        VehicleDriver vehicleDriver = vehicleDrivers.parallelStream()
                                .filter(obj -> ((VehicleDriver) obj).getId() == Long.parseLong(request.getParameter(vehicleParam + (index + 1))))
                                .findAny()
                                .orElse(null);
                        pickupSchedule.setVehicleDriver(vehicleDriver);

                        String pickupPoint = request.getParameter(pipParam + (it + 1)),
                                dropoffPoint = request.getParameter(dopParam + (it + 1)),
                                pickupIndex = request.getParameter(piParam + (it + 1));

                        if ((pickupIndex != null && !pickupIndex.equals(""))
                                || (pickupPoint != null && !pickupPoint.equals(""))
                                || (dropoffPoint != null && !dropoffPoint.equals(""))) {
                            pickupSchedule.setPickupIndex(Long.parseLong(pickupIndex.trim()));
                            pickupSchedule.setPickupPoint(pickupPoint.trim());
                            pickupSchedule.setDropoffPoint(dropoffPoint.trim());
                        } else {
                            throw new NullPointerException();
                        }
                        pickupSchedule.setCreateUser(admin.getId());
                        pickupSchedule.setLastupdateUser(admin.getId());
                        pickupSchedule.setStatus('A');

                        pickupSchedules.add(pickupSchedule);
                    } catch (ParseException | NumberFormatException | NullPointerException ex) {
                        logger.error(ex.getMessage(), ex);
                        isValid = false;
                    }
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                isValid = false;
            }
        } else {
            response.sendRedirect("/");
        }

        if (isValid && (pickupSchedules.size() > 0)) {
            GenericEntity<List<PickupSchedule>> gep = new GenericEntity<List<PickupSchedule>>(pickupSchedules) {
            };
            GrabmRESTClient client = new GrabmRESTClient();
            Invocation.Builder builder = client
                    .getTarget().path("/pickupschedule/create/bulk")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());

            String result = builder.put(Entity.entity(gep, MediaType.APPLICATION_JSON_TYPE), String.class);
            if (result != null) {
                response.sendRedirect(GrabmDashboardConstant.Bundle.context() + "schedule-pick-up?stat=" + result);
            }
            client.getClient().close();
        } else {
            response.sendRedirect(GrabmDashboardConstant.Bundle.context() + "schedule-pick-up?stat=-3");
        }
    }
}
