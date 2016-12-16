<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="">
      <g:execute-stat>
        <c:if test="${stat gt '0'}">
            <div class="stat-respond">
                <div class="stat-respond-success">
                    successfully added new entry
                </div>
            </div>
        </c:if>
        <c:if test="${stat eq '-1'}">
            <div class="stat-respond">
                <div class="stat-respond-danger">
                    Failed to add new entry
                </div>
            </div>
        </c:if>
    </g:execute-stat>
    <div class="box-header with-border">
        <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add New Model</h3>
    </div>

    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" method="POST" id="vehicle_details_model_form" action="/grabmd/vehiclemodelweb">

        <div class="row">
            <div class="col-md-12">
                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_model_brand">Vehicle Brand:</label>
                        <div class="max-comp-width">
                            <select name="vehicle_details_model_brand" id="vehicle_details_model_brand" class="form-control select2 margin-bottom-none" style="width: 100% !important;">
                                <c:forEach var="x" items="${vehiclebrands}">
                                    <option value="${x.id}">${x.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_model_vehicle_type">Vehicle Type:</label>
                        <div class="max-comp-width">
                            <select name="vehicle_details_model_vehicle_type" id="vehicle_details_model_vehicle_type" class="form-control select2 margin-bottom-none" style="width: 100% !important;">
                                <c:forEach var="x" items="${vehicletypes}">
                                    <option value="${x.shortName}">${x.longName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_model_name">Model Name:</label>
                        <div class="max-comp-width">
                            <input type="text" class="form-control" id="vehicle_details_model_name" name="vehicle_details_model_name" autocomplete="off" placeholder="Model Name" required="">
                        </div>
                    </div>
                </div>
                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_year_name">Year:</label>
                        <div class="max-comp-width">
                            <input type="text" class="form-control" id="vehicle_details_year_name" maxlength="4" name="vehicle_details_year_name" autocomplete="off" placeholder="Year" required="">
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_vehicle_model_fuel_type">Fuel Type:</label>
                        <div class="max-comp-width">
                            <div class="">
                                <select name="vehicle_details_vehicle_model_fuel_type" id="vehicle_details_vehicle_model_fuel_type" class="form-control select2 margin-bottom-none" style="width: 100% !important;" required="">
                                    <c:forEach var="x" items="${fueltypes}">
                                        <option value="${x.shortName}">${x.longName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_model_tank_size">Tank Size:</label>
                        <div class="max-comp-width input-group">
                            <input type="text" class="form-control text-right" maxlength="2" id="vehicle_details_model_tank_size" name="vehicle_details_model_tank_size" autocomplete="off" placeholder="Tank Size" required="">
                            <span class="input-group-addon" id="basic-addon3">Liter(l)</span>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div class="row">
            <div class="col-md-12">

                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_model_fuel_efficiency">Fuel Efficiency:</label>
                        <div class="max-comp-width input-group">
                            <input type="text" class="form-control text-right" id="vehicle_details_model_fuel_efficiency" name="vehicle_details_model_fuel_efficiency" autocomplete="off" placeholder="Fuel Efficiency" required="">
                            <span class="input-group-addon" id="basic-addon3">Km/l</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 margin-bottom-none pt-10"> 

            <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="vehicle_details_brand_save">Save</button></span>
            <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="vehicle_details_brand_cancel">Reset</button></span>

        </div>
        <!-- /.box-body -->

        <div class="box-footer">
        </div>
    </form>
</div>