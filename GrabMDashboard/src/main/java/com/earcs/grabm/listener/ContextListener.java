package com.earcs.grabm.listener;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.AreaGroup;
import com.earcs.grabm.pojo.Company;
import com.earcs.grabm.pojo.Driver;
import com.earcs.grabm.pojo.EndUser;
import com.earcs.grabm.pojo.FuelType;
import com.earcs.grabm.pojo.Pickup;
import com.earcs.grabm.pojo.Profile;
import com.earcs.grabm.pojo.Vehicle;
import com.earcs.grabm.pojo.VehicleBrand;
import com.earcs.grabm.pojo.VehicleDriver;
import com.earcs.grabm.pojo.VehicleModel;
import com.earcs.grabm.pojo.VehicleType;
import com.earcs.grabm.pojo.WebPage;
import com.earcs.grabm.util.GrabmDashboardConstant;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Sachith Dickwella
 */
@WebListener
public class ContextListener implements ServletContextListener,
        GrabmDashboardConstant, GrabmDashboardConstant.Attributes, GrabmDashboardConstant.Bundle {

    /**
     * Initialize log4j logger.
     */
    private final Logger logger = Logger.getLogger(ContextListener.class);
    /**
     * Backend URL key on servlet context.
     */
    private static final String BACKEND_URL_KEY = "backend_url_key";
    /**
     * Backend URL value on servlet context.
     */
    private static final String BACKEND_URL = "/grabmd/commons";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /**
         * Log4j property configuration from external file.
         */
        PropertyConfigurator.configure(getClass().getResource("/log/log4j.properties"));
        /**
         * Adding backend URL to application context.
         */
        sce.getServletContext().setAttribute(BACKEND_URL_KEY, BACKEND_URL);

        /**
         * Initialize application context meta data.
         */
        final GrabmRESTClient client = new GrabmRESTClient();
        try {
            /**
             * FuelTypes in memory.
             */
            Invocation.Builder builder = client
                    .getTarget().path("/fueltype/all")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            List<?> types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<FuelType>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_FUELTYPES, types);
            /**
             * VehicleTypes in memory.
             */
            builder = client
                    .getTarget().path("/vehicletype/all")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<VehicleType>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_VEHICLETYPES, types);
            /**
             * VehicleBrands in memory.
             */
            builder = client
                    .getTarget().path("/vehiclebrand/all")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<VehicleBrand>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_VEHICLEBRANDS, types);
            /**
             * VehicleModels in memory.
             */
            builder = client
                    .getTarget().path("/vehiclemodel/all")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<VehicleModel>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_VEHICLEMODELS, types);
            /**
             * AreaGroups in memory.
             */
            builder = client
                    .getTarget().path("/areagroup/all")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<AreaGroup>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_AREAGROUP, types);
            /**
             * Companies in memory.
             */
            builder = client
                    .getTarget().path("/company/all")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Company>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_COMPANY, types);
            /**
             * User profiles in memory.
             */
            builder = client
                    .getTarget().path("/profile/idname")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Profile>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_PROFILES, types);
            /**
             * Profile web pages in memory.
             */
            builder = client
                    .getTarget().path("/webpage/idnameurl")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<WebPage>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_WEBPAGES, types);
            /**
             * Pickups in memory.
             */
            builder = client
                    .getTarget().path("/pickup/future")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Pickup>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_FUTUREPICKUPDS, types);
            /**
             * Vehicles in memory.
             */
            builder = client
                    .getTarget().path("/vehicle/all")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Vehicle>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_VEHICLE, types);
            /**
             * Drivers in memory.
             */
            builder = client
                    .getTarget().path("/driver/all")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Driver>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_DRIVER, types);
            /**
             * End users in memory.
             */
            builder = client
                    .getTarget().path("/enduser/all")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<EndUser>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_ENDUSER, types);
            /**
             * Vehicle Drivers in memory.
             */
            builder = client
                    .getTarget().path("/vehicledriver/all/ACTIVE")
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders())
                    .accept(MediaType.APPLICATION_JSON_TYPE);
            types = builder.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<VehicleDriver>>() {
            });
            sce.getServletContext().setAttribute(CONTEXT_VEHICLEDRIVER, types);
            /**
             * Application context path.
             */
            sce.getServletContext().setAttribute(CONTEXT_PATH, Bundle.context());
        } catch (Exception ex) {
            logger.error(EXCEPTION_INITIALIZING, ex);
        } finally {
            /**
             * End of the initializing phase close the client connection.
             */
            client.getClient().close();
            System.gc();
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /**
         * Nothing goes here.
         */
    }
}
