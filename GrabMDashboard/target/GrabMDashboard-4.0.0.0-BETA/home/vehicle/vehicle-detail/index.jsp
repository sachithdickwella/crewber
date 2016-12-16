<%-- 
    Document   : index
    Created on : May 19, 2016, 12:18:07 PM
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
        <title>Vehicle-Details</title>
        <!--include favicon-->
        <%@include file="../../fav.jsp" %>
        <!--include css-->
        <%@include file="../../dashb_style.jsp" %>
        <!--add select2 style-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/select2.min.css">

    </head>
    <body  class="hold-transition skin-blue sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <!--top menu-->
            <%@include file="../../dash_header.jsp" %>
            <!--view model-->
            <%@include file="view/view-model.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../../side_menu.jsp" %>
            <!-- Main Content -->
            <%@include file="vehicle-detail-content.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../../footer.jsp" %>

        </div>


        <!--include java script-->
        <%@include file="../../dashb_js.jsp" %>
        <!--select2 js-->
        <script src="${pageContext.request.contextPath}/js/im-js/select2.full.min.js"></script>
        <!--color picker js-->
        <script src="${pageContext.request.contextPath}/js/im-js/bootstrap-colorpicker.min.js"></script>
        



        <script>
            $(document).ready(function () {
                //Initialize Select2 Elements
                $(".select2").select2();
                //color picker with addon
                $(".my-colorpicker2").colorpicker();


                /*$('#view-vehicle-details-model').on('shown.bs.modal', function () {
                 
                 });*/

               
            });
        </script>

    </body>
</html>
