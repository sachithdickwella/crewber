<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <!--left column-->
    <div class="col-md-8">
        <!-- general form elements -->
        <div class="">
            <div class="box-header with-border">
                <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add New Vehicle</h3>
            </div>

            <!-- /.box-header -->
            <!-- form start -->
            <form role="form" data-toggle="validator" id="new_vehicle_registration_form" method="POST" action="/grabmd/vehicleweb">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-6 pt-10">
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_registration_number">Reg.Number:</label>
                                <div class="max-comp-width">
                                    <input type="text" class="form-control" id="vehicle_rg_registration_number" name="vehicle_rg_registration_number" autocomplete="off" placeholder="Registration Number">
                                </div>
                            </div>
                        </div>
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-6 pt-10">
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_vehicle_model">Vehicle Model:</label>
                                <div class="max-comp-width">
                                    <select name="vehicle_rg_vehicle_model" id="vehicle_rg_vehicle_model" class="form-control select2 element-full-width">
                                        <c:forEach var="x" items="${vehiclemodels}">
                                            <option value="${x.id}">${x.vehicleTypeId.longName} - ${x.vehicleBrandId.name} ~ ${x.name} ${x.year} (${x.fuelType.longName})</option>
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
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_vehicle_company">Company:</label>
                                <div class="max-comp-width">
                                    <select name="vehicle_rg_vehicle_company" id="vehicle_rg_vehicle_company" class="form-control select2">
                                        <c:forEach var="x" items="${company}">
                                            <option value="${x.id}">${x.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-6 pt-10">
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_vehicle_group">Vehicle Group:</label>
                                <div class="max-comp-width">
                                    <select name="vehicle_rg_vehicle_group" id="vehicle_rg_vehicle_group" class="form-control select2">
                                        <c:forEach var="x" items="${areagroup}">
                                            <option value="${x.id}">${x.name}</option>
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
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_min_passengers">Min. Passengers:</label>
                                <div class="max-comp-width">
                                    <input type="text" class="form-control" id="vehicle_rg_min_passengers" maxlength="2" name="vehicle_rg_min_passengers" autocomplete="off" placeholder="Minimum passengers">
                                </div>
                            </div>
                        </div>
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-6 pt-10">
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_max_passengers">Max. Passengers:</label>
                                <div class="max-comp-width">
                                    <input type="text" class="form-control" id="vehicle_rg_max_passengers" maxlength="2" name="vehicle_rg_max_passengers" autocomplete="off" placeholder="Maximum passengers">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-6 pt-10">
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_color_1">Primary Color</label>
                                <div class="input-group max-comp-width my-colorpicker2">
                                    <input type="text" class="form-control" id="vehicle_rg_color_1" name="vehicle_rg_color_1" autocomplete="off" placeholder="Please pick the color1" >
                                    <div class="input-group-addon">
                                        <span class="fa fa-eyedropper" aria-hidden="true"> </span>
                                        <i class="vehicle_rg_color_background_1"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-6 pt-10">
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_color_2">Secondary Color</label>
                                <div class="input-group max-comp-width my-colorpicker2">
                                    <input type="text" class="form-control" id="vehicle_rg_color_2" name="vehicle_rg_color_2" autocomplete="off" placeholder="Please pick the color2">
                                    <div class="input-group-addon">
                                        <span class="fa fa-eyedropper" aria-hidden="true"> </span>
                                        <i class="vehicle_rg_color_background_2"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-6 pt-10">
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_mileage">Mileage:</label>
                                <div class="max-comp-width">
                                    <input type="text" class="form-control" id="vehicle_rg_mileage" name="vehicle_rg_mileage" autocomplete="off" placeholder="Mileage">
                                </div>
                            </div>
                        </div>
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-6 pt-10">
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_tracker_imei">IMEI:</label>
                                <div class="max-comp-width">
                                    <input type="text" class="form-control" id="vehicle_rg_tracker_imei" name="vehicle_rg_tracker_imei" autocomplete="off" placeholder="IMEI">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-6 pt-10">
                                <label class="control-label label-overide reg-form-lable" for="vehicle_rg_mobile_number">Mobile Number:</label>
                                <div class="max-comp-width input-group">
                                    <span class="input-group-addon" id="basic-addon3">+94</span>
                                    <input type="text" class="form-control" id="vehicle_rg_mobile_number" maxlength="9" name="vehicle_rg_mobile_number" autocomplete="off" placeholder="Mobile Number">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group margin-bottom-none">
                    <div class="col-md-12 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_rg_note">Note</label>
                        <div class="max-comp-width">
                            <textarea class="form-control" rows="3" id="vehicle_rg_note" name="vehicle_rg_note" placeholder="Note"></textarea>
                        </div>

                    </div>
                </div>

                <div class="col-xs-12 margin-bottom-none pt-10"> 

                    <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="new_vehicle_registration_form_save">save Vehicle</button></span>

                    <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="new_vehicle_registration_form_cancel">Reset</button></span>

                </div>
                <!-- /.box-body -->

                <div class="box-footer">

                </div>
            </form>
        </div>

        <!-- /.box -->
    </div>
    <!--right column-->
    <%@include file="vehicle-view-content.jsp" %>

</div>