<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class="col-md-12">

        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Pickup Schedule</h3>
        </div>

        <form role="form" method="POST" id="pickup_schedule_form">
            <div class="row">
                <div class="col-md-5">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-12 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="enduser_multi_schedule_flight_number">Flight Number:</label>
                            <div class="max-comp-width">
                                <select name="enduser_multi_schedule_flight_number" id="enduser_multi_schedule_flight_number" class="form-control select2" style="width: 100% !important;">
                                    <c:forEach var="x" items="${futurepickups}">
                                        <fmt:formatDate var="flightDate" value="${x.flightDateTime}" type="date" pattern="MMM-dd-yyyy HH:mm (Z)"/>
                                        <option value="${x.id}">${x.flightNo} | ${flightDate}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div>
                                <label class="text-uppercase txt-light-gray txt-light-weight2">Note : </label> 
                                <p id="upload_crew_schedule_note"></p>
                            </div>
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-12 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="enduser_multi_schedule_drop">
                                Default Drop off
                            </label>

                            <select name="enduser_multi_schedule_drop" id="enduser_multi_schedule_drop" class="form-control" style="width: 100% !important;">

                                <option value="">BIA</option>

                            </select>

                            <div class="max-comp-width hidden" id="transit_drop_location_lat_long_wrapper">
                                <input type="text" class="form-control" id="transit_drop_location_lat_long" name="transit_drop" autocomplete="off" placeholder="Drop latitude/longitude">
                            </div>

                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-12 pt-10">
                            <label class="control-label label-overide reg-form-lable">Crew List for selected flight:</label>
                            <ul class="list-group" id="multischedule-list">

                            </ul>

                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12 pt-10"> 
                            <label class="checkbox PL-20" style="max-width: 100px;"><input type="checkbox" value="">Edit</label>
                            <span><button style="margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="multi_schedule_add_to_schedule">Group Selected</button></span>
                        </div>
                    </div>
                </div>
                <div class="col-md-7 margin-bottom">
                    <div id="map4"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 margin-top-10">
                    <div>
                        <div class="box-header  margin-bottom">
                            <h4 class="box-title text-uppercase txt-light-gray txt-light-weight2">Crew Schedule List Preview</h4>
                        </div>
                    </div>
                    <div class="grouped-crew-section-wrapper" id="grouped-crew-section">

                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12 pt-10"> 
                        <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="multi_schedule_reset">Reset</button></span>
                        <span><button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="multi_schedule_submit">Submit</button></span>
                    </div>
                </div>
            </div>
            <!--            <div class="box-footer">
                        </div>-->
        </form>
    </div>
</div>
