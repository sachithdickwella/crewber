<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="">
    <div class="box-header with-border">
        <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add Fuel Type</h3>
    </div>

    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" method="POST" id="vehicle_details_fuel_type_form" action="/grabmd/fueltypeweb">

        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">
                <label class="control-label label-overide reg-form-lable" for="vehicle_details_fuel_type_short_name">Long Name:</label>
                <div class="max-comp-width">
                    <input type="text" class="form-control" id="vehicle_details_fuel_type_short_name" name="vehicle_details_fuel_type_long_name" autocomplete="off" placeholder="Long Name" required>
                </div>
            </div>
        </div>
        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">
                <label class="control-label label-overide reg-form-lable" for="vehicle_details_fuel_type_long_name">Short Name:</label>
                <div class="max-comp-width">
                    <input type="text" class="form-control text-capitalize" maxlength="1" id="vehicle_details_fuel_type_long_name" name="vehicle_details_fuel_type_short_name" autocomplete="off" placeholder="Short Name" required>
                </div>
            </div>
        </div>

        <div class="col-xs-12 margin-bottom-none pt-10"> 

            <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="vehicle_details_fuel_type_save">Save</button></span>

            <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="vehicle_details_fuel_type_cancel">Reset</button></span>

        </div>
        <!-- /.box-body -->

        <div class="box-footer">
        </div>
    </form>
</div>