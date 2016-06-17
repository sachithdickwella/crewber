package com.grabm.service;

import com.grabm.service.subresource.AdministratorResource;
import com.grabm.service.subresource.AreaGroupResource;
import com.grabm.service.subresource.CompanyResource;
import com.grabm.service.subresource.DriverResource;
import com.grabm.service.subresource.EndUserResource;
import com.grabm.service.subresource.FuelTypeResource;
import com.grabm.service.subresource.LocationServiceResource;
import com.grabm.service.subresource.PickupResource;
import com.grabm.service.subresource.PickupScheduleResource;
import com.grabm.service.subresource.ProfileResource;
import com.grabm.service.subresource.VehicleBrandResource;
import com.grabm.service.subresource.VehicleDriverResource;
import com.grabm.service.subresource.VehicleModelResource;
import com.grabm.service.subresource.VehicleResource;
import com.grabm.service.subresource.VehicleTypeResource;
import com.grabm.service.subresource.WebPageResource;
import javax.ws.rs.Path;

/**
 *
 * @author Sachith Dickwella
 */
@Path("/gmdresource")
public class GrabMResource {

    @Path("/admin")
    public AdministratorResource getAdministratorResource() {
        return new AdministratorResource();
    }

    @Path("/profile")
    public ProfileResource getProfileResource() {
        return new ProfileResource();
    }

    @Path("/webpage")
    public WebPageResource getWebPageResource() {
        return new WebPageResource();
    }

    @Path("/driver")
    public DriverResource getDriverResource() {
        return new DriverResource();
    }
    
    @Path("/vehicle")
    public VehicleResource getVehicleResource() {
        return new VehicleResource();
    }
    
    @Path("/areagroup")
    public AreaGroupResource getAreaGroupResource() {
        return new AreaGroupResource();
    }
    
    @Path("/company")
    public CompanyResource getCompanyResource() {
        return new CompanyResource();
    }
    
    @Path("/vehiclebrand")
    public VehicleBrandResource getVehicleBrandResource() {
        return new VehicleBrandResource();
    }
    
    @Path("/fueltype")
    public FuelTypeResource getFuelTypeResource() {
        return new FuelTypeResource();
    }
    
    @Path("/vehicletype")
    public VehicleTypeResource getVehicleTypeResource() {
        return new VehicleTypeResource();
    }
    
    @Path("/vehiclemodel")
    public VehicleModelResource getVehicleModelResource() {
        return new VehicleModelResource();
    }
    
    @Path("/vehicledriver")
    public VehicleDriverResource getVehicleDriverResource() {
        return new VehicleDriverResource();
    }
    
    @Path("/pickup")
    public PickupResource getPickupResource() {
        return new PickupResource();
    }
    
    @Path("/pickupschedule")
    public PickupScheduleResource getPickupScheduleResource() {
        return new PickupScheduleResource();
    }
    
    @Path("/enduser")
    public EndUserResource getEndUserResource() {
        return new EndUserResource();
    }
    
    @Path("/location")
    public LocationServiceResource getLocationServiceResource() {
        return new LocationServiceResource();
    }
}
