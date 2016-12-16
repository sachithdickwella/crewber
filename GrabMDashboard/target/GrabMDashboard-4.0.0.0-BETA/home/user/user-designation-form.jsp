<div class="row">
    <div class="col-md-8">
        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Crew Designation</h3>
        </div>
        <div class="hidden" id="user-designation-stat-respond">
           
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form role="form" id="user_designation_form" method="POST">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="crew_member_designation_long_name">Designation Long Name :</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="crew_member_designation_long_name" name="crew_member_designation_long_name" autocomplete="off" placeholder="Long Name">
                            </div>
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="crew_member_designation_short_name">Designation Short Name :</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="crew_member_designation_short_name" name="crew_member_designation_short_name" autocomplete="off" placeholder="Short Name">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="crew_member_type">Member Type :</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="crew_member_type" name="crew_member_type" autocomplete="off" placeholder="Member Type">
                            </div>
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="crew_member_type_reporting_time">Reporting time before flight departure :</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="crew_member_type_reporting_time" name="crew_member_type_reporting_time" autocomplete="off" placeholder="Report [ _ ] Hours Before Departure">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10"> 
                <span><button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="user_type_add" onclick="addDesignation();">ADD</button></span>
                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="user_type_reload">Reload</button></span>
            </div>
            <!-- /.box-body -->


            <div class="box-footer">

            </div>
        </form>
    </div>
    <div class="col-md-4">
        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Crew Types | View</h3>
        </div>
        <div class="pt-10" id="designation-list-wrapper">
            <ul class="list-group" id="list-group-designations">
               
            </ul>
        </div>

        <!-- /.box-header -->
        <!-- form start -->

    </div>

</div>