<%-- 
    Document   : index
    Created on : Apr 30, 2016, 12:52:20 PM
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
        <title>Profile Registry</title>
        <!--include favicon-->
        <%@include file="../fav.jsp" %>
        <!--include css-->

        <!--adding select2-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/select2.min.css">
        <%@include file="../dashb_style.jsp" %>
    </head>
    <body  class="hold-transition skin-blue sidebar-mini">
        <!--loader-->
        <div class="container hidden" id="overlay">
            <img id="loading" src="${pageContext.request.contextPath}/images/icn/gears.svg">       
            <img id="loading2" src="${pageContext.request.contextPath}/images/icn/Loader.png">       
        </div>
        <!-- Site wrapper -->
        <div class="wrapper">
            <!--top menu-->
            <%@include file="../dash_header.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../side_menu.jsp" %>
            <!-- Main Content -->
            <%@include file="profile-content.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../footer.jsp" %>

        </div>


        <!--include java script-->
        <%@include file="../dashb_js.jsp" %>
        <!--select2 js-->
        <script src="${pageContext.request.contextPath}/js/im-js/select2.full.min.js"></script>

        <script type="text/javascript">
            $(function () {
                //Initialize Select2 Elements
                $(".select2").select2();
            });
        </script>
    </body>
</html>
