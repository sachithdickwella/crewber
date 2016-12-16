/* global crewJSON */

$(document).ready(function () {

    var table = $('#crew_profile_to_confirm_table').DataTable();
    table.clear().draw();
    draw_table(crewJSON);
});

var draw_table = function (response) {
    var table = "<table id='crew_profile_to_confirm_table' class='table table-bordered table-striped table-hover' cellspacing='0'>"
            + "<thead>"
            + "<tr class='th-txt'>"
            + "<th>Member id</th><th>Title</th><th>Name</th><th>select</th>"
            + "</tr>"
            + "</thead>"
            + "<tbody>";
    for (var idx = 0; idx < response.length; idx++) {
        table += "<tr class='th-txt' id='" + "row" + idx + "'>"
                + "<td>" + response[idx].memberId + "</td>"
                + "<td>" + response[idx].title + "</td>"
                + "<td>" + response[idx].firstName + ' ' + response[idx].lastName + "</td>"
                + "<td><a class='link-info text-sm select-btn' id='" + response[idx].ID + "' style='cursor:pointer'>VIEW</a></td>"
                + "</tr>";
    }
    table += "</tbody>"
            + "</table>";
    $('#crew-profile-to-confirm-table-section').html(table);
    table = $("#crew_profile_to_confirm_table").DataTable();

    $('#crew_profile_to_confirm_table tbody').on('click', 'a.select-btn', function () {
        var tr = $(this).parents('tr');
        var id = table.row(tr).data();
        set_data_modal(id[0]);
    });
};

var set_data_modal = function (id) {

    $('#end-user-view-modal').modal('show');
    for (var i = 0; i < crewJSON.length; i++) {
        if (id === crewJSON[i].memberId) {
            $('#profile-view-crew-member').text(crewJSON[i].title + '. ' + crewJSON[i].firstName + ' ' + crewJSON[i].lastName);
        }
    }
};