package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.Profile;
import com.earcs.grabm.util.Email;
import com.earcs.grabm.util.GrabmDashboardConstant;
import com.earcs.grabm.util.GrabmDashboardConstant.Bundle;
import com.earcs.grabm.util.GrabmDashboardConstant.Attributes;
import com.earcs.grabm.util.RandomString;
import java.io.IOException;
import java.util.Map;
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
@WebServlet(name = "UserServlet", urlPatterns = {"/userweb"})
public class UserServlet extends HttpServlet
        implements Bundle, Attributes {

    private static final long serialVersionUID = -6529380841963633829L;

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
        Map<String, String[]> params = request.getParameterMap();

        Administrator admin = new Administrator();
        admin.setFirstName(params.get("it_admin_rg_first_name")[0]);
        admin.setLastName(params.get("it_admin_rg_last_name")[0]);
        admin.setMobileNo(params.get("it_admin_rg_mobile_number")[0]);
        admin.setEmail(params.get("it_admin_rg_email")[0]);
        admin.setUserName(params.get("it_admin_rg_email")[0]);
        admin.setPassword(RandomString.getRandomString());
        admin.setProfileId(new Profile(Long.valueOf(params.get("it_admin_rg_profile")[0])));
        admin.setNote(params.get("it_admin_rg_note")[0]);
        admin.setStatus('F');

        Administrator admin_session = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
        if (admin_session != null) {
            admin.setCreateUser(admin_session.getId());
            admin.setLastUpdateUser(admin_session.getId());
        }

        GrabmRESTClient client = new GrabmRESTClient();
        Invocation.Builder builder = client
                .getTarget().path("/admin/create")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .headers(GrabmDashboardConstant.Bundle.getHeaders());

        String result = builder.put(Entity.json(admin), String.class);
        if (result != null && !result.equals("")) {
            int id = Integer.valueOf(result);
            if (id > 0) {
                final Email _FisrtLoginMail = new Email();
                _FisrtLoginMail.sendMail(admin.getEmail(), "New Profile - Authentication Request", admin.getPassword(), admin.getFirstName());
            }
            response.sendRedirect(Bundle.context() + "admin?id=" + id);
        }
        client.getClient().close();
    }
}
