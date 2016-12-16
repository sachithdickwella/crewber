<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%--
    Depricated UI
--%>
<div class="row">
    <div class="col-md-7">
        <g:execute-stat>
            <c:if test="${stat gt '0'}">
                <div class="stat-respond">
                    <div class="stat-respond-success">
                        successfully scheduled a crew member
                    </div>
                </div>
            </c:if>
            <c:if test="${stat eq '-1'}">
                <div class="stat-respond">
                    <div class="stat-respond-danger">
                        Failed to schedule
                    </div>
                </div>
            </c:if>
        </g:execute-stat>
        <div class="box-header">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Schedule Crew</h3>
        </div>

        <form role="form" data-toggle="validator" id="shcedule-user-form" action="/grabmd/pickupscheduleweb" method="POST">
            <input type="hidden" id="tran_count" name="tran_count" value="1"/>
            <div class="form-group">
                <div class="col-md-12 pt-10">
                    <label class="control-label label-overide reg-form-lable" for="schedule_user_flight_number">Flight Number:</label>
                    <div class="max-comp-width">
                        <select name="schedule_user_flight_number" id="schedule_user_flight_number" class="form-control select2" style="width: 100% !important;">
                            <c:forEach var="x" items="${futurepickups}">
                                <fmt:formatDate var="flightDate" value="${x.flightDateTime}" type="date" pattern="MMM-dd-yyyy HH:mm (Z)"/>
                                <option value="${x.id}">${x.flightNo} | ${flightDate}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="pull-right">
                        <button type="button" data-toggle="collapse" data-target="#flight-number-details" class="btn bg-theme-active2 btn-sm"><i class="fa fa-bars"></i>
                        </button>
                    </div>
                </div>
            </div>

            <div class="form-group margin-bottom-none">
                <div class="col-md-12 pt-10 collapse" id="flight-number-details">
                    <!--Load Schedule Data-->
                    <div class="row add-bottom-border hidden">
                        <div class="col-md-6">
                            <label class="text-uppercase txt-light-gray txt-light-weight2">Flight Number : </label> <span><b class="text-uppercase">KAL-0222</b></span>
                        </div>
                        <div class="col-md-6">
                            <label class="text-uppercase txt-light-gray txt-light-weight2">Depature Date/time : </label> <span><b class="text-uppercase">05/05/2016 <br/>- 06:50 PM</b></span>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <label class="text-uppercase txt-light-gray txt-light-weight2">Note : </label> 
                            <p id="note"></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group margin-bottom-none">
                <div class="col-md-12 pt-10">
                    <div class="row sub-heading-1 theme-sub-heading text-uppercase text-center">
                        | Add Crew to the Schedule |
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12 pt-10 margin-bottom">
                    <label class="control-label label-overide reg-form-lable" for="it_admin_rg_profile">Flight Crew Member:</label>
                    <div class="max-comp-width">
                        <select name="it_admin_rg_profile" id="it_admin_rg_profile" class="form-control select2" style="width: 100% !important;">
                            <c:forEach var="x" items="${endusers}">
                                <option value="${x.id}">${x.firstName} ${x.lastName} - ${x.designation} (${x.membershipNumber})</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="pull-right hidden">

                        <button type="button" data-toggle="collapse" data-target="#flight-crew-member-details" class="btn bg-theme-active2 btn-sm"><i class="fa fa-bars"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div class="form-group margin-bottom-none hidden">
                <div class="col-md-12 pt-10 collapse" id="flight-crew-member-details">
                    <!--Load Schedule Data-->
                    <div class="row add-bottom-border">
                        <div class="col-md-6">
                            <label class="text-uppercase txt-light-gray txt-light-weight2">Crew Member : </label> <span><b class="text-uppercase">John Doe | PILOT |</b></span>
                        </div>
                        <div class="col-md-6">
                            <label class="text-uppercase txt-light-gray txt-light-weight2">Pickup Address : </label> <span><b class="text-uppercase">No : 22/2 | horton place | colombo 7 |</b></span>
                        </div>
                    </div>
                </div>
            </div>

            <!--Transit Section-->

            <div id="entry1" class="form-group  transits-section-wrapper col-md-12 clonedInput">
                <div class="form-group margin-bottom-none">
                    <div class="col-md-12 pt-10">
                        <div class="with-border">
                            <h3 id="reference" class="margin-top-none box-title text-uppercase txt-light-gray txt-light-weight2 heading-reference">Pick Up</h3>
                        </div>
                    </div>
                </div>

                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">

                        <label class="control-label label-overide reg-form-lable lable_user_pickup_date_time" for="user_sch_pickup_date_time_1">Pickup Date/Time:</label>
                        <div class="max-comp-width">
                            <div class='input-group date datetimepicker'>
                                <input type='text' class="form-control input_user_pickup_date_time " name="user_sch_pickup_date_time_1" id="user_sch_pickup_date_time_" placeholder="Select Date/Time" />
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group margin-bottom-none">
                    <div class="col-md-12 pt-10">
                        <label class="control-label label-overide reg-form-lable lable_pick_vehicle" for="user_sch_pick_vehicle_1">Vehicle</label>
                        <div class="max-comp-width">
                            <select name="user_sch_pick_vehicle_1" id="user_sch_pick_vehicle_1" class="form-control select2 select_user_sch_vehicle" style="width: 100% !important;">
                                <c:forEach var="x" items="${vehicledrivers}">
                                    <option value="${x.id}">${x.vehicle.vehicleModelId.vehicleTypeId.longName} (${x.vehicle.registrationNumber}) - ${x.driver.firstName} ${x.driver.lastName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="pull-right">
                            <button type="button" data-toggle="collapse" data-target="#vehicle-details_1" class="btn bg-theme-active2 btn-sm btn-vehicle-details"><i class="fa fa-bars"></i>
                            </button>
                        </div>
                    </div>
                </div>


                <div class="form-group margin-bottom-none">
                    <div class="col-md-12 pt-10 collapse div_vehicle_details margin-bottom" id="vehicle-details_1">
                        <!--Load Schedule Data-->
                        <div class="row">
                            <div class="col-md-6 hidden">
                                <label class="text-uppercase txt-light-gray txt-light-weight2">V/Reg Number : </label> <span><label class="text-uppercase lable_user_sch_v_r_number" id="user_sch_v_r_number_1">KAL-0222</label></span>
                            </div>
                            <div class="col-md-6 hidden">
                                <label class="text-uppercase txt-light-gray txt-light-weight2">Driver : </label> <span><label class="text-uppercase lable_user_sch_driver_name"  id="user_sch_driver_name_1">John Doe</label></span>
                            </div>
                            <div class="col-md-6">
                                <label class="text-uppercase txt-light-gray txt-light-weight2">Max Passengers Allowed: </label> <span><label class="text-uppercase lable_user_sch_max_passengers_allowed" id="user_sch_max_passengers_allowed_1">3 </label></span>
                            </div>
                            <div class="col-md-6">
                                <label class="text-uppercase txt-light-gray txt-light-weight2">Scheduled Passengers: </label> <span><label class="text-uppercase lable_user_sch_scheduled_passengers" id="user_sch_scheduled_passengers_1">2</label></span>
                            </div>
                        </div>

                        <div class="row hidden">
                            <div class="col-md-12 add-bottom-border">
                                <label class="text-uppercase txt-light-gray txt-light-weight2">Note : </label> 
                                <p class="lable_user_sch_note" id="user_sch_note">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas rhoncus consectetur lorem, 
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group margin-bottom-none">
                    <div class="col-md-4 pt-10">
                        <label class="control-label label-overide reg-form-lable lable_user_sch_pickup_index" for="user_sch_pickup_index_1">Pickup Index:</label>
                        <div class="max-comp-width">
                            <input type="text" class="form-control input_user_sch_pickup_index" id="user_sch_pickup_index_1" name="user_sch_pickup_index_1" autocomplete="off" placeholder="Index">
                        </div>
                    </div>
                </div>

                <div class="form-group margin-bottom-none">
                    <div class="col-md-4 pt-10">
                        <label class="control-label label-overide reg-form-lable lable_user_sch_pickup_point" for="user_sch_pickup_point_1">Pickup Point:</label>
                        <div class="max-comp-width input-group">
                            <input type="text" class="form-control input_user_sch_pickup_point" id="user_sch_pickup_point_1" name="user_sch_pickup_point_1" autocomplete="off" placeholder="Pickup Point">
                            <a href="#" class="input-group-addon" data-toggle="modal" data-target="#location-map-model"><span class=" trigger_model_user_sch_pickup_point_" id="model_user_sch_pickup_point_"><i class="fa fa-map-marker fa-lg" aria-hidden="true"></i></span></a>
                        </div>
                    </div>
                </div>

                <div class="form-group margin-bottom-none">
                    <div class="col-md-4 pt-10">
                        <label class="control-label label-overide reg-form-lable lable_user_sch_drop_off_point" for="user_sch_drop_off_point_1">Drop off Point:</label>
                        <div class="max-comp-width input-group">
                            <input type="text" class="form-control input_user_sch_drop_off_point" id="user_sch_drop_off_point_1" name="user_sch_drop_off_point_1" autocomplete="off" placeholder="Drop off Point">
                            <a href="#" class="input-group-addon" data-toggle="modal" data-target="#location-map-model"><span class=" trigger_model_user_sch_pickup_point_" id="model_user_sch_pickup_point_"><i class="fa fa-map-marker fa-lg" aria-hidden="true"></i></span></a>
                        </div>

                    </div>
                </div>
            </div>
            <div class="form-group margin-bottom-none">
                <div class="col-md-12 pt-10">

                    <div class="addDelButtons pull-left">
                        <button type="button" id="btnAdd" value="Add Transit" class="btn bg-theme-active2 btn-sm"><i class="fa fa-plus"></i>
                        </button>
                        <button type="button" id="btnDel" value="Remove Transit Above" class="hidden btn bg-theme-active2 btn-sm"><i class="fa fa-minus"></i>
                        </button>
                    </div>
                    <!--            <div id="addDelButtons">
                                    <input type="button" id="btnAdd" value="Add Transit" class="small btn-sm btn-override btn-green1"> <input type="button" id="btnDel" value="Remove Transit Above" class="hidden small btn-sm btn-override btn-green1">
                                </div>-->

                </div>
            </div>
            <div class="col-xs-12 margin-bottom-none pt-10"> 

                <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="schedule_user_add">Add To Schedule</button></span>

                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="schedule_user_add_cancel">Reset</button></span>

            </div>


            <!--/End Transit Section-->

            <div class="box-footer">
            </div>
        </form>

    </div>
</div>


