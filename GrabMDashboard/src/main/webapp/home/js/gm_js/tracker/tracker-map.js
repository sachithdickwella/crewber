/* global google, newNum, pickupPath, trackerListShowVehicleStatus, trackerListShowScheduleStatus */

var map;
var ind;
/**
 * Active Pickup Schedule JSON
 */
var pickupSchedulesActiveVar = [];
var activeDiverVehicle = [];

var image_vehicle = ' /grabmd/images/icn/car-48.png';
var image_on_click = ' /grabmd/images/icn/car-front-48-select.png';
var image_on_click_animate = ' /grabmd/images/icn/tracker/ripple.svg';
var image_vehicle_move = '/grabmd/images/icn/tracker/car-on-move.png';
var image_vehicle_move_selected = '/grabmd/images/icn/tracker/car-on-move-selected.png';
var image_vehicle_stopped = '/grabmd/images/icn/tracker/car-stopped.png';
var image_vehicle_stopped_selected = '/grabmd/images/icn/tracker/car-stopped-seleced.png';
var image_unlisted_vehicle_move = '/grabmd/images/icn/tracker/unlisted-on-move.png';
var image_unlisted_vehicle_stopped = '/grabmd/images/icn/tracker/unlisted-stopped.png';
var image_user_pickup_marker = '/grabmd/images/icn/tracker/pickup-marker.png';
var image_pickup_marker_pilot = '/grabmd/images/icn/tracker/location_marker_pilot_48.png';

function initAutocomplete2() {

    map = new google.maps.Map(document.getElementById('map2'), {
        center: {lat: 6.912708758438757, lng: 79.8621940612793},
        zoom: 9,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });


    // Create the search box and link it to the UI element.
    var input = document.getElementById('pac-input');
    var searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    /***
     * Call active pickup schedule method
     * @param {type} output
     * @returns {undefined}
     */


    picukupSchedulesActive(function (output) {
  
        pickupSchedulesActiveVar = JSON.parse(output);
        setInterval(picukupSchedulesActive, 10000);
        setInterval(moveActivePickupScheduleMarkerAjax, 10000);
    });

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    var markers = [];
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();
        if (places.length === 0) {
            return;
        }
        // Clear out the old markers.
        markers.forEach(function (marker) {
            marker.setMap(null);
        });
        markers = [];

        // For each place, get the icon, name and location.
        var bounds = new google.maps.LatLngBounds();
        places.forEach(function (place) {
            var icon = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25)
            };

            // Create a marker for each place.
            markers.push(new google.maps.Marker({
                map: map,
                icon: icon,
                title: place.name,
                position: place.geometry.location
            }));

            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
}

function picukupSchedulesActive(handleData) {

    $.ajax({
        method: 'GET',
        url: '/grabmd/commons',
        data: {url: '/pickupschedule/future', body: '', accept: 'json', content: 'plain'}

    }).done(function (response) {

        pickupSchedulesActiveVar = JSON.parse(response);
        handleData(response);

    }).fail(function () {
//        alert("Failed");
        console.log("Ajax query failed on /pickupschedule/future");
        document.getElementById("tracker-status").innerHTML = 'LIVE SCHEDULES &nbsp;&nbsp;&nbsp;<small class="label label-danger">OFFLINE</small>';
        document.getElementById('shceduled-vehicle-list').innerHTML = '<div class = "text-center pt-20 txt-light-gray text-uppercase text-sm"> - server offline - </div>';
    });
}

function futurePickup() {

    $.ajax({
        method: 'GET',
        url: '/grabmd/commons',
        data: {url: '/pickup/future', body: '', accept: 'json', content: 'plain'}

    }).done(function (response) {
        var pickup = JSON.parse(response);
        var list = document.getElementById('shceduled-vehicle-list');

        if ($("#shceduled-vehicle-list li").length !== pickup.length) {
            $("#shceduled-vehicle-list").empty();
        }

        for (var i = 0; i < pickup.length; i++) {
            if ($('#id_' + pickup[i].id).length > 0)
                break;
            //create li element per scheduled vehicle
            var entry = document.createElement('li');
            entry.id = 'id_' + pickup[i].id;
            entry.className = 'tracker-menu-scheduled-vehicles';
            entry.innerHTML = '<a href="#"><i class="fa fa-plane" aria-hidden="true"></i>' + pickup[i].flightNo + ' <br> ' + pickup[i].flightDateTime + ' </a>';
            //append li element to ul
            list.appendChild(entry);
        }
    }).fail(function () {
//        alert("Failed");
        console.log("Ajax query failed on /pickup/future");
    });
}


