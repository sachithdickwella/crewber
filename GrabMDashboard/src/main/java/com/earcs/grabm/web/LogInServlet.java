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
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Invocation;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "LogInServlet", urlPatterns = {"/login"})
public class LogInServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes, GrabmDashboardConstant.Bundle {

    private static final long serialVersionUID = -1535720065584181729L;

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
        String user = request.getParameter("user"),
                pass = request.getParameter("pass");

        GrabmRESTClient client = new GrabmRESTClient();
        Invocation.Builder builder = client
                .getTarget().path("/admin/login")
                .queryParam("user", user)
                .queryParam("pass", pass)
                .request()
                .headers(GrabmDashboardConstant.Bundle.getHeaders());

        Administrator admin = builder.post(null, Administrator.class);

        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute(SESSION_ADMIN, admin);
            switch(admin.getStatus()) {
                case 'F':
                    /**
                     * First login.
                     */
                    response.sendRedirect(Bundle.context() + "admin-activate-account");
                    break;
                case 'I':
                    /**
                     * TODO when inactive.
                     */
                    break;
                case 'B':
                    /**
                     * TODO when blocked.
                     */
                    break;
                default:
                    /**
                     * Active user login.
                     */
                    response.sendRedirect(Bundle.context() + "profile");
                    break;
            }
        } else {
            response.sendRedirect(Bundle.context() + "?stat=f");
        }
        client.getClient().close();
    }
}
