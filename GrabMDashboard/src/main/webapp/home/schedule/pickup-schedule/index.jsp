<%-- 
    Documeotit   : index
    Created on : Apr 26, 2016, 6:17:32 PM
    Author     : Roshin Perera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<!DOCTYPE html>
<html>
    <head>
        <g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
        <g:session-validate sessionAttribute="${admin}" url="${CONTEXT_PATH}"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PickUp Schedule</title>
        <!--include favicon-->
        <%@include file="../../fav.jsp" %>
        <!--include css-->
        <!--select css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/select2.min.css">
        <!--date/time picker css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/bootstrap-datetimepicker.min.css">
        <%@include file="../../dashb_style.jsp" %>
        <!--map css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gm_css/maps.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/fileinput.min.css">

        <!-- DataTables -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/dataTables.bootstrap.css">

    </head>
    <body  class="hold-transition skin-blue sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <!--top menu-->
            <%@include file="../../dash_header.jsp" %>
            <!--map model-->
            <%@include file="../map-model.jsp" %>
            <!--Transit model-->
            <%@include file="transit-modal.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../../side_menu.jsp" %>
            <!-- Main Content -->
            <%@include file="pickup-schedule-content.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../../footer.jsp" %>

            <div class="control-sidebar-bg"></div>
        </div>

        <!--include java script-->
        <%@include file="../../dashb_js.jsp" %>
        <!-- canvas-to-blob.min.js is only needed if you wish to resize images before upload.
         This must be loaded before fileinput.min.js -->
        <script src="${pageContext.request.contextPath}/js/im-js/canvas-to-blob.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/im-js/fileinput.min.js"></script>
        <!-- DataTables -->
        <script src="${pageContext.request.contextPath}/js/im-js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/im-js/dataTables.bootstrap.min.js"></script>
        <!--select2 js-->
        <script src="${pageContext.request.contextPath}/js/im-js/select2.full.min.js"></script>       
        <!--Date/time picker js-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/im-js/bootstrap-datetimepicker.min.js"></script>
        <!--transit js-->
        <script src="${pageContext.request.contextPath}/js/gm_js/clone-form-section.js"></script>
        <!--schedule validater-->
        <script src="${pageContext.request.contextPath}/js/gm_js/grabm-schedule-validater.js"></script>
        <!--load map-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gm_js/pick-location-map.js"></script>
        <!--<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>-->

        <script type = "text/javascript" src = "${pageContext.request.contextPath}/js/gm_js/multi-schedule/multi-schedule-ui.js"></script>
        <!--google map javascript API-->
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA3u56UnbVfMnhHOgppeQh1QgIAUdA2hjs&callback=initAutocomplete4">
        </script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gm_js//multi-schedule/enduser-schedule-map.js"></script>
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/gm_js/multi-schedule/lable.js"></script>-->

        <script type="text/javascript">

                    var pickupMap = new Map();
                    $(".select2").select2();
                    $('.datetimepicker').datetimepicker();
                    $(document).ready(function () {
                        $("#upload_flight_data_table").DataTable();
                        $("#upload_crew_data_table").DataTable();
                        $("#flight_schedule_upload_file").fileinput({
                            uploadUrl: '/grabmd/fileupload?type=flight',
                            showPreview: false,
                            showUpload: true,
                            uploadAsync: true,
                            allowedFileExtensions: ["xlsx", "csv", "xls"],
                            elErrorContainer: "#errorBlock"
                        }).on('fileuploaded', function (event, data, id, index) {
                            draw_table(data.response);
                        });
                        var draw_table = function (response) {
                            var table = "<table id='upload_flight_data_table' class='table table-bordered table-striped table-hover' cellspacing='0'>"
                                    + "<thead>"
                                    + "<tr class='th-txt'>"
                                    + "<th>Flight</th><th>DEP</th><th>Departure Date/Time</th><th>ARR</th><th>Arrival Date/Time</th><th>DEL</th>"
                                    + "</tr>"
                                    + "</thead>"
                                    + "<tbody>";
                            for (var idx = 0; idx < response.length; idx++) {
                                table += "<tr class='th-txt' id='" + "row" + idx + "'>"
                                        + "<td>" + response[idx].flightNumber + "</td>"
                                        + "<td>" + response[idx].departureFrom + "</td>"
                                        + "<td>" + response[idx].departureDateTime + "</td>"
                                        + "<td>" + response[idx].arriveTo + "</td>"
                                        + "<td>" + response[idx].arriveDateTime + "</td>"
                                        + "<td><a class='link-danger text-sm delete-btn' style='cursor:pointer'><i class='fa fa-trash-o margin-r-5'></i></a></td>"
                                        + "</tr>";
                            }
                            table += "</tbody>"
                                    + "</table>";
                            $('#flight-table-content').html(table);
                            table = $("#upload_flight_data_table").DataTable();
                            $("#selected-flights").val(JSON.stringify(response));
                            $('#upload_flight_data_table tbody').on('click', 'a.delete-btn', function () {
                                var tr = $(this).parents('tr');
                                table.row(tr).remove().draw();
                                var id = tr.attr("id");
                                response.splice(parseInt(id.replace('row', '')), 1);
                                $("#selected-flights").val(JSON.stringify(response));
                            });
                        };
                        var common_json = JSON.parse("[]");
                        $("#crew_schedule_upload_file").fileinput({
                            uploadUrl: '/grabmd/fileupload?type=crew',
                            showPreview: false,
                            showUpload: true,
                            uploadAsync: true,
                            allowedFileExtensions: ["xlsx", "csv", "xls"],
                            elErrorContainer: "#errorBlock2"
                        }).on('fileuploaded', function (event, data, id, index) {
                            for (var idx = 0; idx < data.response.length; idx++) {
                                var isExists = false;
                                for (var x = 0; x < common_json.length; x++) {
                                    if (parseInt(common_json[x].id) === parseInt(data.response[idx].id)) {
                                        isExists = true;
                                        break;
                                    }
                                }
                                if (!isExists) {
                                    common_json.push(data.response[idx]);
                                }
                            }
                            draw_crewtable();
                        });
                        var draw_crewtable = function () {
                            var table = '<table id="upload_crew_data_table" class="table table-bordered table-striped" style="width:100%">'
                                    + '<thead>'
                                    + '<tr class="th-txt">'
                                    + '<th>Member ID</th>'
                                    + '<th>Title</th>'
                                    + '<th>first Name</th>'
                                    + '<th>Last Name</th>'
                                    + '<th>Description</th>'
                                    + '<th>DEL</th>'
                                    + '</tr>'
                                    + '</thead>'
                                    + '<tbody>';
                            for (var idx = 0; idx < common_json.length; idx++) {
                                table += '<tr class="th-txt" id="' + idx + '">'
                                        + '<td>' + common_json[idx].membershipNumber + '</td>'
                                        + '<td>' + common_json[idx].designation.longName + ' (' + common_json[idx].designation.shortName + ')' + '</td>'
                                        + '<td>' + common_json[idx].firstName + '</td>'
                                        + '<td>' + common_json[idx].lastName + '</td>'
                                        + '<td>' + common_json[idx].note + '</td>'
                                        + '<td>'
                                        + '<a style="cursor:pointer" class="link-danger delete-btn text-sm"><i class="fa fa-trash-o margin-r-5"></i></a>'
                                        + '</td>'
                                        + '</tr>';
                            }
                            table += "</tbody>"
                                    + "</table>";
                            $('#crew-table-content').html(table);
                            table = $("#upload_crew_data_table").DataTable();
                            $("#selected-crew").val(JSON.stringify(common_json));
                            $('#upload_crew_data_table tbody').on('click', 'a.delete-btn', function () {
                                var tr = $(this).parents('tr');
                                table.row(tr).remove().draw();
                                var id = tr.attr("id");
                                common_json.splice(parseInt(id.replace('row', '')), 1);
                                $("#selected-crew").val(JSON.stringify(common_json));
                            });
                        };
                        $("#add_new_crew_upload").click(function (e) {
                            var select_crew = $('#upload_crew_add_new_member option:selected');
                            for (var idx = 0; idx < common_json.length; idx++) {
                                if (parseInt(common_json[idx].id) === parseInt(select_crew.val())) {
                                    alert("User already exists on the table.");
                                    return false;
                                }
                            }

                            common_json.push({"id": select_crew.val(),
                                "membershipNumber": select_crew.data("memno"),
                                "designation": {"id": select_crew.data("desid"),
                                    "shortName": select_crew.data("desshortname"),
                                    "longName": select_crew.data("deslongname")},
                                "firstName": select_crew.data("firstname"),
                                "lastName": select_crew.data("lastname"),
                                "note": select_crew.data("desc")});
                            draw_crewtable();
                            return true;
                        });
                        $("#crew_schedule_upload_file").fileinput({
                            showPreview: false,
                            showUpload: false,
                            allowedFileExtensions: ["xlsx", "csv", "xls"],
                            elErrorContainer: "#errorBlock2"
                        });
                        $("#note").text('${futurepickups[0].note}');
                        $("#schedule_user_flight_number").change(function () {
                            var note = pickupMap.get($(this).val().toString());
                            $("#note").text(note);
                        });
                        firstEleValue = '${vehicledrivers[0].vehicle.passengersMax}';
                        $("#user_sch_max_passengers_allowed_1").text('${vehicledrivers[0].vehicle.passengersMax}');
                        $("#user_sch_pick_vehicle_1").change(function () {
                            var max = maxPassengers.get($(this).val().toString());
                            $("#user_sch_max_passengers_allowed_1").text(max);
                        });
                        $("#vehiclenote").text('${futurepickups[0].note}');
                        scheduleDispatch($("#despatch_flight_number").val().toString());
                        $("#despatch_flight_number").change(function () {
                            var note = pickupMap.get($(this).val().toString());
                            $("#vehiclenote").text(note);
                            $('#row-data').text('');
                            scheduleDispatch($(this).val().toString());
                        });
                    });
            <c:forEach var="x" items="${futurepickups}">
                    pickupMap.set('${x.id}', '${x.note}');
            </c:forEach>
            <c:forEach var="x" items="${vehicledrivers}">
                    maxPassengers.set('${x.id}', '${x.vehicle.passengersMax}');
            </c:forEach>
                    var notify = function (pickupId, statusCode, group, idx) {
                        $.ajax({
                            method: 'POST',
                            url: '${backend_url_key}',
                            data: {url: '/pickupschedule/notify/' + pickupId + "?status=" + statusCode + "&group=" + group
                                        + "&vehicleDriver=" + $('#dispatch_vehicle_driver' + idx + ' option:selected').val(), body: '', accept: 'json', content: 'plain'}
                        }).done(function (response) {
                            alert(response);
                            scheduleDispatch(pickupId);
                        }).fail(function (e) {
                            console.log(e);
                        });
                    };
                    var scheduleDispatch = function (e) {
                        $.ajax({
                            method: 'GET',
                            url: '${backend_url_key}',
                            data: {url: '/pickupschedule/schedules/' + e, body: '', accept: 'json', content: 'plain'}
                        }).done(function (response) {
                            render_rowdata(JSON.parse(response));
                        }).fail(function () {
                            console.log("Ajax query failed");
                        });
                    };
                    var render_rowdata = function (response) {
                        $('#row-data').text("");
                        var group_json = JSON.parse('[]'), previous = '', temp_json = JSON.parse('[]');
                        for (var idx = 0; idx < response.length; idx++) {
                            var schedule = response[idx];
                            if (schedule.vehicleGroup.toString() !== previous) {
                                if (JSON.stringify(temp_json) !== '[]') {
                                    group_json.push({"group": temp_json});
                                }
                                temp_json = JSON.parse('[]');
                                temp_json.push(schedule);
                                previous = schedule.vehicleGroup;
                            } else {
                                if (JSON.stringify(temp_json) !== '[]') {
                                    temp_json.push(schedule);
                                }
                                previous = schedule.vehicleGroup;
                            }
                            if ((idx + 1) === response.length) {
                                group_json.push({"group": temp_json});
                            }
                        }

                        for (var idx = group_json.length - 1; idx >= 0; idx--) {
                            var groups = group_json[idx];
                            var group_name = '', pickup_id;
                            for (var i = 0; i < groups.group.length; i++) {
                                var group = groups.group[i];
                                group_name = group.vehicleGroup.replace('GROUP', 'VEHICLE');
                                pickup_id = group.pickupId.id;
                                break;
                            }
                            template(idx, group_name, groups.group.length, pickup_id);
                        }
                    };

                    var template = function (idx, group, group_pasengers, pickup_id) {
                        var group_name = group.replace('VEHICLE', 'GROUP');
                        var template = '<div class="col-md-12">'
                                + '<div class="post border-bottom">'
                                + '<div class="row">'
                                + '<div class="col-lg-12">'
                                + '<div class="not-despatched pull-right">'
                                + '<span id="despatch-status" class="text-uppercase">Pending</span>'
                                + '</div>'
                                + '<p class="txt-light-gray">' + group
                                + '<br>| Schedule Passengers : ' + group_pasengers + ' | Max Passengers Allowed :  <span id="temp_max_passengers' + idx + '"></span>&nbsp;|</p>'
                                + '</div>'
                                + '</div>'
                                + '<div class="row">'
                                + '<div class="col-md-12 pt-10 margin-bottom">'
                                + '<label class="text-uppercase txt-light-gray txt-light-weight2">Pick Driver/Vehicle : </label> '
                                + '<div class="max-comp-width">'
                                + '<select id="dispatch_vehicle_driver' + idx + '" name="select_vehicle_dispatch" class="form-control select2" style="width: 100%">'
                                + '<c:forEach var="x" items="${vehicledrivers}">'
                                + '<option value="${x.id}">${x.vehicle.registrationNumber} - ${x.driver.firstName} ${x.driver.lastName} (${x.vehicle.vehicleModelId.vehicleTypeId.longName})</option>'
                                + '</c:forEach>'
                                + '</select>'
                                + '</div>'
                                + '</div>'
                                + '</div>'
                                + '<div class="row">'
                                + '<div class="col-lg-12">'
                                + '<ul class="list-inline">'
                                + '<li class=""><a href="javascript:notify(' + pickup_id + ', \'DISPATCHED\', \'' + group_name + '\', ' + idx + ');" class="link-black text-sm text-uppercase"><i class="fa fa-car margin-r-5"></i>Dispatch</a></li>'
                                + '<li><a href="#" class="link-black text-sm text-uppercase"><i class="fa fa-pencil-square-o margin-r-5"></i>Replace Driver/vehicle</a></li>'
                                + '<li><a href="#" class="link-black text-sm text-uppercase"><i class="fa fa-eye-slash margin-r-5"></i> View Crew</a></li>'
                                + '</ul>'
                                + '</div>'
                                + '</div>'
                                + '</div>'
                                + '</div>';
                        $('#row-data').append(template);
                        var max_passenger_map = new Map();
            <c:forEach var="x" items="${vehicledrivers}">
                        max_passenger_map.set('${x.id}', '${x.vehicle.passengersMax}');
            </c:forEach>
                        $('#dispatch_vehicle_driver' + idx).on('change', function () {
                            $('#temp_max_passengers' + idx).text(max_passenger_map.get($(this).val()));
                        });
                        $('#dispatch_vehicle_driver' + idx).change();
                    };

        </script>
    </body>
</html>