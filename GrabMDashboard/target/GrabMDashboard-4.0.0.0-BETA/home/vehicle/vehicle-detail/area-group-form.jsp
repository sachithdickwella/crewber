<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="">
    <div class="box-header with-border">
        <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Area Group</h3>
    </div>

    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" method="POST" id="vehicle_details_area_group_form" action="/grabmd/areagroupweb">

        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">
                <label class="control-label label-overide reg-form-lable" for="vehicle_details_area_group_name">Area Group Name:</label>
                <div class="max-comp-width">
                    <input type="text" class="form-control" id="vehicle_details_area_group_name" name="vehicle_details_area_group_name" autocomplete="off" placeholder="Enter Name" required>
                </div>
            </div>
        </div>
        

        <div class="col-xs-12 margin-bottom-none pt-20"> 

            <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="vehicle_details_area_group_save">Save</button></span>

            <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="vehicle_details_area_group_cancel">Reset</button></span>

        </div>
        <!-- /.box-body -->

        <div class="box-footer">
        </div>
    </form>
</div>