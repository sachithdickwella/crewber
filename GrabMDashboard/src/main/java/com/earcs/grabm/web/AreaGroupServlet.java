package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.AreaGroup;
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
@WebServlet(name = "AreaGroupServlet", urlPatterns = {"/areagroupweb"})
public class AreaGroupServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes, GrabmDashboardConstant.Bundle {

    private static final long serialVersionUID = 3710898760283385956L;

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
                .getTarget().path("/areagroup/all")
                .request()
                .headers(GrabmDashboardConstant.Bundle.getHeaders());
        List<AreaGroup> areaGroups = builder.get(new GenericType<List<AreaGroup>>() {
        });
        getServletContext().setAttribute(CONTEXT_AREAGROUP, areaGroups);
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
        String name = request.getParameter("vehicle_details_area_group_name");

        if (name == null || name.equals("")) {
            isValid = false;
        }

        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                AreaGroup areaGroup = new AreaGroup();
                areaGroup.setName(name.trim());
                areaGroup.setCreateUser(admin.getId());
                areaGroup.setLastUpdateUser(admin.getId());

                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/areagroup/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());

                String result = builder.put(Entity.json(areaGroup), String.class);
                if (result != null) {
                    long stat = Long.parseLong(result);
                    response.sendRedirect(Bundle.context() + "areagroupweb?stat=" + stat);
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
