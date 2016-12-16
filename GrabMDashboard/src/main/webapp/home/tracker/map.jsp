<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
<div class="content-wrapper bg-content" id="map-content-wrapper">

    <!-- Main content -->
    <section class="content" style="padding-top: 0px !important">
        <div class="row">
            <div class="col-md-12">
                <div class="row" id="map-height">
                    <input id="pac-input" class="controls" type="text" placeholder="Search Box">
                    <div id="map2" class="">

                    </div>
                    <div id="temp3">
                        <!--TODO write content-->
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>