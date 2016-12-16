<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:session-validate sessionAttribute="${admin}" url="${CONTEXT_PATH}"/>
<div class="container-fluid">
    <div class="row">
        <div class="grabm-headbar-admin-xt">
         
            <div class="pull-left">
                <a href="${pageContext.request.contextPath}/signin"><img src="images/img/GrabMLogo.png" class="img-responsive" alt="GrabM"></a>
            </div>
            <div class="pull-right">
                <a href="${pageContext.request.contextPath}/signin" class="btn-override btn-theme">Sign In</a>
            </div>

        </div>
    </div>
</div>
<div class="container-fluid"  style="max-width: 800px;">
    <div class="">


        <div class="row">
            <!--left column-->
            <div class="col-md-12">
                <!-- general form elements -->

                <div class="box-header with-border">
                    <h1 class="txt-light-gray" style="font-weight: 100">Get Your Administrative Account</h1>
                </div>

                <!-- /.box-header -->
                <!-- form start -->
                <form role="form" method="POST" action="/grabmd/first-login" id="admin_activate_account_form">

                                       
                    <div class="form-group margin-bottom-none">
                        <div class="col-sm-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="xt_adm_rg_user_name">User Name:</label>
                            <div class="max-comp-width has-feedback">
                                <input type="text" class="form-control" id="xt_adm_rg_user_name" name="xt_adm_rg_user_name" autocomplete="off" placeholder="Enter User Name">
                                <span class="glyphicon glyphicon-user form-control-feedback graphic-icon-properties"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-sm-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="xt_adm_rg_temp_password">Temporary Password:</label>
                            <div class="max-comp-width has-feedback">
                                <input type="password" class="form-control" id="xt_adm_rg_temp_password" name="xt_adm_rg_temp_password" autocomplete="off" placeholder="Temporary Password">
                                <span class="glyphicon glyphicon-lock form-control-feedback graphic-icon-properties"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-sm-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="xt_adm_rg_password">New Password:</label>
                            <div class="max-comp-width has-feedback">
                                <input type="password" class="form-control" id="xt_adm_rg_password" name="xt_adm_rg_password" autocomplete="off" placeholder="New Password">
                                <span class="glyphicon glyphicon-lock form-control-feedback graphic-icon-properties"></span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group margin-bottom-none">
                        <div class="col-sm-6 pt-10">
                            <div class="max-comp-width has-feedback">
                                <label class="control-label label-overide reg-form-lable" for="xt_adm_rg_repeat_password">Repeat Password:</label>
                                <input type="password" class="form-control" id="xt_adm_rg_repeat_password" name="xt_adm_rg_repeat_password" autocomplete="off" placeholder="Retype Password">
                                 <span class="glyphicon glyphicon-lock form-control-feedback graphic-icon-properties"></span>
                            </div>
                        </div>
                    </div>

                  

                    <div class="col-xs-12 margin-bottom-none pt-10"> 

                        <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="admin_acc_activation">Register</button></span>

                        <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="admin_acc_activation_cancel" name="cus_cancel">Cancel</button></span>

                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer">
                    </div>
                </form>

                <!-- /.box -->
            </div>
        </div>
    </div>
</div>
