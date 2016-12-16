//Bootstrap validator form variable

var bvalidator_add_new_schedule_form = $('#add_new_schedule_form');

//Form Validation---------------------------------------------------------------------------
//validate Add Schedule form
bvalidator_add_new_schedule_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        add_schedule_flight_number: {
            validators: {
                notEmpty: {
                    message: 'Flight Number can not be empty'
                }
            }
        },
        add_schedule_depature_date_time: {
            validators: {
                notEmpty: {
                    message: 'Date/Time cannot be empty'
                }
            }
        }
    }
}).on('submit', function (e) {
    if (e.isDefaultPrevented()) {
        // handle the invalid form...

    } else {
        // everything looks good!
    }
});

//reset forms----------------------------------------------------------------------------------------------


//Reset Add Schedule  form
$('#add_new_schedule_form_cancel').on('click', function () {

    document.getElementById("add_new_schedule_form").reset();
    bvalidator_add_new_schedule_form.bootstrapValidator('resetForm', true);

});


//reset schedule-user  form
$('#schedule_user_add_cancel').on('click', function () {

    document.getElementById("shcedule-user-form").reset();
    var num = $('.clonedInput').length;
    // how many "duplicatable" input fields we currently have
    for (var i = 2; i <= num; i++) {
        $('#entry' + i).slideUp(function () {
            $(this).remove();
            // if only one element remains, disable the "remove" button
        });
    }
    $('#btnDel').addClass('hidden');
    // enable the "add" button
    $('#btnAdd').attr('disabled', false).prop('value', "Add Transit");
    return false; // Removes the last section you added

});