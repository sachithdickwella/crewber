<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="">
    <div class="box-header with-border">
        <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add New Profile</h3>
    </div>

    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" method="POST" id="add_new_profile_form" action="/grabmd/profileweb">

        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">
                <label class="control-label label-overide reg-form-lable" for="ui_profile_rg_name">Name:</label>
                <div class="max-comp-width">
                    <input type="text" class="form-control" id="ui_profile_rg_name" name="ui_profile_rg_name" autocomplete="off" placeholder="Enter Name" required>
                </div>
            </div>
        </div>

        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">

                <label class="control-label label-overide reg-form-lable" for="ui_profile_rg_note">Description:</label>
                <div class="max-comp-width">

                    <textarea class="form-control" rows="3" id="ui_profile_rg_note" name="ui_profile_rg_note" placeholder="Note" required></textarea>
                </div>

            </div>
        </div>

        <div class="col-xs-12 margin-bottom-none pt-10"> 

            <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="add_new_profile_add">Add Profile</button></span>

            <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="add_new_profile_form_cancel">Reset</button></span>

        </div>
        <!-- /.box-body -->

        <div class="box-footer">
        </div>
    </form>
</div>