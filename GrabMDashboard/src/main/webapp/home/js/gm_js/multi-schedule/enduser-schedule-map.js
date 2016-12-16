/* global google, crewJSON, image_user_pickup_marker, i */
var map4;
var map4Center;
var EUMarker = new Array();
var userJson;
var selectedUsers = [];
var groupedUsers = [];
var transitUsers = [];
var table_count = 0;
var passengerGroups = JSON.parse("[]");

var active_pickup_schedule_list_by_flight;
function initAutocomplete4() {


    map4Center = {lat: 6.912708758438757, lng: 79.8621940612793};
    map4 = new google.maps.Map(document.getElementById('map4'), {
        center: map4Center,
        zoom: 12,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
}

var EUPickupmarker = '/grabmd/images/icn/schedule/location-marker-pickup.png';
var EUPickupmarkerDisabled = '/grabmd/images/icn/schedule/location-marker-pickup-disabled.png';
var EUPickupmarkerSelected = '/grabmd/images/icn/schedule/location-marker-pickup-selected.png';

var deleteTable = function (id) {

    var div = document.getElementById('grouped-crew-section');
    var remove_table = document.getElementById('table_remove_' + id);

    var table_rw = $('#upload_multi_schedule_data_table' + id + ' tbody tr');
    for (var i = 0; i < table_rw.length; i++) {
        console.log(table_rw[i].id);
        var gr_u = groupedUsers.indexOf(table_rw[i].id);
        if (gr_u !== -1) {
            groupedUsers.splice(gr_u, 1);
        }

        $('#id_' + table_rw[i].id).removeClass('list-disabled');

        for (var j = 0; j < EUMarker.length; j++) {
            if (EUMarker[j].id === table_rw[i].id) {
//                EUMarker[j].data('state', false);
                EUMarker[j].setIcon(EUPickupmarker);
            }
        }
    }

    console.log(groupedUsers);
    toggleSelected();


    var table = $('#upload_multi_schedule_data_table' + id);
    console.log(div.childNodes);
    div.removeChild()(table);
    div.removeChild(remove_table);
};

$(document).ready(function () {

    $("a[href='#pickup-schedule']").on('shown.bs.tab', function () {
        google.maps.event.trigger(map4, 'resize');
        map4.setCenter(map4Center);
    });

    $('#multi_schedule_add_to_schedule').click(function () {
        if (selectedUsers.length > 0) {
            draw_table(active_pickup_schedule_list_by_flight);
            toggleSelected();
            selectedUsers = [];
        } else {
            alert('select users to group');
        }
    });

    $('#toggle-transit-drop').on('click', function (ev) {
        ev.preventDefault();
        var condition = $('#transit_drop_location_default_wrapper').is(":visible");

        if (condition) {
            $('#transit_drop_location_lat_long_wrapper').removeClass('hidden');
            $('#transit_drop_location_lat_long').focus();
            $('#transit_drop_location_default_wrapper').addClass('hidden');
            $('#toggle-transit-drop').html('DROP OFF LAT/LONG');

        } else {
            $('#transit_drop_location_default_wrapper').removeClass('hidden');
            $('#transit_drop_location_default').focus();
            $('#transit_drop_location_lat_long_wrapper').addClass('hidden');
            $('#toggle-transit-drop').html('DEFAULT DROP OFF');
        }
    });

});

function transitModal(row_) {

    var transit_row = ['#d3f0b4', '#9fbfff', '#f4b0b0', '#f5f1ab', '#ddabf5'];
    resetModal();

    $('#transit-modal').modal('show');
    $('#transit-modal-confirm').unbind();

    $('#transit-modal-crew-member').text(row_[2].innerHTML + ". " + row_[3].innerHTML);

    var coord = row_[4].innerHTML.split('|');
    $('#transit_pick_location_lat_long').val(coord[0].trim());

    $('#transit-modal-confirm').bind("click", function () {
        var pick = $('#transit_pick_location_lat_long').val().trim();
        var drop;

        if ($('#transit_drop_location_default_wrapper').is(":visible")) {
            drop = $('#transit_drop_location_default').text();
        } else {
            drop = $('#transit_drop_location_lat_long').val();
        }
        /**
         * UPDATE TRANSPORT TYPE
         */
        var row = row_[0];
        var transit_user = row.parentNode.id.split('_');

        var transit_update = function () {

            var tag = row_[6].childNodes;
            tag[0].innerHTML = "TRANSIT";

            transitUsers.push(transit_user[1]);
            var i = groupedUsers.indexOf(parseInt(transit_user[1]));
            if (i > -1) {
                groupedUsers.splice(i, 1);
            }

            $('#id_' + transit_user[1]).removeClass('list-disabled').addClass('transit-user');

            EUMarker.forEach(function (marker) {
                if (parseInt(marker.id) === parseInt(transit_user[1])) {
                    marker.setIcon(EUPickupmarker);
                }
            });
        };

        if ((pick !== coord[0].trim()) || (row_[5].innerHTML.trim() !== drop.trim())) {
            /**
             * UPDATE PICKUP LOCATION
             */
            if (pick !== coord[0].trim()) {
                var coordi = $('#transit_pick_location_lat_long').val() + " | (TRANSIT)";
                row_[4].innerHTML = coordi;
            }
            /**
             * UPDATE DROP LOCATION
             */
            if (row_[5].innerHTML.trim() !== drop.trim()) {
                row_[5].innerHTML = drop;
            }

            if (transitUsers.length > 0) {
                transit_update();
            } else {
                transit_update();
            }

            var user_index = transitUsers.indexOf(transit_user[1]);
            $(row.parentNode).addClass('transit-user-tr');
            $(row.parentNode).css({'background-color': transit_row[user_index]});

        }
        $('#transit-modal').modal('hide');
    });
}

$('#transit-modal-close').on('click', function (ev) {
    ev.preventDefault();
});

function resetModal() {
    $('#transit_pick_location_lat_long').val('');
    $('#transit_drop_location_lat_long').val('');
    $('#transit-modal').data('modal', null);
}

var draw_table = function (response) {
    var passengerList = JSON.parse("[]");
    var tb_count = table_count++;
    var delete_tb = '<div class="pull-right" id="table_remove_ ' + table_count + '">' +
            '<a id="table-delete-btn' + tb_count + '" class="link-danger text-sm table-delete-btn" style="cursor:pointer">' +
            '<i class="fa fa-trash-o margin-r-5">&nbsp</i>DEL</a></div>';

    var table = "<table id='upload_multi_schedule_data_table" + tb_count + "' class='table table-bordered table-hover' cellspacing='0'>"
            + '<thead>'
            + '<tr class="th-txt">'
            + '<th>Member ID</th><th>PIndex</th><th>Rank</th><th>Name</th><th>PICK</th><th>DROP</th><th>TYPE</th><th>DEL</th>'
            + '</tr>'
            + '</thead>'
            + "</tr>"
            + "<tbody>";

    for (var i = 0; i < selectedUsers.length; i++) {
        for (var idx = 0; idx < response.length; idx++) {
            if (response[idx].id === selectedUsers[i]) {
                var latitude, longitude, dropLatLng;
                dropLatLng = $('#enduser_multi_schedule_drop option:selected').text();
                if (response[idx].locationStatus === '2') {
                    latitude = response[idx].currentLatitude;
                    longitude = response[idx].currentLongitude;
                } else {
                    latitude = response[idx].previousLatitude;
                    longitude = response[idx].previousLongitude;
                }

                /**
                 * Checking Transit
                 * */
                var tbcount = $('#grouped-crew-section .table');
                if (transitUsers.length > 0) {
                    for (var tb_idx = 0; tb_idx < tbcount.length; tb_idx++) {
                        var id = tbcount[tb_idx].id;
                        /**
                         * Get transit rows from each table
                         * */
                        var trRows = $('#' + id + ' tbody tr.transit-user-tr');
                        for (var rw = 0; rw < trRows.length; rw++) {
                            /**
                             * Split user id from row 
                             * */
                            var trUser = trRows[rw].id.split('_');
                            var trData = trRows[rw].childNodes;

                            var style = trRows[rw].style;
                            if (parseInt(response[idx].id) === parseInt(trUser[1])) {
                                /**
                                 * Split user transit column from row 
                                 * sample 6.788164104451289,79.915522746741778 | (TRANSIT)
                                 * */
                                var transit_html = trData[4].innerHTML.split('|');
                                var transit_pickup_latLng = transit_html[0];
                                var transit_pickup_latLng_Arr = transit_pickup_latLng.split(',');
                                /**
                                 * Check if pickup location has changed from 1st pick(table row)
                                 * */
                                if (latitude !== transit_pickup_latLng_Arr[0] | longitude !== transit_pickup_latLng_Arr[1]) {
                                    dropLatLng = transit_pickup_latLng_Arr[0] + ',' + transit_pickup_latLng_Arr[1];
                                }
                                /**
                                 * Check if dropoff location has changed from 1st pick(table row)
                                 * */
                                if (dropLatLng !== 'BIA') {
                                    latitude = transit_pickup_latLng_Arr[0];
                                    longitude = transit_pickup_latLng_Arr[1];
                                    dropLatLng = 'BIA';
                                }
                            }
                        }
                    }
                }

                var pindex = selectedUsers.indexOf(response[idx].id) + 1;
                table += "<tr class='th-txt' id='" + "row_" + response[idx].id + "'>"
                        + "<td>" + response[idx].membershipNumber + "</td>"
                        + "<td>" + pindex + "</td>"
                        + "<td>" + response[idx].designation.shortName + "</td>"
                        + "<td>" + response[idx].firstName + ' ' + response[idx].lastName + "</td>"
                        + "<td>" + latitude + ',' + longitude + ' | (' + response[idx].cityTown + ')' + "</td>"
                        + "<td> " + dropLatLng + " </td>"
                        + "<td><a class='link-danger text-sm bulk-schedule-table-edit transit-modal-trigger' style='cursor:pointer'>GENARAL</a></td>"
                        + "<td><a class='link-danger text-sm delete-btn' style='cursor:pointer'><i class='fa fa-trash-o margin-r-5'></i></a></td>"
                        + "</tr>";

                passengerList.push({"id": response[idx].id,
                    "pickupIndex": pindex,
                    "pickupPoint": latitude + ',' + longitude,
                    "dropoffPoint": dropLatLng});
            }
        }
    }
    passengerGroups.push({"group": passengerList});
    console.log(JSON.stringify(passengerGroups));

    table += "</tbody>"
            + "</table>";

    $('#grouped-crew-section').append(delete_tb);
    $('#grouped-crew-section').append(table);


    $('#upload_multi_schedule_data_table' + tb_count + ' tbody').on('click', 'a.bulk-schedule-table-edit', function () {

        var tr = $(this).closest("tr").find('td');
        transitModal(tr);
    });
};

function toggleSelected() {

    var li_length = $("#multischedule-list li");
    for (var i = 0; i < selectedUsers.length; i++) {
        for (var j = 0; j < li_length.length; j++) {
            if ('id_' + selectedUsers[i] === li_length[j].id) {
                groupedUsers.push(selectedUsers[i]);
                $('#id_' + selectedUsers[i]).addClass('list-disabled').removeClass('active-row');
                document.getElementById('lbl_id_' + selectedUsers[i] + '').innerHTML = '';
                transitUsers.forEach(function (user) {
                    if (user === selectedUsers[i]) {
                        $('#id_' + selectedUsers[i]).removeClass('list-disabled');
                        document.getElementById('lbl_id_' + selectedUsers[i] + '').innerHTML = 'TRANSIT';
                    }
                });

            }
        }

        for (var j = 0; j < EUMarker.length; j++) {
            if (EUMarker[j].id === selectedUsers[i]) {
                EUMarker[j].setIcon(EUPickupmarkerDisabled);
            }
        }
    }
}

function dynamicList(user, index) {
    var list = document.getElementById('multischedule-list');
    var entry = document.createElement('li');
    var title = user[index].designation.shortName + '. ' + user[index].firstName + ' ' + user[index].lastName;
    entry.id = 'id_' + user[index].id;
    entry.className = 'list-group-item list-group-item-multi-schedule-list';
    entry.innerHTML = '<span class="badge" id ="lbl_id_' + user[index].id + '"></span>' + title;
    //append li element to ul
    list.appendChild(entry);
}

function endUserMarkers(user, index) {
    var location;
    if (user[index].locationStatus === '2') {
        location = {lat: user[index].currentLatitude, lng: user[index].currentLongitude};
    } else {
        location = {lat: user[index].previousLatitude, lng: user[index].previousLongitude};
    }
    var id = user[index].id;
    var title = user[index].designation.shortName + ' ' + user[index].firstName + ' ' + user[index].lastName;
    EUMarker[index] = new google.maps.Marker({
        map: map4,
        draggable: false,
        animation: google.maps.Animation.NONE,
        position: location,
        icon: EUPickupmarker,
        title: title,
        id: id
    });

    google.maps.event.addListener(EUMarker[index], "click", function () {
        if (!(groupedUsers.indexOf(this.id) > -1)) {

            var state = $(this).data('state');
            state = !state;
//            alert(this.id);

            if (EUMarker[index].getIcon() === EUPickupmarker) {
                selectedUsers.push(this.id);
                pickupIndexLabel();
                EUMarker[index].setIcon(EUPickupmarkerSelected);
                console.log(selectedUsers);
            } else {
                var i = selectedUsers.indexOf(this.id);
                if (i !== -1) {
                    selectedUsers.splice(i, 1);
                }
                document.getElementById('lbl_id_' + this.id + '').innerHTML = '';
                pickupIndexLabel();
                EUMarker[index].setIcon(EUPickupmarker);
                console.log(selectedUsers);
            }
            $(this).data('state', state);
        }

    });
}

function pickupIndexLabel() {
    for (var i = 0; i < selectedUsers.length; i++) {
        document.getElementById('lbl_id_' + selectedUsers[i] + '').innerHTML = selectedUsers.indexOf(selectedUsers[i]) + 1;
    }
}

var load_active_pickup_schedule_list_by_flight = function (pickupId) {
    $.ajax({
        method: 'GET',
        url: '/grabmd/commons',
        data: {url: '/pickupschedule/endusers/' + pickupId, body: '', accept: 'json', content: 'plain'}
    }).done(function (response) {
        active_pickup_schedule_list_by_flight = JSON.parse(response);
        $('#multischedule-list').empty();
        EUMarker.forEach(function (marker) {
            marker.setMap(null);
        });
        EUMarker = new Array();
        for (var i = 0; i < active_pickup_schedule_list_by_flight.length; i++) {
            dynamicList(active_pickup_schedule_list_by_flight, i);
            endUserMarkers(active_pickup_schedule_list_by_flight, i);
        }
    });
};

$('#multi_schedule_submit').on('click', function () {
    $.ajax({
        url: "/grabmd/pickupschedulesubmitweb",
        method: "POST",
        data:{tableData: JSON.stringify(passengerGroups)}
    }).done(function (response) {
        alert(response);
    });
});

$(function () {
    var flight = $('#enduser_multi_schedule_flight_number option:selected').val();
    load_active_pickup_schedule_list_by_flight(flight);
    $('#enduser_multi_schedule_flight_number').change(function () {
        load_active_pickup_schedule_list_by_flight($(this).val());
    });
});
