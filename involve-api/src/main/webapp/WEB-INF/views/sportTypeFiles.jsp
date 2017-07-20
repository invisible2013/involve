<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>
    $(document).ready(function () {

    });
    var app = angular.module("app", ["ui.bootstrap"]);
    app.controller("filesCtrl", function ($scope, $http,$location) {
        var absUrl = $location.absUrl();
        $scope.selectedItemId = 0;
        $scope.fileTypeId = 2;
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
        ajaxCall($http, "parameter/get-sport-type-files?itemId="+$scope.selectedItemId, null, getSuccessItems);

        $scope.saveItem = function () {
            var oMyForm = new FormData();
            oMyForm.append("sportTypeId", $scope.selectedItemId);
            oMyForm.append("fileTypeId", $scope.fileTypeId);
            oMyForm.append("file", $('#documentId')[0].files[0]);
            $.ajax({
                url: 'parameter/add-sport-type-file',
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
                    ajaxCall($http, "parameter/delete-sport-type-file?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.open = function (name) {
            window.open('upload/get-file?identifier=' + name);
        };

    });
</script>

<div class="col-md-12" ng-controller="filesCtrl">

    <form id="sportsmanForm" >
        <div class="col-md-12 text-center"><h4>ფაილები</h4></div>
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
                <th></th>
            </tr>
            <tr ng-repeat="s in items">
                <td>{{$index+1}}</td>
                <td>{{s.fileTypeName}}</td>
                <td><a class="btn btn-xs" ng-click="open(s.fileName);">
                   ფაილი{{$index+1}}
                </a></td>
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
