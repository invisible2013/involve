<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header2.jsp" %>

<script>
    var app = angular.module("app", []);
    app.controller("usersCtrl", function ($scope, $http, $filter, $location) {
        var absUrl = $location.absUrl();
        $scope.currentUserId = 0;
        if (absUrl.split("?")[1]) {
            $scope.currentUserId = absUrl.split("?")[1].split("=")[1];
        }
        function getUser(res) {
            if (res.data.userData) {
                $scope.currentUser = res.data.userData;
            } else {
                $scope.currentUser = res.data;
            }
        }

        if ($scope.currentUserId > 0) {
            ajaxCall($http, "users/get-user-by-id?itemId=" + $scope.currentUserId, {}, getUser);
        } else {
            ajaxCall($http, "get-user", {}, getUser);
        }


        $scope.changePassword = function () {
            if ($scope.password != $scope.passwordTwo) {
                alert("პაროლები არ ემთხვევა ერთმანეთს");
                return;
            } else {
                ajaxCall($http, "users/change-password?password=" + $scope.password, null, reload);
            }
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
                    <h4 class="modal-title" id="myModalLabel">პაროლის ცვლილება</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-sm-12">
                            <label class="control-label">პაროლი</label>
                            <input type="password" ng-model="password"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="control-label">პაროლი</label>
                            <input type="password" ng-model="passwordTwo"
                                   class="form-control ng-pristine ng-valid">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">დახურვა</button>
                    <button type="button" class="btn btn-success" ng-click="changePassword()">შენახვა</button>
                </div>
            </div>
        </div>
    </div>

    <div class="page-title">
        <div class="title_left">
            <h3>მომხმარებლის პროფილი
            </h3>
        </div>
    </div>

    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>ჩემი პროფილი</h2>

                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="col-md-3 col-sm-3 col-xs-12 profile_left">
                        <div class="profile_img">
                            <div id="crop-avatar">
                                <!-- Current avatar -->
                                <img class="img-circle img-responsive avatar-view" src="resources/imgs/user.png"
                                     alt="Avatar"
                                     title="Change the avatar">
                            </div>
                        </div>
                        <h3>{{currentUser.name}}</h3>

                        <ul class="list-unstyled user_data">
                            <li><i class="fa fa-user user-profile-icon"></i> {{currentUser.idNumber}}
                            </li>

                            <li>
                                <i class="fa fa-phone user-profile-icon"></i> {{currentUser.phone}}
                            </li>

                            <li class="m-top-xs">
                                <i class="fa fa-suitcase user-profile-icon"></i> {{currentUser.email}}</a>
                            </li>
                        </ul>

                        <a class="btn btn-success" data-toggle="modal" ng-show="currentUserId==0"
                           data-target="#itemModal"><i class="fa fa-edit m-right-xs"></i> პაროლის შეცვლა</a>
                        <br>

                        <!-- start skills -->
                        <h4>დეტალური ინფორმაცია</h4>
                        <ul class="list-unstyled">
                            <li>
                                <p class="title">ჯგუფი</p>
                                <div class="label label-warning  ">
                                    {{currentUser.groupName}}
                                </div>
                            </li>
                            <li>
                                <p class="title">ტიპი</p>
                                <div class="label label-warning  ">
                                    {{currentUser.typeName}}
                                </div>
                            </li>
                            <li>
                                <p class="title">სტატუსი</p>
                                <div class="label label-warning  ">
                                    {{currentUser.statusName}}
                                </div>
                            </li>

                        </ul>


                    </div>
                    <div class="col-md-9"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer2.jsp" %>
