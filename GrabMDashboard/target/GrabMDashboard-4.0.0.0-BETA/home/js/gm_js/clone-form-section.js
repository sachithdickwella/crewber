var maxPassengers = new Map(),
        firstEleValue;
$(function () {
    $('#btnAdd').click(function () {
        $("#tran_count").val(parseInt($("#tran_count").val()) + 1);
        $(".select_user_sch_vehicle").select2("destroy");
        $('.input_user_pickup_date_time').data("DateTimePicker");
//        $('.input_user_pickup_date_time_').datetimepicker('destroy');
        var num = $('.clonedInput').length, // Checks to see how many "duplicatable" input fields we currently have
                newNum = new Number(num + 1), // The numeric ID of the new input field being added, increasing by 1 each time
                newElem = $('#entry' + num).clone().attr('id', 'entry' + newNum).fadeIn('slow'); // create the new element via clone(), and manipulate it's ID using newNum value

        /*  This is where we manipulate the name/id values of the input inside the new, cloned element
         Below are examples of what forms elements you can clone, but not the only ones.
         There are 2 basic structures below: one for an H2, and one for form elements.
         To make more, you can copy the one for form elements and simply update the classes for its label and input.
         Keep in mind that the .val() method is what clears the element when it gets cloned. Radio and checkboxes need .val([]) instead of .val('').
         */

        //Scroll down to bottom

        $("html, body").animate({scrollTop: $(document).height()}, 1000);
        // H3 - section
        newElem.find('.heading-reference').attr('id', 'ID' + newNum + '_reference').attr('name', 'ID' + newNum + '_reference').html('Transit ' + (newNum - 1));

        // User Pickup Date/ Time
        newElem.find('.lable_user_pickup_date_time').attr('for', 'user_sch_pickup_date_time_' + newNum);
        newElem.find('.input_user_pickup_date_time').attr('id', 'user_sch_pickup_date_time_' + newNum).attr('name', 'user_sch_pickup_date_time_' + newNum).attr('placeholder', 'Time').val('');


        // User Select Vehicle
        newElem.find('.lable_pick_vehicle').attr('for', 'user_sch_pick_vehicle_' + newNum);
        newElem.find('.select_user_sch_vehicle').attr('id', 'user_sch_pick_vehicle_' + newNum).attr('name', 'user_sch_pick_vehicle_' + newNum).val('');

        //collapse button
        newElem.find('.btn-vehicle-details').attr('data-target', '#vehicle-details_' + newNum);
        newElem.find('.btn-vehicle-details').attr('aria-expanded', 'false');

        //collapse div 
        newElem.find('.div_vehicle_details').attr('id', 'vehicle-details_' + newNum);
        newElem.find('.div_vehicle_details').attr('class', 'col-md-12 pt-10 div_vehicle_details_ collapse');
        newElem.find('.div_vehicle_details').attr('aria-expanded', 'false');

        // User Selected Vehicle registration number
        newElem.find('.lable_user_sch_v_r_number').attr('id', 'user_sch_v_r_number_' + newNum);

        // User Selected Driver Name
        newElem.find('.lable_user_sch_driver_name').attr('id', 'user_sch_driver_name_' + newNum);

        // User Selected Vehicle Max passengers allowed Name
        newElem.find('.lable_user_sch_max_passengers_allowed').attr('id', 'user_sch_max_passengers_allowed_' + newNum);

        // User Selected Vehicle number of scheduled passengers
        newElem.find('.lable_user_sch_scheduled_passengers').attr('id', 'user_sch_scheduled_passengers_' + newNum);

        // User Selected Vehicle - driver note
        newElem.find('.lable_user_sch_note').attr('id', 'user_sch_note_' + newNum);

        // User Pickup Index
        newElem.find('.lable_user_sch_pickup_index').attr('for', 'user_sch_pickup_index_' + newNum);
        newElem.find('.input_user_sch_pickup_index').attr('id', 'user_sch_pickup_index_' + newNum).attr('name', 'user_sch_pickup_index_' + newNum).val('');

        // User Pickup Point
        newElem.find('.lable_user_sch_pickup_point').attr('for', 'user_sch_pickup_point_' + newNum);
        newElem.find('.input_user_sch_pickup_point').attr('id', 'user_sch_pickup_point_' + newNum).attr('name', 'user_sch_pickup_point_' + newNum).val('');

        // User Pickup Point
        newElem.find('.lable_user_sch_drop_off_point').attr('for', 'user_sch_drop_off_point_' + newNum);
        newElem.find('.input_user_sch_drop_off_point').attr('id', 'user_sch_drop_off_point_' + newNum).attr('name', 'user_sch_drop_off_point_' + newNum).val('');


        // Insert the new element after the last "duplicatable" input field
        $('#entry' + num).after(newElem);
        $('#ID' + newNum + '_title').focus();

        // Enable the "remove" button. This only shows once you have a duplicated section.
        $('#btnDel').removeClass('hidden');
        $('#btnDel').attr('disabled', false);
        
        $("#user_sch_max_passengers_allowed_" + newNum).text(firstEleValue);
        $("#user_sch_pick_vehicle_" + newNum).change(function () {
            var max = maxPassengers.get($(this).val().toString());
            $("#user_sch_max_passengers_allowed_" + newNum).text(max);
        });

        // Right now you can only add 4 sections, for a total of 5. Change '5' below to the max number of sections you want to allow.
        if (newNum == 4) {
            $('#btnAdd').attr('disabled', true).prop('value', "You've reached the limit"); // value here updates the text in the 'add' button when the limit is reached 
        }


        $(".select_user_sch_vehicle").select2({
            placeholder: "Select Vehicle",
            alowClear: true
        });

        $('.input_user_pickup_date_time').datetimepicker();
    });

    $('#btnDel').click(function () {
        // Confirmation dialog box. Works on all desktop browsers and iPhone.
        $("#tran_count").val(parseInt($("#tran_count").val()) - 1);
        var num = $('.clonedInput').length;
        // how many "duplicatable" input fields we currently have
        $('#entry' + num).slideUp('slow', function () {
            $(this).remove();
            // if only one element remains, disable the "remove" button
            if (num - 1 === 1)
                $('#btnDel').addClass('hidden');
            // enable the "add" button
            $('#btnAdd').attr('disabled', false).prop('value', "Add Transit");
        });

        return false; // Removes the last section you added
    });
    // Enable the "add" button
    $('#btnAdd').attr('disabled', false);
    // Disable the "remove" button
    $('#btnDel').attr('disabled', true);
});