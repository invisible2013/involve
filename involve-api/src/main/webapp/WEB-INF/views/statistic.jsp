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
    });
    var app = angular.module("app", ["ui.bootstrap"]);
    app.controller("refereeCtrl", function ($scope, $http, $filter, $location) {
        var absUrl = $location.absUrl();
        $scope.type = "sportsman";
        $scope.items = [];
        $scope.referees = [];
        $scope.trainers = [];
        $scope.sportTypeRow = [1];
        $scope.startSportsman = 0;
        $scope.limitSportsman = 40;
        $scope.sizeSportsman = 0;
        $scope.loadCountSportsman = 0;
        var type = absUrl.split("?")[1].split("=")[1];
        if (type) {
            $scope.type = type;
            $('li.active').removeClass('active');
            $('li#' + type).addClass('active');
        }

        function getSuccessItems(res) {
            $scope.items = $scope.items.concat(res.data.list);
            $scope.sizeSportsman = res.data.size;
            $scope.loadCountSportsman = $scope.items.length;
        }

        ajaxCall($http, "statistic/get-sportsman-statistic?start=" + $scope.startSportsman + "&limit=" + $scope.limitSportsman, null, getSuccessItems);

        function getSuccessReferees(res) {
            $scope.referees = $scope.referees.concat(res.data.list);
            $scope.sizeSportsman = res.data.size;
            $scope.loadCountSportsman = $scope.referees.length;
        }

        ajaxCall($http, "statistic/get-referee-statistic?start=" + $scope.startSportsman + "&limit=" + $scope.limitSportsman, null, getSuccessReferees);

        function getSuccessTrainers(res) {
            $scope.trainers = $scope.trainers.concat(res.data.list);
            $scope.sizeSportsman = res.data.size;
            $scope.loadCountSportsman = $scope.trainers.length;
        }

        ajaxCall($http, "statistic/get-trainer-statistic?start=" + $scope.startSportsman + "&limit=" + $scope.limitSportsman, null, getSuccessTrainers);

        function getSuccessRegions(res) {
            $scope.regions = res.data;
        }

        ajaxCall($http, "parameter/get-regions", null, getSuccessRegions);


        function getSuccessSportTypes(res) {
            $scope.sportTypes = res.data;
        }

        ajaxCall($http, "parameter/get-sport-types", null, getSuccessSportTypes);

        function getSuccessGenders(res) {
            $scope.genders = res.data;
        }

        ajaxCall($http, "parameter/get-genders", null, getSuccessGenders);

        function getSuccessRanges(res) {
            $scope.ranges = res.data;
        }

        ajaxCall($http, "parameter/get-ranges", null, getSuccessRanges);

        function getSuccessRanks(res) {
            $scope.ranks = res.data;
        }

        ajaxCall($http, "parameter/get-statistic-categories?typeId=1", null, getSuccessRanks);

        function getSuccessRanksReferee(res) {
            $scope.ranksReferee = res.data;
        }

        ajaxCall($http, "parameter/get-statistic-categories?typeId=2", null, getSuccessRanksReferee);

        function getSuccessRanksTrainer(res) {
            $scope.ranksTrainer = res.data;
        }

        ajaxCall($http, "parameter/get-statistic-categories?typeId=3", null, getSuccessRanksTrainer);

        $scope.addNewSportsman = function () {
            $('#sportsmanList').addClass("hidden");
            $('#sportsmanPanel').removeClass("hidden");
            $scope.item = {'sportTypeId': 1, 'regionId': 1, 'para': 0};
        };
        $scope.addNewReferee = function () {
            $('#refereeList').addClass("hidden");
            $('#refereePanel').removeClass("hidden");
            $scope.item = {'sportTypeId': 1, 'regionId': 1};
        };
        $scope.addNewTrainer = function () {
            $('#trainerList').addClass("hidden");
            $('#trainerPanel').removeClass("hidden");
            $scope.item = {'sportTypeId': 1, 'regionId': 1};
        };

        $scope.testPanelHide = function () {
            $('#sportsmanPanel').addClass("hidden");
            $('#sportsmanList').removeClass("hidden");
        };
        $scope.refereePanelHide = function () {
            $('#refereePanel').addClass("hidden");
            $('#refereeList').removeClass("hidden");
        };
        $scope.trainerPanelHide = function () {
            $('#trainerPanel').addClass("hidden");
            $('#trainerList').removeClass("hidden");
        };

        $scope.saveItem = function () {
            $scope.item.para = $('input[name=paraSportsmanRadio]:checked').val() == 1 ? true : false;
            console.log($scope.item);

            var list = $scope.item.sportTypes;
            if (list == undefined) {
                alert("აირჩიეთ სპორტის სახეობა");
                return;
            } else {
                $scope.item.sportTypes = [];
                angular.forEach(list, function (v) {
                    $scope.item.sportTypes.push({'sportTypeId': v.sportTypeId});
                });
                function saveSuccessItem(res) {
                    console.log(res);
                    location.reload();
                };
                ajaxCall($http, "statistic/save-sportsman-statistic", angular.toJson($scope.item), saveSuccessItem);
            }
        };

        $scope.saveReferee = function () {
            var list = $scope.item.sportTypes;
            if (list == undefined) {
                alert("აირჩიეთ სპორტის სახეობა");
                return;
            } else {
                $scope.item.sportTypes = [];
                angular.forEach(list, function (v) {
                    $scope.item.sportTypes.push({'sportTypeId': v.sportTypeId});
                });
                function saveSuccessItem(res) {
                    location.reload();
                };
                ajaxCall($http, "statistic/save-referee-statistic", angular.toJson($scope.item), saveSuccessItem);
            }
        };

        $scope.saveTrainer = function () {
            var list = $scope.item.sportTypes;
            if (list == undefined) {
                alert("აირჩიეთ სპორტის სახეობა");
                return;
            } else {
                $scope.item.sportTypes = [];
                angular.forEach(list, function (v) {
                    $scope.item.sportTypes.push({'sportTypeId': v.sportTypeId});
                });
                function saveSuccessItem(res) {
                    location.reload();
                };
                ajaxCall($http, "statistic/save-trainer-statistic", angular.toJson($scope.item), saveSuccessItem);
            }
        };


        $scope.editItem = function (itemId) {
            if (itemId != undefined) {
                $('#sportsmanList').addClass("hidden");
                $('#sportsmanPanel').removeClass("hidden");
                var selected = $filter('filter')($scope.items, {id: itemId}, true);
                $scope.item = selected[0];

                if ($scope.item.sportTypes != undefined && $scope.item.sportTypes.length > 0) {
                    $scope.sportTypeRow = [];
                    angular.forEach($scope.item.sportTypes, function (v, k) {
                        $scope.sportTypeRow.push(k + 1);
                    });
                }
            }
        };
        $scope.editReferee = function (itemId) {
            if (itemId != undefined) {
                $('#refereeList').addClass("hidden");
                $('#refereePanel').removeClass("hidden");
                var selected = $filter('filter')($scope.referees, {id: itemId}, true);
                $scope.item = selected[0];

                if ($scope.item.sportTypes != undefined && $scope.item.sportTypes.length > 0) {
                    $scope.sportTypeRow = [];
                    angular.forEach($scope.item.sportTypes, function (v, k) {
                        $scope.sportTypeRow.push(k + 1);
                    });
                }
            }
        };
        $scope.editTrainer = function (itemId) {
            if (itemId != undefined) {
                $('#trainerList').addClass("hidden");
                $('#trainerPanel').removeClass("hidden");
                var selected = $filter('filter')($scope.trainers, {id: itemId}, true);
                $scope.item = selected[0];

                if ($scope.item.sportTypes != undefined && $scope.item.sportTypes.length > 0) {
                    $scope.sportTypeRow = [];
                    angular.forEach($scope.item.sportTypes, function (v, k) {
                        $scope.sportTypeRow.push(k + 1);
                    });
                }
            }
        };

        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "statistic/delete-statistic?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.adSportTypeRow = function () {
            var size = $scope.sportTypeRow.length;
            $scope.sportTypeRow.push(size + 1);
        };
        $scope.removeSportType = function (index) {
            $scope.sportTypeRow.splice(index, 1);
            $scope.item.sportTypes.splice(index, 1);
        };
        $scope.loadMoreSportsman = function () {
            $scope.startSportsman = $scope.startSportsman + $scope.limitSportsman;
            ajaxCall($http, "statistic/get-sportsman-statistic?start=" + $scope.startSportsman + "&limit=" + $scope.limitSportsman, null, getSuccessItems);
        };
        $scope.loadAllSportsman = function () {
            $scope.startSportsman = $scope.startSportsman + $scope.limitSportsman;
            $scope.limitSportsman = $scope.sizeSportsman;
            ajaxCall($http, "statistic/get-sportsman-statistic?start=" + $scope.startSportsman + "&limit=" + $scope.limitSportsman, null, getSuccessItems);
        };
        $scope.loadMoreReferee = function () {
            $scope.startSportsman = $scope.startSportsman + $scope.limitSportsman;
            ajaxCall($http, "statistic/get-referee-statistic?start=" + $scope.startSportsman + "&limit=" + $scope.limitSportsman, null, getSuccessReferees);
        };
        $scope.loadAllReferee = function () {
            $scope.startSportsman = $scope.startSportsman + $scope.limitSportsman;
            $scope.limitSportsman = $scope.sizeSportsman;
            ajaxCall($http, "statistic/get-referee-statistic?start=" + $scope.startSportsman + "&limit=" + $scope.limitSportsman, null, getSuccessReferees);
        };
        $scope.loadMoreTrainer = function () {
            $scope.startSportsman = $scope.startSportsman + $scope.limitSportsman;
            ajaxCall($http, "statistic/get-trainer-statistic?start=" + $scope.startSportsman + "&limit=" + $scope.limitSportsman, null, getSuccessTrainers);
        };
        $scope.loadAllTrainer = function () {
            $scope.startSportsman = $scope.startSportsman + $scope.limitSportsman;
            $scope.limitSportsman = $scope.sizeSportsman;
            ajaxCall($http, "statistic/get-trainer-statistic?start=" + $scope.startSportsman + "&limit=" + $scope.limitSportsman, null, getSuccessTrainers);
        };
    });
