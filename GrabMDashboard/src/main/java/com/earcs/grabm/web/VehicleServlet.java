package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.AreaGroup;
import com.earcs.grabm.pojo.Company;
import com.earcs.grabm.pojo.Vehicle;
import com.earcs.grabm.pojo.VehicleModel;
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
@WebServlet(name = "VehicleServlet", urlPatterns = {"/vehicleweb"})
public class VehicleServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes {

    private static final long serialVersionUID = -8078947430075000080L;

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
                .getTarget().path("/vehicle/all")
                .request()
                .headers(GrabmDashboardConstant.Bundle.getHeaders());
        List<Vehicle> vehicles = builder.get(new GenericType<List<Vehicle>>() {
        });
        getServletContext().setAttribute(CONTEXT_VEHICLE, vehicles);
        response.sendRedirect(Bundle.context() + "vehicle?stat=" + stat);
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
        String reg_no = request.getParameter("vehicle_rg_registration_number"),
                vehicle_model = request.getParameter("vehicle_rg_vehicle_model"),
                company = request.getParameter("vehicle_rg_vehicle_company"),
                area_group = request.getParameter("vehicle_rg_vehicle_group"),
                min_passengers = request.getParameter("vehicle_rg_min_passengers"),
                max_passengers = request.getParameter("vehicle_rg_max_passengers"),
                primary_color = request.getParameter("vehicle_rg_color_1"),
                secondry_color = request.getParameter("vehicle_rg_color_2"),
                note = request.getParameter("vehicle_rg_note"),
                mileage = request.getParameter("vehicle_rg_mileage"),
                tracker_imei = request.getParameter("vehicle_rg_tracker_imei"),
                tracker_mobileno = request.getParameter("vehicle_rg_mobile_number");

        int mileageInt = 0, min_passengersInt = 0, max_passengersInt = 0;
        if (reg_no == null || reg_no.equals("")) {
            isValid = false;
        }
        if (min_passengers == null || min_passengers.equals("")) {
            isValid = false;
        } else {
            try {
                min_passengersInt = Integer.parseInt(min_passengers);
            } catch (NumberFormatException ex) {
                /**
                 * Disregards exception logging.
                 */
                isValid = false;
            }
        }
        if (max_passengers == null || max_passengers.equals("")) {
            isValid = false;
        } else {
            try {
                max_passengersInt = Integer.parseInt(max_passengers);
            } catch (NumberFormatException ex) {
                /**
                 * Disregards exception logging.
                 */
                isValid = false;
            }
        }
        if (primary_color == null || primary_color.equals("")) {
            isValid = false;
        }
        if (secondry_color != null) {
            secondry_color = secondry_color.trim();
        }
        if (mileage == null || mileage.equals("")) {
            isValid = false;
        } else {
            try {
                mileageInt = Integer.parseInt(mileage);
            } catch (NumberFormatException e) {
                /**
                 * Disregards exception logging.
                 */
                isValid = false;
            }
        }
        if (tracker_imei == null || tracker_imei.equals("")) {
            isValid = false;
        }
        if (tracker_mobileno == null || tracker_mobileno.equals("")) {
            isValid = false;
        }

        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                Vehicle vehicle = new Vehicle();
                vehicle.setRegistrationNumber(reg_no.trim());
                vehicle.setColor1(primary_color.trim());
                vehicle.setColor2(secondry_color);
                vehicle.setNote(note);
                vehicle.setMileage(mileageInt);
                vehicle.setPassengersMin(min_passengersInt);
                vehicle.setPassengersMax(max_passengersInt);

                List<?> types = (List<VehicleModel>) getServletContext().getAttribute(CONTEXT_VEHICLEMODELS);
                VehicleModel model = (VehicleModel) types.parallelStream()
                        .filter(obj -> Long.parseLong(vehicle_model) == ((VehicleModel) obj).getId())
                        .findAny()
                        .orElse(null);
                vehicle.setVehicleModelId(model);

                types = (List<Company>) getServletContext().getAttribute(CONTEXT_COMPANY);
                Company comp = (Company) types.parallelStream()
                        .filter(obj -> Long.parseLong(company) == ((Company) obj).getId())
                        .findAny()
                        .orElse(null);
                vehicle.setCompanyId(comp);

                types = (List<AreaGroup>) getServletContext().getAttribute(CONTEXT_AREAGROUP);
                AreaGroup area = (AreaGroup) types.parallelStream()
                        .filter(obj -> Long.parseLong(area_group) == ((AreaGroup) obj).getId())
                        .findAny()
                        .orElse(null);
                vehicle.setGroupId(area);

                vehicle.setStatus('A');
                vehicle.setTrackerImei(tracker_imei.trim());
                vehicle.setTrackerMobileNumber(tracker_mobileno.trim());
                vehicle.setCreateUser(admin.getId());
                vehicle.setLastupdateUser(admin.getId());

                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/vehicle/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());

                String result = builder.put(Entity.json(vehicle), String.class);
                if (result != null) {
                    long stat = Long.parseLong(result);
                    response.sendRedirect(Bundle.context() + "vehicleweb?stat=" + stat);
                }
                client.getClient().close();
            } else {
                response.sendRedirect(Bundle.context());
            }
        } else {
            response.sendRedirect(Bundle.context() + "vehicle?stat=-3");
        }
    }
}
