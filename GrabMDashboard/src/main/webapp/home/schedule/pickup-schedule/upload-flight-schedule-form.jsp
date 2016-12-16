<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class="col-md-12">

        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Upload Flight Schedule</h3>
        </div>

        <form role="form" method="POST" id="upload_flight_schedule_form" action="/grabmd/pushexceldata">

            <div class="form-group margin-bottom-none">
                <div class="col-md-6 pt-10">
                    <div class="file-upload-theme">
                        <!--<label class="control-label label-overide reg-form-lable" for="flight_schedule_upload_file">Upload Flight Schedule :</label>-->
                        <input id="flight_schedule_upload_file" name="flight_schedule_upload_file" type="file"  class="file-loading">
                        <div id="errorBlock" class="help-block"></div>
                    </div>
                </div>
            </div>
            
            <input type="hidden" id="selected-flights" name="selected-flights"/>

            <div class="col-xs-6 margin-bottom-none pt-10">
                <!--<span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="add_new_schedule_form_cancel">Process</button></span>-->
                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="add_new_schedule_form_cancel">Reset</button></span>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10">
                <div class="box">
                    <div class="box-header">
                        <h4 class="box-title text-uppercase txt-light-gray txt-light-weight2">Flight Schedule List Preview</h4>
                    </div>
                    <!-- /.box-header -->

                    <div class="box-body" id="flight-table-content">
                        <table id="upload_flight_data_table" class="table table-bordered table-striped table-hover" cellspacing="0">
                            <thead>
                                <tr class="th-txt">
                                    <th>Flight</th>
                                    <th>DEP</th>
                                    <th>Departure Date/Time</th>
                                    <th>ARR</th>
                                    <th>Arrival Date/Time</th>
                                    <th>DEL</th>
                                </tr>
                            </thead>
                            <tbody>                                
                            </tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10">
                <span><button style="min-width: 200px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="upload_flight_schedule_form_submit">SUBMIT Schedule</button></span>
            </div>
            <!-- /.box-body -->

            <div class="box-footer">
            </div>
        </form>
    </div>
</div>
