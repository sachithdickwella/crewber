<%-- 
    Document   : index
    Created on : May 10, 2016, 7:52:24 AM
    Author     : Roshin Perera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pickup schedule update</title>
        <!--include favicon-->
        <%@include file="../../fav.jsp" %>
        <!--include css-->
        <!--select css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/select2.min.css">
        <!--date/time picker css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/im-css/bootstrap-datetimepicker.min.css">
        <%@include file="../../dashb_style.jsp" %>

    </head>
    <body  class="hold-transition skin-blue sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <!--top menu-->
            <%@include file="../../dash_header.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../../side_menu.jsp" %>
            <!-- Main Content -->
            <%@include file="pickup-schedule-update-content.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../../footer.jsp" %>

            <div class="control-sidebar-bg"></div>
        </div>

        <!--include java script-->
        <%@include file="../../dashb_js.jsp" %>
        <!--select2 js-->
        <script src="${pageContext.request.contextPath}/js/im-js/select2.full.min.js"></script>       
        <!--Date/time picker js-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/im-js/bootstrap-datetimepicker.min.js"></script>
        <!--transit js-->
        <script src="${pageContext.request.contextPath}/js/gm_js/clone-form-section.js"></script>
        <!--schedule validater-->
        <script src="${pageContext.request.contextPath}/js/gm_js/grabm-schedule-validater.js"></script>

        <script type="text/javascript">
            $(function () {
                //Initialize Select2 Elements
                $(".select2").select2();
                $('.datetimepicker').datetimepicker();
            });
        </script>
    </body>
</html>
