<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class="col-md-7">
        <g:execute-stat>
            <c:if test="${stat gt '0'}">
                <div class="stat-respond">
                    <div class="stat-respond-success">
                        successfully added new schedule
                    </div>
                </div>
            </c:if>
            <c:if test="${stat eq '-1'}">
                <div class="stat-respond">
                    <div class="stat-respond-danger">
                        Failed to add new schedule
                    </div>
                </div>
            </c:if>
        </g:execute-stat>
        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add New Schedule</h3>
        </div>
        
        <form role="form" method="POST" id="add_new_schedule_form" action="/grabmd/pickupweb">
            <div class="form-group margin-bottom-none">
                <div class="col-md-6 pt-10">
                    <label class="control-label label-overide reg-form-lable" for="add_schedule_flight_number">Flight Number:</label>
                    <div class="max-comp-width">
                        <input type="text" class="form-control" id="add_schedule_flight_number" name="add_schedule_flight_number" autocomplete="off" placeholder="Enter Name">
                    </div>
                </div>
            </div>
            <div class="form-group margin-bottom-none">
                <div class="col-md-6 pt-10">
                    <label class="control-label label-overide reg-form-lable" for="add_schedule_depature_date_time">Depature Date/Time</label>
                    <div class="max-comp-width">
                        <div class='input-group date datetimepicker'>
                            <input type='text' class="form-control" name="add_schedule_depature_date_time" id="add_schedule_depature_date_time" placeholder="Select Date/Time" />
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group margin-bottom-none">
                <div class="col-md-12 pt-10">

                    <label class="control-label label-overide reg-form-lable" for="it_admin_rg_note">Note</label>
                    <div class="max-comp-width">

                        <textarea class="form-control" rows="3" id="it_admin_rg_note" name="it_admin_rg_note" placeholder="Note"></textarea>
                    </div>

                </div>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10"> 

                <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="add_new_schedule_form_add">save Schedule</button></span>

                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="add_new_schedule_form_cancel">Reset</button></span>

            </div>
            <!-- /.box-body -->

            <div class="box-footer">
            </div>
        </form>


    </div>
</div>
