<div class="modal fade" tabindex="-1" role="dialog" id="location-map-model">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">LOCATION PICKER</h4>
            </div>
            <div class="modal-body">
                <div class="col-md-12 pt-10">

                    <label class="control-label label-overide reg-form-lable" for="it_user_rg_note">Search location and click on the map to get coordinations</label>
                    <div id="inputWrapper">
                        <input id="pac-input" class="controls" type="text" placeholder="Search Box">
                    </div>
                    <div id="map3"></div>
                    <div class="row">
                        <div class="col-md-6 pt-10">
                            <div class="max-comp-width input-group">
                                <!--<label class="control-label label-overide reg-form-lable" for="it_user_rg_coordinates">Coordinates</label>-->
                                <input type="text" class="form-control" id="map_model_location_pickup" name="map_model_location_pickup" autocomplete="off">
                                <span class="input-group-addon"><i class="fa fa-map-marker" aria-hidden="true"></i></span>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

