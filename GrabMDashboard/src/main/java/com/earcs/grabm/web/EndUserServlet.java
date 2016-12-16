package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.Designation;
import com.earcs.grabm.pojo.EndUser;
import com.earcs.grabm.util.EntityBean;
import com.earcs.grabm.util.GrabmDashboardConstant;
import java.io.IOException;
import java.util.Map;
import com.earcs.grabm.util.GrabmDashboardConstant.Bundle;
import com.earcs.grabm.util.GrabmDashboardConstant.Attributes;
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
@WebServlet(name = "EndUserServlet", urlPatterns = {"/enduser"})
public class EndUserServlet extends HttpServlet
        implements Bundle, Attributes {

    private static final long serialVersionUID = -6901031081656479522L;

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
                .getTarget().path("/enduser/all")
                .request()
                .headers(GrabmDashboardConstant.Bundle.getHeaders());

        List<EndUser> endUsers = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<EndUser>>() {
        });
        getServletContext().setAttribute(CONTEXT_ENDUSER, endUsers);
        response.sendRedirect(Bundle.context() + "flight-crew?stat=" + stat);
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();

        EndUser endUser = new EndUser();
        endUser.setPrefix(params.get("it_user_rg_title")[0]);
        endUser.setFirstName(params.get("it_user_rg_first_name")[0].trim());
        endUser.setLastName(params.get("it_user_rg_last_name")[0].trim());
        endUser.setDesignation(new Designation(Long.parseLong(params.get("it_user_rg_designaion")[0])));
        endUser.setMembershipNumber(params.get("it_user_rg_member_id")[0].trim());
        endUser.setMobileNumber(params.get("it_user_rg_mobile_number")[0].trim());
        endUser.setEmail(params.get("it_user_rg_email")[0].trim());
        endUser.setAddressLine1(params.get("it_user_rg_address_1")[0].trim());
        endUser.setAddressLine2(params.get("it_user_rg_address_2")[0].trim());
        endUser.setAddressLine2(params.get("it_user_rg_address_2")[0].trim());
        endUser.setCityTown(params.get("it_user_rg_city")[0].trim());
        endUser.setZipCode(params.get("it_user_rg_zip_code")[0]);
        endUser.setCountry(params.get("it_user_rg_country")[0].trim());
        endUser.setNote(params.get("it_user_rg_note")[0].trim());
        endUser.setStatus(EntityBean.Status.ACTIVE.toString().charAt(0));
        endUser.setSubscriptionStatus(EntityBean.SubscriptionStatus.NO_SUBSCRIPTION.toString().charAt(0));
        endUser.setLocationStatus(EntityBean.LocationStatus.NO_GPS_LOCATION.toString().charAt(0));

        Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
        if (admin != null) {
            endUser.setCreateUser(admin.getId());
            endUser.setLastupdateUser(admin.getId());
        }

        GrabmRESTClient client = new GrabmRESTClient();
        Invocation.Builder builder = client
                .getTarget().path("/enduser/create")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .headers(Bundle.getHeaders());
        String respond = builder.put(Entity.json(endUser), String.class);
        response.sendRedirect(Bundle.context() + "enduser?stat=" + respond);
        client.getClient().close();
    }
}
