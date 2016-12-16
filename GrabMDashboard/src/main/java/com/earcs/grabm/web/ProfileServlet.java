package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.Profile;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes;
import static com.earcs.grabm.util.GrabmDashboardConstant.Bundle;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profileweb"})
public class ProfileServlet extends HttpServlet
        implements Bundle, Attributes {

    private static final long serialVersionUID = -553816579972523794L;

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
        String get = request.getParameter("get"),
                id = request.getParameter("id"),
                stat = request.getParameter("stat");

        GrabmRESTClient rClient = new GrabmRESTClient();
        WebTarget target = rClient.getTarget().path("/profile/");

        switch (get) {
            case "all":
                target = target.path(get);
                break;
            case "idname":
                target = target.path(get);
                if (id != null) {
                    target = target.path("/" + id);
                }
                break;
        }

        Invocation.Builder builder = target.request()
                .headers(Bundle.getHeaders())
                .accept(MediaType.APPLICATION_JSON_TYPE);

        if (get != null) {
            if (get.equals("all")
                    || (get.equals("idname") && id == null)) {
                getServletContext().setAttribute(
                        CONTEXT_PROFILES,
                        builder.get(new GenericType<List<Profile>>() {
                        }));
            } else {
                request.getSession().setAttribute(
                        SESSION_PROFILE,
                        builder.get(Profile.class));
            }
        }
        String redirect_url = Bundle.context() + "ui-profile-registry";
        if (stat != null) {
            redirect_url += "?stat=" + stat;
        }
        response.sendRedirect(redirect_url);
        rClient.getClient().close();
    }

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
        String profileName = request.getParameter("ui_profile_rg_name"),
                profileNote = request.getParameter("ui_profile_rg_note");

        GrabmRESTClient rClient = new GrabmRESTClient();
        Invocation.Builder builder = rClient.getTarget()
                .path("/profile/create")
                .request(MediaType.TEXT_PLAIN)
                .headers(Bundle.getHeaders());

        Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
        Profile profile = new Profile();
        profile.setName(profileName);
        profile.setDescription(profileNote);

        if (admin != null) {
            profile.setCreateUser(admin.getId());
            profile.setLastupdateUser(admin.getId());
        }

        String result = builder.put(Entity.json(profile), String.class);
        response.sendRedirect(Bundle.context() + "profileweb?get=idname&stat=" + result);
        rClient.getClient().close();
    }
}
