<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="g" uri="http://grabm.earcs.com/core"%>
<div class="row">
    <div class="col-md-7">
        <g:execute-stat>
            <c:if test="${stat gt '0'}">
                <div class="stat-respond">
                    <div class="stat-respond-success">
                        Vehicle Despatched
                    </div>
                </div>
            </c:if>
            <c:if test="${stat eq '-1'}">
                <div class="stat-respond">
                    <div class="stat-respond-danger">
                        Failed despatch Vehicle
                    </div>
                </div>
            </c:if>
        </g:execute-stat>

        <div class="box-header">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">Schedule Notify / Dispatch</h3>
        </div>
        <div class="callout callout-info">
            <h4>INFO !</h4>
            <p>
                Notify schedule when vehicle reach it's max passenger limit or it's original schedule is completed, 
                by clicking 'Notify schedule' link.
            </p>
        </div>

        <form role="form" method="POST" id="add_new_schedule_form">

            <div class="form-group">
                <div class="col-md-12 pt-10">
                    <label class="control-label label-overide reg-form-lable" for="despatch_flight_number">Flight Number:</label>
                    <div class="max-comp-width">
                        <select name="despatch_flight_number" id="despatch_flight_number" class="form-control select2" style="width: 100% !important;">
                            <c:forEach var="x" items="${futurepickups}">
                                <fmt:formatDate var="flightDate" value="${x.flightDateTime}" type="date" pattern="MMM-dd-yyyy HH:mm (Z)"/>
                                <option value="${x.id}">${x.flightNo} | ${flightDate}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="margin-top-10">
                        <label class="text-uppercase txt-light-gray txt-light-weight2">Description : </label> 
                        <p id="vehiclenote">Sample Description</p>
                    </div>
                </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
            </div>
        </form>
        <div class="" id="row-data">

        </div>

        <div class="">
            <div class="">
                
            </div>
        </div>
    </div>
</div>
<div class="row">

    <!--<div class="col-md-12">
        <div class="post border-bottom">
            <div class="row">
                <div class="col-md-5">
                    <div>
                        <img class="img-circle img-bordered" src="/images/icn/pickup.png" alt="user image">
                        <span class="view-title"><a href="#">VR-NUMBER</a></span>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="not-despatched pull-right">
                        <span id="despatch-status" class="text-uppercase">not despatched</span>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <p class="txt-light-gray">
                        Car (JQ-2462) - Roshin Perera | 2016/05/08 <br>
                        | Schedule Passengers : 3 | Max Passengers Allowed : 3 |

                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <ul class="list-inline">
                        <li><a href="#" class="link-black text-sm text-uppercase"><i class="fa fa-bell margin-r-5"></i>Notify Schedule</a></li>
                        <li><a href="#" class="link-black text-sm text-uppercase"><i class="fa fa-car margin-r-5"></i> Dispatch</a></li>
                        <li><a href="#" class="link-black text-sm text-uppercase"><i class="fa fa-pencil-square-o margin-r-5"></i> Replace Driver/vehicle</a></li>
                        <li><a href="#" class="link-black text-sm text-uppercase"><i class="fa fa-eye-slash margin-r-5"></i> View Crew</a></li>
                    </ul>
                </div>
            </div>

        </div>
    </div>-->
</div>
