package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.FuelType;
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

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "FuelTypeServlet", urlPatterns = {"/fueltypeweb"})
public class FuelTypeServlet extends HttpServlet
        implements GrabmDashboardConstant.Bundle, GrabmDashboardConstant.Attributes {

    private static final long serialVersionUID = -8146758715190362984L;

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
                .getTarget().path("/fueltype/all")
                .request()
                .headers(GrabmDashboardConstant.Bundle.getHeaders());
        List<FuelType> fuelTypes = builder.get(new GenericType<List<FuelType>>() {
        });
        getServletContext().setAttribute(CONTEXT_FUELTYPES, fuelTypes);
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
        String short_name = request.getParameter("vehicle_details_fuel_type_short_name"),
                long_name = request.getParameter("vehicle_details_fuel_type_long_name");

        if (short_name == null || short_name.equals("")) {
            isValid = false;
        }
        if (long_name == null || long_name.equals("")) {
            isValid = false;
        }

        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                FuelType fuelType = new FuelType();
                fuelType.setShortName(short_name.trim().charAt(0));
                fuelType.setLongName(long_name.trim());
                fuelType.setCreateUser(admin.getId());
                fuelType.setLastUpdateUser(admin.getId());

                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/fueltype/create")
                        .request()
                        .headers(Bundle.getHeaders());

                String result = builder.put(Entity.json(fuelType), String.class);
                if (result != null) {
                    long stat = Long.parseLong(result);
                    response.sendRedirect(Bundle.context() + "fueltypeweb?stat=" + stat);
                }
                client.getClient().close();
            } else {
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect(Bundle.context() + "vehicle-detail?stat=-3");
        }
    }
}
