/* global google, map4, crewJSON */

var map5;
var map5Center;
var firstLocationRequests;
var endUserIdUpdate;
function initAutocomplete5() {
    map5Center = {lat: 6.912708758438757, lng: 79.8621940612793};
    map5 = new google.maps.Map(document.getElementById('map5'), {
        center: map5Center,
        zoom: 12,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
}

$(document).ready(function () {

    $("a[href='#crew_pickup_address_permission']").on('shown.bs.tab', function () {
        google.maps.event.trigger(map5, 'resize');
        map5.setCenter(map5Center);
    });

    loadActiveFirstLocationRequests();

    var tabID = ["pickup-first-permission-tab", "pickup-location-permission-tab", "pickup-address-permission-tab", "pickup-denied-requests-tab"];
    var list_item = ["reject-r1", "reject-r2", "reject-r3"];
    permissionTabEvents(tabID);
    selectReasonEvents(list_item);
    $('#pickup-first-permission-tab').removeClass('nav-list-inactive');
    $('#pickup-first-permission-tab').addClass('nav-list-active');
});

var selectReasonEvents = function (liId) {
    liId.forEach(function (id) {
        $('#' + id + '').on('click', function () {
            liId.forEach(function (id) {
                $('#' + id + '').removeClass('select-modal-li');
            });
            $(this).addClass('select-modal-li');
        });
    });
};

var reject_modal = function (id) {

    $('#reject-location-modal-confirm').unbind('click');
    $('#reject-location-request-modal').modal('show');

    $('#reject-location-modal-confirm').bind('click', function () {
        /**
         * SUBMIT CODE GOES HERE
         * */
        get_selected(id);

    });
};

var get_selected = function (id) {

    var list_item = ["reject-r1", "reject-r2", "reject-r3"];
    var msg = $('.reject-modal-select li.select-modal-li a').html();
    if (typeof msg !== 'undefined') {
        list_item.forEach(function (id) {
            $('#' + id + '').removeClass('select-modal-li');
        });
        $('#reject-location-request-modal').modal('hide');
        updateFirstRequest(endUserIdUpdate, false, id, 0, msg);
    }
};


var draw_table = function (response) {
    var table = $('#crew_location_to_confirm_table').DataTable();
    table.clear().draw();
    table = "<table id='crew_location_to_confirm_table' class='table table-bordered table-striped table-hover' cellspacing='0'>"
            + "<thead>"
            + "<tr class='th-txt'>"
            + "<th>Member id</th><th>Title</th><th>Name</th><th>select</th>"
            + "</tr>"
            + "</thead>"
            + "<tbody>";
    for (var idx = 0; idx < response.length; idx++) {
        table += "<tr class='th-txt' id='" + "row" + idx + "'>"
                + "<td>" + response[idx].membershipNumber + "</td>"
                + "<td>" + response[idx].designation.shortName + "</td>"
                + "<td>" + response[idx].firstName + ' ' + response[idx].lastName + "</td>"
                + "<td><a class='link-info text-sm select-btn' id='" + response[idx].id + "' style='cursor:pointer'><i class='fa fa-arrow-right margin-r-5'></i></a></td>"
                + "</tr>";
    }
    table += "</tbody>"
            + "</table>";
    $('#crew-location-to-confirm-table-section').html(table);
    table = $("#crew_location_to_confirm_table").DataTable();

    $('#crew_location_to_confirm_table tbody').on('click', 'a.select-btn', function () {
        var tr = $(this).parents('tr');
        var id = table.row(tr).data();
        set_data(id[0]);
    });
};

var gml_marker;
var set_data = function (id) {

    for (var i = 0; i < firstLocationRequests.length; i++) {
        if (id === firstLocationRequests[i].membershipNumber) {
            endUserIdUpdate = firstLocationRequests[i].id;
            $('#user-pickup-location-permission-permitted-address').text(firstLocationRequests[i].cityTown);
            $('#user-pickup-location-permission-member').text(firstLocationRequests[i].designation.shortName + '. '
                    + firstLocationRequests[i].firstName + ' ' + firstLocationRequests[i].lastName
                    + ' ( ' + firstLocationRequests[i].membershipNumber + ' ) ');
            if (typeof gml_marker !== 'undefined') {
                gml_marker.setMap(null);
            }

            var location = {lat: firstLocationRequests[i].currentLatitude, lng: firstLocationRequests[i].currentLongitude};
            gml_marker = new google.maps.Marker({
                position: location,
                map: map5,
                title: firstLocationRequests[i].firstName
            });

            var geocoder = new google.maps.Geocoder;
            geocoder.geocode({'location': location}, function (results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    if (results[1]) {
                        $('#user-pickup-location-permission-marked-address').text(results[1].formatted_address);
                    } else {
                        $('#user-pickup-location-permission-marked-address').text('No Result');
                    }
                } else {
                    $('#user-pickup-location-permission-marked-address').text('Geocoder failed due to: ' + status);
                }
            });

            map5.setCenter(location);
            map5.setZoom(13);
        }
    }
};

function permissionTabEvents(tabID) {
    tabID.forEach(function (id) {
        $('#' + id + '').on('click', function () {
            setInactiveTabs(tabID);
            $(this).removeClass('nav-list-inactive').addClass('nav-list-active');
        });
    });
}

function setInactiveTabs(tabID) {
    tabID.forEach(function (id) {
        $('#' + id + '').removeClass('nav-list-active').addClass('nav-list-inactive');
    });
}

var loadActiveFirstLocationRequests = function () {
    $.ajax({
        url: '/grabmd/commons',
        method: "GET",
        data: {url: '/enduser/all/location/WAITING_APPROVAL_GPS_LOCATION', body: '', accept: 'json', content: 'plain'}
    }).done(function (response) {
        firstLocationRequests = JSON.parse(response);
        var size = firstLocationRequests.length;
        if (size > 0) {
            $('#first-request-permission-badge').text(firstLocationRequests.length);
        } else {
            $('#first-request-permission-badge').text("");
        }

        draw_table(firstLocationRequests);
    });
};

var updateFirstRequest = function (id, isAccepted, userId, pickupAreaId, msg) {
    if (typeof id === "undefined" || id === null || id === "") {
        return false;
    }
    var stat;
    if (isAccepted) {
        stat = 'APPROVED_GPS_LOCATION';
    } else {
        stat = 'REJECTED_FIRST_GPS_LOCATION';
    }
    $.ajax({
        url: '/grabmd/commons',
        method: "POST",
        data: {url: '/enduser/update/location/status/' + id
                    + '?location-status=' + stat + '&user-id=' + userId + '&pickup-area-id=' + pickupAreaId + '&msg=' + msg,
            body: '', accept: 'plain', content: 'plain'}
    }).done(function (response) {
        if (parseInt(response) === 0) {
            loadActiveFirstLocationRequests();
            clearPage();
        } else {
            alert("Response: " + response);
        }
    });
    return true;
};

var clearPage = function () {
    endUserIdUpdate = null;
    $('#user-pickup-location-permission-member').text("");
    $('#user-pickup-location-permission-permitted-address').text("");
    $('#user-pickup-location-permission-marked-address').text("");

    gml_marker.setMap(null);
    map5.setZoom(12);
    map5.setCenter(map5Center);
};