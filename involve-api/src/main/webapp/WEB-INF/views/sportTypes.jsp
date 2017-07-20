<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>
    $(document).ready(function () {
        $('#birthDate').datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
    var app = angular.module("app", ["ui.bootstrap"]);
    app.filter('asHtml', function ($sce) {
        return function (val) {
            return $sce.trustAsHtml(val);
        };
    });
    app.filter('htmlToPlaintext', function () {
        return function (text) {
            return text ? String(text).replace(/<[^>]+>/gm, '') : '';
        };
    });
    app.controller("homeCtrl", function ($scope, $http, $filter) {
        $scope.sportTypes=[];
        $scope.sportType={};
        $scope.detailInfo="";
        $scope.detailInfoHeader="";

        function getSuccessSportTypes(res) {
            $scope.sportTypes = res.data;
        }
        ajaxCall($http, "parameter/get-sport-types-with-description", null, getSuccessSportTypes);

        $scope.testPanelHide = function () {
            $('#sportsmanPanel').addClass("hidden");
            $('#sportsmanList').removeClass("hidden");
        }

        $scope.saveSportTypes = function () {
            console.log($scope.sportType);
            $scope.sportType.description = $scope.getNoteAsHtml();
            function saveSuccessSportType(res) {
                location.reload();
            };
            ajaxCall($http, "parameter/save-sport-type", angular.toJson($scope.sportType), saveSuccessSportType);
        };


        $scope.detailSportType = function (itemId) {
            var selected = $filter('filter')($scope.sportTypes, {id: itemId}, true);
            $scope.detailInfo = selected[0].description;
            $scope.detailInfoHeader = selected[0].name;
        };
        $scope.editSportType = function (itemId) {
            if (itemId != undefined) {
                $('#sportsmanList').addClass("hidden");
                $('#sportsmanPanel').removeClass("hidden");
                var selected = $filter('filter')($scope.sportTypes, {id: itemId}, true);
                $scope.sportType = selected[0];
                $scope.setNoteAsHtml($scope.sportType.description);
            }
        };
        $scope.setNoteAsHtml = function (text) {
            CKEDITOR.instances.ckNote.setData(text);
        };
        $scope.getNoteAsHtml = function () {
            return CKEDITOR.instances.ckNote.getData();
        };
        $scope.addFiles = function (itemId) {
            window.location = "sportTypeFiles?itemId=" + itemId;
        };

    });
</script>

<div class="col-md-12" ng-controller="homeCtrl">

    <div class="modal fade"  id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">{{detailInfoHeader}}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    {{detailInfo | htmlToPlaintext}}
                </div>

            </div>
        </div>
    </div>


    <form name="sportsmanForm" class="hidden" id="sportsmanPanel">
        <div class="col-md-12 text-center"><h4>სპორტის სახეობის შესახებ</h4></div>
        <div class="form-group col-md-12">
            <label class="control-label">დასახელება</label>
            <input type="text" ng-model="sportType.name" class="form-control input-sm" placeholder="დასახელება">
        </div>
        <div class="form-group col-md-12">
            <label class="control-label">აღწერა</label>
            <script>
                $(function () {
                    CKEDITOR.replace('ckNote');
                });
            </script>
            <textarea id="ckNote" class="form-control" ng-model="sportType.description" rows="2"
                      style="width: 500px;"></textarea>
        </div>
        <div class="form-group col-md-12">
            <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveSportTypes();">
            <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="testPanelHide();">
        </div>
    </form>


    <table class="table table-striped table-hover" id="sportsmanList">
        <tr>
            <th>ID</th>
            <th>დასახელება</th>
            <th>აღწერა</th>
            <th></th>
        </tr>
        <tr ng-repeat="s in sportTypes">
            <td>{{$index+1}}</td>
            <td>{{s.name}}</td>
            <td style="max-width: 400px;">{{s.description.substring(0,300) | htmlToPlaintext}}...</td>
            <td style="min-width: 75px;">
                <button type="button" class="btn btn-xs" ng-click="editSportType(s.id)">
                    <span class="glyphicon glyphicon-pencil"></span>
                </button>
                <button type="button" class="btn btn-xs" data-toggle="modal" data-target="#myModal" ng-click="detailSportType(s.id)">
                    <span class="glyphicon glyphicon-info-sign"></span>
                </button>
                <button type="button" class="btn btn-xs" ng-click="addFiles(s.id)">
                    <span class="glyphicon glyphicon-file"></span>
                </button>
            </td>
        </tr>
    </table>


</div>
<%@include file="footer.jsp" %>
