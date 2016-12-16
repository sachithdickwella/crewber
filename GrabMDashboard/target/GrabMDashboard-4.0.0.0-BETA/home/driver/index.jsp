<%-- 
    Document   : index_driver
    Created on : Apr 25, 2016, 3:54:25 PM
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
        <title>Driver</title>

        <!--include favicon-->
        <%@include file="../fav.jsp" %>
        <!--include css-->
        <%@include file="../dashb_style.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/select2.min.css">
        <!--file input-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/fileinput.min.css">
    </head>
    <body  class="hold-transition skin-blue sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <!--top menu-->
            <%@include file="../dash_header.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../side_menu.jsp" %>
            <!-- Main Content -->
            <%@include file="driver-content.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../footer.jsp" %>

            <div class="control-sidebar-bg"></div>
        </div>

        <!--include java script-->
        <%@include file="../dashb_js.jsp" %>
        <!-- canvas-to-blob.min.js is only needed if you wish to resize images before upload.
     This must be loaded before fileinput.min.js -->
        <script src="${pageContext.request.contextPath}/js/im-js/canvas-to-blob.min.js"></script>
        <!--select2 js-->
        <script src="${pageContext.request.contextPath}/js/im-js/select2.full.min.js"></script>
        <!--file input js-->
        <script src="${pageContext.request.contextPath}/js/im-js/fileinput.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/gm_js/driver/driver.js"></script>
    </body>
</html>
