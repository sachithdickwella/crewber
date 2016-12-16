<div class="content-wrapper  bg-content">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            FLIGHT CREW | 
            <!--<small>Crew Registration</small>-->
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>           
            <li class="active">Crew</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">

        <div class="row">
            <!--left column-->
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs small theme-bg-clr-dark">
                        <li class="active"><a href="#crew_registration" data-toggle="tab" aria-expanded="true" class="text-uppercase txt-color-white">Crew Single Registration</a></li>
                        <li class=""><a href="#crew_designation" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Crew Designation</a></li>
                        <li class=""><a href="#crew_pickup_area" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Pickup Area</a></li>
                        <li class=""><a href="#crew_pickup_address_permission" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Crew Pickup Address Permission</a></li>
                        <li class=""><a href="#crew_upload" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Crew Upload</a></li>
                    </ul>
                    
                    <div class="tab-content">
                        <div class="tab-pane active" id="crew_registration">
                            <%@include file="user-registration-form.jsp" %>
                        </div>
                        <div class="tab-pane" id="crew_designation">
                            <%@include file="user-designation-form.jsp" %>
                        </div>
                        <div class="tab-pane" id="crew_pickup_area">
                            <%@include file="user-area-form.jsp" %>
                        </div>
                        <div class="tab-pane" id="crew_pickup_address_permission">
                            <%@include file="user-pikcup-location-permission-form.jsp" %>
                        </div>
                        <div class="tab-pane" id="crew_upload">
                            <%@include file="enduser-upload-form.jsp" %>
                        </div>
                    </div>
                    <!-- /.tab-content -->
                </div>
            </div>
        </div>

    </section>
    <!-- /.content -->
</div>