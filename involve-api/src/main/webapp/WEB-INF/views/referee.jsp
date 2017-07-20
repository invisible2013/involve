<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script>
    $(document).ready(function () {
        $('#birthDate').datepicker({
            dateFormat: 'dd/mm/yy',
            yearRange: "-150:+0",
            changeMonth: true,
            changeYear: true
        });
        $('#deathDate').datepicker({
            dateFormat: 'dd/mm/yy',
            yearRange: "-150:+0",
            changeMonth: true,
            changeYear: true
        });
        $('#careerStartDate').datepicker({
            dateFormat: 'dd/mm/yy',
            yearRange: "-150:+0",
            changeMonth: true,
            changeYear: true
        });
        $('#careerEndDate').datepicker({
            dateFormat: 'dd/mm/yy',
            yearRange: "-150:+0",
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
    app.controller("refereeCtrl", function ($scope, $http, $filter) {
        $scope.sportTypeRow = [1];
        function getSuccessItems(res) {
            $scope.items = res.data;
            console.log($scope.items);
        }

        ajaxCall($http, "referee/get-referees", null, getSuccessItems);

        function getSuccessCategories(res) {
            $scope.categories = res.data;
        }

        ajaxCall($http, "parameter/get-referee-categories", null, getSuccessCategories);

        function getSuccessRegions(res) {
            $scope.regions = res.data;
        }

        ajaxCall($http, "parameter/get-regions", null, getSuccessRegions);

        if ($scope.cities == undefined) {
            function getSuccessCitiesStart(res) {
                $scope.cities = res.data;
            }

            ajaxCall($http, "parameter/get-cities?regionId=1", null, getSuccessCitiesStart);
        }

        function getSuccessSportTypes(res) {
            $scope.sportTypes = res.data;
        }
        ajaxCall($http, "parameter/get-sport-types", null, getSuccessSportTypes);

        function getSuccessGenders(res) {
            $scope.genders = res.data;
        }

        ajaxCall($http, "parameter/get-genders", null, getSuccessGenders);

        function getSuccessRanks(res) {
            $scope.ranks = res.data;
        }

        ajaxCall($http, "parameter/get-ranks", null, getSuccessRanks);

        $scope.getCities = function () {
            function getSuccessCities(res) {
                $scope.cities = res.data;
            }

            ajaxCall($http, "parameter/get-cities?regionId=" + $scope.item.regionId, null, getSuccessCities);
        };
        $scope.addNewSportsman = function () {
            $('#sportsmanList').addClass("hidden");
            $('#sportsmanPanel').removeClass("hidden");
            $scope.item = {'sportTypeId': 1, 'regionId': 1, 'cityId': 1, 'genderId': 1, 'categoryId': 1};
        };
        $scope.testPanelHide = function () {
            $('#sportsmanPanel').addClass("hidden");
            $('#sportsmanList').removeClass("hidden");
        };

        $scope.saveItem = function () {
            console.log($scope.item);
            $scope.item.biography = $scope.getNoteAsHtml();
            var list = $scope.item.sportTypes;
            $scope.item.sportTypes = [];
            angular.forEach(list, function (v) {
                $scope.item.sportTypes.push({'sportTypeId': v.sportTypeId});
            });
            function saveSuccessItem(res) {
                console.log(res);
                location.reload();
            };
            ajaxCall($http, "referee/save-referee", angular.toJson($scope.item), saveSuccessItem);
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
                $scope.setNoteAsHtml($scope.item.biography);
                $scope.getCities();
                if (itemId != undefined) {
                    function getRefereeSportTypes(res) {
                        console.log(res.data);
                        $scope.item.sportTypes = res.data;

                        if ($scope.item.sportTypes != undefined && $scope.item.sportTypes.length > 0) {
                            $scope.sportTypeRow = [];
                            angular.forEach($scope.item.sportTypes, function (v, k) {
                                $scope.sportTypeRow.push(k + 1);
                                //$scope.item.sportTypes[k].sportTypeId = v.sportTypeId;
                            });
                        }
                    }

                    ajaxCall($http, "referee/get-referee-sport-types?refereeId=" + itemId, null, getRefereeSportTypes);
                }

            }
        };
        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "referee/delete-referee?itemId=" + itemId, null, reload);
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
        $scope.setNoteAsHtml = function (text) {
            CKEDITOR.instances.ckNote.setData(text);
        };
        $scope.getNoteAsHtml = function () {
            return CKEDITOR.instances.ckNote.getData();
        };
    });
</script>

<div class="col-md-12" ng-controller="refereeCtrl">

    <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewSportsman();">დამატება</button>

    <form name="sportsmanForm" class="hidden" id="sportsmanPanel">
        <div class="col-md-12 text-center"><h4>მსაჯის შესახებ</h4></div>
        <div class="form-group col-md-4">
            <label class="control-label">სახელი</label>
            <input type="text" ng-model="item.firstName" class="form-control input-sm" placeholder="სახელი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">გვარი</label>
            <input type="text" ng-model="item.lastName" class="form-control input-sm" placeholder="გვარი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">სქესი</label>
            <select class="form-control input-sm" ng-model="item.genderId">
                <option ng-repeat="g in genders" value="{{g.id}}">{{g.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">დაბადების თარიღი</label>
            <input type="text" id="birthDate" ng-model="item.birthDate" class="form-control input-sm"
                   placeholder="დაბადების თარიღი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">გარდაცვალების თარიღი</label>
            <input type="text" id="deathDate" ng-model="item.deathDate" class="form-control input-sm"
                   placeholder="გარდაცვალების თარიღი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">კარიერის დასაწყისი</label>
            <input type="text" id="careerStartDate" ng-model="item.careerStartDate" class="form-control input-sm"
                   placeholder="არიერის დასაწყისი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">კარიერის დასასრული</label>
            <input type="text" id="careerEndDate" ng-model="item.careerEndDate" class="form-control input-sm"
                   placeholder="კარიერის დასასრული">
        </div>
        <%-- <div class="form-group col-md-12">
             <label class="control-label">სპორტის სახეობა</label>
             <select class="form-control input-sm" ng-model="item.sportTypeId">
                 <option ng-repeat="st in sportTypes" value="{{st.id}}">{{st.name}}</option>
             </select>
         </div>--%>

        <div class="form-group col-md-4">
            <label class="control-label">რეგიონი</label>
            <select class="form-control input-sm" ng-model="item.regionId" ng-change="getCities()">
                <option ng-repeat="r in regions" value="{{r.id}}">{{r.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">ქალაქი</label>
            <select class="form-control input-sm" ng-model="item.cityId">
                <option ng-repeat="r in cities" value="{{r.id}}">{{r.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">კატეგორია</label>
            <select class="form-control input-sm" ng-model="item.categoryId">
                <option ng-repeat="r in categories" value="{{r.id}}">{{r.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">კომენტარი</label>
            <input type="text" ng-model="item.comment" class="form-control input-sm" placeholder="კომენტარი">
        </div>
        <div class="form-group col-md-10">
            <label class="control-label">ბიოგრაფია</label>
            <script>
                $(function () {
                    CKEDITOR.replace('ckNote');
                });
            </script>
            <textarea id="ckNote" class="form-control" ng-model="item.biography" rows="2"
                      style="width: 500px;"></textarea>
        </div>
        <div class="clear"></div>
        <div class="form-group col-sm-6">
            <label class="control-label">სპორტის სახეობა</label>

            <div class="form-group" ng-repeat="r in sportTypeRow">
                <div class="row">
                    <div class="col-md-8">
                        <select class="form-control input-sm" ng-model="item.sportTypes[r - 1].sportTypeId">
                            <option ng-repeat="cc in sportTypes" value="{{cc.id}}"
                                    ng-selected="{{cc.id == item.sportTypes[r - 1].sportTypeId}}">{{cc.name}}
                            </option>
                        </select>
                    </div>
                    <div class="col-md-1" ng-show="$index == 0">
                        <a class="btn btn-xs row">
                            <span class="glyphicon glyphicon-plus" ng-click="adSportTypeRow()"></span>
                        </a>
                    </div>
                    <div class="col-md-1" ng-show="$index > 0">
                        <a class="btn btn-xs row">
                            <span class="glyphicon glyphicon-remove" ng-click="removeSportType($index)"></span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>

        <div class="form-group col-md-12">
            <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveItem();">
            <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="testPanelHide();">
        </div>
    </form>


    <table class="table table-striped table-hover" id="sportsmanList">
        <tr>
            <th>ID</th>
            <th>სახელი გვარი</th>
            <th>დაბადების თარიღი</th>
            <th>რეგიონი</th>
            <th></th>
        </tr>
        <tr ng-repeat="s in items">
            <td>{{$index+1}}</td>
            <td>{{s.firstName}} {{s.lastName}}</td>
            <td>{{s.birthDate}}</td>
            <td>{{s.regionName}} {{s.cityName}}</td>
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