var markers = new Array();
var markersUnlisted = new Array();
var path = [];
var last_clicked = null;
var animated_marker = null;
var imeiMap = new Map();
var imeiUnscheduled = new Map();

function moveActivePickupScheduleMarkerAjax() {

    $.ajax({
        method: 'POST',
        url: '/grabmd/commons',
        data: {url: '/location/all', body: '', accept: 'json', content: 'plain'}
    }).done(function (response) {
        moveActivePickupScheduleMarker(response);
    }).fail(function () {
        console.log("Ajax query failed on /location/all");
        document.getElementById("tracker-status").innerHTML = 'LIVE SCHEDULES &nbsp;&nbsp;&nbsp;<small class="label label-danger">OFFLINE</small>';
        document.getElementById('shceduled-vehicle-list').innerHTML = '<div class = "text-center pt-20 txt-light-gray text-uppercase text-sm"> - server offline - </div>';
    });
}


function moveActivePickupScheduleMarker(response) {

    futurePickup();
    document.getElementById("tracker-status").innerHTML = 'LIVE SCHEDULES &nbsp;&nbsp;&nbsp;<small class="label label-success">ONLINE</small>';
    var obj = JSON.parse(response);
    var count = 0;
    var unscheduledCount = 0;
    imeiMap = {};
    imeiUnscheduled = {};
    imeiMap = new Map();
    imeiUnscheduled = new Map();

    for (var i = 0; i < obj.length; i++) {
        for (var a = 0; a < pickupSchedulesActiveVar.length; a++) {
            /**
             * Check if active imei's exist on active pickup schedule
             */
            if (pickupSchedulesActiveVar[a].vehicleDriver.vehicle.trackerImei === obj[i].imie) {
                imeiMap.set(count, obj[i]);
                count++;
                break;
            }
        }
    }

    for (var i = 0; i < obj.length; i++) {
        if (imeiMap.size !== 0) {
            for (var a = 0; a < imeiMap.size; a++) {
                if (obj[i].imie !== imeiMap.get(a).imie) {
                    imeiUnscheduled.set(unscheduledCount, obj[i]);
                    unscheduledCount++;
                }
            }
        } else {
            imeiUnscheduled.set(unscheduledCount, obj[i]);
            unscheduledCount++;
        }
    }

    if (markers.length !== imeiMap.size) {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = new Array();
    }

    if (markersUnlisted.length !== imeiUnscheduled.size) {
        for (var i = 0; i < markersUnlisted.length; i++) {
            markersUnlisted[i].setMap(null);
        }
        markersUnlisted = new Array();
    }

    for (var i = 0; i < imeiMap.size; i++) {
        if (markers.length < imeiMap.size) {
            setMarker(i);
        } else {
            updateMarkerPosition(i);
        }
    }

    for (var i = 0; i < imeiUnscheduled.size; i++) {
        if (markersUnlisted.length < imeiUnscheduled.size) {
            setUnlistedVehicleMarker(i);
        } else {
            updateUnlistedMarkerPosition(i);
        }
    }

    function setMarker(index) {
        var location = {lat: imeiMap.get(index).latitude, lng: imeiMap.get(index).longitude};
        var imei = imeiMap.get(index).imie;
        var icon;
        if (imeiMap.get(index).speed > 1) {
            icon = image_vehicle_move;
        } else {
            icon = image_vehicle_stopped;
        }
        markers[index] = new google.maps.Marker({
            map: map,
            draggable: false,
            animation: google.maps.Animation.NONE,
            position: location,
            icon: icon,
            title: imei,
            id: imei
        });

        google.maps.event.addListener(markers[index], "dblclick", function () {

            if (last_clicked === null) {
                last_clicked = markers[index];
                map.setZoom(15);
                map.setCenter(markers[index].getPosition());
                $('.control-sidebar').addClass('control-sidebar-open');
                /*slideToggle();*/
            } else if (last_clicked === markers[index]) {
                $('.control-sidebar').addClass('control-sidebar-open');
                /*slideToggle();*/
            } else {
                $('.control-sidebar').addClass('control-sidebar-open');
                last_clicked = markers[index];

                map.setZoom(17);
                map.setCenter(markers[index].getPosition());
                /*slideToggle();*/
            }
            //set vehicle live tracking status to slide panel when marker is clicked

            /*markers[index].setIcon(image_on_click);*/
        });

        google.maps.event.addListener(markers[index], "click", function () {

            var state = $(this).data('state');
            state = !state;
            for (var i = 0; i < markers.length; i++) {
                if (imeiMap.get(i).speed > 1) {
                    markers[i].setIcon(image_vehicle_move);
                } else {
                    markers[i].setIcon(image_vehicle_stopped);
                }
            }
            if (state) {
                last_clicked = this.id;
                for (var i = 0; i < markers.length; i++) {
                    if (markers[i] !== this) {
                        $(markers[i]).data('state', false);
                    }
                }
                displayPickupScheduleOnMap(imeiMap.get(index).imie);
                $('.control-sidebar').addClass('control-sidebar-open');
                displayScheduledCrew(this.id);
                if (imeiMap.get(index).speed > 1) {
                    markers[index].setIcon(image_vehicle_move_selected);
                } else {
                    markers[index].setIcon(image_vehicle_stopped_selected);
                }
            } else {
                removePickupMarkers();
                toggleDetailMenu();
                if (imeiMap.get(index).speed > 1) {
                    markers[index].setIcon(image_vehicle_move);
                } else {
                    markers[index].setIcon(image_vehicle_stopped);
                }
            }
            $(this).data('state', state);
            //set vehicle live tracking status to slide panel when marker is clicked

        });
    }

    function setUnlistedVehicleMarker(index) {

        var location = {lat: imeiUnscheduled.get(index).latitude, lng: imeiUnscheduled.get(index).longitude};
        var imei = imeiUnscheduled.get(index).imie;

        var icon;
        if (imeiUnscheduled.get(index).speed > 1) {
            icon = image_unlisted_vehicle_move;
        } else {
            icon = image_unlisted_vehicle_stopped;
        }

        markersUnlisted[index] = new google.maps.Marker({
            map: map,
            draggable: false,
            animation: google.maps.Animation.NONE,
            position: location,
            icon: icon,
            title: imei,
            id: imei
        });

        google.maps.event.addListener(markersUnlisted[index], "click", function () {
            alert(this.id);
        });
    }

    function updateMarkerPosition(index) {

        var location = {lat: imeiMap.get(index).latitude, lng: imeiMap.get(index).longitude};
        markers[index].setPosition(location);
        var currentIcon = markers[index].getIcon();
        if (currentIcon === image_vehicle_move_selected || currentIcon === image_vehicle_stopped_selected) {

            if (imeiMap.get(index).speed > 1) {
                markers[index].setIcon(image_vehicle_move_selected);
            } else {
                markers[index].setIcon(image_vehicle_stopped_selected);
            }
        } else {
            if (imeiMap.get(index).speed > 1) {
                markers[index].setIcon(image_vehicle_move);
            } else {
                markers[index].setIcon(image_vehicle_stopped);
            }
        }
    }
    function updateUnlistedMarkerPosition(index) {

        var location = {lat: imeiUnscheduled.get(index).latitude, lng: imeiUnscheduled.get(index).longitude};
        markersUnlisted[index].setPosition(location);

        if (imeiUnscheduled.get(index).speed > 1) {
            markersUnlisted[index].setIcon(image_unlisted_vehicle_move);
        } else {
            markersUnlisted[index].setIcon(image_unlisted_vehicle_stopped);
        }
    }

    if (trackerListShowVehicleStatus) {
        setUnscheduledMarkersVisible(true);
    } else {
        setUnscheduledMarkersVisible(false);
    }
}

