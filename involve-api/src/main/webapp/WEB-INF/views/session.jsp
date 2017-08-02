<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header2.jsp" %>
<script>
    $(document).ready(function () {
        $('#startDate').datepicker({
            dateFormat: 'dd/mm/yy',
            yearRange: "-150:+0",
            changeMonth: true,
            changeYear: true
        });
        /*$('#birthDate').datepicker({
         dateFormat: 'dd/mm/yy',
         yearRange: "-150:+0",
         changeMonth: true,
         changeYear: true
         });

         $('#endDate').datepicker({
         dateFormat: 'dd/mm/yy',
         yearRange: "-150:+0",
         changeMonth: true,
         changeYear: true
         });
         $('#deathDate').datepicker({
         dateFormat: 'dd/mm/yy',
         yearRange: "-150:+0",
         changeMonth: true,
         changeYear: true
         });*/
    });

    var app = angular.module("app", []);
    app.controller("homeCtrl", function ($scope, $http, $filter,$location) {
        $scope.items = [];
        $scope.selectedItemId = 0;
        var absUrl = $location.absUrl();
        if (absUrl.split("?")[1]) {
            if (absUrl.split("?")[1].split("=")[1]) {
                $scope.selectedItemId = absUrl.split("?")[1].split("=")[1];
            }
        }
        function getSuccessSession(res) {
            console.log(res.data);
            $scope.items = res.data;
        }

        ajaxCall($http, "reform/get-reform-sessions?itemId=" + $scope.selectedItemId, null, getSuccessSession);



        $scope.showReform = function () {
            $scope.reform = {
                'reformTypeId': 1,
                'progressBarPercent1': 0
            };
        };

        $scope.testPanelHide = function () {
            $('#sportsmanPanel').addClass("hidden");
            $('#sportsmanList').removeClass("hidden");
        }

        $scope.saveItem = function () {
            console.log($scope.item);
            function saveSuccessItem(res) {
                location.reload();
            };
            ajaxCall($http, "reform/save-session", angular.toJson($scope.item), saveSuccessItem);
        };



        $scope.detailEvent = function (eventId) {
            function getEvent(res) {
                console.log(res.data);
                $scope.detailInfo = res.data;
                $scope.persons = res.data.persons;
                $scope.documents = res.data.documents;
            }

            ajaxCall($http, "event/get-event", angular.toJson({'eventId': eventId}), getEvent);
            function getEventHistory(res) {
                $scope.history = res.data;
            }

            ajaxCall($http, "event/get-event-history", angular.toJson({'eventId': eventId}), getEventHistory);
        };

        $scope.editItem = function (itemId) {
            if (itemId != undefined) {
                var selected = $filter('filter')($scope.items, {id: itemId}, true);
                $scope.item = selected[0];
            }
        };
        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "reform/delete-session?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.open = function (name) {
            window.open('file/draw/' + name + '/');
        };

    });
</script>


<!-- page content -->
<div class="right_col" ng-controller="homeCtrl">

    <div class="modal fade" id="itemModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="myModalLabel">სესიის დამატება</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-sm-12">
                            <label class="control-label">დასახელება</label>
                            <input type="text" ng-model="item.name"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-12">


                            <label class="control-label">დაწყება</label>
                            <div class="control-group">
                                <div class="controls">
                                    <div class="col-md-11 xdisplay_inputx form-group has-feedback">
                                        <input type="text" class="form-control has-feedback-left"  ng-model="item.startDate" id="single_cal3" aria-describedby="inputSuccess2Status3">
                                        <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                                        <span id="inputSuccess2Status3" class="sr-only">(success)</span>
                                    </div>
                                </div>
                            </div>
                           <%-- <input type="text" ng-model="item.startDate"
                                   class="form-control ng-pristine ng-valid">--%>
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="control-label">დასრულება</label>
                            <div class="control-group">
                                <div class="controls">
                                    <div class="col-md-11 xdisplay_inputx form-group has-feedback">
                                        <input type="text" class="form-control has-feedback-left"  ng-model="item.endDate" id="single_cal4" aria-describedby="inputSuccess4">
                                        <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                                        <span id="inputSuccess4" class="sr-only">(success)</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">დახურვა</button>
                    <button type="button" class="btn btn-success" ng-click="saveItem()">შენახვა</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="reformViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="viewModalLabel">სესიის დეტალური ინფორმაცია</h4>
                </div>
                <div class="modal-body">
                    <div class="row">

                        <table class="table table-striped">
                            <tr>
                                <th class="col-md-4 text-right">სესიის დასახელება :</th>
                                <td>{{item.name}}</td>
                            </tr>
                        </table>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">დახურვა</button>
                </div>
            </div>
        </div>
    </div>

    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>სესიები
                    <small>დეტალური ინფორმაცია</small>
                </h3>
            </div>
        </div>

        <div class="clearfix"></div>

        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>სესიები</h2>

                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            <li></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">

                        <p>აქ შეგიძლიათ დაამატოთ დაარედაქტიროთ ან წაშალოთ სესიები</p>
                        <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#itemModal"
                                ng-click="showItem();">დამატება
                        </button>
                        <!-- start project list -->
                        <table class="table table-striped table-hover projects">
                            <thead>
                            <tr>
                                <th style="width: 1%">#</th>
                                <th style="width: 20%">დასახელება</th>
                                <th>ტიპი</th>
                                <th>1 პროგრეს ბარი</th>
                                <th style="width: 20%">#Edit</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="r in items">
                                <td>
                                    <a data-toggle="modal" data-target="#reformViewModal" ng-click="editItem(r.id)"
                                       class="btn btn-link btn-xs">{{$index+1}}</a>
                                </td>
                                <td>
                                    <a>{{r.name}}</a>
                                    <br/>
                                    <small>თარიღი: {{r.createDate}}</small>
                                </td>
                                <td>
                                    {{r.reformTypeName}}
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar"
                                             data-transitiongoal="r.progressBarPercent1"
                                             style="width:{{r.progressBarPercent1}}%;"></div>
                                    </div>
                                    <small>{{r.progressBarPercent1}}% {{r.progressBarName1}}</small>
                                </td>
                                <td ng-click="editItem(r.id)">
                                    <a data-toggle="modal" data-target="#itemModal" ng-click="editItem(r.id)"
                                       class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> შეცვლა</a>
                                    <a ng-click="deleteItem(r.id)" class="btn btn-danger btn-xs"><i
                                            class="fa fa-trash-o"></i> წაშლა</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <!-- end project list -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->


</div>
<%@include file="footer2.jsp" %>
