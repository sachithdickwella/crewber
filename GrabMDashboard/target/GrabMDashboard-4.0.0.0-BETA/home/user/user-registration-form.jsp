<div class="row">
    <div class="col-md-8">
        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add New Crew Member</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form role="form" id="new_user_registration_form" method="POST" action="/grabmd/enduser">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_title">Title:</label>
                            <div class="max-comp-width" style="width: 100px;">
                                <select id="it_user_rg_title" name="it_user_rg_title" class="form-control">
                                    <option>Mr.</option>
                                    <option>Ms.</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_first_name">First Name:</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="it_user_rg_first_name" name="it_user_rg_first_name" autocomplete="off" placeholder="Enter Name">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_last_name">Last Name:</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="it_user_rg_last_name" name="it_user_rg_last_name" autocomplete="off" placeholder="Enter Name">
                            </div>
                        </div>
                    </div>

                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_designaion">Designation:</label>
                            <div class="max-comp-width">
                                <select id="it_user_rg_designaion" name="it_user_rg_designaion" class="form-control select2" style="width: 100%;">
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
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_member_id">Member ID:</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="it_user_rg_member_id" name="it_user_rg_member_id" autocomplete="off" placeholder="Enter Member ID">
                            </div>
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_mobile_number">Mobile Number</label>
                            <div class="max-comp-width input-group">
                                <span class="input-group-addon" id="basic-addon3">+94</span>
                                <input type="text"  class="form-control" maxlength="9" id="it_user_rg_mobile_number" name="it_user_rg_mobile_number" autocomplete="off" placeholder="Enter Mobile Number">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_email">Email:</label>
                            <div class="max-comp-width">
                                <input type="email" class="form-control" id="it_user_rg_email" name="it_user_rg_email" autocomplete="off" placeholder="Enter Email">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="col-md-6 pt-10">
                        <div class="form-group margin-bottom-none">
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_address_1">Address:</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="it_user_rg_address_1" name="it_user_rg_address_1" autocomplete="off" placeholder="Address Line 1">
                            </div>
                        </div>
                        <div class="form-group margin-bottom-none pt-10">
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="it_user_rg_address_2" name="it_user_rg_address_2" autocomplete="off" placeholder="Address Line 2">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group margin-bottom-none">
                <div class="col-md-12 pt-10">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_city">City</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="it_user_rg_city" name="it_user_rg_city" autocomplete="off" placeholder="Enter City">
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="form-group margin-bottom-none">
                <div class="col-md-12 pt-10">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_zip_code">Zip Code</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="it_user_rg_zip_code" name="it_user_rg_zip_code" autocomplete="off" placeholder="Enter Zip Code">
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="form-group margin-bottom-none">
                <div class="col-md-12 pt-10">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="control-label label-overide reg-form-lable" for="it_user_rg_country">Country</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="it_user_rg_country" name="it_user_rg_country" autocomplete="off" value="Sri Lanka" readonly="readonly">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="form-group margin-bottom-none">
                <div class="col-md-12 pt-10">

                    <label class="control-label label-overide reg-form-lable" for="it_user_rg_note">Note</label>
                    <div class="max-comp-width">

                        <textarea class="form-control" rows="3" id="it_user_rg_note" name="it_user_rg_note" placeholder="Note"></textarea>
                    </div>

                </div>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10"> 

                <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="new_user_registration_form_save">save User</button></span>

                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="new_user_registration_form_cancel">Reset</button></span>

            </div>
            <!-- /.box-body -->

            <div class="box-footer">

            </div>
        </form>
    </div>

</div>