package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.Profile;
import com.earcs.grabm.pojo.WebPage;
import com.earcs.grabm.util.GrabmDashboardConstant;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes.SESSION_ADMIN;
import com.earcs.grabm.util.GrabmDashboardConstant.Bundle;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "WebProfileServlet", urlPatterns = {"/webprofileweb"})
public class WebProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1459145454007430866L;

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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String profile_id = request.getParameter("ui_web_page_rg_profile");
        String[] web_page_ids = request.getParameterValues("ui_web_page_rg_web_pages");

        Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);

        Profile profile = new Profile();
        profile.setId(Long.parseLong(profile_id));

        List<WebPage> webPages = new LinkedList<>();
        for (String webPageId : web_page_ids) {
            WebPage webPage = new WebPage(Long.parseLong(webPageId));
            webPages.add(webPage);
        }
        profile.setWebPageList(webPages);

        if (admin != null) {
            profile.setLastupdateUser(admin.getId());
        }

        GrabmRESTClient rClient = new GrabmRESTClient();
        Invocation.Builder builder = rClient.getTarget()
                .path("/profile/update/weblist")
                .request(MediaType.TEXT_PLAIN)
                .headers(GrabmDashboardConstant.Bundle.getHeaders());

        String stat = builder.post(Entity.json(profile), String.class);
        response.sendRedirect(Bundle.context() + "profileweb?get=idname&stat=" + stat);
        rClient.getClient().close();
    }
}
