<div class="modal fade" tabindex="-1" role="dialog" id="view-vehicle-details-model">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="view-vehicle-detail-model-title">Vehicle Company</h4>
            </div>
            <div class="modal-body">
                <div class="include-vehicle-company-view hidden">
                    <%@include file="vehicle-company-view.jsp" %>
                </div>
                <div class="include-vehicle-model-view">
                    <%@include file="vehicle-model-view.jsp" %>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-override btn-theme" data-dismiss="modal">Close</button>
<!--                <button type="button" class="btn-override btn-theme">Save changes</button>-->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

