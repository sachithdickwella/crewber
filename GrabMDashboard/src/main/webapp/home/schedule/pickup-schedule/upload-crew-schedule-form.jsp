<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class="col-md-12">

        <div class="box-header">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Upload Crew Schedule</h3>
        </div>

        <form role="form" method="POST" id="upload_crew_schedule_form" action="/grabmd/pushcrewdataweb">
            <div class="form-group">
                <div class="col-md-12 pt-10">
                    <label class="control-label label-overide reg-form-lable" for="upload_crew_schedule_flight_number">Flight Number:</label>
                    <div class="max-comp-width">
                        <select name="upload_crew_schedule_flight_number" id="upload_crew_schedule_flight_number" class="form-control select2" style="width: 100% !important;">
                            <c:forEach var="x" items="${futurepickups}">
                                <fmt:formatDate var="flightDate" value="${x.flightDateTime}" type="date" pattern="MMM-dd-yyyy HH:mm (Z)"/>
                                <option value="${x.id}">${x.flightNo} | ${flightDate}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label class="text-uppercase txt-light-gray txt-light-weight2">Note : </label> 
                        <p id="upload_crew_schedule_note"></p>
                    </div>
                </div>
            </div>
            <input type="hidden" id="selected-crew" name="selected-crew"/>
            <div class="form-group margin-bottom-none">
                <div class="col-md-6 pt-10">
                    <div class="file-upload-theme">
                        <!--<label class="control-label label-overide reg-form-lable" for="crew_schedule_upload_file">Upload Flight Schedule :</label>-->
                        <input id="crew_schedule_upload_file" name="crew_schedule_upload_file" type="file"  class="file-loading">
                        <div id="errorBlock2" class="help-block"></div>
                    </div>
                </div>
            </div>

            <div class="col-xs-6 margin-bottom-none pt-10">
                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="add_new_schedule_form_cancel">Process</button></span>
                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="add_new_schedule_form_cancel">Reset</button></span>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10">
                <div class="box">
                    <div class="box-header">
                        <h4 class="box-title text-uppercase txt-light-gray txt-light-weight2">Crew Schedule List Preview</h4>
                    </div>
                    <!-- /.box-header -->
                    <div class="col-md-12" style="padding-left: 0 !important;">
                        <div class="form-group" style="margin-left: 0 !important;">
                            <div class="col-md-12 margin-bottom-none">
                                <label class="control-label label-overide reg-form-lable" for="upload_crew_add_new_member">Add new flight crew member to list:</label>
                            </div>
                            <div class="col-md-6 margin-bottom">
                                <div class="max-comp-width">
                                    <select name="upload_crew_add_new_member" id="upload_crew_add_new_member" class="form-control select2" style="width: 100% !important;">
                                        <c:forEach var="x" items="${endusers}">
                                            <option value="${x.id}" 
                                                    data-memno="${x.membershipNumber}" 
                                                    data-desid="${x.designation.id}"
                                                    data-desshortname="${x.designation.shortName}" 
                                                    data-deslongname="${x.designation.longName}"
                                                    data-firstname="${x.firstName}"
                                                    data-lastname="${x.lastName}"
                                                    data-desc="${x.note}">
                                                ${x.firstName} ${x.lastName} - ${x.designation.longName} (${x.membershipNumber})
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-6 margin-bottom-none">
                                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="add_new_crew_upload">Add to table</button></span>
                            </div>
                        </div>
                    </div>
                    <div class="box-body" id="crew-table-content">
                        <table id="upload_crew_data_table" class="table table-bordered table-striped" style="width:100%">
                            <thead>
                                <tr class="th-txt">
                                    <th>Member ID</th>
                                    <th>Title</th>
                                    <th>first Name</th>
                                    <th>Last Name</th>
                                    <th>Description</th>
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
