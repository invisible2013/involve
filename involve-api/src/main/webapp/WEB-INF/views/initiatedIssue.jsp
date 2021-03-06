<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header2.jsp" %>
<script>
    $(document).ready(function () {

    });

    var app = angular.module("app", []);
    app.directive('ckEditor', [function () {
        return {
            require: '?ngModel',
            link: function ($scope, elm, attr, ngModel) {
                var ck = CKEDITOR.replace(elm[0]);
                ck.on('pasteState', function () {
                    $scope.$apply(function () {
                        ngModel.$setViewValue(ck.getData());
                    });
                });
                ngModel.$render = function (value) {
                    ck.setData(ngModel.$modelValue);
                };
            }
        };
    }]);
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

        ajaxCall($http, "initiate/get-issues?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);


        $scope.loadMore = function () {
            $scope.start = $scope.start + $scope.limit;
            ajaxCall($http, "initiate/get-issues?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);
        };
        $scope.loadAll = function () {
            $scope.start = $scope.start + $scope.limit;
            $scope.limit = $scope.size;
            ajaxCall($http, "initiate/get-issues?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);
        };

        $scope.showItem = function () {
            $scope.item = {};
        };


        $scope.saveItem = function () {
            console.log($scope.item);
            function saveSuccessItem(res) {
                location.reload();
            };
            ajaxCall($http, "initiate/save-issue", angular.toJson($scope.item), saveSuccessItem);
        };

        $scope.editItem = function (itemId) {
            if (itemId != undefined) {
                var selected = $filter('filter')($scope.items, {id: itemId}, true);
                $scope.item = selected[0];
            }
        };

        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "initiate/delete-issue?itemId=" + itemId, null, reload);
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

    <div class="modal fade" id="itemModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="myModalLabel">ინიცირების დამატება</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-sm-12">
                            <label class="control-label">დასახელება</label>
                            <textarea rows="4" ng-model="item.name" class="form-control ng-pristine ng-valid">
                            </textarea>
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="control-label">აღწერა</label>
                            <textarea data-ng-model="item.description" rows="2"
                                      placeholder="აღწერა" data-ck-editor
                                      class="form-control"></textarea>
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



    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3> ინიცირებული საკითხები
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
                        <h2>ინიცირებული საკითხები</h2>

                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            <li></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">

                        <p>სამინისტროს მიერ ინიცირებული საკითხები</p>
                        <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#itemModal"
                                ng-click="showItem();">დამატება
                        </button>
                        <!-- start project list -->
                        <table class="table table-striped table-hover projects">
                            <thead>
                            <tr>
                                <th style="width: 1%">#</th>
                                <th>ადმინისტრატორი</th>
                                <th>დასახელება</th>
                                <th>აღწერა</th>
                                <th style="width: 23%">#Edit</th>
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
                                <td>
                                    {{r.name}}
                                </td>
                                <td>
                                    {{r.description}}
                                </td>
                                <td>
                                    <%--  <a data-toggle="modal" data-target="#reformViewModal" ng-click="editItem(r.id)"
                                         class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> ნახვა</a>--%>
                                    <a data-toggle="modal" data-target="#itemModal" ng-click="editItem(r.id)"
                                       class="btn btn-info btn-xs"><i
                                            class="fa fa-pencil"></i> შეცვლა</a>
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
