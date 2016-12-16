<%-- 
    Document   : index
    Created on : Apr 26, 2016, 6:16:44 PM
    Author     : Roshin Perera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<!DOCTYPE html>
<html>
    <head>
        <g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule Drop Off</title>
        <!--include favicon-->
        <%@include file="../../fav.jsp" %>
        <!--include css-->
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
            <%@include file="drop-off-schedule-content.jsp" %>
            <!-- Left side column. contains the sidebar -->
            <%@include file="../../footer.jsp" %>

            <div class="control-sidebar-bg"></div>
        </div>

        <!--include java script-->
        <%@include file="../../dashb_js.jsp" %>

    </body>
</html>
