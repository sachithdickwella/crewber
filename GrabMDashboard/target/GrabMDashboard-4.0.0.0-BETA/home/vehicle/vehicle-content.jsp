<div class="content-wrapper  bg-content">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Vehicle | 
            <!--<small>Crew Registration</small>-->
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>           
            <li class="active">vehicle</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">

        <div class="row">
            <!--left column-->
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs small theme-bg-clr-dark">
                        <li class="active"><a href="#vehicle-registration" data-toggle="tab" aria-expanded="true" class="text-uppercase txt-color-white">Vehicle Registration</a></li>
                        <li class=""><a href="#vehicle-upload" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Vehicle Upload</a></li>
                       
                    </ul>
                    
                    <div class="tab-content">
                        <div class="tab-pane active" id="vehicle-registration">
                            <%@include file="vehicle_registration_form.jsp" %>
                        </div>
                        <div class="tab-pane" id="vehicle-upload">
                            <%@include file="vehicle-upload-form.jsp" %>
                        </div>
                        
                    </div>
                    <!-- /.tab-content -->
                </div>
            </div>
        </div>

    </section>
    <!-- /.content -->
</div>