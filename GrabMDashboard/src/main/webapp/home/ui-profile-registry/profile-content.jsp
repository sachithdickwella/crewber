<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
 
<div class="content-wrapper bg-content">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            UI-PROFILE | 
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
            <div class="col-md-6">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs small theme-bg-clr-dark">
                        <li class="active"><a href="#tab_1-1" data-toggle="tab" aria-expanded="true" class="text-uppercase txt-color-white">Add New Profile</a></li>
                        <li class=""><a href="#tab_1-2" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Add New UI Page</a></li>
                        <li class=""><a href="#tab_1-3" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">UI Profile Registry</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_1-1">
                            <!--include add-profile-form-->
                            <!-- general form elements -->
                            <%@include file="add-profile-form.jsp" %>
                            <!-- /.box -->
                        </div>

                        <div class="tab-pane" id="tab_1-2">
                            <!--include add-ui-pages-form-->
                            <!-- general form elements -->
                            <%@include file="add-ui-pages-form.jsp" %>
                            <!-- /.box -->
                        </div>
                        <div class="tab-pane" id="tab_1-3">
                            <!--include ui-profile-registry-form-->
                            <!-- general form elements -->
                            <%@include file="ui-profile-registry-form.jsp" %>
                            <!-- /.box -->
                        </div>
                    </div>
                    <!-- /.tab-content -->
                </div>
            </div>

        </div>

    </section>
    <!-- /.content -->
</div>