<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="resources/css/bootstrap-yeti.min.css"/>
    <%--<link rel="stylesheet" href="resources/css/components.min.css"/>--%>
    <link rel="stylesheet" href="resources/css/jquery-ui.css"/>
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="shortcut icon" href="resources/imgs/favicon.ico">
    <script type="text/javascript" src="resources/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="resources/js/jquery-ui.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/js/angular.min.js"></script>
    <script src="resources/js/ui-bootstrap-0.14.3.js" type="text/javascript"></script>
    <script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="resources/js/global_error_handler.js"></script>
    <script type="text/javascript" src="resources/js/global_util.js"></script>
    <script>
        $(document).ready(function () {
            var url = window.location;
            $('.list-group a').filter(function () {
                return this.href.indexOf(url.pathname) > -1;
            }).addClass('active');
            if (url.pathname.indexOf("home") > -1) {
                $('#selected_item').text("რეფორმები");
            } else if (url.pathname.indexOf("referee") > -1) {
                $('#selected_item').text("მსაჯები");
            } else if (url.pathname.indexOf("users") > -1) {
                $('#selected_item').text("მომხმარებლები");
            } else if (url.pathname.indexOf("trainer") > -1) {
                $('#selected_item').text("მწვრთნელები");
            }else if (url.pathname.indexOf("event") > -1) {
                $('#selected_item').text("ღონისძიებები");
            }else if (url.pathname.indexOf("result") > -1) {
                $('#selected_item').text("შედეგები");
            }else if (url.pathname.indexOf("championships") > -1) {
                $('#selected_item').text("ჩემპიონატები");
            }
        });
        menuCtrl = function ($scope, $http) {
            function getUser(res) {
                $scope.currentUser = res.userData.name;
            }

            //ajaxCall($http, "get-user", {}, getUser);
        };
    </script>

</head>
<body ng-app="app">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row" ng-controller="menuCtrl">
                <div class="navbar navbar-inverse">
                    <div class="navbar-header english-font">
                        <a class="navbar-brand" href="#">JOIN</a>
                        <a class="navbar-brand color-red" href="#">REFORMS</a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="logout">გასვლა</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 left-menu">
            <div class="row border-bottom-gray">
                <div class="col-md-12">
                    <h4 class="english-font"><span class="glyphicon glyphicon-stats"></span> Statistic</h4>
                </div>
            </div>
            <br/>
            <br/>
            <br/>

            <div class="row">
                <div class="list-group">
                        <a href="home" class="list-group-item"><span
                                class="glyphicon glyphicon-adjust"></span> რეფორმები</a>
                    <a href="users" class="list-group-item" ng-show="true"><span
                            class="glyphicon glyphicon-user"></span> მომხმარებლები</a>
                </div>
            </div>
        </div>
        <div class="col-md-10">
           <div class="row border-bottom-gray">
                <div class="col-md-12">
                    <h4 id="selected_item"></h4>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">



