$(document).ready(function () {
    $(".select2").select2();
    //color picker with addon
    $(".my-colorpicker2").colorpicker();
    $("#vehicle_list_upload_file").fileinput({
        showPreview: false,
        showUpload: false,
        allowedFileExtensions: ["xlsx", "csv", "xls"],
        elErrorContainer: "#errorBlockVehicle"
    });
});