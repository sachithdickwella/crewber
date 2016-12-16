<div class="row">
    <div class="col-md-8">
        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Crew Pickup Area</h3>
        </div>

        <!-- /.box-header -->
        <!-- form start -->
        <form role="form" id="user_area_form" method="POST">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="user_area">Pickup Area :</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="user_area" name="user_area" autocomplete="off" placeholder="Pickup Area">
                            </div>
                        </div>
                    </div>
                    <div class="form-group margin-bottom-none">
                        <div class="col-md-6 pt-10">
                            <label class="control-label label-overide reg-form-lable" for="user_area_first_pickup_time">Area Pickup time:</label>
                            <div class="max-comp-width">
                                <input type="text" class="form-control" id="user_area_first_pickup_time" name="user_area_first_pickup_time" autocomplete="off" placeholder="Pickup [ _ ] Hours Before Reporting">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xs-12 margin-bottom-none pt-10"> 
                <span><button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="user_area_add" onclick="addPickupArea();">ADD</button></span>
                <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="user_area_reload">Reload</button></span>
            </div>
            <!-- /.box-body -->
           
            <div class="box-footer">

            </div>
        </form>
    </div>
    <div class="col-md-4">
        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Area | View</h3>
        </div>
        <div class="pt-10">
            <ul class="list-group" id="pickup-area-list">
                
            </ul>
        </div>

        <!-- /.box-header -->
        <!-- form start -->

    </div>

</div>