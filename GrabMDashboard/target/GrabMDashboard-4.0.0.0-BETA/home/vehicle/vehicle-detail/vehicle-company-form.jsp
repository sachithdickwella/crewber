<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="">
    <div class="box-header with-border">
        <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add New Company</h3>
    </div>

    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" method="POST" id="vehicle_details_company_form" action="/grabmd/companyweb">

        <div class="row">
            <div class="col-md-12">
                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_company_name">Company Name:</label>
                        <div class="max-comp-width ">
                            <input type="text"  class="form-control"  id="vehicle_details_company_name" name="vehicle_details_company_name" autocomplete="off" placeholder="Enter Company Name">
                        </div>
                    </div>
                </div>
                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_company_tp_number">Telephone Number</label>
                        <div class="max-comp-width input-group">
                            <span class="input-group-addon" id="basic-addon3">+94</span>
                            <input type="text"  class="form-control" maxlength="9" id="vehicle_details_company_tp_number" name="vehicle_details_company_tp_number" autocomplete="off" placeholder="Enter Mobile Number">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="form-group margin-bottom-none">
                    <div class="col-md-6 pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_company_mobile_number">Mobile Number</label>
                        <div class="max-comp-width input-group">
                            <span class="input-group-addon" id="basic-addon3">+94</span>
                            <input type="text"  class="form-control" maxlength="9" id="vehicle_details_company_mobile_number" name="vehicle_details_company_mobile_number" autocomplete="off" placeholder="Enter Mobile Number">
                        </div>
                    </div>
                </div>
                <div class="col-md-6 pt-10">
                    <div class="form-group margin-bottom-none">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_company_address_1">Address:</label>
                        <div class="max-comp-width">
                            <input type="text" class="form-control" id="vehicle_details_company_address_1" name="vehicle_details_company_address_1" autocomplete="off" placeholder="Address Line 1">
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none pt-10">
                        <div class="max-comp-width">
                            <input type="text" class="form-control" id="vehicle_details_company_address_2" name="vehicle_details_company_address_2" autocomplete="off" placeholder="Address Line 2">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="col-md-6">
                    <div class="form-group margin-bottom-none pt-10">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_company_city">City</label>
                        <div class="max-comp-width">
                            <input type="text" class="form-control" id="vehicle_details_company_city" name="vehicle_details_company_city" autocomplete="off" placeholder="Enter City">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group margin-bottom-none pt-10">
                         <label class="control-label label-overide reg-form-lable" for="vehicle_details_company_zip_code">Zip Code</label>
                        <div class="max-comp-width">
                            <input type="text" class="form-control " maxlength="5" id="vehicle_details_company_zip_code" name="vehicle_details_company_zip_code" autocomplete="off" placeholder="Enter Zip Code">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="form-group margin-bottom-none">
                    <div class="col-md-6">
                        <label class="control-label label-overide reg-form-lable" for="vehicle_details_company_country">Country</label>
                        <div class="max-comp-width">
                            <input type="text" class="form-control" id="vehicle_details_company_country" name="vehicle_details_company_country" autocomplete="off" value="Sri Lanka" readonly="readonly">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xs-12 margin-bottom-none pt-10"> 

            <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="vehicle_details_company_save">Save</button></span>
            <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="vehicle_details_company_cancel">Reset</button></span>

        </div>
        <!-- /.box-body -->

        <div class="box-footer">
        </div>
    </form>
</div>