<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header2.jsp" %>
<script>

    function getDateById(id) {
        if ($('#' + id).val() != "") {
            var arraydates = $('#' + id).val().split("/");
            var newdate = arraydates[1] + "/" + arraydates[0] + "/" + arraydates[2];
            return newdate;
        }
    }

    function reverseDate(date) {
        if (date && date.length > 0) {
            var arraydates = date.split("/");
            var newdate = arraydates[1] + "/" + arraydates[0] + "/" + arraydates[2];
            return new Date(newdate);
        }
    }
    function reverseDateString(date) {
        if (date && date.length > 0) {
            var arraydates = date.split("/");
            var newdate = arraydates[1] + "/" + arraydates[0] + "/" + arraydates[2];
            return newdate;
        }
    }

    $(document).ready(function () {
        var start = new Date();
        $('input[name="startdate"]').daterangepicker({
            singleDatePicker: true,
            locale: {
                //format: 'DD/MM/YYYY HH:mm'
            }
        });
        $('input[name="enddate"]').daterangepicker({
            singleDatePicker: true,
            startDate: start,
            locale: {
                //format: 'DD/MM/YYYY HH:mm'
            }
        });
    });

    var app = angular.module("app", []);
    app.controller("homeCtrl", function ($scope, $http, $filter, $location) {
        $scope.items = [];
        $scope.sessionPolls = [];
        $scope.poll = {};
        $scope.poll.answers = [];
        $scope.selectedItemId = 0;
        $scope.pollRows = [1];
        $scope.start = 0;
        $scope.limit = 20;
        $scope.size = 0;
        $scope.loadCount = 0;
        var absUrl = $location.absUrl();
        if (absUrl.split("?")[1]) {
            if (absUrl.split("?")[1].split("=")[1]) {
                $scope.selectedItemId = absUrl.split("?")[1].split("=")[1];
            }
        }
        function getSuccessSession(res) {
            if (res.data.list) {
                $scope.items = $scope.items.concat(res.data.list);
                $scope.size = res.data.size;
                $scope.loadCount = $scope.items.length;
            } else {
                $scope.items = res.data;
            }
        }

        ajaxCall($http, "reform/get-all-sessions?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSession);

        $scope.loadMore = function () {
            $scope.start = $scope.start + $scope.limit;
            ajaxCall($http, "reform/get-all-sessions?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSession);
        };
        $scope.loadAll = function () {
            $scope.start = $scope.start + $scope.limit;
            $scope.limit = $scope.size;
            ajaxCall($http, "reform/get-all-sessions?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSession);
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

        $scope.saveItem = function () {
            console.log($scope.item);
            $scope.item.startDate = getDateById('single_cal3');
            $scope.item.endDate = getDateById('single_cal4');
            function saveSuccessItem(res) {
                if ($('#documentId')[0].files[0] != undefined) {
                    var oMyForm = new FormData();
                    oMyForm.append("itemId", $scope.item.id);
                    oMyForm.append("file", $('#documentId')[0].files[0]);
                    $.ajax({
                        url: 'reform/add-session-image',
                        data: oMyForm,
                        dataType: 'text',
                        processData: false,
                        contentType: false,
                        type: 'POST',
                        success: function (data) {
                            location.reload();
                        }
                    }).success(function (data) {
                        location.reload();
                    }).error(function (data, status, headers, config) {
                        location.reload();
                    });
                }
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
                $scope.selectedItemId = itemId;
                var selected = $filter('filter')($scope.items, {id: itemId}, true);
                var item = selected[0];
                $('#single_cal3').val(reverseDateString(item.startDate));
                $('#single_cal4').val(reverseDateString(item.endDate));
                $scope.item = item;
            }
        };

        $scope.questionItem = function (itemId) {
            if (itemId != undefined) {
                $scope.poll.sessionId = itemId;
                function getSuccessSessionPoll(res) {
                    $scope.sessionPolls = res.data;
                }

                ajaxCall($http, "reform/get-session-polls?itemId=" + itemId, null, getSuccessSessionPoll);
            }
        };
        $scope.addPollItem = function () {
            console.log($scope.poll);
            function saveSuccessPoll(res) {
                function getSuccessSessionPoll(res) {
                    $scope.sessionPolls = res.data;
                    var sessionId = $scope.poll.sessionId;
                    $scope.poll = {};
                    $scope.poll.answers = [];
                    $scope.poll.sessionId = sessionId;
                    $scope.pollRows = [1];
                }

                ajaxCall($http, "reform/get-session-polls?itemId=" + $scope.poll.sessionId, null, getSuccessSessionPoll);
            };
            ajaxCall($http, "reform/save-session-poll", angular.toJson($scope.poll), saveSuccessPoll);
        };
        $scope.deletePollItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                function deleteSuccessPoll(res) {
                    function getSuccessSessionPoll(res) {
                        $scope.sessionPolls = res.data;
                    }

                    ajaxCall($http, "reform/get-session-polls?itemId=" + $scope.poll.sessionId, null, getSuccessSessionPoll);
                };
                ajaxCall($http, "reform/delete-session-poll?itemId=" + itemId, angular.toJson($scope.poll), deleteSuccessPoll);
            }
        };
        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "reform/delete-session?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.addPollRow = function () {
            var size = $scope.pollRows.length;
            $scope.pollRows.push(size + 1);
            //$scope.poll.answers[size + 1] = $scope.poll.answers[1];
        };
        $scope.removePoll = function (index) {
            $scope.pollRows.splice(index, 1);
            if ($scope.poll.answers) {
                $scope.poll.answers.splice(index, 1);
            }
        };
        $scope.sessionVote = function (itemId) {
            window.location = "sessionVote?sessionId=" + itemId;
        };


        $scope.open = function (name) {
            window.open('upload/get-file?identifier=' + name);
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
                            <label class="control-label">აღწერა</label>
                            <textarea rows="4" ng-model="item.note"
                                      class="form-control ng-pristine ng-valid">
                            </textarea>
                        </div>
                        <div class="form-group col-sm-6" has-feedback>
                            <label class="control-label">დაწყება</label>
                            <div class="control-group">
                                <div class="controls">
                                    <div class="col-md-12 row xdisplay_inputx form-group has-feedback">
                                        <input type="text" name="startdate" class="form-control has-feedback-left"
                                               id="single_cal3">
                                        <span class="fa fa-calendar-o form-control-feedback left"
                                              aria-hidden="true"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="control-label">დასრულება</label>
                            <div class="control-group">
                                <div class="controls">
                                    <div class="col-md-12 row xdisplay_inputx form-group has-feedback">
                                        <input type="text" name="enddate" class="form-control has-feedback-left"
                                               id="single_cal4"
                                        >
                                        <span class="fa fa-calendar-o form-control-feedback left"
                                              aria-hidden="true"></span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <%--<div class="form-group col-sm-6">
                            <label class="control-label">პროგრეს ბარი დასახელება</label>
                            <input type="text" ng-model="reform.progressBarName"
                                   class="form-control ng-pristine ng-valid">
                        </div>--%>
                        <div class="form-group col-sm-12">
                            <label class="control-label">შესრულებული სამუშაოს პროცენტი</label>
                            <input type="number" ng-model="item.workPercent" min="0" max="100"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="control-label">ლოგო</label>
                            <input type="file" id="documentId" name="file" class="form-control upload-file">
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

    <div class="modal fade bs-example-modal-lg" id="questionModal" tabindex="-1" role="dialog"
         aria-labelledby="questionModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="questionModalLabel">კითხვების დამატება</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-sm-12">
                            <label class="control-label">კითხვა</label>
                            <input type="text" ng-model="poll.name"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="control-label col-sm-12">პასუხები</label>
                            <div class="row">
                                <div ng-repeat="d in pollRows">
                                    <div class="col-md-10 form-group">
                                        <input type="text" placeholder="პასუხი"
                                               ng-model="poll.answers[d - 1].value"
                                               class="form-control input-sm">
                                    </div>
                                    <div class="col-md-1 form-group" ng-show="$index == 0">
                                        <a class="btn btn-xs">
                                            <span class="glyphicon glyphicon-plus" ng-click="addPollRow()"></span>
                                        </a>
                                    </div>
                                    <div class="col-md-1 form-group" ng-show="$index > 0">
                                        <a class="btn btn-xs row">
                                                        <span class="glyphicon glyphicon-remove"
                                                              ng-click="removePoll($index)"></span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12 form-group">
                            <button type="button" class="btn btn-sm btn-primary" ng-click="addPollItem()">დამატება
                            </button>
                        </div>
                        <div class="col-md-12">

                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th style="width: 1%">#</th>
                                    <th style="width: 20%">კითხვა</th>
                                    <th>პასუხები</th>
                                    <th>#Edit</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="r in sessionPolls">
                                    <td>{{$index+1}}</td>
                                    <td>
                                        {{r.name}}
                                    </td>
                                    <td>
                                        <ul ng-repeat="s in r.answers">
                                            <li>{{s.value}}</li>
                                        </ul>
                                    </td>
                                    <td>
                                        <a ng-click="deletePollItem(r.id)" class="btn btn-danger btn-xs"><i
                                                class="fa fa-trash-o"></i> წაშლა</a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">დახურვა</button>
                    <%--<button type="button" class="btn btn-success" ng-click="saveItem()">შენახვა</button>--%>
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

                        <p>აქ შეგიძლიათ ნახოთ ყველა სესია</p>
                        <button ng-show="selectedItemId>0" class="btn btn-primary pull-right" data-toggle="modal"
                                data-target="#itemModal"
                                ng-click="showItem();">დამატება
                        </button>
                        <!-- start project list -->
                        <table class="table table-striped table-hover projects">
                            <thead>
                            <tr>
                                <th style="width: 1%">#</th>
                                <th style="width: 20%">დასახელება</th>
                                <th>ლოგო</th>
                                <th>გასული დრო</th>
                                <th>შესრულებული სამუშაო</th>
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
                                    <a class="btn btn-xs" ng-click="open(r.imageName);" ng-show="r.imageName.length>0">
                                        <img src="upload/get-file?identifier={{r.imageName}}" class="img-thumbnail"
                                             style="height: 60px;" height="60">
                                    </a>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar"
                                             data-transitiongoal="r.timePercent"
                                             style="width:{{r.timePercent}}%;"></div>
                                    </div>
                                    <small>{{r.timePercent}}%</small>
                                    <small>{{r.startDate}} - {{r.endDate}}</small>
                                </td>
                                <td class="project_progress">
                                    <div class="progress progress_sm">
                                        <div class="progress-bar bg-green" role="progressbar"
                                             data-transitiongoal="r.workPercent"
                                             style="width:{{r.workPercent}}%;"></div>
                                    </div>
                                    <small>{{r.workPercent}}%</small>
                                </td>
                                <td ng-click="editItem(r.id)">
                                    <a data-toggle="modal" data-target="#itemModal" ng-click="editItem(r.id)"
                                       class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> შეცვლა</a>
                                    <a ng-click="deleteItem(r.id)" class="btn btn-danger btn-xs"><i
                                            class="fa fa-trash-o"></i> წაშლა</a>
                                    <a data-toggle="modal" data-target="#questionModal" ng-click="questionItem(r.id)"
                                       class="btn btn-default btn-xs"><i class="fa fa-area-chart"></i> კითხვარი</a>
                                    <a ng-click="sessionVote(r.id)" class="btn btn-success btn-xs"><i
                                            class="fa fa-info"></i> ხმები</a>
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
