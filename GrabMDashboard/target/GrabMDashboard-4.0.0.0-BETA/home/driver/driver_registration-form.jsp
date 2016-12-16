<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="row">
    <div class="col-md-7">
        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add New Driver</h3>
        </div>
        <!-- form start -->
        <form role="form" method="POST" id="new_driver_registration_form" action="/grabmd/driverweb" enctype="multipart/form-data">

            <div class="row">
                <div class="col-md-12">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="driver_rg_first_name">First Name:</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="driver_rg_first_name" name="driver_rg_first_name" autocomplete="off" placeholder="Enter Name">
                            </div>
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="driver_rg_last_name">Last Name:</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="driver_rg_last_name" name="driver_rg_last_name" autocomplete="off" placeholder="Enter Name">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="driver_rg_nic">NIC:</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" maxlength="10" id="driver_rg_nic" name="driver_rg_nic" autocomplete="off" placeholder="Enter NIC">

                            </div>
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="driver_rg_mobile_number_1">Mobile Number 1:</label>
                            <div class="max-comp-width input-group">
                                <span class="input-group-addon" id="basic-addon3">+94</span>
                                <input type="text" class="form-control" maxlength="9" id="driver_rg_mobile_number_1" name="driver_rg_mobile_number_1" autocomplete="off" placeholder="Enter Mobile Number">
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="driver_rg_mobile_number_2">Mobile Number 2:</label>
                            <div class="max-comp-width input-group">
                                <span class="input-group-addon" id="basic-addon3">+94</span>
                                <input type="text" class="form-control" maxlength="9" id="driver_rg_mobile_number_2" name="driver_rg_mobile_number_2" autocomplete="off" placeholder="Enter Mobile Number">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group margin-bottom-none">
                <div class="col-md-12 pt-10">
                    <div class="file-upload-theme">
                        <label class="control-label label-overide reg-form-lable" for="driver_rg_pro_pic">Upload Profile Picture (1 MB Max | 512x512 ):</label>
                        <input id="driver_rg_pro_pic" type="file" accept="image/*" class="file form-control" data-show-upload="false" data-preview-file-type="text" name="driver_rg_pro_pic">
                    </div>
                </div>
            </div>

            <div class="form-group margin-bottom-none">
                <div class="col-md-12 pt-10">

                    <label class="control-label label-overide reg-form-lable" for="driver_rg_note">Note</label>
                    <div class="max-comp-width">

                        <textarea class="form-control" rows="3" id="driver_rg_note" name="driver_rg_note" placeholder="Note"></textarea>
                    </div>

                </div>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10"> 

                <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="new_driver_registration_form_save">save Driver</button></span>

                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="new_driver_registration_form_cancel">Reset</button></span>

            </div>
            <!-- /.box-body -->

            <div class="box-footer">
            </div>
        </form>
    </div>


</div>