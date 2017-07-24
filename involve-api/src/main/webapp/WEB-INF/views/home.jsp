<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header2.jsp" %>
<script>
    $(document).ready(function () {
        $('#birthDate').datepicker({
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
        });
    });

    var app = angular.module("app", ["ui.bootstrap"]);
    app.filter('asHtml', function ($sce) {
        return function (val) {
            return $sce.trustAsHtml(val);
        };
    });
    app.controller("homeCtrl", function ($scope, $http, $filter, $location) {
        $scope.type = "details";
        $scope.sportsmans=[];
        $scope.start = 0;
        $scope.limit = 40;
        $scope.size = 0;
        $scope.loadCount = 0;
        var absUrl = $location.absUrl();
        if (absUrl.split("?")[1]) {
            if (absUrl.split("?")[1].split("&")[1]) {
                $scope.type = absUrl.split("?")[1].split("&")[0].split("=")[1];
            }
        }
        if ($scope.type) {
            $('.nav li.active').removeClass('active');
            $('.nav li#' + $scope.type).addClass('active');
        }
        function getSuccessSportsmans(res) {
            console.log(res.data);
            $scope.sportsmans=$scope.sportsmans.concat(res.data.list);
            $scope.size = res.data.size;
            $scope.loadCount = $scope.sportsmans.length;
        }

        ajaxCall($http, "reform/get-reforms?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);




        $scope.loadMore = function () {
            $scope.start=$scope.start+$scope.limit;
            ajaxCall($http, "sportsman/get-sportsmans?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);
        };
        $scope.loadAll = function () {
            $scope.start=$scope.start+$scope.limit;
            $scope.limit=$scope.size;
            ajaxCall($http, "sportsman/get-sportsmans?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);
        };

        $scope.addNewSportsman = function () {
            $('#sportsmanList').addClass("hidden");
            $('#sportsmanPanel').removeClass("hidden");
            $scope.type = 'details';
            $scope.sportsman = {
                'sportTypeId': 1,
                'regionId': 1,
                'cityId': 1,
                'genderId': 1,
                'rankId': 1,
                'paraSportsman': 0,
                'olimpic': 0,
                'paralympic': 0
            };
        };

        $scope.testPanelHide = function () {
            $('#sportsmanPanel').addClass("hidden");
            $('#sportsmanList').removeClass("hidden");
        }

        $scope.saveSportsman = function () {
            $scope.sportsman.paraSportsman = $('input[name=paraSportsmanRadio]:checked').val() == 1 ? true : false;
            $scope.sportsman.olimpic = $('input[name=olimpicRadio]:checked').val() == 1 ? true : false;
            $scope.sportsman.paralympic = $('input[name=paralympicRadio]:checked').val() == 1 ? true : false;
            $scope.sportsman.biography = $scope.getNoteAsHtml();
            console.log($scope.sportsman);
            function saveSuccessSportsman(res) {

                if ($('#documentId')[0].files[0]) {
                    var oMyForm = new FormData();
                    oMyForm.append("sportsmanId", $scope.sportsman.id);
                    oMyForm.append("file", $('#documentId')[0].files[0]);
                    $.ajax({
                        url: 'sportsman/add-image',
                        data: oMyForm,
                        dataType: 'text',
                        processData: false,
                        contentType: "multipart/form-data",
                        type: 'POST',
                        success: function (data) {
                            location.reload();
                        }
                    }).success(function (data) {
                        location.reload();
                    }).error(function (data, status, headers, config) {
                        location.reload();
                    });
                } else {
                    location.reload();
                }
            };
            ajaxCall($http, "sportsman/save-sportsman", angular.toJson($scope.sportsman), saveSuccessSportsman);
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

        $scope.editSportsman = function (sportsmanId) {
            if (sportsmanId != undefined) {
                $('#sportsmanList').addClass("hidden");
                $('#sportsmanPanel').removeClass("hidden");
                var selected = $filter('filter')($scope.sportsmans, {id: sportsmanId}, true);
                $scope.sportsman = selected[0];
                $scope.setNoteAsHtml($scope.sportsman.biography);
                $scope.getCities();
                //$scope.user.userGroupId = $scope.user.usersGroup.id;
                //$scope.user.userStatusId = $scope.user.usersStatus.id;
            }
        };
        $scope.deleteSportsman = function (sportsmanId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (sportsmanId != undefined) {
                    ajaxCall($http, "sportsman/delete-sportsman?sportsmanId=" + sportsmanId, null, reload);
                }
            }
        };

        $scope.addFiles = function (itemId) {
            window.location = "sportsmanFiles?sportsmanId=" + itemId;
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
<div class="right_col" ng-controller="homeCtrl" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>რეფორმები <small>დეტალური ინფორმაცია</small></h3>
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
                        <h2>Projects</h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">

                        <p>Simple table with project listing with progress and editing options</p>

                        <!-- start project list -->
                        <table class="table table-striped projects">
                            <thead>
                            <tr>
                                <th style="width: 1%">#</th>
                                <th style="width: 20%">Project Name</th>
                                <th>Team Members</th>
                                <th>Project Progress</th>
                                <th>Status</th>
                                <th style="width: 20%">#Edit</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>#</td>
                                <td>
                                    <a>Pesamakini Backend UI</a>
                                    <br />
                                    <small>Created 01.01.2015</small>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                    </ul>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="57"></div>
                                    </div>
                                    <small>57% Complete</small>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs">Success</button>
                                </td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                    <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                    <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                </td>
                            </tr>
                            <tr>
                                <td>#</td>
                                <td>
                                    <a>Pesamakini Backend UI</a>
                                    <br />
                                    <small>Created 01.01.2015</small>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                    </ul>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="47"></div>
                                    </div>
                                    <small>47% Complete</small>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs">Success</button>
                                </td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                    <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                    <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                </td>
                            </tr>
                            <tr>
                                <td>#</td>
                                <td>
                                    <a>Pesamakini Backend UI</a>
                                    <br />
                                    <small>Created 01.01.2015</small>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                    </ul>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="77"></div>
                                    </div>
                                    <small>77% Complete</small>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs">Success</button>
                                </td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                    <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                    <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                </td>
                            </tr>
                            <tr>
                                <td>#</td>
                                <td>
                                    <a>Pesamakini Backend UI</a>
                                    <br />
                                    <small>Created 01.01.2015</small>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                    </ul>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="60"></div>
                                    </div>
                                    <small>60% Complete</small>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs">Success</button>
                                </td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                    <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                    <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                </td>
                            </tr>
                            <tr>
                                <td>#</td>
                                <td>
                                    <a>Pesamakini Backend UI</a>
                                    <br />
                                    <small>Created 01.01.2015</small>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                    </ul>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="12"></div>
                                    </div>
                                    <small>12% Complete</small>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs">Success</button>
                                </td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                    <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                    <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                </td>
                            </tr>
                            <tr>
                                <td>#</td>
                                <td>
                                    <a>Pesamakini Backend UI</a>
                                    <br />
                                    <small>Created 01.01.2015</small>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                    </ul>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="35"></div>
                                    </div>
                                    <small>35% Complete</small>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs">Success</button>
                                </td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                    <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                    <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                </td>
                            </tr>
                            <tr>
                                <td>#</td>
                                <td>
                                    <a>Pesamakini Backend UI</a>
                                    <br />
                                    <small>Created 01.01.2015</small>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                    </ul>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="87"></div>
                                    </div>
                                    <small>87% Complete</small>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs">Success</button>
                                </td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                    <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                    <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                </td>
                            </tr>
                            <tr>
                                <td>#</td>
                                <td>
                                    <a>Pesamakini Backend UI</a>
                                    <br />
                                    <small>Created 01.01.2015</small>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                    </ul>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="77"></div>
                                    </div>
                                    <small>77% Complete</small>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs">Success</button>
                                </td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                    <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                    <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                </td>
                            </tr>
                            <tr>
                                <td>#</td>
                                <td>
                                    <a>Pesamakini Backend UI</a>
                                    <br />
                                    <small>Created 01.01.2015</small>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                        <li>
                                            <img src="images/user.png" class="avatar" alt="Avatar">
                                        </li>
                                    </ul>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="77"></div>
                                    </div>
                                    <small>77% Complete</small>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs">Success</button>
                                </td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                    <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                    <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <!-- end project list -->


                        <div id="sportsmanPanel" class="hidden">
                            <br/>
                            <div ng-show="type == 'details'">
                                <form name="sportsmanForm">
                                    <div class="col-md-12 text-center"><h4>სპორტსმენის შესახებ</h4></div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">სახელი</label>
                                        <input type="text" ng-model="sportsman.firstName" class="form-control input-sm"
                                               placeholder="სახელი">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">გვარი</label>
                                        <input type="text" ng-model="sportsman.lastName" class="form-control input-sm" placeholder="გვარი">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">სქესი</label>
                                        <select class="form-control input-sm" ng-model="sportsman.genderId">
                                            <option ng-repeat="g in genders" value="{{g.id}}">{{g.name}}</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">დაბადების თარიღი</label>
                                        <input type="text" id="birthDate" ng-model="sportsman.birthDate" class="form-control input-sm"
                                               placeholder="დაბადების თარიღი">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label">სპორტის სახეობა</label>
                                        <select class="form-control input-sm" ng-model="sportsman.sportTypeId">
                                            <option ng-repeat="st in sportTypes" value="{{st.id}}">{{st.name}}</option>
                                        </select>
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label class="control-label">რეგიონი</label>
                                        <select class="form-control input-sm" ng-model="sportsman.regionId" ng-change="getCities()">
                                            <option ng-repeat="r in regions" value="{{r.id}}">{{r.name}}</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">ქალაქი</label>
                                        <select class="form-control input-sm" ng-model="sportsman.cityId">
                                            <option ng-repeat="r in cities" value="{{r.id}}">{{r.name}}</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">სოფელი</label>
                                        <input type="text" ng-model="sportsman.district" class="form-control input-sm" placeholder="სოფელი">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">რანგი</label>
                                        <select class="form-control input-sm" ng-model="sportsman.rankId">
                                            <option ng-repeat="r in ranks" value="{{r.id}}">{{r.name}}</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">სიმაღლე</label>
                                        <input type="text" ng-model="sportsman.height" class="form-control input-sm" placeholder="სიმაღლე">
                                    </div>
                                    <div class="clear"></div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">შენიშვნა</label>
                                        <input type="text" ng-model="sportsman.comment" class="form-control input-sm"
                                               placeholder="შენიშვნა">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">პარა სპორტსმენი</label>
                                        <div class="mt-radio-inline">
                                            <label class="mt-radio">
                                                <input type="radio" ng-model="sportsman.paraSportsman" name="paraSportsmanRadio"
                                                       id="optionsRadios4"
                                                       value="1">კი
                                                <span></span>
                                            </label>
                                            <label class="mt-radio">
                                                <input type="radio" ng-model="sportsman.paraSportsman" name="paraSportsmanRadio"
                                                       id="optionsRadios5"
                                                       value="0" checked=""> არა
                                                <span></span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">ოლიმპიელი</label>
                                        <div class="mt-radio-inline">
                                            <label class="mt-radio">
                                                <input type="radio" ng-model="sportsman.olimpic" name="olimpicRadio" id="optionsRadios41"
                                                       value="1">კი
                                                <span></span>
                                            </label>
                                            <label class="mt-radio">
                                                <input type="radio" ng-model="sportsman.olimpic" name="olimpicRadio" id="optionsRadios51"
                                                       value="0" checked=""> არა
                                                <span></span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">პარალიმპიელი</label>
                                        <div class="mt-radio-inline">
                                            <label class="mt-radio">
                                                <input type="radio" ng-model="sportsman.paralympic" name="paralympicRadio"
                                                       id="optionsRadiosparalympic"
                                                       value="1">კი
                                                <span></span>
                                            </label>
                                            <label class="mt-radio">
                                                <input type="radio" ng-model="sportsman.paralympic" name="paralympicRadio"
                                                       id="optionsRadiosparalympic2"
                                                       value="0" checked=""> არა
                                                <span></span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="clear"></div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">კარიერის დასაწყისი</label>
                                        <input type="text" id="startDate" ng-model="sportsman.careerStartDate" class="form-control input-sm"
                                               placeholder="დაბადების თარიღი">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">კარიერის დასასრული</label>
                                        <input type="text" id="endDate" ng-model="sportsman.careerEndDate" class="form-control input-sm"
                                               placeholder="დაბადების თარიღი">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="control-label">გარდაცვალების თარიღი</label>
                                        <input type="text" id="deathDate" ng-model="sportsman.deathDate" class="form-control input-sm"
                                               placeholder="დაბადების თარიღი">
                                    </div>
                                    <div class="form-group col-md-10">
                                        <label class="control-label">ბიოგრაფია</label>
                                        <script>
                                            $(function () {
                                                CKEDITOR.replace('ckNote');
                                            });
                                        </script>
                                        <textarea id="ckNote" class="form-control" ng-model="sportsman.biography" rows="2"
                                                  style="width: 500px;"></textarea>
                                    </div>
                                    <div class="clear"></div>
                                    <div class="form-group col-md-12">
                                        <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveSportsman();">
                                        <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="testPanelHide();">
                                    </div>
                                </form>
                            </div>
                            <div ng-show="type == 'files'">
                            </div>
                        </div>

                        <div id="sportsmanList">
                            <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewSportsman();">დამატება</button>
                            <table class="table table-striped table-hover">
                                <tr>
                                    <th>ID</th>
                                    <th>სახელი გვარი</th>
                                    <th>სპორტის სახეობა</th>
                                    <th>დაბადების თარიღი</th>
                                    <th>რეგიონი</th>
                                    <th></th>
                                </tr>
                                <tr ng-repeat="s in sportsmans">
                                    <td>{{$index+1}}</td>
                                    <td>{{s.firstName}} {{s.lastName}}</td>
                                    <td>{{s.sportTypeName}}</td>
                                    <td>{{s.birthDate}}</td>
                                    <td>{{s.regionName}} {{s.cityName}}</td>
                                    <td style="min-width: 75px;">
                                        <button type="button" class="btn btn-xs" ng-click="editSportsman(s.id)" title="რედაქტირება">
                                            <span class="glyphicon glyphicon-pencil"></span>
                                        </button>
                                        <button type="button" class="btn btn-xs" ng-click="deleteSportsman(s.id)" title="წაშლა">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                        <button type="button" class="btn btn-xs" ng-click="addFiles(s.id)" title="სურათების ატვირთვა">
                                            <span class="glyphicon glyphicon-file"></span>
                                        </button>
                                    </td>
                                </tr>
                            </table>
                            <input type="button" ng-show="size>loadCount" class="btn btn-primary btn-xs" value="მეტის ჩატვირთვა" ng-click="loadMore();">
                            <input type="button" ng-show="size>loadCount" class="btn btn-primary btn-xs" value="ყველა" ng-click="loadAll();">
                            <br/><br/>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->



</div>
<%@include file="footer2.jsp" %>