</script>

<div class="col-md-12" ng-controller="refereeCtrl">
    <br/>
    <ul class="nav nav-tabs">
        <li id="sportsman" role="presentation" data-toggle="tooltip" title="სპორტსმენების სტატისტიკა" class="active"><a
                href="statistic?type=sportsman">სპორტსმენები</a></li>
        <li id="referee" role="presentation" data-toggle="tooltip" title="მსაჯების სტატისტიკა"><a
                href="statistic?type=referee">მსაჯები</a></li>
        <li id="trainer" role="presentation" data-toggle="tooltip" title="მწვრთნელების სტატისტიკა"><a
                href="statistic?type=trainer">მწვრთნელები</a></li>
    </ul>
    <br/>

    <div ng-show="type == 'sportsman'">
        <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewSportsman();">დამატება</button>

        <form name="sportsmanForm" class="hidden" id="sportsmanPanel">
            <div class="col-md-12 text-center"><h4>სპორტსმენების სტატისტიკა</h4></div>

            <div class="form-group col-md-4">
                <label class="control-label">რეგიონი</label>
                <select class="form-control input-sm" ng-model="item.regionId" ng-change="getCities()">
                    <option ng-repeat="r in regions" value="{{r.id}}">{{r.name}}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">კატეგორია</label>
                <select class="form-control input-sm" ng-model="item.rankId">
                    <option ng-repeat="r in ranks" value="{{r.id}}">{{r.name}}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">სქესი</label>
                <select class="form-control input-sm" ng-model="item.genderId">
                    <option ng-repeat="g in genders" value="{{g.id}}">{{g.name}}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">რეინჯი</label>
                <select class="form-control input-sm" ng-model="item.rangeTypeId">
                    <option ng-repeat="s in ranges" value="{{s.id}}">{{s.name}}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">რაოდენობა</label>
                <input type="text" ng-model="item.count" class="form-control input-sm"
                       placeholder="-მდე">
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">პარა სპორტსმენი</label>
                <div class="mt-radio-inline">
                    <label class="mt-radio">
                        <input type="radio" ng-model="item.para" name="paraSportsmanRadio" id="optionsRadios4"
                               value="1">კი
                        <span></span>
                    </label>
                    <label class="mt-radio">
                        <input type="radio" ng-model="item.para" name="paraSportsmanRadio" id="optionsRadios5"
                               value="0" checked=""> არა
                        <span></span>
                    </label>
                </div>
            </div>

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

        <div id="sportsmanList">
            <table class="table table-striped table-hover">
                <tr>
                    <th>ID</th>
                    <th>რეგიონი</th>
                    <th>სახეობა</th>
                    <th>რანგი</th>
                    <th>სქესი</th>
                    <th>რეინჯი</th>
                    <th>რაოდენობა</th>
                    <th></th>
                </tr>
                <tr ng-repeat="s in items">
                    <td>{{$index+1}}</td>
                    <td>{{s.regionName}}</td>
                    <td>
                    <span ng-repeat="s in s.sportTypes">
                        {{s.sportTypeName}},
                    </span>
                    </td>
                    <td>{{s.rankName}}</td>
                    <td>{{s.genderName}}</td>
                    <td>{{s.rangeTypeName}}</td>
                    <td>{{s.count}}</td>
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
            <input type="button" ng-show="sizeSportsman>loadCountSportsman" class="btn btn-primary btn-xs"
                   value="მეტის ჩატვირთვა" ng-click="loadMoreSportsman();">
            <input type="button" ng-show="sizeSportsman>loadCountSportsman" class="btn btn-primary btn-xs" value="ყველა"
                   ng-click="loadAllSportsman();">
            <br/><br/>
        </div>
    </div>

    <div ng-show="type == 'referee'">

        <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewReferee();">დამატება</button>

        <form name="sportsmanForm" class="hidden" id="refereePanel">
            <div class="col-md-12 text-center"><h4>მსაჯების სტატისტიკა</h4></div>

            <div class="form-group col-md-4">
                <label class="control-label">რეგიონი</label>
                <select class="form-control input-sm" ng-model="item.regionId" ng-change="getCities()">
                    <option ng-repeat="r in regions" value="{{r.id}}">{{r.name}}</option>
                </select>
            </div>

            <div class="form-group col-md-4">
                <label class="control-label">კატეგორია</label>
                <select class="form-control input-sm" ng-model="item.rankId">
                    <option ng-repeat="r in ranksReferee" value="{{r.id}}">{{r.name}}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">სქესი</label>
                <select class="form-control input-sm" ng-model="item.genderId">
                    <option ng-repeat="g in genders" value="{{g.id}}">{{g.name}}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">რეინჯი</label>
                <select class="form-control input-sm" ng-model="item.rangeTypeId">
                    <option ng-repeat="s in ranges" value="{{s.id}}">{{s.name}}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">რაოდენობა</label>
                <input type="text" ng-model="item.count" class="form-control input-sm"
                       placeholder="-მდე">
            </div>
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
                <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveReferee();">
                <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="refereePanelHide();">
            </div>
        </form>

        <div id="refereeList">
            <table class="table table-striped table-hover">
                <tr>
                    <th>ID</th>
                    <th>რეგიონი</th>
                    <th>სახეობა</th>
                    <th>რანგი</th>
                    <th>სქესი</th>
                    <th>რეინჯი</th>
                    <th>რაოდენობა</th>
                    <th></th>
                </tr>
                <tr ng-repeat="s in referees">
                    <td>{{$index+1}}</td>
                    <td>{{s.regionName}}</td>
                    <td><span ng-repeat="s in s.sportTypes">
                        {{s.sportTypeName}},
                    </span></td>
                    <td>{{s.rankName}}</td>
                    <td>{{s.genderName}}</td>
                    <td>{{s.rangeTypeName}}</td>
                    <td>{{s.count}}</td>
                    <td style="min-width: 75px;">
                        <button type="button" class="btn btn-xs" ng-click="editReferee(s.id)">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>
                        <button type="button" class="btn btn-xs" ng-click="deleteItem(s.id)">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                    </td>
                </tr>
            </table>
            <input type="button" ng-show="sizeSportsman>loadCountSportsman" class="btn btn-primary btn-xs"
                   value="მეტის ჩატვირთვა" ng-click="loadMoreReferee();">
            <input type="button" ng-show="sizeSportsman>loadCountSportsman" class="btn btn-primary btn-xs" value="ყველა"
                   ng-click="loadAllReferee();">
            <br/><br/>
        </div>
    </div>

    <div ng-show="type == 'trainer'">
        <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewTrainer();">დამატება</button>

        <form name="sportsmanForm" class="hidden" id="trainerPanel">
            <div class="col-md-12 text-center"><h4>მწვრთნელების სტატისტიკა</h4></div>

            <div class="form-group col-md-4">
                <label class="control-label">რეგიონი</label>
                <select class="form-control input-sm" ng-model="item.regionId" ng-change="getCities()">
                    <option ng-repeat="r in regions" value="{{r.id}}">{{r.name}}</option>
                </select>
            </div>

            <div class="form-group col-md-4">
                <label class="control-label">კატეგორია</label>
                <select class="form-control input-sm" ng-model="item.rankId">
                    <option ng-repeat="r in ranksTrainer" value="{{r.id}}">{{r.name}}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">სქესი</label>
                <select class="form-control input-sm" ng-model="item.genderId">
                    <option ng-repeat="g in genders" value="{{g.id}}">{{g.name}}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">რეინჯი</label>
                <select class="form-control input-sm" ng-model="item.rangeTypeId">
                    <option ng-repeat="s in ranges" value="{{s.id}}">{{s.name}}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label">რაოდენობა</label>
                <input type="text" ng-model="item.count" class="form-control input-sm"
                       placeholder="-მდე">
            </div>

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
                <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveTrainer();">
                <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="trainerPanelHide();">
            </div>
        </form>

        <div id="trainerList">
            <table class="table table-striped table-hover">
                <tr>
                    <th>ID</th>
                    <th>რეგიონი</th>
                    <th>სახეობა</th>
                    <th>რანგი</th>
                    <th>სქესი</th>
                    <th>რეინჯი</th>
                    <th>რაოდენობა</th>
                    <th></th>
                </tr>
                <tr ng-repeat="s in trainers">
                    <td>{{$index+1}}</td>
                    <td>{{s.regionName}}</td>
                    <td><span ng-repeat="s in s.sportTypes">
                        {{s.sportTypeName}},
                    </span>
                    </td>
                    <td>{{s.rankName}}</td>
                    <td>{{s.genderName}}</td>
                    <td>{{s.rangeTypeName}}</td>
                    <td>{{s.count}}</td>
                    <td style="min-width: 75px;">
                        <button type="button" class="btn btn-xs" ng-click="editTrainer(s.id)">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>
                        <button type="button" class="btn btn-xs" ng-click="deleteItem(s.id)">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                    </td>
                </tr>
            </table>
            <input type="button" ng-show="sizeSportsman>loadCountSportsman" class="btn btn-primary btn-xs"
                   value="მეტის ჩატვირთვა" ng-click="loadMoreTrainer();">
            <input type="button" ng-show="sizeSportsman>loadCountSportsman" class="btn btn-primary btn-xs" value="ყველა"
                   ng-click="loadAllTrainer();">
            <br/><br/>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
