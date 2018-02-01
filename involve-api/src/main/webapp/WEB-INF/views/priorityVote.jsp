<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header2.jsp" %>
<script>
    $(document).ready(function () {

    });

    var app = angular.module("app", []);
    app.controller("homeCtrl", function ($scope, $http, $location) {
        var absUrl = $location.absUrl();
        $scope.selectedItemId = 0;
        $scope.votes = [];
        $scope.priority = {};
        $scope.start = 0;
        $scope.limit = 20;
        $scope.size = 0;
        $scope.loadCount = 0;
        if (absUrl.split("?")[1]) {
            if (absUrl.split("?")[1].split("=")[1]) {
                $scope.selectedItemId = absUrl.split("?")[1].split("=")[1];
            }
        }


        if ($scope.selectedItemId > 0) {

            function getSuccessReform(res) {
                $scope.priority = res.data;
            }

            ajaxCall($http, "priority/get-priority?itemId=" + $scope.selectedItemId, null, getSuccessReform);

            function getSuccessReformVote(res) {
                console.log(res.data);
                $scope.votes = $scope.votes.concat(res.data.list);
                $scope.size = res.data.size;
                $scope.loadCount = $scope.votes.length;
            }

            ajaxCall($http, "priority/get-priority-votes?priorityId=" + $scope.selectedItemId + "&start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessReformVote);
        }


        $scope.loadMore = function () {
            $scope.start = $scope.start + $scope.limit;
            ajaxCall($http, "priority/get-priority-votes?priorityId=" + $scope.selectedItemId + "&start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessReformVote);
        };
        $scope.loadAll = function () {
            $scope.start = $scope.start + $scope.limit;
            $scope.limit = $scope.size;
            ajaxCall($http, "priority/get-priority-votes?priorityId=" + $scope.selectedItemId + "&start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessReformVote);
        };


    });
</script>


<!-- page content -->
<div class="right_col" ng-controller="homeCtrl">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>პრიორიტეტულობის ხმები
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
                        <h2>{{priority.name}}</h2>

                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            <li></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <!-- start project list -->
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th style="width: 1%">#</th>
                                <th style="width: 20%">თარიღი</th>
                                <th>მომხმარებელი</th>
                                <th style="width: 165px;">#Edit</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="r in votes">
                                <td>
                                    {{$index+1}}
                                </td>
                                <td>
                                    {{r.createDate}}
                                </td>
                                <td>
                                    {{r.clientUid}}
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
