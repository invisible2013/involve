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
    app.controller("homeCtrl", function ($scope, $http, $filter) {

        function getSuccessSportsmans(res) {
            $scope.sportsmans = res.data;
        }
        ajaxCall($http, "sportsman/get-sportsmans", null, getSuccessSportsmans);

        function getSuccessRegions(res) {
            $scope.regions = res.data;
        }
        ajaxCall($http, "sportsman/get-regions", null, getSuccessRegions);

        if($scope.cities == undefined) {
            function getSuccessCitiesSart(res) {
                $scope.cities = res.data;
            }

            ajaxCall($http, "sportsman/get-cities?regionId=1", null, getSuccessCitiesSart);
        }

        function getSuccessSportTypes(res) {
            $scope.sportTypes = res.data;
        }
        ajaxCall($http, "sportsman/get-sport-types", null, getSuccessSportTypes);

        function getSuccessGenders(res) {
            $scope.genders = res.data;
        }
        ajaxCall($http, "sportsman/get-genders", null, getSuccessGenders);

        function getSuccessRanks(res) {
            $scope.ranks = res.data;
        }
        ajaxCall($http, "sportsman/get-ranks", null, getSuccessRanks);

        $scope.getCities = function(){
            function getSuccessCities(res){
                $scope.cities = res.data;
            }
            ajaxCall($http, "sportsman/get-cities?regionId="+$scope.sportsman.regionId, null, getSuccessCities);
        };
        $scope.addNewSportsman = function () {
            $('#sportsmanList').addClass("hidden");
            $('#sportsmanPanel').removeClass("hidden");
            $scope.sportsman= {'sportTypeId':1,'regionId':1,'cityId':1,'genderId':1,'rankId':1};
        };
        $scope.testPanelHide = function(){
            $('#sportsmanPanel').addClass("hidden");
            $('#sportsmanList').removeClass("hidden");
        }

        $scope.saveSportsman = function(){
            console.log($scope.sportsman);
            function saveSuccessSportsman(res){
                console.log(res);
                location.reload();
            };
            ajaxCall($http, "sportsman/save-sportsman", angular.toJson($scope.sportsman), saveSuccessSportsman);
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
        $scope.editSportsman = function (sportsmanId) {
            if (sportsmanId != undefined) {
                $('#sportsmanList').addClass("hidden");
                $('#sportsmanPanel').removeClass("hidden");
                var selected = $filter('filter')($scope.sportsmans, {id: sportsmanId}, true);
                $scope.sportsman = selected[0];
                //$scope.user.userGroupId = $scope.user.usersGroup.id;
                //$scope.user.userStatusId = $scope.user.usersStatus.id;
            }
        };
        $scope.deleteSportsman = function (sportsmanId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (sportsmanId != undefined) {
                    ajaxCall($http, "sportsman/delete-sportsman", angular.toJson({'sportsmanId': id}), reload);
                }
            }
        };

        $scope.open = function (name) {
            window.open('file/draw/' + name + '/');
        };
    });
</script>

<div class="col-md-12" ng-controller="homeCtrl">

    <button class="btn btn-primary btn-xs pull-right margin5"  ng-click="addNewSportsman();">დამატება</button>

    <form name="sportsmanForm" class="hidden" id="sportsmanPanel">
        <div class="col-md-12 text-center"><h4>სექციის შესახებ</h4></div>
        <div class="form-group col-md-4">
            <label class="control-label">სახელი</label>
            <input type="text" ng-model="sportsman.firstName" class="form-control input-sm" placeholder="სახელი" >
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">გვარი</label>
            <input type="text"  ng-model="sportsman.lastName" class="form-control input-sm" placeholder="გვარი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">სქესი</label>
            <select class="form-control input-sm" ng-model="sportsman.genderId">
                <option ng-repeat="g in genders" value="{{g.id}}">{{g.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">ტელეფონის N</label>
            <input type="text"  ng-model="sportsman.mobileNumber" class="form-control input-sm" placeholder="ტელეფონის N">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">მისამართი</label>
            <input type="text"  ng-model="sportsman.address" class="form-control input-sm" placeholder="მისამართი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">დაბადების თარიღი</label>
            <input type="text" id="birthDate"  ng-model="sportsman.birthDate" class="form-control input-sm" placeholder="დაბადების თარიღი">
        </div>
        <div class="form-group col-md-12">
            <label class="control-label">სპორტის სახეობა</label>
            <select class="form-control input-sm" ng-model="sportsman.sportTypeId">
                <option ng-repeat="st in sportTypes" value="{{st.id}}">{{st.name}}</option>
            </select>
        </div>

        <div class="form-group col-md-4">
            <label class="control-label">რეგიონი</label>
            <select class="form-control input-sm" ng-model="sportsman.regionId" ng-change="getCities()">
                <option ng-repeat="r in regions" value="{{r.id}}">{{r.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">ქალაქი</label>
            <select class="form-control input-sm" ng-model="sportsman.cityId">
                <option ng-repeat="r in cities" value="{{r.id}}">{{r.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-12">
            <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveSportsman();">
            <input type="button" class="btn btn-default btn-sm"  value="გაუქმება" ng-click="testPanelHide();">
        </div>
    </form>


    <table class="table table-striped table-hover" id="sportsmanList">
        <tr>
            <th>ID</th>
            <th>დასახელება</th>
            <th>სპორტის სახეობა</th>
            <th>მისამართი</th>
            <th>რეგიონი</th>
            <th></th>
        </tr>
        <tr ng-repeat="s in sportsmans">
            <td> </td>
            <td>{{s.firstName}} {{s.lastName}}</td>
            <td>{{s.sportTypeName}}</td>
            <td>{{s.address}}</td>
            <td>{{s.regionName}}</td>
            <td style="min-width: 75px;">
                <button type="button" class="btn btn-xs" ng-click="editSportsman(s.id)">
                    <span class="glyphicon glyphicon-pencil"></span>
                </button>
                <button type="button" class="btn btn-xs" ng-click="deleteSportsman(s.id)">
                    <span class="glyphicon glyphicon-remove"></span>
                </button>
            </td>
        </tr>
    </table>



</div>
<%@include file="footer.jsp" %>
