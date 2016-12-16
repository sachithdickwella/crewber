package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.Driver;
import com.earcs.grabm.util.GrabmDashboardConstant;
import com.earcs.grabm.util.GrabmDashboardConstant.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Roshin Perera
 */
@MultipartConfig
@WebServlet(name = "DriverServlet", urlPatterns = {"/driverweb"})
public class DriverServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes, GrabmDashboardConstant.Bundle {

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
                .getTarget().path("/driver/all")
                .request()
                .headers(GrabmDashboardConstant.Bundle.getHeaders());
        List<Driver> drivers = builder.get(new GenericType<List<Driver>>() {
        });
        getServletContext().setAttribute(CONTEXT_DRIVER, drivers);
        response.sendRedirect(Bundle.context() + "driver?stat=" + stat);
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
        String first_name = request.getParameter("driver_rg_first_name"),
                last_name = request.getParameter("driver_rg_last_name"),
                nic = request.getParameter("driver_rg_nic"),
                mobile_no1 = request.getParameter("driver_rg_mobile_number_1"),
                mobile_no2 = request.getParameter("driver_rg_mobile_number_2"),
                note = request.getParameter("driver_rg_note");

        Part part = request.getPart("driver_rg_pro_pic");

        if (first_name == null || first_name.equals("")) {
            isValid = false;
        }
        if (last_name == null || last_name.equals("")) {
            isValid = false;
        }
        if (nic == null || nic.equals("")) {
            isValid = false;
        }
        if (mobile_no1 == null || mobile_no1.equals("")) {
            isValid = false;
        }
        if (mobile_no2 != null) {
            mobile_no2 = mobile_no2.trim();
        }
        if (note == null || note.equals("")) {
            isValid = false;
        }
        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                Driver driver = new Driver();
                driver.setFirstName(first_name.trim());
                driver.setLastName(last_name.trim());
                driver.setNic(nic);
                driver.setStatus('A');
                driver.setMobileNumber1(mobile_no1.trim());
                driver.setMobileNumber2(mobile_no2);
                driver.setNote(note);
                                
                try(InputStream is = part.getInputStream()) {
                    byte[] buffer = new byte[is.available()];
                    is.read(buffer);
                    driver.setPhoto(buffer);
                }               
                
                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/driver/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());

                String result = builder.put(Entity.json(driver), String.class);
                if (result != null) {
                    long stat = Long.parseLong(result);
                    response.sendRedirect(Bundle.context() + "driverweb?stat=" + stat);
                }
                client.getClient().close();
            } else {
                response.sendRedirect(Bundle.context());
            }
        } else {
            response.sendRedirect(GrabmDashboardConstant.Bundle.context() + "driver?stat=-3");
        }
    }
}
