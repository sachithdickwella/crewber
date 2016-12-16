<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="">
    <div class="box-header with-border">
        <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add UI Pages</h3>
    </div>

    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" method="POST" id="add_new_ui_pages_form" action="/grabmd/webpagesweb">

        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">
                <label class="control-label label-overide reg-form-lable" for="ui_web_page_rg_file_name">File Name:</label>
                <div class="max-comp-width">
                    <input type="text" class="form-control" id="ui_web_page_rg_file_name" name="ui_web_page_rg_file_name" autocomplete="off" placeholder="File Name" required>
                </div>
            </div>
        </div>

        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">
                <label class="control-label label-overide reg-form-lable" for="ui_web_page_rg_mapping_url">Mapping URL:</label>
                <div class="max-comp-width">
                    <input type="text" class="form-control" id="ui_web_page_rg_mapping_url" name="ui_web_page_rg_mapping_url" autocomplete="off" placeholder="URL" required>
                </div>
            </div>
        </div>

        <div class="col-xs-12 margin-bottom-none pt-10"> 

            <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="add_new_ui_pages_form_add">Add UI Pages</button></span>

            <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="add_new_ui_pages_form_cancel">Reset</button></span>
        </div>
        <!-- /.box-body -->

        <div class="box-footer">
        </div>
    </form>
</div>