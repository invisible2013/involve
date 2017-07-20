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
            $scope.items = res.data;
        }

        ajaxCall($http, "organisation/get-organisations", null, getSuccessSportsmans);

        function getSuccessRegions(res) {
            $scope.regions = res.data;
        }

        ajaxCall($http, "parameter/get-regions", null, getSuccessRegions);

        $scope.addNewSportsman = function () {
            $('#sportsmanList').addClass("hidden");
            $('#sportsmanPanel').removeClass("hidden");
            $scope.item = {'regionId': 1};
        };
        $scope.testPanelHide = function () {
            $('#sportsmanPanel').addClass("hidden");
            $('#sportsmanList').removeClass("hidden");
        }

        $scope.saveItem = function () {
            function saveSuccessOrganisation(res) {
                console.log(res);
                if ($('#documentId')[0].files[0]) {
                    var oMyForm = new FormData();
                    oMyForm.append("organisationId", res.data.id);
                    oMyForm.append("file", $('#documentId')[0].files[0]);
                    $.ajax({
                        url: 'organisation/add-image',
                        data: oMyForm,
                        dataType: 'text',
                        processData: false,
                        contentType: false,
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
            ajaxCall($http, "organisation/save-organisation", angular.toJson($scope.item), saveSuccessOrganisation);
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
                    ajaxCall($http, "organisation/delete-organisation", angular.toJson({'itemId': itemId}), reload);
                }
            }
        };

        $scope.open = function (name) {
            window.open('upload/get-file?identifier=' + name);
        };
    });
</script>

<div class="col-md-12" ng-controller="homeCtrl">

    <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewSportsman();">დამატება</button>

    <form name="sportsmanForm" class="hidden" id="sportsmanPanel">
        <div class="col-md-12 text-center"><h4>ორგანიზაციის შესახებ</h4></div>
        <div class="form-group col-md-4">
            <label class="control-label">სახელი</label>
            <input type="text" ng-model="item.name" class="form-control input-sm" placeholder="სახელი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">მისამართი</label>
            <input type="text" ng-model="item.address" class="form-control input-sm" placeholder="მისამართი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">ტელეფონის N</label>
            <input type="text" ng-model="item.mobilePhone" class="form-control input-sm" placeholder="ტელეფონის N">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">ელ-ფოსტა</label>
            <input type="text" ng-model="item.email" class="form-control input-sm" placeholder="ელ-ფოსტა">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">ვებ-გვერდი</label>
            <input type="text" ng-model="item.web" class="form-control input-sm" placeholder="ვებ-გვერდი">
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">რეგიონი</label>
            <select class="form-control input-sm" ng-model="item.regionId">
                <option ng-repeat="r in regions" value="{{r.id}}">{{r.name}}</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label class="control-label">ლოგო</label>
            <input type="file" id="documentId" name="file" class="form-control input-sm upload-file">
        </div>
        <div class="form-group col-md-12">
            <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveItem();">
            <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="testPanelHide();">
        </div>
    </form>


    <table class="table table-striped table-hover" id="sportsmanList">
        <tr>
            <th>ID</th>
            <th>ლოგო</th>
            <th>დასახელება</th>
            <th>რეგიონი</th>
            <th>მისამართი</th>
            <th>ტელეფონი</th>
            <th>ელ-ფოსტა</th>
            <th></th>
        </tr>
        <tr ng-repeat="s in items">
            <td>{{$index+1}}</td>
            <td><a class="btn btn-xs" ng-click="open(s.logoUrl);">
                <img src="upload/get-file?identifier={{s.logoUrl}}" class="img-thumbnail"
                     style="height: 50px;" height="50">
            </a></td>
            <td>{{s.name}}</td>
            <td>{{s.regionName}}</td>
            <td>{{s.address}}</td>
            <td>{{s.mobilePhone}}</td>
            <td>{{s.email}}</td>
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
