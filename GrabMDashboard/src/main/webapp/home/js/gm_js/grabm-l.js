$(document).ready(function () {

    //change name
    $("#edit-profile-name").click(function () {
        $("#profile_change_name_form")[0].reset();

        $("#profile_name_change").removeClass("hidden");
        $("#profile_name_show").addClass(" hidden");
    });

    $("#prof-name-cancel-btn").click(function () {
        $("#profile_change_name_form")[0].reset();

        $("#profile_name_change").addClass("hidden");
        $("#profile_name_show").removeClass("hidden");

    });

    //change username
    $("#edit-profile-username").click(function () {
        $("#profile_change_username_form")[0].reset();


        $("#profile_username_change").removeClass("hidden");
        $("#profile_username_show").addClass(" hidden");

    });

    $("#prof-username-cancel-btn").click(function () {
        $("#profile_change_username_form")[0].reset();


        $("#profile_username_change").addClass("hidden");
        $("#profile_username_show").removeClass("hidden");

    });

    //change Password
    $("#edit-profile-password").click(function () {
        $("#profile_change_password_form")[0].reset();


        $("#profile_password_change").removeClass("hidden");
        $("#profile_password_show").addClass(" hidden");

    });

    $("#prof-password-cancel-btn").click(function () {
        $("#profile_change_password_form")[0].reset();


        $("#profile_password_change").addClass("hidden");
        $("#profile_password_show").removeClass("hidden");

    });
    
    //change contact
    
    $("#edit-profile-contact").click(function () {
        $("#profile_change_contact_form")[0].reset();
     

        $("#profile_contact_change").removeClass("hidden");
        $("#profile_contact_show").addClass(" hidden");

    });

    $("#prof-contact-cancel-btn").click(function () {
        $("#profile_change_contact_form")[0].reset();
      
        $("#profile_contact_change").addClass("hidden");
        $("#profile_contact_show").removeClass("hidden");

    });
});

function setModelTitle(){
    
//    titleID.html = title;
   
    document.getElementById("view-vehicle-detail-model-title").innerHTML = 'setTitle';
}