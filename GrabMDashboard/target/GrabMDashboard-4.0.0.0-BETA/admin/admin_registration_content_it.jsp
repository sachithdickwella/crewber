<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="content-wrapper bg-content">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Admin | 
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
            <div class="col-md-8">
                <!-- general form elements -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add New Admin</h3>
                    </div>

                    <!-- /.box-header -->
                    <!-- form start -->
                    <form role="form" method="POST" action="/grabmd/userweb" id="new_admin_registration_form" name="new_admin_registration_form">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group margin-bottom-none">
                                    <div class="col-md-6 pt-10">
                                        <label class="control-label label-overide reg-form-lable" for="it_admin_rg_first_name">First Name:</label>
                                        <div class="max-comp-width">
                                            <input type="text" class="form-control" id="it_admin_rg_first_name" name="it_admin_rg_first_name" autocomplete="off" placeholder="Enter Name" required>

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group margin-bottom-none">
                                    <div class="col-md-6 pt-10">
                                        <label class="control-label label-overide reg-form-lable" for="it_admin_rg_last_name">Last Name:</label>
                                        <div class="max-comp-width">
                                            <input type="text" class="form-control" id="it_admin_rg_last_name" name="it_admin_rg_last_name" autocomplete="off" placeholder="Enter Name" required>
                                            <!--<div class="help-block with-errors"></div>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group margin-bottom-none">
                                    <div class="col-md-6 pt-10">
                                        <label class="control-label label-overide reg-form-lable" for="it_admin_rg_mobile_number">Mobile Number</label>
                                        <div class="max-comp-width input-group">
                                            <span class="input-group-addon" id="basic-addon3">+94</span>
                                            <input type="text" class="form-control" maxlength="9"  id="it_admin_rg_mobile_number" name="it_admin_rg_mobile_number" autocomplete="off" placeholder="Enter Mobile Number" required>
                                            <!--<div class="help-block with-errors"></div>-->
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group margin-bottom-none">
                                    <div class="col-md-6 pt-10">
                                        <label class="control-label label-overide reg-form-lable" for="it_admin_rg_email">Email</label>
                                        <div class="max-comp-width">
                                            <input type="email" class="form-control" id="it_admin_rg_email" name="it_admin_rg_email" autocomplete="off" placeholder="Enter Email" required>
                                            <!--<div class="help-block with-errors"></div>-->
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-6 pt-10">
                                <label class="control-label label-overide reg-form-lable" for="it_admin_rg_profile">Profile:</label>
                                <div class="max-comp-width">
                                    <select name="it_admin_rg_profile" id="it_admin_rg_profile" class="form-control">
                                        <c:forEach var="x" items="${profiles}">
                                            <option value="${x.id}">${x.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group margin-bottom-none">
                            <div class="col-md-12 pt-10">

                                <label class="control-label label-overide reg-form-lable" for="it_admin_rg_note">Note</label>
                                <div class="max-comp-width">

                                    <textarea class="form-control no-resize" rows="3" id="it_admin_rg_note"  name="it_admin_rg_note" placeholder="Note"></textarea>
                                </div>

                            </div>
                        </div>

                        <div class="col-xs-12 margin-bottom-none pt-10"> 

                            <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="new_admin_registration_form_save">save Admin</button></span>

                            <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="new_admin_registration_form_cancel">Reset</button></span>

                        </div>
                        <!-- /.box-body -->

                        <div class="box-footer">
                        </div>
                    </form>
                </div>
                <!-- /.box -->
            </div>
        </div>

    </section>
    <!-- /.content -->
</div>