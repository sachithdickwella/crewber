/* global last_clicked */

var trackerListShowVehicleStatus;
var trackerListShowScheduleStatus;
var trackerListShowAreaStatus;

$(document).ready(function () {

    $('#tracker-list-show-vehicle').on('click', function () {
        // retrieve current state, initially undefined
        trackerListShowVehicleStatus = $(this).data('state');
        // toggle the state - first click will make this "true"
        trackerListShowVehicleStatus = !trackerListShowVehicleStatus;
        // do your stuff
        if (trackerListShowVehicleStatus) {
            setUnscheduledMarkersVisible(true);
            $(this).addClass('tracker-list-active');
            $(this).removeClass('tracker-list-inactive');
            // do this (1st click, 3rd click, etc)
        } else {
            // do that
            setUnscheduledMarkersVisible(false);
            $(this).removeClass('tracker-list-active');
            $(this).addClass('tracker-list-inactive');
        }
        $(this).data('state', trackerListShowVehicleStatus);
    });

    $('#tracker-list-show-schedule').on('click', function () {
        // retrieve current state, initially undefined
        trackerListShowScheduleStatus = $(this).data('state');

        // toggle the state - first click will make this "true"
        trackerListShowScheduleStatus = !trackerListShowScheduleStatus;
        // do your stuff
        if (trackerListShowScheduleStatus) {
            displayPickupScheduleOnMap(last_clicked);
            $(this).addClass('tracker-list-active');
            $(this).removeClass('tracker-list-inactive');
            // do this (1st click, 3rd click, etc)
        } else {
            // do that
            removePickupMarkers();
            $(this).removeClass('tracker-list-active');
            $(this).addClass('tracker-list-inactive');
        }
        $(this).data('state', trackerListShowScheduleStatus);
    });

    $('#tracker-list-show-area').on('click', function () {
        // retrieve current state, initially undefined
        trackerListShowAreaStatus = $(this).data('state');
        // toggle the state - first click will make this "true"
        trackerListShowAreaStatus = !trackerListShowAreaStatus;
        // do your stuff
//        alert(trackerListShowAreaStatus);
        if (trackerListShowAreaStatus) {
            $(this).addClass('tracker-list-active');
            $(this).removeClass('tracker-list-inactive');
            transportPeriphery();

            // do this (1st click, 3rd click, etc)
        } else {
            // do that
            $(this).removeClass('tracker-list-active');
            $(this).addClass('tracker-list-inactive');
            removeTransportPeriphery();
        }
        $(this).data('state', trackerListShowAreaStatus);
    });



});