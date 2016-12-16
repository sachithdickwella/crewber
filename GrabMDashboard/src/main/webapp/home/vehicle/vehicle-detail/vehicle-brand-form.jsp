<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="">
    <g:execute-stat>
        <c:if test="${stat gt '0'}">
            <div class="stat-respond">
                <div class="stat-respond-success text-uppercase">
                    New Vehicle brand has added successfully
                </div>
            </div>
        </c:if>
        <c:if test="${stat eq '-1'}">
            <div class="stat-respond">
                <div class="stat-respond-danger">
                    Failed to add new vehicle brand
                </div>
            </div>
        </c:if>
    </g:execute-stat>

    <div class="box-header with-border">
        <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Add New Brand</h3>
    </div>

    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" method="POST" id="vehicle_details_brand_form" action="/grabmd/vehiclebrandweb">

        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">
                <label class="control-label label-overide reg-form-lable" for="vehicle_details_brand_name">Name:</label>
                <div class="max-comp-width">
                    <input type="text" class="form-control" id="vehicle_details_brand_name" name="vehicle_details_brand_name" autocomplete="off" placeholder="Enter Name" required>
                </div>
            </div>
        </div>
        <div class="form-group margin-bottom-none">
            <div class="col-md-12 pt-10">
                <label class="control-label label-overide reg-form-lable" for="vehicle_details_brand_country">Country :</label>
                <div class="max-comp-width">
                    <input type="text" class="form-control" id="vehicle_details_brand_country" name="vehicle_details_brand_country" autocomplete="off" placeholder="Enter Country" required>
                </div>
            </div>
        </div>

        <div class="col-xs-12 margin-bottom-none pt-10"> 

            <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="vehicle_details_brand_save">Save</button></span>

            <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="vehicle_details_brand_cancel">Reset</button></span>

        </div>
        <!-- /.box-body -->

        <div class="box-footer">
        </div>
    </form>
</div>