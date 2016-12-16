package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.Driver;
import com.earcs.grabm.pojo.Vehicle;
import com.earcs.grabm.pojo.VehicleDriver;
import com.earcs.grabm.util.GrabmDashboardConstant;
import com.earcs.grabm.util.GrabmDashboardConstant.Bundle;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "VehicleDriverServlet", urlPatterns = {"/vehicledriverweb"})
public class VehicleDriverServlet extends HttpServlet
        implements GrabmDashboardConstant.Bundle, GrabmDashboardConstant.Attributes {

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
                .getTarget().path("/vehicledriver/all/ACTIVE")
                .request()
                .headers(GrabmDashboardConstant.Bundle.getHeaders())
                .accept(MediaType.APPLICATION_JSON_TYPE);
        List<VehicleDriver> vehicleDrivers = builder.get(new GenericType<List<VehicleDriver>>() {
        });
        getServletContext().setAttribute(CONTEXT_VEHICLEDRIVER, vehicleDrivers);
        response.sendRedirect(Bundle.context() + "driver?stat=" + stat);
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
        String driver = request.getParameter("schedule_dv_driver_name"),
                vehicle = request.getParameter("schedule_dv_vehicle_name"),
                note = request.getParameter("schedule_dv_note");
        long driverId = 0, vehicleId = 0;

        if ((driver != null || !driver.equals("")) && ((vehicle != null || !vehicle.equals("")))) {
            try {
                driverId = Long.parseLong(driver);
                vehicleId = Long.parseLong(vehicle);
            } catch (NumberFormatException ex) {
                /**
                 * No error logging goes here.
                 */
                isValid = false;
            }
        } else {
            isValid = false;
        }
        if (note != null) {
            note = note.trim();
        }
        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                VehicleDriver vehicle_driver = new VehicleDriver();
                vehicle_driver.setVehicle(new Vehicle(vehicleId));
                vehicle_driver.setDriver(new Driver(driverId));
                vehicle_driver.setStatus('A');
                vehicle_driver.setCreateUser(admin.getId());
                vehicle_driver.setLastupdateUser(admin.getId());
                vehicle_driver.setNote(note);

                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/vehicledriver/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());

                String result = builder.put(Entity.json(vehicle_driver), String.class);
                if (result != null) {
                    response.sendRedirect(Bundle.context() + "vehicledriverweb?stat=" + result);
                }
                client.getClient().close();
            } else {
                response.sendRedirect(Bundle.context());
            }
        } else {
            response.sendRedirect(Bundle.context() + "driver?stat=-3");
        }
    }
}
