<%-- 
    Document   : index
    Created on : Apr 26, 2016, 9:34:31 PM
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
        <title>Profile</title>
        <!--include favicon-->
        <%@include file="../fav.jsp" %>
        <!--include css-->
        <%@include file="../dashb_style.jsp" %>
    </head>
    <body  class="hold-transition skin-blue sidebar-mini">
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gm_js/grabm-l.js"></script>

    </body>
</html>
