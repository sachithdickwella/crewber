package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.VehicleBrand;
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
@WebServlet(name = "VehicleBrandServlet", urlPatterns = {"/vehiclebrandweb"})
public class VehicleBrandServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes {

    private static final long serialVersionUID = 6928794721306258343L;

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
                .getTarget().path("/vehiclebrand/all")
                .request()
                .headers(GrabmDashboardConstant.Bundle.getHeaders());
        List<VehicleBrand> vehicleBrands = builder.get(new GenericType<List<VehicleBrand>>() {
        });
        getServletContext().setAttribute(CONTEXT_VEHICLEBRANDS, vehicleBrands);
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
        String country = request.getParameter("vehicle_details_brand_country"),
                name = request.getParameter("vehicle_details_brand_name");

        if (name == null || name.equals("")) {
            isValid = false;
        }
        if (country != null) {
            country = country.trim();
        }

        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                VehicleBrand vehicleBrand = new VehicleBrand();
                vehicleBrand.setName(name.trim());
                vehicleBrand.setCountry(country);
                vehicleBrand.setCreateUser(admin.getId());
                vehicleBrand.setLastUpdateUser(admin.getId());

                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/vehiclebrand/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());

                String result = builder.put(Entity.json(vehicleBrand), String.class);
                if (result != null) {
                    long stat = Long.parseLong(result);
                    response.sendRedirect(Bundle.context() + "vehiclebrandweb?stat=" + stat);
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
