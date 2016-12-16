<div class="content-wrapper bg-content">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            PROFILE | 

        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>           
            <li class="active">Profile</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-8">
                <div class="box box-widget widget-user">
                    <!-- Add the bg color to the header using any of the bg-* classes -->
                    <div class="widget-user-header bg-theme-active2">
                        <h3 class="widget-user-username txt-color-white">${admin.firstName} ${admin.lastName}</h3>
                        <h5 class="widget-user-desc txt-color-white">${admin.profileId.name}</h5>
                    </div>
                    <div class="widget-user-image">
                        <img class="img-circle" src="${pageContext.request.contextPath}/images/img/profile/default-profile-image.png" alt="User Avatar">
                    </div>
                    <div class="box-footer">

                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8">
                <!-- general form elements -->
                <div class="box box-primary" style="border-top: none;">
                    <!-- /.box-header -->
                    <!-- form start -->
                    <div class="row profile_detail_wrapper">
                        <div class="col-sm-3">
                            <label class="label-overide color-green text-uppercase">
                                Name
                            </label>
                        </div>
                        <div class="col-sm-9 pl-20 bl"  id="profile_name_show">
                            <span id="profile_name_sp">${admin.firstName} ${admin.lastName}</span>           
                            <br>
                            <span><a href="#" onclick="return false;" id="edit-profile-name" class="epn color-light-blue"><i class="fa fa-pencil-square-o"></i> Edit</a></span>   
                        </div>
                        <div class="col-sm-9 pl-20 profile_detail_wrapper_edit bl hidden" id="profile_name_change">
                            <div id="profile_name_loader"></div>
                            <div id="profile_name_msg"></div>
                            <form role="form" method="post" name="profile_change_name_form" id="profile_change_name_form">   
                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label class="label-overide text-uppercase" for="prof_change_f_name">First Name</label>
                                        <input type="text" class="form-control" id="prof_change_f_name_id" name="prof_change_f_name" value="${admin.firstName}" autocomplete="off" placeholder="First Name">
                                    </div>                                          
                                </div>
                                <div class="form-group">                   
                                    <div class="col-md-6">
                                        <label class="label-overide text-uppercase" for="prof_change_l_name">Last Name</label>
                                        <input type="text" class="form-control" id="prof_change_f_name_id" name="prof_change_l_name" value="${admin.lastName}" autocomplete="off" placeholder="Last Name">
                                    </div>                        
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-12 pt-10">
                                        <input type="text" class="form-control hidden" name="prof-name-edit-btn-c">
                                        <span><button  type="submit" class="btn-override btn-theme" id="prof-name-edit-btn">save changes</button></span>
                                        <span><button  type="button" class="btn-override btn-theme" id="prof-name-cancel-btn" name="prof-name-cancel-btn">Cancel</button></span>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>

                    <hr class="no-margin"/>

                    <!--change Contact-->

                    <div class="row profile_detail_wrapper">
                        <div class="col-sm-3 br">
                            <label class="label-overide color-green text-uppercase">
                                Contact Number 
                            </label>
                        </div>
                        <div class="col-sm-8 pl-20" id="profile_contact_show">
                            <span id="profile_contact_sp">+94 ${admin.mobileNo}</span>       
                            <br>
                            <span><a href="#" id="edit-profile-contact" onclick="return false;" class="color-light-blue"><i class="fa fa-pencil-square-o"></i> Edit</a></span>          
                        </div>
                        <div class="col-sm-9 pl-20 profile_detail_wrapper_edit bl hidden" id="profile_contact_change">
                            <div id="profile_contact_loader"></div>
                            <div id="profile_contact_msg"></div>
                            <form role="form" method="post" name="profile_change_contact_form" id="profile_change_contact_form">   
                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label class="label-overide text-uppercase" for="prof_change_contact">Contact Number</label>
                                        <input type="text" class="form-control" id="prof_change_f_name_id" name="prof_change_contact" autocomplete="off" placeholder="Contact Number">
                                    </div>                                     
                                </div>

                                <div class="form-group">
                                    <div class="col-md-12 pt-10">
                                        <input type="text" class="form-control hidden" name="prof-contact-edit-btn-c">
                                        <span><button  type="submit" class="btn-override btn-theme" id="prof-contact-edit-btn">save changes</button></span>
                                        <span><button  type="button" class="btn-override btn-theme" id="prof-contact-cancel-btn" name="prof-contact-cancel-btn">Cancel</button></span>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <hr class="no-margin"/>

                    <!--change username-->

                    <div class="row profile_detail_wrapper">
                        <div class="col-sm-3">
                            <label class="label-overide color-green text-uppercase">
                                User Name(Email)
                            </label>
                        </div>

                        <div class="col-sm-8 pl-20 bl"  id="profile_username_show">
                            <span>${admin.email}</span>      
                            <br>
                            <span><a href="#" onclick="return false;" id="edit-profile-username" class="color-light-blue"><i class="fa fa-pencil-square-o"></i> Change Username here</a></span>   
                        </div>

                        <div class="col-sm-9 pl-20 profile_detail_wrapper_edit bl hidden" id="profile_username_change">

                            <div id="profile_username_loader"></div>
                            <div id="profile_username_msg"></div>
                            <form role="form" method="post" name="profile_change_username_form" id="profile_change_username_form">   
                                <div class="form-group" style="margin: 0 !important;">
                                    <div class="col-md-6 pt-10">
                                        <label class="label-overide text-uppercase" for="prof_change_u_name">New User Name</label>
                                        <input type="text" class="form-control" id="prof_change_u_name_id" name="prof_change_u_name" autocomplete="off" placeholder="Username">
                                    </div>    

                                </div>
                                <div class="form-group" style="margin: 0 !important;">

                                    <div class="col-md-6 pt-10">
                                        <label class="label-overide text-uppercase" for="prof_change_r_u_name">Repeat User Name</label>
                                        <input type="text" class="form-control" id="prof_change_r_u_name_id" name="prof_change_r_u_name" autocomplete="off" placeholder="Repeat Username">
                                    </div> 
                                </div>

                                <div class="form-group">
                                    <div class="col-xs-6 pt-10" style="padding-bottom: 10px">
                                        <label class="label-overide text-uppercase" for="prof_change_uname_pw">Password</label>
                                        <input type="password" class="form-control" id="prof_change_uname_pw_id" name="prof_change_uname_pw" autocomplete="off" placeholder="Password">
                                    </div> 
                                    <br/>
                                    <br/>
                                </div>

                                <div class="form-group">
                                    <div class="col-xs-12 callout callout-danger margin-top-10">
                                        <strong>Important!</strong> Once you change the username, you'll be automatically loged out from your account and verifycation email will be sent to your new email address.
                                            you won't be able to access your account until you confirm your account through email we sent to you.
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-xs-12 pt-10">
                                        <input type="text" class="form-control hidden" name="prof-username-edit-btn-c">
                                        <span><button  type="submit" class="btn-override btn-theme" id="prof-username-edit-btn">save changes</button></span>
                                        <span><button  type="button" class="btn-override btn-theme" id="prof-username-cancel-btn" name="prof-username-cancel-btn">Cancel</button></span>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <hr class="no-margin"/>

                    <!--change password-->

                    <div class="row profile_detail_wrapper">
                        <div class="col-sm-3">
                            <label class="label-overide color-green text-uppercase">
                                Password 
                            </label>
                        </div>
                        <div class="col-sm-8 pl-20 bl" id="profile_password_show">
                            <div id="profile_password_msg2"></div>
                            <span><a href="#" id="edit-profile-password" onclick="return false;" class="color-light-blue"><i class="fa fa-pencil-square-o"></i> Change password here </a></span>   
                        </div>
                        <div class="col-sm-9 pl-20 profile_detail_wrapper_edit bl hidden" id="profile_password_change">

                            <div id="profile_password_loader"></div>
                            <div id="profile_password_msg"></div>

                            <form role="form" method="post" name="profile_change_password_form" id="profile_change_password_form">   
                                <div class="form-group row pl-15">
                                    <div class="col-md-6">

                                        <label class="label-overide text-uppercase" for="prof_current_password">Current Password</label>
                                        <input type="password" class="form-control" id="prof_change_f_name_id" name="prof_current_password" autocomplete="off" placeholder="Current Password">
                                    </div>      

                                </div>
                                <div class="form-group row pl-15">
                                    <div class="col-md-6">
                                        <label class="label-overide text-uppercase" for="prof_new_password">New Password</label>
                                        <input type="password" class="form-control" id="prof_change_f_name_id" name="prof_new_password" autocomplete="off" placeholder="New Password">
                                    </div>          

                                </div>
                                <div class="form-group row pl-15">
                                    <div class="col-md-6">
                                        <label class="label-overide text-uppercase" for="prof_r_new_password">Repeat Password</label>
                                        <input type="password" class="form-control" id="prof_change_f_name_id" name="prof_r_new_password" autocomplete="off" placeholder="Repeat Password">
                                    </div>     

                                </div>

                                <div class="form-group row pl-15">
                                    <div class="col-md-12">
                                        <input type="text" class="form-control hidden" name="prof-password-edit-btn-c">
                                        <span><button  type="submit" class="btn-override btn-theme" id="prof-password-edit-btn">save changes</button></span>
                                        <span><button  type="button" class="btn-override btn-theme" id="prof-password-cancel-btn" name="prof-screen-name-cancel-btn">Cancel</button></span>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>