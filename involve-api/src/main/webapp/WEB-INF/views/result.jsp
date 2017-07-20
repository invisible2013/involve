<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>
    $(document).ready(function () {

    });
    var app = angular.module("app", ["ui.bootstrap"]);
    app.controller("refereeCtrl", function ($scope, $http, $filter) {
        $scope.sportTypeRow = [1];
        function getSuccessItems(res) {
            $scope.items = res.data;
            console.log($scope.items);
        }

        ajaxCall($http, "result/get-results", null, getSuccessItems);

        function getSportsmans(res) {
            $scope.sportsmans = res.data;
        }

        ajaxCall($http, "sportsman/get-sportsman-alphabet", null, getSportsmans);

        function getSuccessAwards(res) {
            $scope.awards = res.data;
        }

        ajaxCall($http, "parameter/get-awards", null, getSuccessAwards);

        function getSuccessChampionships(res) {
            $scope.championships = res.data;
        }
        ajaxCall($http, "championship/get-championships-alphabet", null, getSuccessChampionships);


        function getSuccessSportTypes(res) {
            $scope.sportTypes = res.data;
        }

        ajaxCall($http, "parameter/get-sport-types", null, getSuccessSportTypes);




        $scope.addNewSportsman = function () {
            $('#sportsmanList').addClass("hidden");
            $('#sportsmanPanel').removeClass("hidden");
            $scope.item = {'sportTypeId': 1, 'awardId': 1, 'championshipId': 1};
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
            ajaxCall($http, "result/save-result", angular.toJson($scope.item), saveSuccessItem);
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
                    ajaxCall($http, "result/delete-result?itemId="+itemId, null, reload);
                }
            }
        };

        $scope.open = function (name) {
            window.open('file/draw/' + name + '/');
        };
        $scope.adSportTypeRow = function () {
            var size = $scope.sportTypeRow.length;
            $scope.sportTypeRow.push(size + 1);
            //$scope.sportTypeItems[size + 1] = $scope.sportTypeItems[1];
        };
        $scope.removeSportType = function (index) {
            $scope.sportTypeRow.splice(index, 1);
            $scope.item.sportTypes.splice(index, 1);
        };
    });
</script>

<div class="col-md-12" ng-controller="refereeCtrl">

    <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewSportsman();">დამატება</button>

    <form name="sportsmanForm" class="hidden" id="sportsmanPanel">
        <div class="col-md-12 text-center"><h4>შედეგი</h4></div>

        <div class="form-group col-md-12">
            <label class="control-label">ჩემპიონატი</label>
            <select class="form-control input-sm" ng-model="item.championshipId">
                <option ng-repeat="c in championships" value="{{c.id}}">{{c.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">სპორტის სახეობა</label>
            <select class="form-control input-sm" ng-model="item.sportTypeId">
                <option ng-repeat="st in sportTypes" value="{{st.id}}">{{st.name}}</option>
            </select>
        </div>

        <div class="form-group col-md-4">
            <label class="control-label">სპორტსმენი</label>
            <select class="form-control input-sm" ng-model="item.sportsmanId">
                <option ng-repeat="s in sportsmans" value="{{s.id}}">{{s.lastName}} {{s.firstName}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">დაკავებული ადგილი</label>
            <select class="form-control input-sm" ng-model="item.awardId">
                <option ng-repeat="a in awards" value="{{a.id}}">{{a.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">დისციპლინა</label>
            <input type="text"  ng-model="item.discipline" class="form-control input-sm"
                   placeholder="დისციპლინა">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">წონითი კატეგორია</label>
            <input type="text"  ng-model="item.weight" class="form-control input-sm"
                   placeholder="წონა">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">შედეგი</label>
            <input type="text"  ng-model="item.score" class="form-control input-sm"
                   placeholder="შედეგი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">შენიშვნა</label>
            <input type="text"  ng-model="item.note" class="form-control input-sm"
                   placeholder="შენიშვნა">
        </div>

        <div class="form-group col-md-12">
            <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveItem();">
            <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="testPanelHide();">
        </div>
    </form>


    <table class="table table-striped table-hover" id="sportsmanList">
        <tr>
            <th>ID</th>
            <th>ჩემპიონატი</th>
            <th>სპორტსმენი</th>
            <th>სპორტის სახეობა</th>
            <th>ჯილდო</th>
            <th></th>
        </tr>
        <tr ng-repeat="s in items">
            <td>{{$index+1}}</td>
            <td>{{s.championshipName}}</td>
            <td>{{s.sportsmanName}}</td>
            <td>{{s.sportTypeName}}</td>
            <td>{{s.awardName}}</td>
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
