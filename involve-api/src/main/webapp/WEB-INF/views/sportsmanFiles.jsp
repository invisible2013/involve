<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>

    var app = angular.module("app", ["ui.bootstrap"]);
    app.controller("filesCtrl", function ($scope, $http, $location) {
        var absUrl = $location.absUrl();
        $scope.selectedSportsmanId = 0;
        $scope.fileTypeId = 1;
        $scope.fileUrl = "";
        $scope.types = [];
        if (absUrl.split("?")[1]) {
            if (absUrl.split("?")[1].split("=")[1]) {
                $scope.selectedSportsmanId = absUrl.split("?")[1].split("=")[1];
            }
        }
        var a = {id: '1', name: 'სურათი'};
        var b = {id: '3', name: 'ვიდეო'};
        $scope.types.push(a);
        $scope.types.push(b);

        function getSuccessItems(res) {
            $scope.items = res.data;
        }

        ajaxCall($http, "sportsman/get-sportsman-files?sportsmanId=" + $scope.selectedSportsmanId, null, getSuccessItems);

        $scope.saveItem = function () {
            if (parseInt($scope.fileTypeId) != 3) {
                var oMyForm = new FormData();
                oMyForm.append("sportsmanId", $scope.selectedSportsmanId);
                oMyForm.append("fileTypeId", $scope.fileTypeId);
                oMyForm.append("fileUrl", $scope.fileUrl);
                oMyForm.append("file", $('#documentId')[0].files[0]);
                $.ajax({
                    url: 'sportsman/add-image',
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
                var sportsmanFile = {
                    "sportsmanId": $scope.selectedSportsmanId,
                    "fileTypeId": $scope.fileTypeId,
                    "fileUrl": $scope.fileUrl
                };
                ajaxCall($http, "sportsman/save-file-url", angular.toJson(sportsmanFile), reload);
            }

        };

        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "sportsman/delete-file?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.open = function (name) {
            window.open('upload/get-file?identifier=' + name);
        };


        $scope.updateSelection = function (position, itemId) {
            angular.forEach($scope.items, function (subscription, index) {
                if (subscription.id == itemId && (subscription.main == null || subscription.main == false))
                    ajaxCall($http, "sportsman/set-main-file?itemId=" + itemId, null, reload);
            });
        };
    });
</script>

<div class="col-md-12" ng-controller="filesCtrl">

    <form id="sportsmanForm">
        <div class="col-md-12 text-center"><h4>დოკუმენტები</h4></div>
        <div class="form-group col-md-6">
            <label class="control-label">ფაილის ტიპი</label>
            <select class="form-control input-sm" ng-model="fileTypeId">
                <option ng-repeat="r in types" value="{{r.id}}">{{r.name}}</option>
            </select>
        </div>
        <div class="clear"></div>
        <div class="form-group col-sm-6" ng-show="fileTypeId==1">
            <input type="file" id="documentId" name="file" class="form-control input-sm upload-file">
        </div>
        <div class="form-group col-md-6" ng-show="fileTypeId==3">
            <label class="control-label">ვიდეოს ლინკი</label>
            <input type="text" ng-model="fileUrl" class="form-control input-sm" placeholder="ვიდეოს ლინკი">
        </div>

        <div class="form-group col-md-12">
            <button class="btn btn-primary btn-sm" ng-click="saveItem();">შენახვა</button>
        </div>
    </form>

    <div class="col-md-10">
        <table class="table table-striped table-hover" id="sportsmanList">
            <tr>
                <th>ID</th>
                <th>ფაილის ტიპი</th>
                <th>ფაილი</th>
                <th>მისამართი</th>
                <th>მთავარი</th>
                <th></th>
            </tr>
            <tr ng-repeat="s in items">
                <td>{{$index+1}}</td>
                <td>{{s.fileTypeName}}</td>
                <td><a class="btn btn-xs" ng-click="open(s.fileName);" ng-show="s.fileTypeId!=3">
                    <img src="upload/get-file?identifier={{s.fileName}}" class="img-thumbnail"
                         style="height: 60px;" height="60">
                </a></td>
                <td>{{s.fileUrl}}</td>
                <td>
                    <input type="checkbox" ng-model="s.main" ng-click="updateSelection($index, s.id)"
                           ng-show="s.fileTypeId==1">
                </td>
                <td style="min-width: 75px;">
                    <button type="button" class="btn btn-xs" ng-click="deleteItem(s.id)">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                </td>
            </tr>
        </table>
    </div>

</div>
<%@include file="footer.jsp" %>
