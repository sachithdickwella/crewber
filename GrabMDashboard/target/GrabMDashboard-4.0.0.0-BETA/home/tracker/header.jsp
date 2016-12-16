<header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo theme-color">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>C</b>B</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg" id="target-2"><b>CREWBER</b> | LIVE TRACKING</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top theme-color">
        <!--         Sidebar toggle button
                <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>-->

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav" style="left: 0">
              
                <!-- Control Sidebar Toggle Button -->
                <li class="">
                    <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                </li>

                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${pageContext.request.contextPath}/images/img/profile/default-profile-image.png" class="user-image" alt="User Image">
                        <span class="hidden-xs">${admin.firstName} ${admin.lastName}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="${pageContext.request.contextPath}/images/img/profile/default-profile-image.png" class="img-circle" alt="User Image">
                            <p class="">
                                ${admin.firstName} ${admin.lastName}
                                <small>${admin.profileId.name}</small>
                            </p>
                        </li>
                        <!-- Menu Body -->
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-right">
                                <a href="${pageContext.request.contextPath}/logout" class="btn-override btn-theme">Sign out</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>