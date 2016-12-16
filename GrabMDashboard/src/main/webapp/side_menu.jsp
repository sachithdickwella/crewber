<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/images/img/profile/default-profile-image.png" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${admin.firstName} ${admin.lastName}</p>
                
                <!--<a href="#"><i class="fa fa-circle text-success"></i> Online</a>-->
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            
            <li class="header text-uppercase">Super Admin</li>
            
            <li><a href="${pageContext.request.contextPath}/ui-profile-registry"><i class="fa fa-user"></i> <span>UI Profile Registry</span></a></li>
            
            <li class="header">MAIN NAVIGATION</li>

            <li><a href="${pageContext.request.contextPath}/user-dashboard"><i class="fa fa-book"></i> <span>Dash Board - Crew</span></a></li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-bars"></i>
                    <span>Schedule</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/schedule-pick-up"><i class="fa fa-car" aria-hidden="true"></i><i class="fa fa-caret-down" aria-hidden="true"></i> Schedule Pick up</a></li>
                    <li><a href="${pageContext.request.contextPath}/schedule-drop-off"><i class="fa fa-car" aria-hidden="true"></i><i class="fa fa-caret-down" aria-hidden="true"></i> Schedule Drop Off</a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/admin"><i class="fa fa-user"></i> <span>Administrator</span></a></li>
            <li><a href="${pageContext.request.contextPath}/flight-crew"><i class="fa fa-users"></i> <span>Flight Crew</span></a></li>
<!--            <li><a href="${pageContext.request.contextPath}/vehicle"><i class="fa fa-taxi"></i> <span>Vehicle</span></a></li>-->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-taxi"></i>
                    <span>Vehicle</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/vehicle"><i class="fa fa-car" aria-hidden="true"></i>Vehicle Registration</a></li>
                    <li><a href="${pageContext.request.contextPath}/vehicle-detail"><i class="fa fa-car" aria-hidden="true"></i>Vehicle Details</a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/driver"><i class="fa fa-user"></i> <span>Driver</span></a></li>

            <li class="header">LABELS</li>
            <li><a href="${pageContext.request.contextPath}/live-tracking"  target="_blank"><i class="fa fa-user-secret"></i> <span>Live Track</span></a></li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>