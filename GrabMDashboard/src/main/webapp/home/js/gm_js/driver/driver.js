$(document).on('ready', function () {
  
    $(".select2").select2();
    
    $("#drivers_upload_file").fileinput({
        showPreview: false,
        showUpload: false,
        allowedFileExtensions: ["xlsx", "csv", "xls"],
        elErrorContainer: "#errorBlockdriver"
    });
    
    $("#driver_rg_pro_pic").fileinput({
    });
});