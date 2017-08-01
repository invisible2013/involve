<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header2.jsp" %>
<script>

    var app = angular.module("app", []);
    app.controller("filesCtrl", function ($scope, $http, $location) {
        var absUrl = $location.absUrl();
        $scope.selectedItemId = 0;
        $scope.fileTypeId = 1;
        $scope.fileName = "";
        $scope.types = [];
        if (absUrl.split("?")[1]) {
            if (absUrl.split("?")[1].split("=")[1]) {
                $scope.selectedItemId = absUrl.split("?")[1].split("=")[1];
            }
        }
        var a = {id: '1', name: 'სურათი'};
        var b = {id: '2', name: 'ვიდეო'};
        $scope.types.push(a);
        $scope.types.push(b);

        function getSuccessItems(res) {
            $scope.items = res.data;
        }

        ajaxCall($http, "reform/get-reform-files?itemId=" + $scope.selectedItemId, null, getSuccessItems);

        $scope.saveItem = function () {
            if (parseInt($scope.fileTypeId) != 2) {
                var oMyForm = new FormData();
                oMyForm.append("itemId", $scope.selectedItemId);
                oMyForm.append("fileTypeId", $scope.fileTypeId);
                oMyForm.append("fileName", $scope.fileName);
                oMyForm.append("file", $('#documentId')[0].files[0]);
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

        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "reform/delete-file?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.open = function (name) {
            window.open('upload/get-file?identifier=' + name);
        };


        $scope.updateSelection = function (position, itemId) {
            angular.forEach($scope.items, function (subscription, index) {
                if (subscription.id == itemId && (subscription.main == null || subscription.main == false))
                    ajaxCall($http, "reform/set-main-file?itemId=" + itemId, null, reload);
            });
        };
    });
</script>

<div class="right_col" ng-controller="filesCtrl">

    <div class="page-title">
        <div class="title_left">
            <h3>რეფორმის ფაილები
            </h3>
        </div>
    </div>

    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>ფაილები</h2>

                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">


                    <form id="sportsmanForm">
                        <div class="form-group col-md-6">
                            <label class="control-label">ფაილის ტიპი</label>
                            <select class="form-control input-sm" ng-model="fileTypeId">
                                <option ng-repeat="r in types" value="{{r.id}}">{{r.name}}</option>
                            </select>
                        </div>
                        <div class="clear"></div>
                        <div class="form-group col-sm-6" ng-show="fileTypeId==1">
                            <label class="control-label">აირჩიეთ</label>
                            <input type="file" id="documentId" name="file" class="form-control upload-file">
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

                    <div class="col-md-6" ng-show="items.length>0">
                        <table class="table table-striped table-hover" id="itemList">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>ფაილის ტიპი</th>
                                <th>ფაილი</th>
                                <th>მისამართი</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tr ng-repeat="s in items">
                                <td>{{$index+1}}</td>
                                <td>{{s.fileTypeId==1? "სურათი":"ვიდეო"}}</td>
                                <td><a class="btn btn-xs" ng-click="open(s.fileName);" ng-show="s.fileTypeId==1">
                                    <img src="upload/get-file?identifier={{s.fileName}}" class="img-thumbnail"
                                         style="height: 60px;" height="60">
                                </a></td>
                                <td><span ng-show="s.fileTypeId==1">{{s.fileName}}</span> <a ng-show="s.fileTypeId==2" href="{{s.fileName}}" target="_blank">{{s.fileName}}</a></td>
                                <td style="min-width: 75px;">
                                    <a ng-click="deleteItem(s.id)" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> წაშლა</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer2.jsp" %>
