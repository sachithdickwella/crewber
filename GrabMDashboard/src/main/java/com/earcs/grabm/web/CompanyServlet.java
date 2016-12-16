package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.Company;
import com.earcs.grabm.util.GrabmDashboardConstant;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes.SESSION_ADMIN;
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
@WebServlet(name = "CompanyServlet", urlPatterns = {"/companyweb"})
public class CompanyServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes {

    private static final long serialVersionUID = 8636617319408684679L;
    
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
                .getTarget().path("/company/all")
                .request()
                .headers(Bundle.getHeaders());
        List<Company> companies = builder.get(new GenericType<List<Company>>() {
        });
        getServletContext().setAttribute(CONTEXT_COMPANY, companies);
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
        String company_name = request.getParameter("vehicle_details_company_name"),
                telephone_no = request.getParameter("vehicle_details_company_tp_number"),
                mobile_no = request.getParameter("vehicle_details_company_mobile_number"),
                address_line1 = request.getParameter("vehicle_details_company_address_1"),
                address_line2 = request.getParameter("vehicle_details_company_address_2"),
                city = request.getParameter("vehicle_details_company_city"),
                zipcode = request.getParameter("vehicle_details_company_zip_code"),
                country = request.getParameter("vehicle_details_company_country");
        
        if (company_name == null || company_name.equals("")) {
            isValid = false;
        }
        if (telephone_no != null) {
            telephone_no = telephone_no.trim();
        }
        if (mobile_no != null) {
            mobile_no = mobile_no.trim();
        }
        if (address_line1 == null || address_line1.equals("")) {
            isValid = false;
        }
        if (address_line2 != null) {
            address_line2 = address_line2.trim();
        }
        if (city == null || city.equals("")) {
            isValid = false;
        }
        if (zipcode != null) {
            zipcode = zipcode.trim();
        }
        if (country == null || country.equals("")) {
            isValid = false;
        }
        
        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                Company company = new Company();
                company.setName(company_name.trim());
                company.setTelephoneNumber(telephone_no);
                company.setMobileNumber(mobile_no);
                company.setAddressLine1(address_line1.trim());
                company.setAddressLine2(address_line2);
                company.setCityTown(city);
                company.setZipCode(zipcode);
                company.setCountry(country.trim());
                company.setCreateUser(admin.getId());
                company.setLastUpdateUser(admin.getId());
                
                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/company/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());
                
                String result = builder.put(Entity.json(company), String.class);
                if (result != null) {
                    long stat = Long.parseLong(result);
                    response.sendRedirect(Bundle.context() + "companyweb?stat=" + stat);
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
