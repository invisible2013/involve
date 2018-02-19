<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header2.jsp" %>
<script>
    $(document).ready(function () {

    });

    var app = angular.module("app", []);
    app.controller("homeCtrl", function ($scope, $http, $filter) {
        $scope.items = [];
        $scope.reformTypes = [];
        $scope.details = [];
        $scope.detailsRows = [1];
        $scope.start = 0;
        $scope.limit = 20;
        $scope.size = 0;
        $scope.loadCount = 0;


        function getSuccessSportsmans(res) {
            $scope.items = $scope.items.concat(res.data.list);
            $scope.size = res.data.size;
            $scope.loadCount = $scope.items.length;
        }

        ajaxCall($http, "initiate/get-initiates?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);


        $scope.loadMore = function () {
            $scope.start = $scope.start + $scope.limit;
            ajaxCall($http, "initiate/get-initiates?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);
        };
        $scope.loadAll = function () {
            $scope.start = $scope.start + $scope.limit;
            $scope.limit = $scope.size;
            ajaxCall($http, "initiate/get-initiates?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);
        };

        $scope.showReform = function () {
            $scope.reform = {
                'reformTypeId': 1,
                'progressBarPercent1': 0
            };
        };


        $scope.saveReform = function () {
            console.log($scope.reform);
            function saveSuccessReform(res) {
                location.reload();
            };
            ajaxCall($http, "reform/save-reform", angular.toJson($scope.reform), saveSuccessReform);
        };





        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "initiate/delete-initiate?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.itemFiles = function (itemId) {
            window.location = "reformFiles?itemId=" + itemId;
        };
        $scope.itemSession = function (itemId) {
            window.location = "session?itemId=" + itemId;
        };


        $scope.setNoteAsHtml = function (text) {
            CKEDITOR.instances.ckNote.setData(text);
        };
        $scope.getNoteAsHtml = function () {
            return CKEDITOR.instances.ckNote.getData();
        };
    });
</script>


<!-- page content -->
<div class="right_col" ng-controller="homeCtrl">



    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3> ინიციატივები
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
                        <h2>ინიციატივები</h2>

                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            <li></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">

                        <p>მომხმარებლის მიერ შემოთავაზებული ინიციატივები</p>

                        <!-- start project list -->
                        <table class="table table-striped table-hover projects">
                            <thead>
                            <tr>
                                <th style="width: 1%">#</th>
                                <th>მომხმარებელი</th>
                                <th>სფერო</th>
                                <th>დასახელება</th>
                                <th>აღწერა</th>
                                <th>საჭიროება</th>
                                <th>დადებითი/უარყოფითი შედეგები</th>
                                <th >#Edit</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="r in items">
                                <td>
                                    <a data-toggle="modal" data-target="#reformViewModal" ng-click="editItem(r.id)"
                                       class="btn btn-link btn-xs">{{$index+1}}</a>
                                </td>
                                <td>
                                    <a>{{r.userName}}</a>
                                    <br/>
                                    <small>თარიღი: {{r.createDate}}</small>
                                </td>
                                <td>{{r.sphereName}} {{r.otherSphereName}}</td>
                                <td>{{r.name}}</td>
                                <td>{{r.description}}</td>
                                <td>{{r.necessity}}</td>
                                <td>{{r.advantages}}</td>
                                <td ng-click="editItem(r.id)">
                                    <%--  <a data-toggle="modal" data-target="#reformViewModal" ng-click="editItem(r.id)"
                                         class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> ნახვა</a>--%>
                                    <a ng-click="deleteItem(r.id)" class="btn btn-danger btn-xs"><i
                                            class="fa fa-trash-o"></i> წაშლა</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <!-- end project list -->
                        <input type="button" ng-show="size>loadCount" class="btn btn-info" value="მეტის ჩატვირთვა"
                               ng-click="loadMore();">
                        <input type="button" ng-show="size>loadCount" class="btn btn-primary" value="ყველა"
                               ng-click="loadAll();">
                        <br/><br/>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->


</div>
<%@include file="footer2.jsp" %>
