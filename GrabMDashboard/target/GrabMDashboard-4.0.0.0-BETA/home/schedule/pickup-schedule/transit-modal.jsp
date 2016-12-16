<div class="modal fade" tabindex="-1" role="dialog" id="transit-modal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">ADD TRANSIT</h4>
            </div>
            <div class="">
                <div class="box-header margin-left">
                    <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2" id="transit-modal-crew-member"></h3>
                </div>
                <div class="col-md-12 pt-10">
                    <div class="row">
                        <div class="col-md-12 margin-bottom">
                            <div class="form-group margin-bottom-none">
                                <div class="col-md-6 pt-10">
                                    <label class="control-label label-overide reg-form-lable" for="transit_pick_location_lat_long">Pickup latitude/longitude:</label>
                                    <div class="max-comp-width">
                                        <input type="text" class="form-control" id="transit_pick_location_lat_long" name="transit_pick_location_lat_long" autocomplete="off" placeholder="Pick latitude/longitude">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group margin-bottom-none">
                                <div class="col-md-6 pt-10">
                                    <label class="control-label label-overide reg-form-lable" for="transit_drop">
                                        <a class="link-info" id="toggle-transit-drop" style="cursor:pointer">DEFAULT DROP OFF</a>
                                    </label>
                                    <div class="max-comp-width" id="transit_drop_location_default_wrapper">
                                        <select name="transit_drop" id="transit_drop_location_default" class="form-control" style="width: 100% !important;">

                                            <option value="">BIA</option>

                                        </select>
                                    </div>
                                    <div class="max-comp-width hidden" id="transit_drop_location_lat_long_wrapper">
                                        <input type="text" class="form-control" id="transit_drop_location_lat_long" name="transit_drop" autocomplete="off" placeholder="Drop latitude/longitude">
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- /.box-body -->

                    <div class="box-footer">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="transit-modal-confirm">Save changes</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="transit-modal-close">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
