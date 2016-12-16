<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:session-validate sessionAttribute="${admin}" url="${CONTEXT_PATH}"/>
<div class="content-wrapper bg-content">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Vehicle Details| 
            <small>Registration</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>           
            <li class="active">Admin</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">

        <div class="row">
            <!--left column-->
            <div class="col-md-7">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs small theme-bg-clr-dark">
                        <li class="active"><a href="#tab-brand" data-toggle="tab" aria-expanded="true" class="text-uppercase txt-color-white">Vehicle Brand</a></li>
                        <li class=""><a href="#tab-model" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Vehicle Model</a></li>
                        <li class=""><a href="#tab-ftype" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Fuel type</a></li>
                        <li class=""><a href="#tab-vehicle-type" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Vehicle Type</a></li>
                        <li class=""><a href="#tab-company" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Company</a></li>
                        <li class=""><a href="#tab-group" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Area Group</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab-brand">
                            <!--include vehicle-brand-form.jsp--->
                            <%@include file="vehicle-brand-form.jsp" %>
                            <!-- /.box -->
                        </div>

                        <div class="tab-pane" id="tab-model">
                            <!--include vehicle-model-form.jsp-->
                            <%@include file="vehicle-model-form.jsp" %>
                            <!-- /.box -->
                        </div>
                        <div class="tab-pane" id="tab-ftype">
                            <!--include vehicle-model-form.jsp-->
                            <%@include file="fuel-type-form.jsp" %>
                            <!-- /.box -->
                        </div>
                        <div class="tab-pane" id="tab-vehicle-type">
                            <!--include vehicle-model-form.jsp-->
                            <%@include file="vehicle-type.jsp" %>
                            <!-- /.box -->
                        </div>
                        <div class="tab-pane" id="tab-company">
                            <!--include vehicle-company-form.jsp-->
                            <%@include file="vehicle-company-form.jsp" %>
                            <!-- /.box -->
                        </div>
                        <div class="tab-pane" id="tab-group">
                            <!--include area-group-form.jsp-->
                            <%@include file="area-group-form.jsp" %>
                            <!-- /.box -->
                        </div>
                    </div>
                    <!-- /.tab-content -->
                </div>
            </div>

            <!--right column-->
            <%@include file="vehicle-detail-view-content.jsp" %>
        </div>

    </section>
    <!-- /.content -->
</div>