package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.util.GrabmDashboardConstant;
import com.earcs.grabm.util.GrabmDashboardConstant.Bundle;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Invocation;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "FirstLoginServlet", urlPatterns = {"/first-login"})
public class FirstLoginServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes {

    private static final long serialVersionUID = 4373937151667255928L;

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
        String userName = request.getParameter("xt_adm_rg_user_name"),
                tempPassword = request.getParameter("xt_adm_rg_temp_password"),
                newPassword = request.getParameter("xt_adm_rg_password"),
                retypePassword = request.getParameter("xt_adm_rg_repeat_password");

        if (newPassword.equals(retypePassword)) {
            final Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/admin/firstlogin")
                        .queryParam("user", userName)
                        .queryParam("temppass", tempPassword)
                        .queryParam("newpass", newPassword)
                        .queryParam("adminId", admin.getId())
                        .request()
                        .headers(Bundle.getHeaders());

                String result = builder.post(null, String.class);
                if (result != null) {
                    int resultValue = Integer.parseInt(result);
                    switch (resultValue) {
                        case 0:
                            request.getSession().invalidate();
                            response.sendRedirect(Bundle.context() + "profile");
                            break;
                        default:
                            response.sendRedirect(Bundle.context() + "admin-activate-account?stat=" + resultValue);
                            break;
                    }
                }
                client.getClient().close();
            }
        } else {
            response.sendRedirect(Bundle.context() + "admin-activate-account?stat=-2");
        }
    }
}
