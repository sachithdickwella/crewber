<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <ul class="sidebar-menu">
            <li class="header" id="tracker-status">CHECKING STATUS &nbsp;&nbsp;&nbsp;<i class="fa fa-circle-o-notch fa-spin fa-lg fa-fw margin-bottom"></i>
                <span class="sr-only">Checking Status...</span>
            </li>
            <li class="header text-uppercase margin-top-10">
                Controllers
            </li>
        </ul>

        <ul class="nav nav-justified">

            <li><a href="#" id="tracker-list-show-vehicle" class="padding-none tracker-list-inactive"><img src="${pageContext.request.contextPath}/images/icn/tracker/car_active.png" class="tracker-nav-icon" alt="icn"></a></li>
            <li><a href="#" id="tracker-list-show-schedule" class="padding-none tracker-list-inactive"><img src="${pageContext.request.contextPath}/images/icn/tracker/schedule_active.png" class="tracker-nav-icon" alt="icn"></a></li>
            <li><a href="#" id="tracker-list-show-area" class="padding-none tracker-list-inactive"><img src="${pageContext.request.contextPath}/images/icn/tracker/radar_active.png" class="tracker-nav-icon" alt="icn"></a></li>
        </ul>

        <ul class="sidebar-menu">
            <li class="header text-uppercase margin-top-10">
                Scheduled Flights
            </li>
        </ul>
     
        <ul class="sidebar-menu" id="shceduled-vehicle-list">

           
        </ul>



    </section>
    <!-- /.sidebar -->
</aside>