package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.VehicleType;
import com.earcs.grabm.util.GrabmDashboardConstant;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes.SESSION_ADMIN;
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

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "VehicleTypeServlet", urlPatterns = {"/vehicletypeweb"})
public class VehicleTypeServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes {

    private static final long serialVersionUID = 909306291645496402L;

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
                .getTarget().path("/vehicletype/all")
                .request()
                .headers(GrabmDashboardConstant.Bundle.getHeaders());
        List<VehicleType> fuelTypes = builder.get(new GenericType<List<VehicleType>>() {
        });
        getServletContext().setAttribute(CONTEXT_VEHICLETYPES, fuelTypes);
        response.sendRedirect(Bundle.context() + "vehicle-detail?stat=" + stat);
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
        String short_name = request.getParameter("vehicle_details_vehicle_type_short_name"),
                long_name = request.getParameter("vehicle_details_vehicle_type_long_name");

        if (short_name == null || short_name.equals("")) {
            isValid = false;
        }
        if (long_name == null || long_name.equals("")) {
            isValid = false;
        }

        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                VehicleType vehicleType = new VehicleType();
                vehicleType.setShortName(short_name.trim().charAt(0));
                vehicleType.setLongName(long_name.trim());
                vehicleType.setCreateUser(admin.getId());
                vehicleType.setLastUpdateUser(admin.getId());

                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/vehicletype/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());

                String result = builder.put(Entity.json(vehicleType), String.class);
                if (result != null) {
                    long stat = Long.parseLong(result);
                    response.sendRedirect(Bundle.context() + "vehicletypeweb?stat=" + stat);
                }
                client.getClient().close();
            } else {
                response.sendRedirect(Bundle.context());
            }
        } else {
            response.sendRedirect(Bundle.context() + "vehicle-detail?stat=-3");
        }
    }
}
