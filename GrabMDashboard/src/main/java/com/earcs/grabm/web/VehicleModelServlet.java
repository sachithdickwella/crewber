package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.FuelType;
import com.earcs.grabm.pojo.VehicleBrand;
import com.earcs.grabm.pojo.VehicleModel;
import com.earcs.grabm.pojo.VehicleType;
import com.earcs.grabm.util.GrabmDashboardConstant;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes.CONTEXT_VEHICLEBRANDS;
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
@WebServlet(name = "VehicleModelServlet", urlPatterns = "/vehiclemodelweb")
public class VehicleModelServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes {

    private static final long serialVersionUID = 6966415183684641804L;

    private boolean isValid = true;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stat = request.getParameter("stat");

        GrabmRESTClient client = new GrabmRESTClient();
        Invocation.Builder builder = client
                .getTarget().path("/vehiclemodel/all")
                .request()
                .headers(Bundle.getHeaders());
        List<VehicleModel> vehicleModels = builder.get(new GenericType<List<VehicleModel>>() {
        });
        getServletContext().setAttribute(CONTEXT_VEHICLEMODELS, vehicleModels);
        response.sendRedirect(Bundle.context() + "vehicle-detail?stat=" + stat);
        client.getClient().close();
    }

    @SuppressWarnings("null")
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vehicleBrand = request.getParameter("vehicle_details_model_brand"),
                vehicleType = request.getParameter("vehicle_details_model_vehicle_type"),
                modelName = request.getParameter("vehicle_details_model_name"),
                year = request.getParameter("vehicle_details_year_name"),
                fueltype = request.getParameter("vehicle_details_vehicle_model_fuel_type"),
                tankSize = request.getParameter("vehicle_details_model_tank_size"),
                fuelEfficiency = request.getParameter("vehicle_details_model_fuel_efficiency");

        int yearInt = 0, tankSizeInt = 0, fuelEfficiencyInt = 0;

        if (modelName == null || modelName.equals("")) {
            isValid = false;
        }
        if (year == null || year.equals("")) {
            isValid = false;
        } else {
            try {
                yearInt = Integer.parseInt(year.trim());
                tankSizeInt = Integer.parseInt(tankSize.trim());
                fuelEfficiencyInt = Integer.parseInt(fuelEfficiency.trim());
            } catch (NumberFormatException ex) {
                /**
                 * No exception loggers goes here.
                 */
                isValid = false;
            }
        }

        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                VehicleModel vehicleModel = new VehicleModel();
                vehicleModel.setName(modelName.trim());
                vehicleModel.setYear(yearInt);
                vehicleModel.setFuelTankSize(tankSizeInt);
                vehicleModel.setFuelEfficiency(fuelEfficiencyInt);
                vehicleModel.setCreateUser(admin.getId());
                vehicleModel.setLastUpdateUser(admin.getId());

                List<?> types = (List<FuelType>) getServletContext().getAttribute(CONTEXT_FUELTYPES);
                FuelType ft = (FuelType) types.parallelStream()
                        .filter(obj -> fueltype.charAt(0) == ((FuelType) obj).getShortName())
                        .findAny().orElse(null);
                vehicleModel.setFuelType(ft);

                types = (List<VehicleType>) getServletContext().getAttribute(CONTEXT_VEHICLETYPES);
                VehicleType vt = (VehicleType) types.parallelStream()
                        .filter(obj -> vehicleType.charAt(0) == ((VehicleType) obj).getShortName())
                        .findAny().orElse(null);
                vehicleModel.setVehicleTypeId(vt);

                types = (List<VehicleBrand>) getServletContext().getAttribute(CONTEXT_VEHICLEBRANDS);
                VehicleBrand vb = (VehicleBrand) types.parallelStream()
                        .filter(obj -> Integer.parseInt(vehicleBrand) == ((VehicleBrand) obj).getId())
                        .findAny().orElse(null);
                vehicleModel.setVehicleBrandId(vb);

                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/vehiclemodel/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());

                String result = builder.put(Entity.json(vehicleModel), String.class);
                if (result != null) {
                    long stat = Long.parseLong(result);
                    response.sendRedirect(Bundle.context() + "vehiclemodelweb?stat=" + stat);
                }
                client.getClient().close();
            } else {
                response.sendRedirect(Bundle.context());
            }
        } else {
            response.sendRedirect(Bundle.context() + "vehicle-detail?stat=-3");
        }
    }
}
