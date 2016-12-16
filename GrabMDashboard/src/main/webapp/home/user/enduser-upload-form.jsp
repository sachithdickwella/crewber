<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-12">

        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Upload Crew Members</h3>
        </div>

        <form role="form" method="POST" id="upload_crew_members_form">

            <div class="form-group margin-bottom-none">
                <div class="col-md-6 pt-10">
                    <div class="file-upload-theme">
                        <!--<label class="control-label label-overide reg-form-lable" for="flight_schedule_upload_file">Upload Flight Schedule :</label>-->
                        <input id="crew_members_upload_file" name="crew_members_upload_file" type="file"  class="file-loading">
                        <div id="errorBlockCrew" class="help-block"></div>
                    </div>
                </div>
            </div>

            <input type="hidden" id="selected-crew" name="selected-crew"/>

            <div class="col-xs-6 margin-bottom-none pt-10">
                <!--<span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="add_new_schedule_form_cancel">Process</button></span>-->
                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="upload_crew_form_cancel">Reset</button></span>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10">
                <div class="box">
                    <div class="">
                        <h4 class="box-title text-uppercase txt-light-gray txt-light-weight2">Crew List Status</h4>
                    </div>

                    <div id="crew-list-status-wrapper">
                        <div class="row">
                            <div class="col-md-6 margin-bottom pt-10"> 
                                
                                <span class="text-uppercase txt-light-weight2" id="upload-users-count">1250</span>
                                <span class="text-uppercase txt-light-gray txt-light-weight2">Crew members are ready to upload</span>
                                
                            </div>
                            <div class="col-md-6 margin-bottom pt-10"> 
                                <span class="text-uppercase txt-light-weight2" id="upload-users-failed-count">2</span>
                                <span class="text-uppercase txt-light-gray txt-light-weight2">Are Failed</span>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-md-12 margin-bottom pt-10" id="upload-user-failed-entries-wrapper"> 
                                
                               <!--FAILED ENTRIES GOES HERE-->
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10">
                <span><button style="min-width: 200px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="upload_crew_list_form_submit">SUBMIT</button></span>
            </div>
            <!-- /.box-body -->

            <div class="box-footer">
            </div>
        </form>
    </div>
</div>
