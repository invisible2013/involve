<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header2.jsp" %>

<script>
    var app = angular.module("app", []);
    app.controller("usersCtrl", function ($scope, $http, $filter) {

        $scope.users = [];
        $scope.selectedUser = {};

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

        function getUserTypes(res) {
            $scope.userTypes = res.data;
        }

        ajaxCall($http, "users/get-user-types", null, getUserTypes);

        function getGenders(res) {
            $scope.genders = res.data;
        }

        ajaxCall($http, "users/get-genders", null, getGenders);

        $scope.saveItem = function () {
            ajaxCall($http, "users/save-user", angular.toJson($scope.user), reload);
        };
        $scope.editUser = function (userId) {
            if (userId != undefined) {
                var selected = $filter('filter')($scope.users, {id: userId});
                $scope.user = selected[0];
            }
        };

        $scope.viewUserItem = function (userId) {
            function getUser(res) {
                $scope.selectedUser = res.data;
                console.log(res.data);
            }

            ajaxCall($http, "users/get-user-by-id?itemId=" + userId, null, getUser);
        }
        $scope.deleteUser = function (userId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                ajaxCall($http, "users/delete-user?itemId=" + userId, null, reload);
            }
            return;
        };
        $scope.groupFilter = function (item) {
            return item.usersGroup.id != 2;
        };
        $scope.showItem = function () {
            $scope.user = {'groupId': 1, 'statusId': 1, 'typeId': 1};
        }
    });
</script>

<div class="right_col" ng-controller="usersCtrl">

    <div class="modal fade" id="itemModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="myModalLabel">მომხმარებლის დამატება</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-sm-12">
                            <label class="control-label">მომხმარებლის ტიპი</label>
                            <select class="form-control input-sm" ng-model="user.typeId">
                                <option ng-repeat="t in userTypes" value="{{t.id}}">{{t.name}}</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-12" ng-show="user.typeId==1">
                            <label class="control-label">სქესი</label>
                            <select class="form-control input-sm" ng-model="user.genderId">
                                <option ng-repeat="g in genders" value="{{g.id}}">{{g.name}}</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-6" ng-show="user.typeId==1">
                            <label class="control-label">სახელი</label>
                            <input type="text" ng-model="user.firstName"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-6" ng-show="user.typeId==1">
                            <label class="control-label">გვარი</label>
                            <input type="text" ng-model="user.lastName"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-12" ng-show="user.typeId!=1">
                            <label class="control-label">ორგანიზაციის დასახელება</label>
                            <input type="text" ng-model="user.orgName"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="control-label">საიდენთიფიკაციო N</label>
                            <input type="text" ng-model="user.idNumber"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="control-label">ტელეფონი</label>
                            <input type="text" ng-model="user.phone"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="control-label">ელ-ფოსტა</label>
                            <input type="text" ng-model="user.email"
                                   class="form-control ng-pristine ng-valid">
                        </div>

                        <div class="form-group col-sm-6">
                            <label class="control-label">ჯგუფი</label>
                            <select class="form-control input-sm" ng-model="user.groupId">
                                <option ng-repeat="u in userGroups" value="{{u.id}}">{{u.name}}</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="control-label">სტატუსი</label>
                            <select class="form-control input-sm" ng-model="user.statusId">
                                <option ng-repeat="s in userStatuses" value="{{s.id}}">{{s.name}}</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="control-label">პაროლი</label>
                            <input type="password" ng-model="user.password"
                                   class="form-control ng-pristine ng-valid">
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

    <div class="modal fade" id="userViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="viewModalLabel">მომხმარებლის ინფორმაცია</h4>
                </div>
                <div class="modal-body">
                    <div class="row">

                        <table class="table table-striped">
                            <tr>
                                <th class="col-md-4 text-right">მომხმარებელი :</th>
                                <td>{{selectedUser.name}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">ელ-ფოსტა :</th>
                                <td>{{selectedUser.email}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">ტელეფონი :</th>
                                <td>{{selectedUser.phone}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">საიდენთ.N :</th>
                                <td>{{selectedUser.idNumber}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">სქესი :</th>
                                <td>{{selectedUser.genderName}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">ასაკობრივი ჯგუფი :</th>
                                <td>{{selectedUser.ageRangeName}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">დასაქმების სფერო(ფიზ) :</th>
                                <td>{{selectedUser.activitySphereName}}<br/>
                                    {{selectedUser.otherSphereName}}
                                </td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">საქმიანობის სფერო :</th>
                                <td>{{selectedUser.sphereName}}<br/>
                                    {{selectedUser.otherSphereName}}
                                </td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">განათლება :</th>
                                <td>{{selectedUser.educationLevelName}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">პროფესია :</th>
                                <td>{{selectedUser.profession}}</td>
                            </tr>
                            <tr>
                                <th class="col-md-4 text-right">საწარმოს ზომა :</th>
                                <td>{{selectedUser.enterpriseSizeName}}</td>
                            </tr>
                            <tr>
                            <th class="col-md-4 text-right">ეკონომიკური აქტიურობის სტატუსი :</th>
                            <td>{{selectedUser.economyActivityStatusName}}</td>
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

    <div class="page-title">
        <div class="title_left">
            <h3>მომხმარებლები
            </h3>
        </div>
    </div>

    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>მომხმარებლები</h2>

                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#itemModal"
                            ng-click="showItem();">დამატება
                    </button>

                    <table class="table table-striped table-hover projects" id="userList">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>სახელი</th>
                            <th>სტატუსი</th>
                            <th>ელ-ფოსტა</th>
                            <th>ტელეფონი</th>
                            <th>საიდენთ. N</th>
                            <th>ტიპი</th>
                            <th>ჯგუფი</th>

                            <th style="width: 215px;">#Edit</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="i in users">
                            <td>

                                <a data-toggle="modal" data-target="#userViewModal" ng-click="viewUserItem(i.id)"
                                   class="btn btn-link btn-xs">{{$index+1}}</a>
                            </td>
                            <td>{{i.name}}</td>
                            <td><span class="label label-success" ng-show="i.statusId==1">{{i.statusName}}</span>
                                <span class="label label-warning" ng-show="i.statusId==2">{{i.statusName}}</span>
                            </td>
                            <td><i class="fa fa-envelope"> </i> {{i.email}}</td>
                            <td><i class="fa fa-phone"> </i> {{i.phone}}</td>
                            <td>{{i.idNumber}}</td>
                            <td>{{i.typeName}}</td>
                            <td>{{i.groupName}}</td>

                            <td>
                                <a data-toggle="modal" data-target="#userViewModal" ng-click="viewUserItem(i.id)"
                                   class="btn btn-default btn-xs"><i class="fa fa-info"></i> ინფო</a>
                                <a data-toggle="modal" data-target="#itemModal" ng-click="editUser(i.id)"
                                   class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> შეცვლა</a>
                                <a ng-click="deleteUser(i.id)" class="btn btn-danger btn-xs"><i
                                        class="fa fa-trash-o"></i> წაშლა</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer2.jsp" %>
