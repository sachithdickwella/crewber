<%-- 
    Document   : index_user
    Created on : Apr 25, 2016, 3:53:51 PM
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
        <title>flight-crew</title>
        <!--<base href="../" target="_self">-->
        <!--include favicon-->
        <%@include file="../fav.jsp" %>
        <!--include css-->
        <%@include file="../dashb_style.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gm_css/maps.css">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/fileinput.min.css">



    </head>
    <body  class="hold-transition skin-blue sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <!--top menu-->
            <%@include file="../dash_header.jsp" %>
            <!--Reject model-->
            <%@include file="reject-modal.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../side_menu.jsp" %>
            <!-- Main Content -->
            <%@include file="user-content.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../footer.jsp" %>
        </div>

        <!--include java script-->
        <%@include file="../dashb_js.jsp" %>
        <script src="${pageContext.request.contextPath}/js/im-js/fileinput.min.js"></script>
        <script type = "text/javascript" src = "${pageContext.request.contextPath}/js/gm_js/multi-schedule/multi-schedule-ui.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gm_js/crew/pick-location-map.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gm_js/crew/location-permission-map.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gm_js/crew/crew.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA3u56UnbVfMnhHOgppeQh1QgIAUdA2hjs&libraries=places&callback=initAutocomplete5" async defer></script>

    </body>
</html>
