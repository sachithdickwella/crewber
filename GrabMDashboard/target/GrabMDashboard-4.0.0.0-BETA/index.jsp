<%-- 
    Document   : index
    Created on : Apr 20, 2016, 3:53:51 PM
    Author     : Roshin Perera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<!DOCTYPE html>
<html>
    <head>
        <g:http-error request="${pageContext.request}" response="${pageContext.response}" code="404"/>
        <g:session-validate-inverse sessionAttribute="${admin}" url="${CONTEXT_PATH}profile"/>
        <title>GrabM | Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!--include favicon-->
        <%@include file="fav.jsp" %>
        <!--include css-->
        <%@include file="include_css.jsp" %>



    </head>

    <body class="">


        <div id="temp">
            <!--TODO write content-->
        </div>
        <br/>
        <br/>
        <div id="temp2">
            <!--TODO write content-->
        </div>
        <!--Adding Login.jsp to index-->
        <%@include file="home/login/login.jsp"%>
        <!--include java script-->
        <%@include file="dashb_js.jsp" %>
        <script type="text/javascript">
            $(document).ready(function () {

                //test_put();
//                setInterval(test_, 1000);
            });

            /**
             * This is a sample for GET, POST, DELETE requests. 
             */

            var test_ = function () {

                $.ajax({
                    method: 'POST',
                    url: '${backend_url_key}',
                    data: {url: '/pickupschedule/11', body: '', accept: 'json', content: 'plain'}
                }).done(function (response) {
                    alert(response);
                    var obj = JSON.parse(response);
                    var print = obj[0];
                    document.getElementById('temp').innerHTML = response;
//                    document.getElementById('temp2').innerHTML = 'speed = ' + print;
                }).fail(function () {
                    alert("Failed");
                });
            };

            /**
             * This is a sample for PUT request.
             */
            var test_put = function () {
                $.ajax({
                    method: 'PUT',
                    url: '${backend_url_key}',
                    contentType: 'application/json',
                    data: JSON.stringify({url: '/admin/create', body: '{}', accept: 'plain', content: 'json'})
                }).done(function (response) {
                    alert(response);
                    document.getElementById('temp').innerHTML = response;
                }).fail(function () {
                    alert("Failed");
                });
            };
        </script>
    </body>
</html>
