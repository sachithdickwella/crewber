<%-- 
    Document   : index
    Created on : May 10, 2016, 10:58:40 AM
    Author     : Roshin Perera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<!DOCTYPE html>
<html>
    <head>
        <g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
        <g:session-validate sessionAttribute="${admin}" url="${CONTEXT_PATH}"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Tracking</title>
        <!--include favicon-->
        <%@include file="../fav.jsp" %>
        <!--include css-->
        <%@include file="../dashb_style.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gm_css/maps.css">
        <style>
            html, body
            {
                height: 100%;
                min-height: 100%;
                background-color: #000;
            }
        </style>

    </head>
    <body  class="hold-transition skin-blue sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <!--top menu-->
            <%@include file="header.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="side-menu.jsp" %>
            <!-- Main Content -->
            <%@include file="map.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="tracking-detail-panel.jsp" %>

        </div>

        <!--include java script-->
        <%@include file="../dashb_js.jsp" %>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gm_js/tracker/tracker-map.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gm_js/tracker/tracker-ui.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA3u56UnbVfMnhHOgppeQh1QgIAUdA2hjs&libraries=places&callback=initAutocomplete2" async defer></script>

        <script>

            $(document).ready(function () {
                /**
                 * Nothing goes here.
                 */
//                alert($(window).height());
                $('[data-toggle="tooltip"]').tooltip()
//                var AdminLTEOptions = {
//                    sidebarSlimScroll: true
//                }
                var header = 50;
                $("#map2").css("min-height", function () {
                    return $(window).height() - header;
                });
                $("#tracking-detail-content-section").css("max-height", function () {
                    return $(window).height() - (header + 40);
                });
                $("#map-content-wrapper").css("max-height", function () {
                    return $(window).height() - header;
                });
                
                $('#tracking-detail-content-section').slimScroll({
                    position: 'left',
                    height: $(window).height() - (header + 40),
                    railVisible: true,
                    color: '#ffffff',
                    alwaysVisible: false
                });
            });
        </script>
    </body>
</html>
