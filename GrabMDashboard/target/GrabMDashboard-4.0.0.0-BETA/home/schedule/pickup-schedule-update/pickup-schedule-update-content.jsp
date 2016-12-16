<div class="content-wrapper bg-content">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Schedule | 
            <small>Pick up</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li> 
            <li><a href="${pageContext.request.contextPath}/schedule-pick-up"><i class="fa fa-taxi"></i> Schedule pick up</a></li>
            <li class="active">Schedule pick up - update</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Main content -->
        <section class="content">

            <div class="row">
                <!--left column-->
                <div class="col-md-7">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs small theme-bg-clr-dark">
                            <li class="active"><a href="#tab_1-1" data-toggle="tab" aria-expanded="true" class="text-uppercase txt-color-white">Add new schedule</a></li>
                            <li class=""><a href="#tab_1-2" data-toggle="tab" aria-expanded="false" class="text-uppercase txt-color-white">Schedule Crew</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1-1">
                                <!--include add new schedule form-->
                                <%--<%@include file="add-new-schedule-form.jsp" %>--%>
                            </div>

                            <div class="tab-pane" id="tab_1-2">
                                <!--include add new schedule form-->
                                <%--<%@include file="schedule-users-form.jsp" %>--%>
                            </div>
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- nav-tabs-custom -->
                </div>
                <!--include shcedule list-->
                <%--<%@include file="recent-schedules.jsp" %>--%>
            </div>

        </section>
        <!-- /.content -->

    </section>
    <!-- /.content -->
</div>