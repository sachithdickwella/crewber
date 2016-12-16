<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>

<div class="content-wrapper bg-content">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Driver |
            <small>Registration | Schedule</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>           
            <li class="active">Driver</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">

        <div class="row">
            <!--left column-->
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs small theme-bg-clr-dark">
                        <li class="active"><a href="#register-drivers" data-toggle="tab" aria-expanded="true" class="text-uppercase txt-color-white">Add New Driver</a></li>
                        <li class=""><a href="#driver-vehicle-schedule" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Schedule Driver-Vehicle</a></li>
                        <li class=""><a href="#upload-drivers" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Driver Upload</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="register-drivers">
                            <%@include file="driver_registration-form.jsp" %>
                        </div>

                        <div class="tab-pane" id="driver-vehicle-schedule">
                            <%@include file="vehicle-driver-form.jsp" %>
                        </div>
                        
                        <div class="tab-pane" id="upload-drivers">
                            <%@include file="driver-upload.jsp" %>
                        </div>
                    </div>
                    <!-- /.tab-content -->
                </div>
            </div>

        </div>

    </section>
    <!-- /.content -->
</div>