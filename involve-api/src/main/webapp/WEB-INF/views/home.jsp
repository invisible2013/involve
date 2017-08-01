<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header2.jsp" %>
<script>
    $(document).ready(function () {
        /*$('#birthDate').datepicker({
         dateFormat: 'dd/mm/yy',
         yearRange: "-150:+0",
         changeMonth: true,
         changeYear: true
         });
         $('#startDate').datepicker({
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
    app.controller("homeCtrl", function ($scope, $http, $filter) {
        $scope.type = "details";
        $scope.reforms = [];
        $scope.reformTypes = [];
        $scope.details = [];
        $scope.detailsRows = [1];
        $scope.start = 0;
        $scope.limit = 20;
        $scope.size = 0;
        $scope.loadCount = 0;

        function getSuccessTypes(res) {
            $scope.reformTypes = res.data;
        }

        ajaxCall($http, "reform/get-reform-types", null, getSuccessTypes);

        function getSuccessSportsmans(res) {
            console.log(res.data);
            $scope.reforms = $scope.reforms.concat(res.data.list);
            $scope.size = res.data.size;
            $scope.loadCount = $scope.reforms.length;
        }

        ajaxCall($http, "reform/get-reforms?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);


        $scope.loadMore = function () {
            $scope.start = $scope.start + $scope.limit;
            ajaxCall($http, "reform/get-reforms?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);
        };
        $scope.loadAll = function () {
            $scope.start = $scope.start + $scope.limit;
            $scope.limit = $scope.size;
            ajaxCall($http, "reform/get-reforms?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);
        };

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

        $scope.saveReform = function () {
            console.log($scope.reform);
            function saveSuccessReform(res) {
                location.reload();
            };
            ajaxCall($http, "reform/save-reform", angular.toJson($scope.reform), saveSuccessReform);
        };

        $scope.addDetailRow = function () {
            var size = $scope.detailsRows.length;
            $scope.detailsRows.push(size + 1);
            $scope.details[size + 1] = $scope.details[1];
        };
        $scope.removeDetail = function (index) {
            $scope.detailsRows.splice(index, 1);
            if ($scope.reform.reformDetails) {
                $scope.reform.reformDetails.splice(index, 1);
            }
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
                var selected = $filter('filter')($scope.reforms, {id: itemId}, true);
                $scope.reform = selected[0];
                $scope.detailsRows = [];
                angular.forEach($scope.reform.reformDetails, function (value, index) {
                    $scope.detailsRows.push(index + 1);
                });
            }
        };
        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "reform/delete-reform?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.itemFiles = function (itemId) {
            window.location = "reformFiles?itemId=" + itemId;
        };

        $scope.open = function (name) {
            window.open('file/draw/' + name + '/');
        };
        $scope.setNoteAsHtml = function (text) {
            CKEDITOR.instances.ckNote.setData(text);
        };
        $scope.getNoteAsHtml = function () {
            return CKEDITOR.instances.ckNote.getData();
        };
    });
</script>


<!-- page content -->
<div class="right_col" ng-controller="homeCtrl">

    <div class="modal fade" id="reformModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="myModalLabel">რეფორმის დამატება</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-sm-12">
                            <label class="control-label">დასახელება</label>
                            <input type="text" id="name" ng-model="reform.name"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-md-12">
                            <label class="control-label">რეფორმის ტიპი</label>
                            <select class="form-control" ng-model="reform.reformTypeId">
                                <option ng-repeat="t in reformTypes" value="{{t.id}}">{{t.name}}</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="control-label">ზოგადი ინფორმაცია</label>
                            <textarea rows="4" ng-model="reform.generalInfo" class="form-control ng-pristine ng-valid">
                            </textarea>
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="control-label">საერთაშორისო გამოცდილება</label>
                            <textarea rows="4" ng-model="reform.experience" class="form-control ng-pristine ng-valid">
                            </textarea>
                        </div>

                        <div class="form-group col-sm-6">
                            <label class="control-label">1 პროგრეს ბარი დასახელება</label>
                            <input type="text" ng-model="reform.progressBarName1"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="control-label">1 პროგრეს ბარი პროცენტი</label>
                            <input type="number" ng-model="reform.progressBarPercent1" min="0" max="100"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="control-label">2 პროგრეს ბარი დასახელება</label>
                            <input type="text" ng-model="reform.progressBarName2"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="control-label">2 პროგრეს ბარი პროცენტი</label>
                            <input type="number" ng-model="reform.progressBarPercent2" min="0" max="100"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="control-label">3 პროგრეს ბარი დასახელება</label>
                            <input type="text" ng-model="reform.progressBarName3"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="control-label">3 პროგრეს ბარი პროცენტი</label>
                            <input type="number" ng-model="reform.progressBarPercent3" min="0" max="100"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="control-label col-sm-12">დამატებითი ინფორმაცია</label>
                            <div class="row">
                                <div ng-repeat="d in detailsRows">
                                    <div class="col-md-5" class="form-group">
                                        <input type="text" placeholder="დასახელება"
                                               ng-model="reform.reformDetails[d - 1].name"
                                               class="form-control input-sm">
                                    </div>
                                    <div class="col-md-5" class="form-group">
                                        <input type="text" placeholder="მნიშვნელობა"
                                               ng-model="reform.reformDetails[d - 1].value"
                                               class="form-control input-sm">
                                    </div>
                                    <div class="col-md-1" ng-show="$index == 0">
                                        <a class="btn btn-xs">
                                            <span class="glyphicon glyphicon-plus" ng-click="addDetailRow()"></span>
                                        </a>
                                    </div>
                                    <div class="col-md-1" ng-show="$index > 0">
                                        <a class="btn btn-xs row">
                                                        <span class="glyphicon glyphicon-remove"
                                                              ng-click="removeDetail($index)"></span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">დახურვა</button>
                    <button type="button" class="btn btn-success" ng-click="saveReform()">შენახვა</button>
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
                    <h4 class="modal-title" id="viewModalLabel">რეფორმის დეტალური ინფორმაცია</h4>
                </div>
                <div class="modal-body">
                    <div class="row">

                        <table class="table table-striped">
                            <tr>
                                <th class="col-md-4 text-right">რეფორმის დასახელება :</th>
                                <td>{{reform.name}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">რეფორმის ტიპი :</th>
                                <td>{{reform.reformTypeName}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">ზოგადი ინფორმაცია :</th>
                                <td>{{reform.generalInfo}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">საერთაშორისო გამოცდილება :</th>
                                <td>{{reform.experience}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">1 პროგრეს ბარი :</th>
                                <td>{{reform.progressBarName1}} {{reform.progressBarPercent1}}%</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">2 პროგრეს ბარი :</th>
                                <td>{{reform.progressBarName2}} {{reform.progressBarPercent2}}%</td>
                            </tr>
                            <tr ng-show="reform.progressBarPercent3>0">
                                <th class="col-md-4 text-right">3 პროგრეს ბარი :</th>
                                <td>{{reform.progressBarName3}} {{reform.progressBarPercent3}}%</td>
                            </tr>
                            <tr ng-repeat="d in reform.reformDetails">
                                <th class="col-md-4 text-right">{{d.name}} :</th>
                                <td>{{d.value}}</td>
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
                <h3>რეფორმები
                    <small>დეტალური ინფორმაცია</small>
                </h3>
            </div>

            <div class="title_right">
                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="ძებნა...">
                        <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="clearfix"></div>

        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>რეფორმები</h2>

                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            <li></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">

                        <p>აქ შეგიძლიათ დაამატოთ დაარედაქტიროთ ან წაშალოთ რეფორმები</p>
                        <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#reformModal"
                                ng-click="showReform();">დამატება
                        </button>
                        <!-- start project list -->
                        <table class="table table-striped table-hover projects">
                            <thead>
                            <tr>
                                <th style="width: 1%">#</th>
                                <th style="width: 20%">დასახელება</th>
                                <th>ტიპი</th>
                                <th>1 პროგრეს ბარი</th>
                                <th>2 პროგრეს ბარი</th>
                                <th>3 პროგრეს ბარი</th>
                                <th style="width: 20%">#Edit</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="r in reforms">
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
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar"
                                             data-transitiongoal="{{r.progressBarPercent2}}"
                                             style="width:{{r.progressBarPercent2}}%;"></div>
                                    </div>
                                    <small>{{r.progressBarPercent2}}% {{r.progressBarName2}}</small>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar"
                                             data-transitiongoal="{{r.progressBarPercent3}}"
                                             style="width:{{r.progressBarPercent3}}%;"></div>
                                    </div>
                                    <small>{{r.progressBarPercent3}}% {{r.progressBarName3}}</small>
                                </td>
                                <td ng-click="editItem(r.id)">
                                  <%--  <a data-toggle="modal" data-target="#reformViewModal" ng-click="editItem(r.id)"
                                       class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> ნახვა</a>--%>
                                    <a data-toggle="modal" data-target="#reformModal" ng-click="editItem(r.id)"
                                       class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> შეცვლა</a>
                                    <a ng-click="deleteItem(r.id)" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> წაშლა</a>
                                    <a ng-click="itemFiles(r.id)" class="btn btn-dark btn-xs"><i class="fa fa-file"></i> ფაილი</a>
                                    <a ng-click="itemSession(r.id)" class="btn btn-primary btn-xs"><i class="fa fa-bar-chart"></i> სესია</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <!-- end project list -->
                        <input type="button" ng-show="size>loadCount" class="btn btn-info" value="მეტის ჩატვირთვა"
                               ng-click="loadMore();">
                        <input type="button" ng-show="size>loadCount" class="btn btn-primary" value="ყველა"
                               ng-click="loadAll();">
                        <br/><br/>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->


</div>
<%@include file="footer2.jsp" %>
