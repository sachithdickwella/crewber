<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<div class="login-box">
    <div class="login-logo">
        <div class="pull-left hidden-xs" style="padding-top: 10px; margin-left: 50px;">
            <img src="/grabmd/images/img/GrabMLogo.png" class="img-responsive img-center" alt="Crewber">
        </div>
        <div class="hidden-lg hidden-md hidden-sm pull-left" style="padding-top: 10px; margin-left: 50px;">
            <img src="/grabmd/images/img/GrabMLogo.png" class="img-responsive img-center" alt="Crewber">
        </div>
        <a href="#" class="hidden-xs" style="margin-right: 50px;">| LOGIN</a>
        <a href="#" class="hidden-lg hidden-md hidden-sm" style="margin-right: 50px;">LOGIN</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>

        <g:execute-stat>
            <c:if test="${stat eq 'f'}">
                <p class="text-danger">Incorrect username or password</p>
            </c:if>
        </g:execute-stat>

        <form  method="post" action="/grabmd/login" id="admin_login_form">
            <div class="form-group has-feedback">
                <input id="user" name="user" type="email" class="form-control" placeholder="Email" required="">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="pass" name="pass" type="password" class="form-control" placeholder="Password" required="">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">

                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <!--<a href="<%--${pageContext.request.contextPath}--%>/admin" class="btn-override btn-theme">Sign In</a>-->
                    <input type="submit" value="Sign In" class="btn-override btn-theme"/>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <!-- /.social-auth-links -->
        <a href="404.jsp" class="txt-light-gray">I forgot my password</a><br>
        <a href="${pageContext.request.contextPath}/admin-activate-account" class="txt-light-gray">Get Registered</a><br>

    </div>
    <!-- /.login-box-body -->
</div>
