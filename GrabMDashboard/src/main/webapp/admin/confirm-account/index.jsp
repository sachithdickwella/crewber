<%-- 
    Document   : index
    Created on : Apr 30, 2016, 2:33:21 PM
    Author     : Roshin Perera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<!DOCTYPE html>
<html>
    <head>
        <g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Registration</title>
        <!--include favicon-->
        <%@include file="../../fav.jsp" %>
        <!--include css-->
        <%@include file="../../dashb_style.jsp" %>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="xt_registration_form_wrapper">
                    <!--include xt_registration_form-->
                    <%@include file="admin_registration_content_xt.jsp" %>
                </div>
            </div>
        </div>

        <!--include java script-->
        <%@include file="../../dashb_js.jsp" %>

    </body>
</html>
