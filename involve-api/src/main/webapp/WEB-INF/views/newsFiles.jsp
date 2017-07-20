<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>
    $(document).ready(function () {

    });
    var app = angular.module("app", ["ui.bootstrap"]);
    app.controller("filesCtrl", function ($scope, $http,$location) {
        var absUrl = $location.absUrl();
        $scope.selectedItemId = 0;
        $scope.fileTypeId = 1;
        $scope.types = [];
        if (absUrl.split("?")[1]) {
            if (absUrl.split("?")[1].split("=")[1]) {
                $scope.selectedItemId = absUrl.split("?")[1].split("=")[1];
            }
        }
        var a = {id: '1', name: 'სურათი'};
        var b = {id: '2', name: 'ფაილი'};
        $scope.types.push(a);
        $scope.types.push(b);
        function getSuccessItems(res) {
            $scope.items = res.data;
        }
        ajaxCall($http, "news/get-news-files?itemId="+$scope.selectedItemId, null, getSuccessItems);

        $scope.saveItem = function () {
            var oMyForm = new FormData();
            oMyForm.append("newsId", $scope.selectedItemId);
            oMyForm.append("fileTypeId", $scope.fileTypeId);
            oMyForm.append("file", $('#documentId')[0].files[0]);
            $.ajax({
                url: 'news/add-file',
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

        };

        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "news/delete-file?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.open = function (name) {
            window.open('upload/get-file?identifier=' + name);
        };

        $scope.updateSelection = function (position, itemId) {
            angular.forEach($scope.items, function (subscription, index) {
                if (subscription.id == itemId && (subscription.main == null || subscription.main == false))
                    ajaxCall($http, "news/set-main-file?itemId=" + itemId, null, reload);
            });
        };
    });
</script>

<div class="col-md-12" ng-controller="filesCtrl">

    <form id="sportsmanForm" >
        <div class="col-md-12 text-center"><h4>სურათები</h4></div>
        <div class="form-group col-md-6">
            <label class="control-label">ფაილის ტიპი</label>
            <select class="form-control input-sm" ng-model="fileTypeId">
                <option ng-repeat="r in types" value="{{r.id}}">{{r.name}}</option>
            </select>
        </div>
        <div class="clear"></div>
        <div class="form-group col-sm-6">
            <input type="file" id="documentId" name="file" class="form-control input-sm upload-file">
        </div>

        <div class="form-group col-md-12">
            <button  class="btn btn-primary btn-sm" ng-click="saveItem();">შენახვა</button>
        </div>
    </form>

    <div class="col-md-6">
        <table class="table table-striped table-hover" id="sportsmanList">
            <tr>
                <th>ID</th>
                <th>ტიპი</th>
                <th>ფაილი</th>
                <th>მთავარი</th>
                <th></th>
            </tr>
            <tr ng-repeat="s in items">
                <td>{{$index+1}}</td>
                <td>{{s.fileTypeName}}</td>
                <td><a class="btn btn-xs" ng-click="open(s.fileName);">
                    <img src="upload/get-file?identifier={{s.fileName}}" class="img-thumbnail"
                         style="height: 60px;" height="60">
                </a></td>
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
