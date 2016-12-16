<div class="content-wrapper bg-content">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Schedule | 
            <small>Pick up</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>           
            <li class="active">Schedule pick up</li>
        </ol>
    </section>

    <!-- Main content -->   
        <section class="content">
            <div class="row">
                <!--left column-->
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs small theme-bg-clr-dark">
                            <li class="active"><a href="#new-schedule" data-toggle="tab" aria-expanded="true" class="text-uppercase txt-color-white">Add Single Flight schedule</a></li>
                            <li class=""><a href="#flight-schedule" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Flight Schedule Upload</a></li>
                            <li class=""><a href="#crew-schedule" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Crew Schedule Upload</a></li>
                            <li class=""><a href="#pickup-schedule" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white pickup-multi-schedule">Pickup Schedule</a></li>
                            <li class=""><a href="#schedule-crew-single" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Schedule Crew</a></li>
                            <li class=""><a href="#scheduleNotify-dispatch" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Schedule Notify /Dispatch</a></li>
                            <li class=""><a href="#default-location" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Default Locations</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="new-schedule">
                                <!--include add new schedule form-->
                                <%@include file="add-new-schedule-form.jsp" %>
                            </div>
                            <div class="tab-pane " id="flight-schedule">
                                <!--include add new schedule form-->
                                <%@include file="upload-flight-schedule-form.jsp" %>
                            </div>
                            <div class="tab-pane " id="crew-schedule">
                                <!--include add new schedule form-->
                                <%@include file="upload-crew-schedule-form.jsp" %>
                            </div>
                            <div class="tab-pane " id="pickup-schedule">
                                <!--include add new schedule form-->
                                <%@include file="end-user-multi-schedule.jsp" %>
                            </div>
                            <div class="tab-pane" id="schedule-crew-single">
                                <!--include add new schedule form-->
                                <%@include file="schedule-users-form.jsp" %>
                            </div>
                            <div class="tab-pane" id="scheduleNotify-dispatch">
                                <!--include dispatch form-->
                                <%@include file="dispatch-vehicles-form.jsp" %>
                            </div>
                            <div class="tab-pane" id="default-location">
                                <!--include add new schedule form-->
                                <%@include file="default-location-form.jsp" %>
                            </div>
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- nav-tabs-custom -->
                </div>
                <!--include shcedule list-->
            </div>
        </section>
        <!-- /.content -->
</div>