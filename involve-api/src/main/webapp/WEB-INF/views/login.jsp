<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<link rel="stylesheet" href="resources/css/style.css" />
<link rel="stylesheet" href="resources/css/bootstrap-yeti.min.css" />
<link rel="stylesheet" href="resources/css/jquery-ui.css" />
<script type="text/javascript" src="resources/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/angular.min.js"></script>
<script type="text/javascript" src="resources/js/global_util.js"></script>
<script type="text/javascript" src="resources/js/global_error_handler.js"></script>
<script type="text/javascript" src="resources/js/misc.js"></script>

<!-- Bootstrap -->
<link href="resources/gentelella/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="resources/gentelella/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="resources/gentelella/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- Animate.css -->
<link href="resources/gentelella/vendors/animate.css/animate.min.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="resources/gentelella/build/css/custom.min.css" rel="stylesheet">

<script type="text/javascript">
    var app = angular.module("app", []);
    app.controller("loginCtrl", function ($scope, $http, $location) {
        var absUrl = $location.absUrl();
        $scope.uri = "";
        if (absUrl.split("?")[1]) {
            $scope.uri = absUrl.split("?")[1].split("=")[1];
        }
        $scope.user = [];
        $scope.login = function () {

            /*if($scope.user.email==$scope.user.password){
                window.location="home";
            }*/
            function errorLogin(res) {
                alert('არასწორი მომხმარებელი ან პაროლი');
            }
            ajaxCall($http, "login?uri=" + $scope.uri + "&email=" + $scope.user.email + "&password=" + $scope.user.password, null, reload, errorLogin);
        };
    });

</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ავტორიზაცია</title>
    </head>
    <body class="login" ng-app="app">
        <div class="container" data-role="none" ng-controller="loginCtrl">

            <div class="login_wrapper">
            <div class="animate form login_form">
                <section class="login_content">
                    <form>
                        <h1>ჩაერთე</h1>
                        <div>
                            <input type="text" class="form-control" placeholder="მომხმარებელი" ng-model="user.email"  required="" />
                        </div>
                        <div>
                            <input type="password" class="form-control" placeholder="პაროლი" ng-model="user.password" required="" />
                        </div>
                        <div>
                            <input type="submit" style="margin-left: 0;float:none;" class="btn btn-success" ng-click="login()" value="ავტორიზაცია" />
                            <%--<a class="reset_pass" href="#">Lost your password?</a>--%>
                        </div>

                        <div class="clearfix"></div>

                        <div class="separator">
                            <p class="change_link">New to site?
                                <a href="#" class="to_register"> Create Account </a>
                            </p>

                            <div class="clearfix"></div>
                            <br />

                            <div>
                                <h1><i class="fa fa-paw"></i> Join Reform</h1>
                                <p>©2017 All Rights Reserved. Economy.ge</p>
                            </div>
                        </div>
                    </form>
                </section>
            </div>
            </div>

           <%-- <div class="row">
                <div class="col-md-12">
                    <br />
                    <div class="row">
                        <div class="col-md-12">
                            <div class="center-block" id="logo"></div>
                            <div class="center-block" id="slogan"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">
                            <form>
                                <div class="row">
                                    <br/>
                                    <div class="col-md-10 col-md-offset-1">
                                        <div class="form-group"  id="UserNameGroup">
                                            <input type="text" id="username" name="email" ng-model="user.email"  class="form-control input-sm" placeholder="მომხმარებელი" />
                                        </div>
                                        <div class="form-group"  id="PasswordGroup">
                                            <input type="password" id="password" name="password" ng-model="user.password"  class="form-control input-sm" placeholder="პაროლი" />
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group text-center">
                                    <input type="submit" id="auth" class="btn btn-primary btn-xs" ng-click="login()" value="ავტორიზაცია" />
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>--%>
        </div>

    </body>
</html>