function displayScheduledCrew(imei) {
    var list = document.getElementById('shceduled-crew-list');
    $("#shceduled-crew-list").empty();
    for (var i = 0; i < pickupSchedulesActiveVar.length; i++) {
        if (pickupSchedulesActiveVar[i].vehicleDriver.vehicle.trackerImei === imei) {
            if ($('#id_' + pickupSchedulesActiveVar[i].enduserId.id).length > 0)
                break;
            /*create li element per scheduled crew*/
            var entry = document.createElement('div');
            entry.id = 'id_' + pickupSchedulesActiveVar[i].enduserId.id;
            entry.className = 'tracker-scheduled-crew label-overide margin-none tracker-title-semi';
            entry.innerHTML = '<div class="col-md-12">' +
                    '<label class="label-overide margin-none tracker-title-13 pt-10">' +
                    '' + pickupSchedulesActiveVar[i].enduserId.prefix + " " + pickupSchedulesActiveVar[i].enduserId.firstName + " " + pickupSchedulesActiveVar[i].enduserId.lastName + ' :<br>ETA :' +
                    '</label>' +
                    '</div>';
            //append li element to ul
            list.appendChild(entry);
        }
    }
}

var pickupMarkers = new Array();
var pickupPoliline = [];
function displayPickupScheduleOnMap(imie) {

    removePickupMarkers();
    pickupPoliline = [];
    if (trackerListShowScheduleStatus) {
        console.log(pickupSchedulesActiveVar);
        for (var i = 0; i < pickupSchedulesActiveVar.length; i++) {
            if (pickupSchedulesActiveVar[i].vehicleDriver.vehicle.trackerImei === imie) {
                addPickupMarkers(i, pickupSchedulesActiveVar[i].pickupIndex);
            }
        }
        drawPickupPoliline();
    }
    var pickupinfowindow;

    function addPickupMarkers(index, pindex) {

        var location = pickupSchedulesActiveVar[index].pickupPoint;
        var splt = location.split(",");
        var latlng = {lat: parseFloat(splt[0]), lng: parseFloat(splt[1])};

        pickupPoliline.push({
            "lat": parseFloat(splt[0]),
            "lng": parseFloat(splt[1])
        });

//        alert();
        pickupMarkers[index] = new google.maps.Marker({
            map: map,
            draggable: false,
            animation: google.maps.Animation.NONE,
            position: latlng,
            icon: image_pickup_marker_pilot,
            title: pindex.toString(),
            id: pindex
        });

        var ETA;
        pickupinfowindow = new google.maps.InfoWindow({
        });

        google.maps.event.addListener(pickupMarkers[index], "click", function () {
            if (this.id === pickupSchedulesActiveVar[index].pickupIndex) {
                pickupinfowindow.setContent(infor(pickupSchedulesActiveVar[index], ETA));
                pickupinfowindow.open(map, this);
            }
        });

        function infor(schedule, ETA) {
            console.log(schedule);
            return '<div id="content" style="background:#ffffff">' +
                    '<h3 class="box-title text-uppercase txt-light-gray txt-light-weight2" id="firstHeading" class="firstHeading">' + schedule.enduserId.designation.shortName + ". " + schedule.enduserId.firstName + " " + schedule.enduserId.lastName + '</h3>' +
                    '<div id="bodyContent">' +
                    '<p><b>Scheduled Pickup Date/Time :</b> ' + new Date(schedule.pickupDatetime).toLocaleString() + '&nbsp <b>Pickup Index : </b>' + schedule.pickupIndex + '</p>' +
                    '<p><b>ETA :</b> ' + new Date(schedule.pickupDatetime).toLocaleString() + '&nbsp <b>ETA : </b>' + ETA + '</p>' +
                    '</div>' +
                    '</div>';
        }
    }
}

