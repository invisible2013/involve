<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>
    $(document).ready(function () {
        $('#startDate').datepicker({
            dateFormat: 'dd/mm/yy',
            yearRange: "-150:+0",
            changeMonth: true,
            changeYear: true
        });
        $('#endDate').datepicker({
            dateFormat: 'dd/mm/yy',
            yearRange: "-150:+0",
            changeMonth: true,
            changeYear: true
        });
    });
    var app = angular.module("app", ["ui.bootstrap"]);
    app.controller("refereeCtrl", function ($scope, $http, $filter) {
        $scope.sportTypeRow = [1];
        function getSuccessItems(res) {
            $scope.items = res.data;
            console.log($scope.items);
        }
        ajaxCall($http, "event/get-events", null, getSuccessItems);


        function getSuccessRegions(res) {
            $scope.eventTypes = res.data;
        }

        ajaxCall($http, "parameter/get-event-types", null, getSuccessRegions);

        function getSuccessSportTypes(res) {
            $scope.sportTypes = res.data;
        }

        ajaxCall($http, "parameter/get-sport-types", null, getSuccessSportTypes);


        $scope.addNewSportsman = function () {
            $('#sportsmanList').addClass("hidden");
            $('#sportsmanPanel').removeClass("hidden");
            $scope.item = {'eventTypeId': 1};
        };
        $scope.testPanelHide = function () {
            $('#sportsmanPanel').addClass("hidden");
            $('#sportsmanList').removeClass("hidden");
        }

        $scope.saveItem = function () {
            console.log($scope.item);
            function saveSuccessItem(res) {
                console.log(res);
                location.reload();
            };
            ajaxCall($http, "event/save-event", angular.toJson($scope.item), saveSuccessItem);
        };


        $scope.detailEvent = function (eventId) {
            function getEvent(res) {
                console.log(res.data);
                $scope.detailInfo = res.data;
                $scope.persons = res.data.persons;
                $scope.documents = res.data.documents;
            }

            ajaxCall($http, "event/get-event", angular.toJson({'eventId': eventId}), getEvent);
            function getEventHistory(res) {
                $scope.history = res.data;
            }

            ajaxCall($http, "event/get-event-history", angular.toJson({'eventId': eventId}), getEventHistory);
        };
        $scope.editItem = function (itemId) {
            if (itemId != undefined) {
                $('#sportsmanList').addClass("hidden");
                $('#sportsmanPanel').removeClass("hidden");
                var selected = $filter('filter')($scope.items, {id: itemId}, true);
                $scope.item = selected[0];
            }
        };
        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "event/delete-event?eventId="+itemId, null, reload);
                }
            }
        };

        $scope.open = function (name) {
            window.open('file/draw/' + name + '/');
        };


    });
</script>

<div class="col-md-12" ng-controller="refereeCtrl">

    <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewSportsman();">დამატება</button>

    <form name="sportsmanForm" class="hidden" id="sportsmanPanel">
        <div class="col-md-12 text-center"><h4>ღონისძიება</h4></div>
        <div class="form-group col-md-4">
            <label class="control-label">დასახელება</label>
            <input type="text" ng-model="item.name" class="form-control input-sm" placeholder="დასახელება">
        </div>


        <div class="form-group col-md-4">
            <label class="control-label">დაწყების თარიღი</label>
            <input type="text" id="startDate" ng-model="item.startDate" class="form-control input-sm"
                   placeholder="დაწყების თარიღი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">დასრულების თარიღი</label>
            <input type="text" id="endDate" ng-model="item.endDate" class="form-control input-sm"
                   placeholder="დაწყების თარიღი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">სპორტის სახეობა</label>
            <select class="form-control input-sm" ng-model="item.sportTypeId" >
                <option ng-repeat="s in sportTypes" value="{{s.id}}">{{s.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">ღონისძიების ტიპი</label>
            <select class="form-control input-sm" ng-model="item.eventTypeId" >
                <option ng-repeat="r in eventTypes" value="{{r.id}}">{{r.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">მდებარეობა</label>
            <input type="text" ng-model="item.location" class="form-control input-sm" placeholder="მდებარეობა">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">აღწერა</label>
            <input type="text" ng-model="item.description" class="form-control input-sm" placeholder="აღწერა">
        </div>

        <div class="form-group col-md-12">
            <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveItem();">
            <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="testPanelHide();">
        </div>
    </form>


    <table class="table table-striped table-hover" id="sportsmanList">
        <tr>
            <th>ID</th>
            <th>დასახელება</th>
            <th>სპორტის სახეობა</th>
            <th>დაწყების თარიღი</th>
            <th>დასრულების თარიღი</th>
            <th></th>
        </tr>
        <tr ng-repeat="s in items">
            <td>{{$index+1}}</td>
            <td>{{s.name}}</td>
            <td>{{s.sportTypeName}}</td>
            <td>{{s.startDate}}</td>
            <td>{{s.endDate}}</td>
            <td style="min-width: 75px;">
                <button type="button" class="btn btn-xs" ng-click="editItem(s.id)">
                    <span class="glyphicon glyphicon-pencil"></span>
                </button>
                <button type="button" class="btn btn-xs" ng-click="deleteItem(s.id)">
                    <span class="glyphicon glyphicon-remove"></span>
                </button>
            </td>
        </tr>
    </table>


</div>
<%@include file="footer.jsp" %>
