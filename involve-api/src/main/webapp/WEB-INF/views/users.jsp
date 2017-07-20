<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>

<script>
    var app = angular.module("app", []);
    app.controller("userCtrl", function ($scope, $http, $filter) {
        $scope.user = {'userGroupId': 1, 'userStatusId': 1};
        $scope.users = [];

        function getUsers(res) {
            $scope.users = res.data;
            console.log(res.data);
        }
        ajaxCall($http, "users/get-users", null, getUsers);
        function getUserGroups(res) {
            $scope.userGroups = res.data;
        }
        ajaxCall($http, "users/get-user-groups", null, getUserGroups);
        function getUserStatuses(res) {
            $scope.userStatuses = res.data;
        }
        ajaxCall($http, "users/get-user-statuses", null, getUserStatuses);

        $scope.addUser = function () {
            ajaxCall($http, "users/add-user", angular.toJson($scope.user), reload);
        };
        $scope.editUser = function (userId) {
            if (userId != undefined) {
                var selected = $filter('filter')($scope.users, {id: userId});
                $scope.user = selected[0];
                $scope.user.userGroupId = $scope.user.usersGroup.id;
                $scope.user.userStatusId = $scope.user.usersStatus.id;
            }
        };
        $scope.deleteUser = function (userId) {
            ajaxCall($http, "user/delete-user", angular.toJson($scope.user), reload);
        };
        $scope.groupFilter = function (item) {
            return item.usersGroup.id != 2;
        };
    });
</script>

<div class="col-md-12" ng-controller="userCtrl">
    <br/>
    <form class="form-horizontal">
        <div class="form-group col-sm-8 ">
            <label class="control-label  col-sm-3">სახელი</label>
            <div class="col-sm-9">
                <input type="text" ng-model="user.name" class="form-control input-sm" >
            </div>
        </div>
        <div class="form-group col-sm-8 ">
            <label class="control-label col-sm-3">ელ.ფოსტა</label>
            <div class="col-sm-9">
                <input type="text" id="" ng-model="user.email" class="form-control input-sm" >
            </div>
        </div>
        <div class="form-group col-sm-8">
            <label class="control-label col-sm-3">ტელეფონი</label>
            <div class="col-xs-9">
                <input type="text" id="" ng-model="user.phone" class="form-control input-sm" >
            </div>
        </div>
        <div class="form-group col-sm-8">
            <label class="control-label col-sm-3">მისამართი</label>
            <div class="col-sm-9">
                <input type="text" id="" ng-model="user.address" class="form-control input-sm" >
            </div>
        </div>
        <div class="form-group col-sm-8 ">
            <label class="control-label col-sm-3">მომხმარებელი</label>
            <div class="col-sm-9">
                <input type="text" id="" ng-model="user.username" class="form-control input-sm" >
            </div>
        </div>
        <div class="form-group col-sm-8">
            <label class="control-label col-sm-3">პაროლი</label>
            <div class="col-sm-9">
                <input type="text" id="" ng-model="user.password" class="form-control input-sm" >
            </div>
        </div>
        <div class="form-group col-sm-8">
            <label class="control-label  col-sm-3">ჯგუფი</label>
            <div class="col-sm-9">
                <select class="form-control input-sm" ng-model="user.userGroupId">
                    <option ng-repeat="g in userGroups" value="{{g.id}}">{{g.description}}</option>
                </select>
            </div>
        </div>
        <div class="form-group col-sm-8">
            <label class="control-label  col-sm-3">სტატუსი</label>
            <div class="col-sm-9">
                <select class="form-control input-sm" ng-model="user.userStatusId">
                    <option ng-repeat="s in userStatuses" value="{{s.id}}">{{s.name}}</option>
                </select>
            </div>
        </div>
        <div class="form-group col-sm-8 text-right">
            <div class="col-sm-12">
                <input id="addPersonItemBtn" type="button" class="btn btn-primary btn-xs" value="შენახვა" ng-click="addUser()">
            </div>
        </div>

    </form>
    <table  class="table table-striped table-hover" id="userList">
        <tr>
            <th >ID</th>
            <th >სახელი</th>
            <th >ელ-ფოსტა</th>
            <th >ტელეფონი</th>
            <th >მისამართი</th>
            <th >ჯგუფი</th>
            <th >სტატუსი</th>
            <th></th>
        </tr>
        <tr ng-repeat="i in users| filter: groupFilter">
            <td>{{$index + 1}}</td>
            <td>{{i.name}}</td>
            <td>{{i.email}}</td>
            <td>{{i.phone}}</td>
            <td>{{i.address}}</td>
            <td>{{i.usersGroup.description}}</td>
            <td>{{i.usersStatus.name}}</td>
            <td>
                <button type="button" class="btn btn-xs" ng-click="editUser(i.id)">
                    <span class="glyphicon glyphicon-pencil"></span>
                </button>
                <button type="button" class="btn btn-xs" ng-click="deleteUser(i.id)">
                    <span class="glyphicon glyphicon-remove"></span>
                </button>
            </td>
        </tr>
    </table>
</div>

<%@include file="footer.jsp" %>
