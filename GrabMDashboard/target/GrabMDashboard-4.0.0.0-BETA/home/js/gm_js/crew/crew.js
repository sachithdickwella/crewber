$(document).ready(function () {

    $("#crew_members_upload_file").fileinput({
        showPreview: false,
        showUpload: false,
        allowedFileExtensions: ["xlsx", "csv", "xls"],
        elErrorContainer: "#errorBlockCrew"
    });
    {
        loadDesignations();
        loadPickupAreas();
    }
});
var addDesignation = function () {
    $.ajax({
        url: "/grabmd/designationweb",
        method: "POST",
        data: {
            crew_member_designation_long_name: $('#crew_member_designation_long_name').val(),
            crew_member_designation_short_name: $('#crew_member_designation_short_name').val(),
            crew_member_type: $('#crew_member_type').val(),
            crew_member_type_reporting_time: $('#crew_member_type_reporting_time').val()
        }
    }).done(function (response) {
        $('#user-designation-stat-respond').removeClass('hidden');
        var stat = parseInt(response);
        if (stat > 0) {

            loadDesignations();
        } else {
            /**
             * Error validation goes HERE
             **/
            alert('else');
            var status;
            if (stat === -1) {
                status = 'Failed';
            } else if (stat === -4) {
                status = 'Failed';
            } else if (stat === -2) {
                status = 'Failed';
            } else {
                status = 'Failed';
            }
        }
    }).fail(function () {
        alert("Failed");
    });
};
var loadDesignations = function () {
    $.ajax({
        url: '/grabmd/commons',
        method: "GET",
        data: {url: '/designation/all', body: '', accept: 'json', content: 'plain'}
    }).done(function (result) {
        reset();
        var objs = JSON.parse(result);
        {
            $.each(objs, function (i, obj) {
                $('#it_user_rg_designaion').append($('<option>', {
                    value: obj.id,
                    text: obj.longName + " (" + obj.shortName + ")"
                }));
            });
        }
        {
            /**
             * View code goes here.
             **/
            var designation_list = $('#list-group-designations');
            designation_list.empty();
            objs.forEach(function (obj) {
                var entry = document.createElement('li');
                entry.className = 'list-group-item';
                entry.id = obj.id;
                entry.innerHTML = '<span class=" pull-right"><a style="cursor:pointer" class = "link-black text-sm text-uppercase designation-edit"><i class = "fa fa-pencil-square-o margin-r-5"></i>EDIT</a></span>' +
                        '' + obj.shortName + '';
                designation_list.append(entry);
            });

            $('#list-group-designations li').on('click', 'a.designation-edit', function () {
                var tr = $(this).closest("li");
                set_selected(tr[0].id);
            });
        }
    });
};

var reset = function () {
//    $('#user_designation_form').reset();
};

var set_selected = function (id) {
    $.ajax({
        url: '/grabmd/commons',
        method: "GET",
        data: {url: '/designation/' + id + '', body: '', accept: 'json', content: 'plain'}
    }).done(function (result) {
        var obj = JSON.parse(result);
        /**
         * Set data to edit code goes here
         * */
    });
};

var addPickupArea = function () {
    $.ajax({
        url: "/grabmd/pickupareaweb",
        method: "POST",
        data: {
            user_area: $('#user_area').val(),
            user_area_first_pickup_time: $('#user_area_first_pickup_time').val()
        }
    }).done(function (response) {
        var stat = parseInt(response);
        if (stat > 0) {
            loadPickupAreas();
        } else {
            /**
             * Error validation goes HERE - Robz
             **/
        }
    });
};
var loadPickupAreas = function () {
    $.ajax({
        url: '/grabmd/commons',
        method: "GET",
        data: {url: '/pickuparea/all', body: '', accept: 'json', content: 'plain'}
    }).done(function (result) {
        var objs = JSON.parse(result);
        {
            $.each(objs, function (i, obj) {
                $('#user_pickup_location_permission_area').append($('<option>', {
                    value: obj.id,
                    text: obj.name
                }));
            });
        }
        {
            /**
             * View code goes here.
             **/
            var pickupArea_list = $('#pickup-area-list');
            pickupArea_list.empty();
            objs.forEach(function (obj) {
                var entry = document.createElement('li');
                entry.className = 'list-group-item';
                entry.id = obj.id;
                entry.innerHTML = '<span class=" pull-right"><a style="cursor:pointer" class = "link-black text-sm text-uppercase pickup-area-edit"><i class = "fa fa-pencil-square-o margin-r-5"></i>EDIT</a></span>' +
                        '' + obj.name + '';
                pickupArea_list.append(entry);
            });

            $('#pickup-area-list li').on('click', 'a.pickup-area-edit', function () {
                var tr = $(this).closest("li");
                set_selected(tr[0].id);
            });
        }
    });
};