var pickupPath;

function drawPickupPoliline() {
    pickupPath = new google.maps.Polyline({
        path: pickupPoliline,
        geodesic: true,
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 2
    });
    pickupPath.setMap(map);
}

function removePickupPolyline() {
    if (typeof pickupPath !== 'undefined') {
        pickupPath.setMap(null);
    }
}

function removePickupMarkers() {

    for (var i = 0; i < pickupMarkers.length; i++) {
        pickupMarkers[i].setMap(null);
    }
    removePickupPolyline();
}

$('#tracking-detail-panel-close-id').on('click', function () {
    toggleDetailMenu();
});


function toggleDetailMenu() {
    $('.control-sidebar').removeClass('control-sidebar-open');
}

function transportPeriphery() {
    var latlng = new google.maps.LatLng(7.165721, 79.882978);
    var latlng2 = new google.maps.LatLng(6.910938, 79.881556);

    var sunCircle = {
        strokeColor: "#c3fc49",
        strokeOpacity: 0.8,
        strokeWeight: 0,
        fillColor: "#334abf",
        fillOpacity: 0.25,
        map: map,
        center: latlng,
        radius: 20000 // in meters
    };
    var sunCircle2 = {
        strokeColor: "#c3fc49",
        strokeOpacity: 0.8,
        strokeWeight: 0,
        fillColor: "#334abf",
        fillOpacity: 0.25,
        map: map,
        center: latlng2,
        radius: 20000 // in meters
    };
    cityCircle = new google.maps.Circle(sunCircle);
    cityCircle2 = new google.maps.Circle(sunCircle2);

}

function setAllMarkerSelecredFalse(condition) {
    for (var i = 0; i < markersUnlisted.length; i++) {
        markersUnlisted[i].setVisible(condition);
    }
}

function removeTransportPeriphery() {
    cityCircle.setMap(null);
    cityCircle2.setMap(null);
}

function setUnscheduledMarkersVisible(condition) {
    for (var i = 0; i < markersUnlisted.length; i++) {
        markersUnlisted[i].setVisible(condition);
    }
}

