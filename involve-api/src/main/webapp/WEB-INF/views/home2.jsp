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
        $('#deathDate').datepicker({
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
    app.controller("homeCtrl", function ($scope, $http, $filter, $location) {
        $scope.type = "details";
        $scope.sportsmans=[];
        $scope.start = 0;
        $scope.limit = 40;
        $scope.size = 0;
        $scope.loadCount = 0;
        var absUrl = $location.absUrl();
        if (absUrl.split("?")[1]) {
            if (absUrl.split("?")[1].split("&")[1]) {
                $scope.type = absUrl.split("?")[1].split("&")[0].split("=")[1];
            }
        }
        if ($scope.type) {
            $('.nav li.active').removeClass('active');
            $('.nav li#' + $scope.type).addClass('active');
        }
        function getSuccessSportsmans(res) {
            console.log(res.data);
            $scope.sportsmans=$scope.sportsmans.concat(res.data.list);
            $scope.size = res.data.size;
            $scope.loadCount = $scope.sportsmans.length;
        }

        ajaxCall($http, "reform/get-reforms?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);




        $scope.loadMore = function () {
            $scope.start=$scope.start+$scope.limit;
            ajaxCall($http, "sportsman/get-sportsmans?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);
        };
        $scope.loadAll = function () {
            $scope.start=$scope.start+$scope.limit;
            $scope.limit=$scope.size;
            ajaxCall($http, "sportsman/get-sportsmans?start=" + $scope.start + "&limit=" + $scope.limit, null, getSuccessSportsmans);
        };

        $scope.addNewSportsman = function () {
            $('#sportsmanList').addClass("hidden");
            $('#sportsmanPanel').removeClass("hidden");
            $scope.type = 'details';
            $scope.sportsman = {
                'sportTypeId': 1,
                'regionId': 1,
                'cityId': 1,
                'genderId': 1,
                'rankId': 1,
                'paraSportsman': 0,
                'olimpic': 0,
                'paralympic': 0
            };
        };

        $scope.testPanelHide = function () {
            $('#sportsmanPanel').addClass("hidden");
            $('#sportsmanList').removeClass("hidden");
        }

        $scope.saveSportsman = function () {
            $scope.sportsman.paraSportsman = $('input[name=paraSportsmanRadio]:checked').val() == 1 ? true : false;
            $scope.sportsman.olimpic = $('input[name=olimpicRadio]:checked').val() == 1 ? true : false;
            $scope.sportsman.paralympic = $('input[name=paralympicRadio]:checked').val() == 1 ? true : false;
            $scope.sportsman.biography = $scope.getNoteAsHtml();
            console.log($scope.sportsman);
            function saveSuccessSportsman(res) {

                if ($('#documentId')[0].files[0]) {
                    var oMyForm = new FormData();
                    oMyForm.append("sportsmanId", $scope.sportsman.id);
                    oMyForm.append("file", $('#documentId')[0].files[0]);
                    $.ajax({
                        url: 'sportsman/add-image',
                        data: oMyForm,
                        dataType: 'text',
                        processData: false,
                        contentType: "multipart/form-data",
                        type: 'POST',
                        success: function (data) {
                            location.reload();
                        }
                    }).success(function (data) {
                        location.reload();
                    }).error(function (data, status, headers, config) {
                        location.reload();
                    });
                } else {
                    location.reload();
                }
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
                $scope.setNoteAsHtml($scope.sportsman.biography);
                $scope.getCities();
                //$scope.user.userGroupId = $scope.user.usersGroup.id;
                //$scope.user.userStatusId = $scope.user.usersStatus.id;
            }
        };
        $scope.deleteSportsman = function (sportsmanId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (sportsmanId != undefined) {
                    ajaxCall($http, "sportsman/delete-sportsman?sportsmanId=" + sportsmanId, null, reload);
                }
            }
        };

        $scope.addFiles = function (itemId) {
            window.location = "sportsmanFiles?sportsmanId=" + itemId;
        };

        $scope.open = function (name) {
            window.open('file/draw/' + name + '/');
        };
        $scope.setNoteAsHtml = function (text) {
            CKEDITOR.instances.ckNote.setData(text);
        };
        $scope.getNoteAsHtml = function () {
            return CKEDITOR.instances.ckNote.getData();
        };
    });
</script>

<div class="col-md-12" ng-controller="homeCtrl">

    <br/>
    <div id="sportsmanPanel" class="hidden">
        <%--<ul class="nav nav-tabs">
            <li id="details" role="presentation" data-toggle="tooltip" title="ზოგადი ინფორმაცია" class="active">
                <a href="home?type=details" style="cursor: pointer;">ზოგადი ინფორმაცია</a></li>
            <li id="files" role="presentation" data-toggle="tooltip" title="ფაილები">
                <a  href="home?type=files" style="cursor: pointer;">ფაილები</a></li>
        </ul>--%>
        <br/>
        <div ng-show="type == 'details'">
            <form name="sportsmanForm">
                <div class="col-md-12 text-center"><h4>სპორტსმენის შესახებ</h4></div>
                <div class="form-group col-md-4">
                    <label class="control-label">სახელი</label>
                    <input type="text" ng-model="sportsman.firstName" class="form-control input-sm"
                           placeholder="სახელი">
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">გვარი</label>
                    <input type="text" ng-model="sportsman.lastName" class="form-control input-sm" placeholder="გვარი">
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">სქესი</label>
                    <select class="form-control input-sm" ng-model="sportsman.genderId">
                        <option ng-repeat="g in genders" value="{{g.id}}">{{g.name}}</option>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">დაბადების თარიღი</label>
                    <input type="text" id="birthDate" ng-model="sportsman.birthDate" class="form-control input-sm"
                           placeholder="დაბადების თარიღი">
                </div>
                <div class="form-group col-md-6">
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
                <div class="form-group col-md-4">
                    <label class="control-label">სოფელი</label>
                    <input type="text" ng-model="sportsman.district" class="form-control input-sm" placeholder="სოფელი">
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">რანგი</label>
                    <select class="form-control input-sm" ng-model="sportsman.rankId">
                        <option ng-repeat="r in ranks" value="{{r.id}}">{{r.name}}</option>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">სიმაღლე</label>
                    <input type="text" ng-model="sportsman.height" class="form-control input-sm" placeholder="სიმაღლე">
                </div>
                <div class="clear"></div>
                <div class="form-group col-md-4">
                    <label class="control-label">შენიშვნა</label>
                    <input type="text" ng-model="sportsman.comment" class="form-control input-sm"
                           placeholder="შენიშვნა">
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">პარა სპორტსმენი</label>
                    <div class="mt-radio-inline">
                        <label class="mt-radio">
                            <input type="radio" ng-model="sportsman.paraSportsman" name="paraSportsmanRadio"
                                   id="optionsRadios4"
                                   value="1">კი
                            <span></span>
                        </label>
                        <label class="mt-radio">
                            <input type="radio" ng-model="sportsman.paraSportsman" name="paraSportsmanRadio"
                                   id="optionsRadios5"
                                   value="0" checked=""> არა
                            <span></span>
                        </label>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">ოლიმპიელი</label>
                    <div class="mt-radio-inline">
                        <label class="mt-radio">
                            <input type="radio" ng-model="sportsman.olimpic" name="olimpicRadio" id="optionsRadios41"
                                   value="1">კი
                            <span></span>
                        </label>
                        <label class="mt-radio">
                            <input type="radio" ng-model="sportsman.olimpic" name="olimpicRadio" id="optionsRadios51"
                                   value="0" checked=""> არა
                            <span></span>
                        </label>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">პარალიმპიელი</label>
                    <div class="mt-radio-inline">
                        <label class="mt-radio">
                            <input type="radio" ng-model="sportsman.paralympic" name="paralympicRadio"
                                   id="optionsRadiosparalympic"
                                   value="1">კი
                            <span></span>
                        </label>
                        <label class="mt-radio">
                            <input type="radio" ng-model="sportsman.paralympic" name="paralympicRadio"
                                   id="optionsRadiosparalympic2"
                                   value="0" checked=""> არა
                            <span></span>
                        </label>
                    </div>
                </div>
                <div class="clear"></div>
                <div class="form-group col-md-4">
                    <label class="control-label">კარიერის დასაწყისი</label>
                    <input type="text" id="startDate" ng-model="sportsman.careerStartDate" class="form-control input-sm"
                           placeholder="დაბადების თარიღი">
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">კარიერის დასასრული</label>
                    <input type="text" id="endDate" ng-model="sportsman.careerEndDate" class="form-control input-sm"
                           placeholder="დაბადების თარიღი">
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">გარდაცვალების თარიღი</label>
                    <input type="text" id="deathDate" ng-model="sportsman.deathDate" class="form-control input-sm"
                           placeholder="დაბადების თარიღი">
                </div>
                <div class="form-group col-md-10">
                    <label class="control-label">ბიოგრაფია</label>
                    <script>
                        $(function () {
                            CKEDITOR.replace('ckNote');
                        });
                    </script>
                    <textarea id="ckNote" class="form-control" ng-model="sportsman.biography" rows="2"
                              style="width: 500px;"></textarea>
                </div>
                <div class="clear"></div>
                <div class="form-group col-md-12">
                    <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveSportsman();">
                    <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="testPanelHide();">
                </div>
            </form>
        </div>
        <div ng-show="type == 'files'">
        </div>
    </div>

    <div id="sportsmanList">
        <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewSportsman();">დამატება</button>
        <table class="table table-striped table-hover">
            <tr>
                <th>ID</th>
                <th>სახელი გვარი</th>
                <th>სპორტის სახეობა</th>
                <th>დაბადების თარიღი</th>
                <th>რეგიონი</th>
                <th></th>
            </tr>
            <tr ng-repeat="s in sportsmans">
                <td>{{$index+1}}</td>
                <td>{{s.firstName}} {{s.lastName}}</td>
                <td>{{s.sportTypeName}}</td>
                <td>{{s.birthDate}}</td>
                <td>{{s.regionName}} {{s.cityName}}</td>
                <td style="min-width: 75px;">
                    <button type="button" class="btn btn-xs" ng-click="editSportsman(s.id)" title="რედაქტირება">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="deleteSportsman(s.id)" title="წაშლა">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="addFiles(s.id)" title="სურათების ატვირთვა">
                        <span class="glyphicon glyphicon-file"></span>
                    </button>
                </td>
            </tr>
        </table>
        <input type="button" ng-show="size>loadCount" class="btn btn-primary btn-xs" value="მეტის ჩატვირთვა" ng-click="loadMore();">
        <input type="button" ng-show="size>loadCount" class="btn btn-primary btn-xs" value="ყველა" ng-click="loadAll();">
        <br/><br/>
    </div>

</div>
<%@include file="footer.jsp" %>
