<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="">
    <div class="box-header with-border">
        <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">UI Profile Registry</h3>
    </div>

    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" id="ui_profile_registry_form" method="POST" action="/grabmd/webprofileweb">

        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">
                <label class="control-label label-overide reg-form-lable" for="ui_web_page_rg_profile">Profile:</label>
                <div class="max-comp-width">
                    <select name="ui_web_page_rg_profile" id="ui_web_page_rg_profile" class="form-control select2" style="width: 100% !important;">
                        <c:forEach var="x" items="${profiles}">
                            <option value="${x.id}">${x.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">
                <label class="control-label label-overide reg-form-lable" for="ui_web_page_rg_web_pages">UI Pages:</label>
                <div class="max-comp-width">
                    <select name="ui_web_page_rg_web_pages" id="ui_web_page_rg_web_pages" class="form-control select2" multiple="multiple" data-placeholder="Select a UI" style="width: 100% !important;">
                        <c:forEach var="x" items="${webpages}">
                            <option value="${x.id}">${x.fileName}&nbsp;(${x.mappingUrl})</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>



        <div class="col-xs-12 margin-bottom-none pt-10"> 

            <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="ui_profile_registry_form_add">Add Profile UI</button></span>

            <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="ui_profile_registry_form_cancel">Reset</button></span>

        </div>
        <!-- /.box-body -->

        <div class="box-footer">
        </div>
    </form>
</div>