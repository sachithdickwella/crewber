<%-- 
    Document   : 404
    Created on : Apr 23, 2016, 8:01:07 AM
    Author     : IRONWOLF_PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404</title>
        <!--include favicon-->
        <%@include file="fav.jsp" %>
        <!--include css-->
        <%@include file="include_css.jsp" %>
        <!--include java script-->
        <%@include file="include_js.jsp" %>
    </head>
    <body class="bg-light-gray">
        
        <div class="container-fluid">
            <div class="margin-tp">
                <div class="row">
                    <div class="img-wrapper">
                        <img src="images/icn/404-ICon-gray.png" class="img-responsive img-center" alt="Lost your way ?">
                    </div>
                </div>
                <div class="row">
                    <div class="text-center">
                        <h1 class="txt-light-gray"> LOST YOUR WAY ? </h1>
                        <h2 class="txt-light-gray"><small>| - 404 Not Found - |</small></h2>
                        <a href="${pageContext.request.contextPath}/signin" class="btn-override btn-theme">Get me out of here</a>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
