<div class="row">
    <div class="col-md-12">
        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Crew Pickup Address | Permission</h3>
        </div>

        <form role="form" id="user_pickup_location_permission_form" method="POST">
            <div class="row">
                <div class="col-md-6">
                    <div class="row">
                        <div class="col-xs-12 margin-bottom-none pt-10">
                            <div class="">
                                <div class="box-header">
                                    <span class="text-uppercase txt-light-gray txt-light-weight2">Crew List to confirm Requests</span>
                                </div>

                                <div class="col-md-12 margin-bottom margin-top-10">
                                    <ul class="nav nav-pills small theme-bg-clr-dark">
                                        <li class=""><a style="cursor: pointer" id="pickup-first-permission-tab" class="text-uppercase txt-color-white nav-list-inactive">1st Requests <span class="badge notify-badge" id ="first-request-permission-badge"></span></a></li>
                                        <li class=""><a style="cursor: pointer" id="pickup-location-permission-tab" class="text-uppercase txt-color-white nav-list-inactive">Location Update Requests <span class="badge notify-badge" id ="location-update-request-permission-badge"></span></a></li>
                                        <li class=""><a style="cursor: pointer" id="pickup-address-permission-tab" class="text-uppercase txt-color-white nav-list-inactive">New Address Requests <span class="badge notify-badge" id ="address-change-request-permission-badge"></span></a></li>
                                        <li class=""><a style="cursor: pointer" id="pickup-denied-requests-tab" class="text-uppercase txt-color-white nav-list-inactive">Denied Requests <span class="badge notify-badge-warn" id ="denied-requests-badge"></span></a></li>
                                    </ul>
                                </div>

                                <div class="box-body" id="crew-location-to-confirm-table-section">

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 margin-tp">
                    <div class="row">
                        <div class="col-md-12">
                            <span class="text-uppercase txt-light-gray txt-light-weight2">Crew Member : </span>
                            <span class="text-uppercase txt-light-weight2" id="user-pickup-location-permission-member"></span>
                        </div>

                        <div class="col-md-6 margin-bottom-none pt-10"> 
                            <label class="text-uppercase txt-light-gray txt-light-weight2">System Address : </label> 
                            <p id="user-pickup-location-permission-permitted-address"></p>
                        </div>
                        <div class="col-md-6 margin-bottom-none pt-10"> 
                            <label class="text-uppercase txt-light-gray txt-light-weight2">Marked Address : </label> 
                            <p id="user-pickup-location-permission-marked-address"></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 margin-bottom-none pt-10"> 
                            <label class="text-uppercase txt-light-gray txt-light-weight2">Residence  Area : </label> 
                            <div class="max-comp-width">
                                <select id="user_pickup_location_permission_area" name="user_pickup_location_permission_area" class="form-control">

                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 margin-bottom-none pt-10"> 
                            <div id="map5"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 margin-bottom-none pt-10"> 
                            <span><button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" onclick="return updateFirstRequest(endUserIdUpdate, true, '${admin.id}', $('#user_pickup_location_permission_area option:selected').val(),'Your location has confirmed');"  id="user_pickup_location_confirm">Confirm</button></span>
                            <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" onclick="reject_modal('${admin.id}');" id="user_pickup_location_reject">Reject</button></span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="box-footer">

            </div>
        </form>
    </div>
</div>