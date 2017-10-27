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
        if (date.length > 0) {
            var arraydates = date.split("/");
            var newdate = arraydates[1] + "/" + arraydates[0] + "/" + arraydates[2];
            return new Date(newdate);
        }
    }
    function reverseDateString(date) {
        if (date.length > 0) {
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
    app.directive('ckEditor', [function () {
        return {
            require: '?ngModel',
            link: function ($scope, elm, attr, ngModel) {

                var ck = CKEDITOR.replace(elm[0]);

                ck.on('pasteState', function () {
                    $scope.$apply(function () {
                        ngModel.$setViewValue(ck.getData());
                    });
                });

                ngModel.$render = function (value) {
                    ck.setData(ngModel.$modelValue);
                };
            }
        };
    }]);
    app.controller("homeCtrl", function ($scope, $http, $filter, $location) {
        var absUrl = $location.absUrl();
        $scope.selectedItemId = 0;
        $scope.selectedItem = {};
        $scope.reforms = [];

        $scope.items = [];
        $scope.files = [];
        $scope.reformTypes = [];
        $scope.details = [];
        $scope.detailsRows = [1];
        $scope.start = 0;
        $scope.limit = 20;
        $scope.size = 0;
        $scope.loadCount = 0;
        $scope.types = [];
        $scope.sessionFileTypes = [];
        $scope.type = "details";
        $scope.pollRows = [1];
        $scope.sessionPolls = [];
        $scope.poll = {};
        $scope.poll.answers = [];
        $scope.selectedSessionId = 0;
        $scope.sessionFileTypeId = 0;

        $scope.reform = {
            'reformTypeId': 1,
            'progressBarPercent1': 0
        };
        $scope.reform.reformDetails = [];

        if (absUrl.split("?")[1]) {
            if (absUrl.split("?")[1].split("=")[1]) {
                var str = absUrl.split("?")[1].split("=")[1];
                if (str.split("#")[1]) {
                    $scope.selectedItemId = str.split("#")[0];
                    $scope.type = str.split("#")[1].split("/")[1];
                } else {
                    $scope.selectedItemId = absUrl.split("?")[1].split("=")[1];
                }
            }
        }

        if ($scope.type) {
            $('.nav li.active').removeClass('active');
            $('.nav li#' + $scope.type + "Tab").addClass('active');
            $('.tab-content div.active').removeClass('active in');
            $('.tab-content div#' + $scope.type).addClass('active in');

        }
        var a = {id: '1', name: 'სურათი'};
        var b = {id: '2', name: 'ვიდეო'};
        var c = {id: '3', name: 'დოკუმენტი'};
        $scope.types.push(a);
        $scope.types.push(b);
        $scope.types.push(c);
        $scope.sessionFileTypes.push(c);

        if ($scope.selectedItemId > 0) {
            function getSuccessReform(res) {
                $scope.selectedItem = res.data;
                $scope.reform = res.data;
                $scope.detailsRows = [];
                if ($scope.reform.reformDetails.length == 0) {
                    $scope.detailsRows = [1];
                }
                angular.forEach($scope.reform.reformDetails, function (value, index) {
                    $scope.detailsRows.push(index + 1);
                });
            }

            ajaxCall($http, "reform/get-reform?itemId=" + $scope.selectedItemId, null, getSuccessReform);

            function getSuccessItems(res) {
                $scope.files = res.data;
            }

            ajaxCall($http, "reform/get-reform-files?itemId=" + $scope.selectedItemId, null, getSuccessItems);


            function getSuccessSession(res) {
                $scope.items = res.data;
            }

            ajaxCall($http, "reform/get-reform-sessions?itemId=" + $scope.selectedItemId, null, getSuccessSession);
        }

        function getSuccessTypes(res) {
            $scope.reformTypes = res.data;
        }

        ajaxCall($http, "reform/get-reform-types", null, getSuccessTypes);


        $scope.showReform = function () {
            $scope.reform = {
                'reformTypeId': 1,
                'progressBarPercent1': 0
            };
        };

        $scope.saveReform = function () {
            console.log($scope.reform);
            function saveSuccessReform(res) {
                if ($scope.selectedItemId > 0) {
                    uploadLogo();
                } else {
                    $scope.selectedItemId = res.data.id;
                    uploadLogo();
                    //window.location = "addReform?reformId=" + newItemId;
                }

                function uploadLogo() {
                    if ($('#reformLogoId')[0].files[0] != undefined) {
                        var oMyForm = new FormData();
                        oMyForm.append("itemId", $scope.selectedItemId);
                        oMyForm.append("file", $('#reformLogoId')[0].files[0]);
                        $.ajax({
                            url: 'reform/add-reform-image',
                            data: oMyForm,
                            dataType: 'text',
                            processData: false,
                            contentType: false,
                            type: 'POST',
                            success: function (data) {
                                //location.reload();
                                window.location = "addReform?reformId=" + $scope.selectedItemId;
                            }
                        }).success(function (data) {
                            //location.reload();
                            window.location = "addReform?reformId=" + $scope.selectedItemId;
                        }).error(function (data, status, headers, config) {
                            //location.reload();
                            window.location = "addReform?reformId=" + $scope.selectedItemId;
                        });
                    }
                    window.location = "addReform?reformId=" + $scope.selectedItemId;
                }
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


        $scope.itemFiles = function (itemId) {
            window.location = "reformFiles?itemId=" + itemId;
        };
        $scope.itemSession = function (itemId) {
            window.location = "session?itemId=" + itemId;
        };

        //Files
        $scope.saveItem = function () {
            if (parseInt($scope.fileTypeId) != 2) {
                var oMyForm = new FormData();
                oMyForm.append("itemId", $scope.selectedItemId);
                oMyForm.append("fileTypeId", $scope.fileTypeId);
                oMyForm.append("fileName", "");
                oMyForm.append("file", $('#reformfileId')[0].files[0]);
                $.ajax({
                    url: 'reform/add-image',
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
            } else {
                var itemFile = {
                    "itemId": $scope.selectedItemId,
                    "fileTypeId": $scope.fileTypeId,
                    "fileName": $scope.fileName
                };
                ajaxCall($http, "reform/save-file-name", angular.toJson(itemFile), reload);
            }
        };

        $scope.open = function (name) {
            window.open('upload/get-file?identifier=' + name);
        };

        //Session

        $scope.saveSession = function () {
            if ($scope.selectedItemId > 0) {
                console.log($scope.item);
                $scope.item.reformId = $scope.selectedItemId;
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
            } else {
                alert("გთხოვთ გვერდი ჩატვირთოთ თავიდან.");
                return;
            }
        };


        $scope.saveSessionFile = function () {
            if (parseInt($scope.fileTypeId) != 2 && $('#sessionfileId')[0].files[0]) {
                var sessionFileForm = new FormData();
                sessionFileForm.append("itemId", $scope.selectedSessionId);
                sessionFileForm.append("fileTypeId", $scope.sessionFileTypeId);
                sessionFileForm.append("fileName", "");
                sessionFileForm.append("file", $('#sessionfileId')[0].files[0]);
                $.ajax({
                    url: 'reform/add-session-file',
                    data: sessionFileForm,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function (data) {
                        $scope.fileItem($scope.selectedSessionId);
                    }
                }).success(function (data) {
                    $scope.fileItem($scope.selectedSessionId);
                }).error(function (data, status, headers, config) {
                    $scope.fileItem($scope.selectedSessionId);
                });
            }
        };


        $scope.editItem = function (itemId) {
            if (itemId != undefined) {
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
        $scope.fileItem = function (itemId) {
            if (itemId != undefined) {
                sessionfileId.value = "";
                $scope.selectedSessionId = itemId;
                function getSuccessSessionFiles(res) {
                    $scope.sessionFiles = res.data;
                }

                ajaxCall($http, "reform/get-session-files?itemId=" + itemId, null, getSuccessSessionFiles);
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

        $scope.deleteReformFile = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "reform/delete-reform-file?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.deleteSessionFile = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "reform/delete-session-file?itemId=" + itemId, null, reload);
                }
            }
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
                                               id="single_cal3"
                                        >
                                        <span class="fa fa-calendar-o form-control-feedback left"
                                              aria-hidden="true"></span>

                                    </div>
                                </div>
                            </div>
                            <%-- <input type="text" ng-model="item.startDate"
                                    class="form-control ng-pristine ng-valid">--%>
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
                    <button type="button" class="btn btn-success" ng-click="saveSession()">შენახვა</button>
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
                            <button type="button" class="btn btn-sm btn-success" ng-click="addPollItem()">დამატება
                            </button>
                        </div>
                        <div class="col-md-12">

                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th style="width: 1%">#</th>
                                    <th style="width: 30%">კითხვა</th>
                                    <th>პასუხები</th>
                                    <th style="width: 5%">#Edit</th>
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
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="fileModal" tabindex="-1" role="dialog"
         aria-labelledby="fileModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="fileModalLabel">ფაილების დამატება</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <form id="fileForm">
                            <div class="form-group col-md-8">
                                <label class="control-label">ფაილის ტიპი</label>
                                <select class="form-control input-sm" ng-model="sessionFileTypeId">
                                    <option ng-repeat="r in sessionFileTypes" value="{{r.id}}">{{r.name}}</option>
                                </select>
                            </div>
                            <div class="clear"></div>
                            <div class="form-group col-sm-8">
                                <label class="control-label">აირჩიეთ</label>
                                <input type="file" id="sessionfileId" name="file"
                                       class="form-control upload-file">
                            </div>
                            <div class="clearfix"></div>
                            <div class="form-group col-md-8 text-right">
                                <button class="btn btn-success" ng-click="saveSessionFile();">შენახვა</button>
                            </div>
                        </form>

                        <div class="col-md-12" ng-show="sessionFiles.length>0">
                            <table class="table table-striped table-hover" id="fileList">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>ტიპი</th>
                                    <th>ფაილი</th>
                                    <th style="width: 50%">მისამართი</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tr ng-repeat="s in sessionFiles">
                                    <td>{{$index+1}}</td>
                                    <td>{{s.fileTypeId==1? "სურათი":(s.fileTypeId==2?"ვიდეო":"დოკუმენტი")}}
                                    </td>
                                    <td><a class="btn btn-xs" ng-click="open(s.fileName);"
                                           ng-show="s.fileTypeId==1">
                                        <img src="upload/get-file?identifier={{s.fileName}}"
                                             class="img-thumbnail"
                                             style="height: 60px;" height="60">
                                    </a></td>
                                    <td><span ng-show="s.fileTypeId==1">{{s.fileName}}</span>
                                        <a ng-show="s.fileTypeId==2" href="{{s.fileName}}" target="_blank">{{s.fileName}}</a>
                                        <a ng-show="s.fileTypeId==3" class="btn btn-xs" ng-click="open(s.fileName);">
                                            {{s.fileName}}</a>
                                    </td>
                                    <td style="min-width: 75px;">
                                        <a ng-click="deleteSessionFile(s.id)"
                                           class="btn btn-danger btn-xs"><i
                                                class="fa fa-trash-o"></i> წაშლა</a>
                                    </td>
                                </tr>
                            </table>
                        </div>

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
                <h3>რეფორმა
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
                        <h2>რეფორმა</h2>

                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            <li></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div class="" role="tabpanel" data-example-id="togglable-tabs">
                            <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                <li id="detailsTab" role="presentation" class="active"><a href="#details" id="home-tab"
                                                                                          role="tab"
                                                                                          data-toggle="tab"
                                                                                          aria-expanded="true">მთავარი</a>
                                </li>
                                <li id="filesTab" role="presentation" class=""><a href="#files" role="tab"
                                                                                  id="profile-tab"
                                                                                  data-toggle="tab"
                                                                                  aria-expanded="false">ფაილები</a>
                                </li>
                                <li id="sessionTab" role="presentation" class=""><a href="#session" role="tab"
                                                                                    id="profile-tab2"
                                                                                    data-toggle="tab"
                                                                                    aria-expanded="false">სესიები</a>
                                </li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <div role="tabpanel" class="tab-pane fade active in" id="details"
                                     aria-labelledby="home-tab">

                                    <div class="form-group col-sm-10">
                                        <label class="control-label">დასახელება</label>
                                        <input type="text" id="name" ng-model="reform.name"
                                               class="form-control ng-pristine ng-valid">
                                    </div>
                                    <div class="form-group col-sm-10">
                                        <label class="control-label">ლოგო</label>
                                        <input type="file" id="reformLogoId" name="file"
                                               class="form-control upload-file">
                                    </div>
                                    <div class="form-group col-md-10">
                                        <label class="control-label">რეფორმის ტიპი</label>
                                        <select class="form-control" ng-model="reform.reformTypeId">
                                            <option ng-repeat="t in reformTypes" value="{{t.id}}">{{t.name}}
                                            </option>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-10">
                                        <label class="control-label">აღწერა</label>
                                        <textarea rows="4" ng-model="reform.note"
                                                  class="form-control ng-pristine ng-valid">
                            </textarea>
                                    </div>
                                    <div class="form-group col-sm-10">
                                        <label class="control-label">რეფორმის საჭიროება</label>
                                        <textarea data-ng-model="reform.generalInfo" rows="2"
                                                  placeholder="მნიშვნელობა" data-ck-editor
                                                  class="form-control"></textarea>
                                    </div>
                                    <div class="form-group col-sm-10">
                                        <label class="control-label">საერთაშორისო გამოცდილება</label>
                                        <textarea data-ng-model="reform.experience" rows="2"
                                                  placeholder="მნიშვნელობა" data-ck-editor
                                                  class="form-control"></textarea>
                                    </div>

                                    <div class="form-group col-sm-5">
                                        <label class="control-label">1 პროგრეს ბარი დასახელება</label>
                                        <input type="text" ng-model="reform.progressBarName1"
                                               class="form-control ng-pristine ng-valid">
                                    </div>
                                    <div class="form-group col-sm-5">
                                        <label class="control-label">1 პროგრეს ბარი პროცენტი</label>
                                        <input type="number" ng-model="reform.progressBarPercent1" min="0" max="100"
                                               class="form-control ng-pristine ng-valid">
                                    </div>
                                    <div class="form-group col-sm-5">
                                        <label class="control-label">2 პროგრეს ბარი დასახელება</label>
                                        <input type="text" ng-model="reform.progressBarName2"
                                               class="form-control ng-pristine ng-valid">
                                    </div>
                                    <div class="form-group col-sm-5">
                                        <label class="control-label">2 პროგრეს ბარი პროცენტი</label>
                                        <input type="number" ng-model="reform.progressBarPercent2" min="0" max="100"
                                               class="form-control ng-pristine ng-valid">
                                    </div>
                                    <div class="form-group col-sm-5">
                                        <label class="control-label">3 პროგრეს ბარი დასახელება</label>
                                        <input type="text" ng-model="reform.progressBarName3"
                                               class="form-control ng-pristine ng-valid">
                                    </div>
                                    <div class="form-group col-sm-5">
                                        <label class="control-label">3 პროგრეს ბარი პროცენტი</label>
                                        <input type="number" ng-model="reform.progressBarPercent3" min="0" max="100"
                                               class="form-control ng-pristine ng-valid">
                                    </div>
                                    <div class="form-group col-sm-10">
                                        <label class="control-label col-sm-12">დამატებითი ინფორმაცია</label>
                                        <div class="row">

                                            <div ng-repeat="d in detailsRows">
                                                <div class="col-md-5 form-group">
                                                    <input type="text" placeholder="დასახელება"
                                                           ng-model="reform.reformDetails[d - 1].name"
                                                           class="form-control input-sm">
                                                </div>
                                                <div class="col-md-5 form-group">
                                                    <textarea data-ng-model="reform.reformDetails[d - 1].value" rows="3"
                                                              placeholder="მნიშვნელობა" data-ck-editor></textarea>
                                                    <%-- <textarea id="ckNote{{d-1}}" rows="3"
                                                               ng-model="reform.reformDetails[d - 1].value"
                                                               placeholder="მნიშვნელობა"
                                                               class="form-control ng-pristine ng-valid editors">
                                                     </textarea>--%>
                                                </div>
                                                <div class="col-md-1 form-group" ng-show="$index == 0">
                                                    <a class="btn btn-xs">
                                                            <span class="glyphicon glyphicon-plus"
                                                                  ng-click="addDetailRow()"></span>
                                                    </a>
                                                </div>
                                                <div class="col-md-1 form-group" ng-show="$index > 0">
                                                    <a class="btn btn-xs row">
                                                        <span class="glyphicon glyphicon-remove"
                                                              ng-click="removeDetail($index)"></span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <button type="button" class="btn btn-success" ng-click="saveReform()">შენახვა
                                        </button>
                                    </div>

                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="files"
                                     aria-labelledby="profile-tab">

                                    <form id="sportsmanForm">
                                        <div class="form-group col-md-6">
                                            <label class="control-label">ფაილის ტიპი</label>
                                            <select class="form-control input-sm" ng-model="fileTypeId">
                                                <option ng-repeat="r in types" value="{{r.id}}">{{r.name}}</option>
                                            </select>
                                        </div>
                                        <div class="clear"></div>
                                        <div class="form-group col-sm-6" ng-show="fileTypeId==1||fileTypeId==3">
                                            <label class="control-label">აირჩიეთ</label>
                                            <input type="file" id="reformfileId" name="file"
                                                   class="form-control upload-file">
                                        </div>
                                        <div class="form-group col-md-6" ng-show="fileTypeId==2">
                                            <label class="control-label">ვიდეოს ლინკი</label>
                                            <input type="text" ng-model="fileName" class="form-control "
                                                   placeholder="ვიდეოს ლინკი">
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="form-group col-md-6 text-right">
                                            <button class="btn btn-success" ng-click="saveItem();">შენახვა</button>
                                        </div>
                                    </form>

                                    <div class="col-md-6" ng-show="files.length>0">
                                        <table class="table table-striped table-hover" id="itemList">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>ტიპი</th>
                                                <th>ფაილი</th>
                                                <th style="width: 50%">მისამართი</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tr ng-repeat="s in files">
                                                <td>{{$index+1}}</td>
                                                <td>{{s.fileTypeId==1? "სურათი":(s.fileTypeId==2?"ვიდეო":"დოკუმენტი")}}
                                                </td>
                                                <td><a class="btn btn-xs" ng-click="open(s.fileName);"
                                                       ng-show="s.fileTypeId==1">
                                                    <img src="upload/get-file?identifier={{s.fileName}}"
                                                         class="img-thumbnail"
                                                         style="height: 60px;" height="60">
                                                </a></td>
                                                <td><span ng-show="s.fileTypeId==1">{{s.fileName}}</span> <a
                                                        ng-show="s.fileTypeId==2" href="{{s.fileName}}" target="_blank">{{s.fileName}}</a>
                                                </td>
                                                <td style="min-width: 75px;">
                                                    <a ng-click="deleteReformFile(s.id)"
                                                       class="btn btn-danger btn-xs"><i
                                                            class="fa fa-trash-o"></i> წაშლა</a>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>

                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="session"
                                     aria-labelledby="profile-tab">
                                    <button class="btn btn-primary pull-right" data-toggle="modal"
                                            data-target="#itemModal"
                                            ng-click="showItem();">დამატება
                                    </button>
                                    <!-- start project list -->
                                    <table class="table table-striped table-hover projects">
                                        <thead>
                                        <tr>
                                            <th style="width: 1%">#</th>
                                            <th style="width: 15%">დასახელება</th>
                                            <th style="width: 10%">ლოგო</th>
                                            <th style="width: 400px;">აღწერა</th>
                                            <th>გასული დრო</th>
                                            <th>შესრულებული სამუშაო</th>
                                            <th style="width: 335px;">#Edit</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr ng-repeat="r in items">
                                            <td>
                                                <a data-toggle="modal" data-target="#reformViewModal"
                                                   ng-click="editItem(r.id)"
                                                   class="btn btn-link btn-xs">{{$index+1}}</a>
                                            </td>
                                            <td>
                                                <a>{{r.name}}</a>
                                                <br/>
                                                <small>თარიღი: {{r.createDate}}</small>
                                            </td>
                                            <td>
                                                <a class="btn btn-xs" ng-click="open(r.imageName);"
                                                >
                                                    <img src="upload/get-file?identifier={{r.imageName}}"
                                                         class="img-thumbnail"
                                                         style="height: 60px;" height="60">
                                                </a>
                                            </td>
                                            <td>{{r.note}}</td>
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
                                            <td>
                                                <a data-toggle="modal" data-target="#itemModal"
                                                   ng-click="editItem(r.id)"
                                                   class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> შეცვლა</a>
                                                <a ng-click="deleteItem(r.id)" class="btn btn-danger btn-xs"><i
                                                        class="fa fa-trash-o"></i> წაშლა</a>
                                                <a data-toggle="modal" data-target="#questionModal"
                                                   ng-click="questionItem(r.id)"
                                                   class="btn btn-default btn-xs"><i class="fa fa-area-chart"></i>
                                                    კითხვარი</a>
                                                <a data-toggle="modal" data-target="#fileModal"
                                                   ng-click="fileItem(r.id)"
                                                   class="btn btn-default btn-xs"><i class="fa fa-file"></i>
                                                    ფაილები</a>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
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
