//Bootstrap validator form variable

var bvalidator_new_admin_registration_form = $('#new_admin_registration_form');
var bvalidator_new_user_registration_form = $('#new_user_registration_form');
var bvalidator_new_vehicle_registration_form = $('#new_vehicle_registration_form');
var bvalidator_new_driver_registration_form = $('#new_driver_registration_form');
var bvalidator_add_new_profile_form = $('#add_new_profile_form');
var bvalidator_add_new_ui_pages_form = $('#add_new_ui_pages_form');
var bvalidator_ui_profile_registry_form = $('#ui_profile_registry_form');
var bvalidator_vehicle_details_area_group_form = $('#vehicle_details_area_group_form');
var bvalidator_vehicle_details_fuel_type_form = $('#vehicle_details_fuel_type_form');

var bvalidator_admin_activate_account_form = $('#admin_activate_account_form');
var bvalidator_admin_login_form = $('#admin_login_form');

//validate new admin registration form
bvalidator_new_admin_registration_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        it_admin_rg_first_name: {
            validators: {
                notEmpty: {
                    message: 'First Name cannot be empty'
                }
            }
        },
        it_admin_rg_last_name: {
            validators: {
                notEmpty: {
                    message: 'Last Name cannot be empty'
                }
            }
        },
        it_admin_rg_mobile_number: {
            validators: {
                notEmpty: {
                    message: 'Mobile Number is required and cannot be empty'
                },
                stringLength: {
                    min: 9,
                    message: 'Length must be 9 charactors'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: 'contact number can only contain numeric values'
                }

            }
        },
        it_admin_rg_email: {
            validators: {
                notEmpty: {
                    message: 'Email address is required and cannot be empty'
                },
                emailAddress: {
                    message: 'Email address is not a valid'
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

//validate new user registration form
bvalidator_new_user_registration_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        it_user_rg_first_name: {
            validators: {
                notEmpty: {
                    message: 'First Name cannot be empty'
                }
            }
        },
        it_user_rg_last_name: {
            validators: {
                notEmpty: {
                    message: 'Last Name cannot be empty'
                }
            }
        },     
        it_user_rg_member_id: {
            validators: {
                notEmpty: {
                    message: 'Member ID cannot be empty'
                }
            }
        },
        it_user_rg_mobile_number: {
            validators: {
                notEmpty: {
                    message: 'Mobile Number is required and cannot be empty'
                },
                stringLength: {
                    min: 9,
                    message: 'Length must be 9 charactors'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: 'contact number can only contain numeric values'
                }

            }
        },
        it_user_rg_email: {
            validators: {
                notEmpty: {
                    message: 'Email address is required and cannot be empty'
                },
                emailAddress: {
                    message: 'Email address is not a valid'
                }
            }
        },
        it_user_rg_address_1: {
            validators: {
                notEmpty: {
                    message: 'Address is required and cannot be empty'
                }
            }
        },
        it_user_rg_address_2: {
            validators: {
                notEmpty: {
                    message: 'Address is required and cannot be empty'
                }
            }
        },
        it_user_rg_city: {
            validators: {
                notEmpty: {
                    message: 'City required and cannot be empty'
                }
            }
        },
        it_user_rg_zip_code: {
            validators: {
                notEmpty: {
                    message: 'Zip Code required and cannot be empty'
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

//validate new Vehicle registration form
bvalidator_new_vehicle_registration_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        vehicle_rg_registration_number: {
            validators: {
                notEmpty: {
                    message: 'Registration number cannot be empty'
                }
            }
        },
        vehicle_rg_vehicle_brand: {
            validators: {
                notEmpty: {
                    message: 'vehicle brand cannot be empty'
                }
            }
        },
        vehicle_rg_min_passengers: {
            validators: {
                notEmpty: {
                    message: 'Minimum passengers cannot be empty'
                }
            }
        },
        vehicle_rg_max_passengers: {
            validators: {
                notEmpty: {
                    message: 'Maximum passengers cannot be empty'
                }
            }
        },
        vehicle_rg_color_1: {
            validators: {
                notEmpty: {
                    message: 'Color1 cannot be empty'
                }
            }
        },
        vehicle_rg_mileage: {
            validators: {
                notEmpty: {
                    message: 'Mileage cannot be empty'
                }
            }
        },
        vehicle_rg_tracker_imei: {
            validators: {
                notEmpty: {
                    message: 'IMEI cannot be empty'
                }
            }
        },
        vehicle_rg_mobile_number: {
            validators: {
                notEmpty: {
                    message: 'Mobile Number cannot be empty'
                },
                stringLength: {
                    min: 9,
                    message: 'Length must be 9 charactors'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: 'Mobile number can only contain numeric values'
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


//validate Driver registration form
bvalidator_new_driver_registration_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        driver_rg_first_name: {
            validators: {
                notEmpty: {
                    message: 'First Name cannot be empty'
                }
            }
        },
        driver_rg_last_name: {
            validators: {
                notEmpty: {
                    message: 'Last Name cannot be empty'
                }
            }
        },
        driver_rg_nic: {
            validators: {
                notEmpty: {
                    message: 'NIC cannot be empty'
                },
                stringLength: {
                    min: 10,
                    message: 'Length must be 10 charactors'
                }
            }
        },
        driver_rg_mobile_number_1: {
            validators: {
                notEmpty: {
                    message: 'Mobile Number is required and cannot be empty'
                },
                stringLength: {
                    min: 9,
                    message: 'Length must be 9 charactors'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: 'contact number can only contain numeric values'
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


//validate Profile registration form
bvalidator_add_new_profile_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        ui_profile_rg_name: {
            validators: {
                notEmpty: {
                    message: 'Profile name cannot be empty'
                }
            }
        },
        ui_profile_rg_note: {
            validators: {
                notEmpty: {
                    message: 'Note cannot be empty'
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

//validate Ui-page registration form
bvalidator_add_new_ui_pages_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        ui_web_page_rg_file_name: {
            validators: {
                notEmpty: {
                    message: 'File name cannot be empty'
                }
            }
        },
        ui_web_page_rg_mapping_url: {
            validators: {
                notEmpty: {
                    message: 'URL cannot be empty'
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


//validate Ui-profile registration form
bvalidator_ui_profile_registry_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        ui_web_page_rg_profile: {
            validators: {
                notEmpty: {
                    message: 'You must select profile'
                }
            }
        },
        ui_web_page_rg_web_pages: {
            validators: {
                notEmpty: {
                    message: 'You must select ui pages'
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

//validate vehicle detail area group form
bvalidator_vehicle_details_area_group_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        vehicle_details_area_group_name: {
            validators: {
                notEmpty: {
                    message: 'Area group name can not be empty'
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

//validate vehicle detail area group form
bvalidator_vehicle_details_fuel_type_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        vehicle_details_fuel_type_short_name: {
            validators: {
                notEmpty: {
                    message: 'Long name can not be empty'
                }
            }
        },
        vehicle_details_fuel_type_long_name: {
            validators: {
                notEmpty: {
                    message: 'Short name can not be empty'
                },
                regexp: {
                    regexp: /^[a-zA-Z]*$/,
                    message: 'Short name can not contain numeric values'
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


//admin activaea account form
bvalidator_admin_activate_account_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        xt_adm_rg_user_name: {
            validators: {
                notEmpty: {
                    message: 'User name cannot be empty'
                }
            }
        },
        xt_adm_rg_temp_password: {
            validators: {
                notEmpty: {
                    message: 'Temporary password cannot be empty'
                }
            }
        },
        xt_adm_rg_password: {
            validators: {
                notEmpty: {
                    message: 'Password cannot be empty'
                }
            }
        },
        xt_adm_rg_repeat_password: {
            validators: {
                notEmpty: {
                    message: 'Repeat password cannot be empty'
                },
                identical: {
                    field: 'xt_adm_rg_password',
                    message: 'Repeated password is not matching'
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


//admin activaea account form
bvalidator_admin_login_form.bootstrapValidator({
    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
    feedbackIcons: {
//        valid: 'glyphicon glyphicon-ok',
//        invalid: 'glyphicon glyphicon-remove',
//        validating: 'glyphicon glyphicon-refresh'
    }, fields: {
        user: {
            validators: {
                notEmpty: {
                    message: 'User name cannot be empty'
                }
            }
        },
        pass: {
            validators: {
                notEmpty: {
                    message: 'Password cannot be empty'
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


//Reset admin registration form
$('#new_admin_registration_form_cancel').on('click', function () {

    document.getElementById("new_admin_registration_form").reset();
    bvalidator_new_admin_registration_form.bootstrapValidator('resetForm', true);

});

//reset user ragistration form
$('#new_user_registration_form_cancel').on('click', function () {

    document.getElementById("new_user_registration_form").reset();
    bvalidator_new_user_registration_form.bootstrapValidator('resetForm', true);
    resetMap();
});

//reset Vehicle ragistration form
$('#new_vehicle_registration_form_cancel').on('click', function () {

    $('.vehicle_rg_color_background_1').removeAttr('style');
    $('.vehicle_rg_color_background_2').removeAttr('style');
    document.getElementById("new_vehicle_registration_form").reset();
    bvalidator_new_vehicle_registration_form.bootstrapValidator('resetForm', true);

});


//reset Driver ragistration form
$('#new_driver_registration_form_cancel').on('click', function () {

    document.getElementById("new_driver_registration_form").reset();
    bvalidator_new_driver_registration_form.bootstrapValidator('resetForm', true);

});

//reset Profile ragistration form
$('#add_new_profile_form_cancel').on('click', function () {

    document.getElementById("add_new_profile_form").reset();
    bvalidator_add_new_profile_form.bootstrapValidator('resetForm', true);

});

//reset Ui-page ragistration form
$('#add_new_ui_pages_form_cancel').on('click', function () {

    document.getElementById("add_new_ui_pages_form").reset();
    bvalidator_add_new_ui_pages_form.bootstrapValidator('resetForm', true);

});

//reset Ui-page ragistration form
$('#ui_profile_registry_form_cancel').on('click', function () {

    document.getElementById("ui_profile_registry_form").reset();
    bvalidator_ui_profile_registry_form.bootstrapValidator('resetForm', true);

});

//reset vehicle details area group form
$('#vehicle_details_area_group_cancel').on('click', function () {

    document.getElementById("vehicle_details_area_group_form").reset();
    bvalidator_vehicle_details_area_group_form.bootstrapValidator('resetForm', true);

});

//reset vehicle details fuel typw form
$('#vehicle_details_fuel_type_cancel').on('click', function () {

    document.getElementById("vehicle_details_fuel_type_form").reset();
    bvalidator_vehicle_details_fuel_type_form.bootstrapValidator('resetForm', true);

});

//reset admin account activation form
$('#admin_acc_activation_cancel').on('click', function () {

    document.getElementById("admin_activate_account_form").reset();
    bvalidator_admin_activate_account_form.bootstrapValidator('resetForm', true);

});
