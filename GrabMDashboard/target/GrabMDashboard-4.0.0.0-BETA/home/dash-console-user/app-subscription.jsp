<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-12">
        <div class="box box-primary">

            <div class="nav-tabs-custom pt-20">
                <!-- Tabs within a box -->
                <ul class="nav nav-tabs pull-right">
                    <li><a href="#subscribe-app" data-toggle="tab">Subscribe</a></li>
                    <li><a href="#app-subscriptions" data-toggle="tab">Subscriptions</a></li>
                    <li class="pull-left header text-uppercase txt-light-gray txt-light-weight2"> &nbsp;&nbsp;<i class="fa fa-android"></i> Subscribe App</li>
                </ul>

                <div class="tab-content no-padding">
                    <!-- Morris chart - Sales -->
                    <div class="chart tab-pane active" id="app-subscriptions" style="position: relative;">
                        <div id="donut-example" style="height: 250px;" class="margin-bottom pt-20"></div>
                        <div class="col-lg-12">
                            <ul class="list-inline">
                                <li><a href="#" class="link-black text-sm text-uppercase"><i class="fa fa-eye-slash margin-r-5  link-subscribed"></i>View Subscribed Users</a></li>
                                <li><a href="#" class="link-black text-sm text-uppercase"><i class="fa fa-eye-slash margin-r-5 link-un-subscribed"></i> View Un-subscribed Users</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="chart tab-pane" id="subscribe-app">
                        <div class="row">
                            <div class="col-md-12 margin-bottom">
                                <form role="form" method="POST" id="app_subscribe_form">
                                    <div class="form-group">
                                        <div class="col-md-12 margin-bottom-none">
                                            <label class="control-label label-overide reg-form-lable" for="app_subscribe_crew_member">Crew Members:</label>
                                        </div>
                                        <div class="col-md-12 margin-bottom">
                                            <div class="max-comp-width">
                                                <select name="app_subscribe_crew_member" id="app_subscribe_crew_member" class="form-control select2" style="width: 100% !important;">
                                                    <c:forEach var="x" items="${endusers}">
                                                        <option value="${x.id}" 
                                                                data-memno="${x.membershipNumber}" 
                                                                data-desid="${x.designation.id}"
                                                                data-desshortname="${x.designation.shortName}" 
                                                                data-deslongname="${x.designation.longName}"
                                                                data-firstname="${x.firstName}"
                                                                data-lastname="${x.lastName}"
                                                                data-desc="${x.note}">
                                                            ${x.firstName} ${x.lastName} - ${x.designation.longName} (${x.membershipNumber})
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <span class="text-uppercase txt-light-gray txt-light-weight2">Selected Member : </span>
                                            <span class="text-uppercase txt-light-weight2">Cpt. John Doe</span>
                                        </div>
                                        <div class="col-md-6 margin-bottom-10">
                                            <span class="text-uppercase txt-light-gray txt-light-weight2">Status : </span>
                                            <span class="text-uppercase txt-light-weight2" id="user-app-subscription-status">Unsubscribed</span>
                                        </div>
                                    </div>

                                    <div class="col-xs-12 margin-bottom-none pt-10">
                                        <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="app_subscribe_crew_member_take_action">Take action</button></span>
                                        <span> <button style="min-width: 150px; margin-bottom: 5px;" type="button" class="btn-override btn-theme" id="app_subscribe_crew_member_cancel">Reset</button></span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>



