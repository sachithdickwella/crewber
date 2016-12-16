<%-- 
    Document   : index
    Created on : Aug 1, 2016, 1:39:02 PM
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
        <title>Dash board-Crew</title>
        <!--include favicon-->
        <%@include file="../fav.jsp" %>
        <!--include css-->
        <%@include file="../dashb_style.jsp" %>
        <!--select css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/select2.min.css">
        <!--Morris chart-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/morris.css">


    </head>
    <body  class="hold-transition skin-blue sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <!--top menu-->
            <%@include file="../dash_header.jsp" %>
            <!--modal-->
            <%@include file="modal-end-user-view.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../side_menu.jsp" %>
            <!-- Main Content jsp -->
            <%@include file="content.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../footer.jsp" %>
        </div>

        <!--include java script-->
        <%@include file="../dashb_js.jsp" %>
        
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <!--select2 js-->
        <script src="${pageContext.request.contextPath}/js/im-js/select2.full.min.js"></script>   
        <!--morris chart js-->
        <script src="${pageContext.request.contextPath}/js/im-js/morris.min.js"></script>
        <script type = "text/javascript" src = "${pageContext.request.contextPath}/js/gm_js/multi-schedule/multi-schedule-ui.js"></script>
        <!--user-dash-console js-->
        <script src="${pageContext.request.contextPath}/js/gm_js/user-dash-console/user-dash-console.js"></script>
        <script src="${pageContext.request.contextPath}/js/gm_js/user-dash-console/crew-profile-permission.js"></script>
    </body>
</html>
