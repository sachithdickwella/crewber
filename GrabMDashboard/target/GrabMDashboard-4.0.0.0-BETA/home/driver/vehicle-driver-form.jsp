<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="row">
    <div class="col-md-7">
        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Schedule Driver - Vehicle</h3>
        </div>
        <g:execute-stat>
            <script type="text/javascript">
                <c:if test="${stat eq -10}">
                    <%--
                        Notify the user that vehicle driver mapping exists if do so.
                    --%>
                </c:if>
            </script>
        </g:execute-stat>
        <!-- /.box-header -->
        <!-- form start -->
        <form role="form" method="POST" id="schedule_driver_vehicle_form" action="/grabmd/vehicledriverweb">

            <div class="row">
                <div class="col-md-12">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-12 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="schedule_dv_driver_name">Driver:</label>
                            <div class="max-comp-width">
                                <select name="schedule_dv_driver_name" id="schedule_dv_driver_name" class="form-control select2" style="width: 100%">
                                    <c:forEach var="x" items="${drivers}">
                                        <option value="${x.id}">${x.firstName}&nbsp;${x.lastName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group margin-bottom-none">
                        <div class="col-md-12 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="schedule_dv_vehicle_name">Vehicle:</label>
                            <div class="max-comp-width">
                                <select name="schedule_dv_vehicle_name" id="schedule_dv_vehicle_name" class="form-control select2" style="width: 100%">
                                    <c:forEach var="x" items="${vehicles}">
                                        <option value="${x.id}">${x.registrationNumber} ~ [${x.vehicleModelId.vehicleTypeId.longName}]</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group margin-bottom-none">
                        <div class="col-md-12 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="schedule_dv_note">Note</label>
                            <div class="max-comp-width">
                                <textarea class="form-control" rows="3" id="schedule_dv_note" name="schedule_dv_note" placeholder="Note"></textarea>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10"> 

                <span><button style="min-width: 150px; margin-bottom: 5px;" type="submit" class="btn-override btn-theme" id="schedule_dv_save">save</button></span>

                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="schedule_dv_cancel">Reset</button></span>

            </div>
            <!-- /.box-body -->

            <div class="box-footer">
            </div>
        </form>
    </div>

</div>