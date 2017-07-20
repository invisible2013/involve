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
    app.filter('asHtml', function ($sce) {
        return function (val) {
            return $sce.trustAsHtml(val);
        };
    });
    app.controller("refereeCtrl", function ($scope, $http, $filter, $location) {
        var absUrl = $location.absUrl();
        $scope.type = "sportsman";
        var type = absUrl.split("?")[1].split("=")[1];
        if (type) {
            $scope.type = type;
            $('li.active').removeClass('active');
            $('li#' + type).addClass('active');
        }

        function getSuccessItems(res) {
            $scope.items = res.data.list;
            console.log($scope.items);
        }
        ajaxCall($http, "news/get-news", null, getSuccessItems);

        function getSuccessReferees(res) {
            $scope.research = res.data.list;
        }
        ajaxCall($http, "news/get-research", null, getSuccessReferees);

        function getSuccessTrainers(res) {
            $scope.analisys = res.data.list;
        }
        ajaxCall($http, "news/get-analisys", null, getSuccessTrainers);

        function getSuccessHistories(res) {
            $scope.histories = res.data.list;
        }
        ajaxCall($http, "news/get-histories", null, getSuccessHistories);

        function getSuccessDocumentations(res) {
            $scope.documentations = res.data.list;
        }
        ajaxCall($http, "news/get-documentations", null, getSuccessDocumentations);

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
        $scope.addNewDocumentation = function () {
            $('#documentationList').addClass("hidden");
            $('#documentationPanel').removeClass("hidden");

        };
        $scope.addNewHistory = function () {
            $('#historyList').addClass("hidden");
            $('#historyPanel').removeClass("hidden");

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
        $scope.documentationPanelHide = function () {
            $('#documentationPanel').addClass("hidden");
            $('#documentationList').removeClass("hidden");
        };
        $scope.historyPanelHide = function () {
            $('#historyPanel').addClass("hidden");
            $('#historyrList').removeClass("hidden");
        };

        $scope.saveItem = function () {
            $scope.item.text = $scope.getNoteAsHtml();
            function saveSuccessItem(res) {
                location.reload();
            };
            ajaxCall($http, "news/save-news", angular.toJson($scope.item), saveSuccessItem);
        };

        $scope.saveReferee = function () {
            function saveSuccessItem(res) {
                location.reload();
            };
            ajaxCall($http, "news/save-research", angular.toJson($scope.item), saveSuccessItem);
        };

        $scope.saveTrainer = function () {
            function saveSuccessItem(res) {
                location.reload();
            };
            ajaxCall($http, "news/save-analisys", angular.toJson($scope.item), saveSuccessItem);
        };
        $scope.saveDocumentation = function () {
            function saveSuccessItem(res) {
                location.reload();
            };
            ajaxCall($http, "news/save-documentation", angular.toJson($scope.item), saveSuccessItem);
        };
        $scope.saveHistory = function () {
            function saveSuccessItem(res) {
                location.reload();
            };
            ajaxCall($http, "news/save-history", angular.toJson($scope.item), saveSuccessItem);
        };


        $scope.editItem = function (itemId) {
            if (itemId != undefined) {
                $('#sportsmanList').addClass("hidden");
                $('#sportsmanPanel').removeClass("hidden");
                var selected = $filter('filter')($scope.items, {id: itemId}, true);
                $scope.item = selected[0];
                $scope.setNoteAsHtml($scope.item.text);
            }
        };
        $scope.editReferee = function (itemId) {
            if (itemId != undefined) {
                $('#refereeList').addClass("hidden");
                $('#refereePanel').removeClass("hidden");
                var selected = $filter('filter')($scope.research, {id: itemId}, true);
                $scope.item = selected[0];
            }
        };
        $scope.editTrainer = function (itemId) {
            if (itemId != undefined) {
                $('#trainerList').addClass("hidden");
                $('#trainerPanel').removeClass("hidden");
                var selected = $filter('filter')($scope.analisys, {id: itemId}, true);
                $scope.item = selected[0];
            }
        };
        $scope.editDocumentation = function (itemId) {
            if (itemId != undefined) {
                $('#documentationList').addClass("hidden");
                $('#documentationPanel').removeClass("hidden");
                var selected = $filter('filter')($scope.documentations, {id: itemId}, true);
                $scope.item = selected[0];
            }
        };
        $scope.editHistory = function (itemId) {
            if (itemId != undefined) {
                $('#historyList').addClass("hidden");
                $('#historyPanel').removeClass("hidden");
                var selected = $filter('filter')($scope.histories, {id: itemId}, true);
                $scope.item = selected[0];
            }
        };

        $scope.deleteItem = function (itemId) {
            if (confirm("დარწმუნებული ხართ რომ გსურთ წაშლა?")) {
                if (itemId != undefined) {
                    ajaxCall($http, "news/delete-news?itemId=" + itemId, null, reload);
                }
            }
        };

        $scope.addFiles = function (itemId) {
            window.location = "newsFiles?itemId=" + itemId;
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
    <br/>
    <ul class="nav nav-tabs">
        <li id="news" role="presentation" data-toggle="tooltip" title="სიახლეები" class="active"><a
                href="news?type=news">სიახლეები</a></li>
        <li id="research" role="presentation" data-toggle="tooltip" title="მკვლევები"><a
                href="news?type=research">კვლევები</a></li>
        <li id="analisys" role="presentation" data-toggle="tooltip" title="ანალიზი"><a
                href="news?type=analisys">ანალიზი</a></li>
        <li id="documentation" role="presentation" data-toggle="tooltip" title="დოკუმენტაცია"><a
                href="news?type=documentation">დოკუმენტაცია</a></li>
        <li id="history" role="presentation" data-toggle="tooltip" title="ისტორია"><a
                href="news?type=history">ისტორია</a></li>
    </ul>
    <br/>

    <div ng-show="type == 'news'">
        <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewSportsman();">დამატება</button>

        <form name="sportsmanForm" class="hidden" id="sportsmanPanel">
            <div class="col-md-12 text-center"><h4>სიახლეები</h4></div>

            <div class="form-group col-md-12">
                <label class="control-label">სათაური</label>
                <input type="text" ng-model="item.title" class="form-control input-sm"
                       placeholder="სათაური">
            </div>
            <div class="form-group col-md-12">
                <label class="control-label">მოკლე აღწერა</label>
                <textarea class="form-control" ng-model="item.shortDescription" rows="2"
                           placeholder="მოკლე აღწერა"></textarea>
            </div>
            <div class="form-group col-md-12">
                <label class="control-label">ტექსტი</label>
                <script>
                    $(function () {
                        CKEDITOR.replace('ckNote');
                    });
                </script>
                <textarea id="ckNote" class="form-control" ng-model="item.text" rows="2"
                          style="width: 500px;"></textarea>
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
                <th>სათაური</th>
                <th>მოკლე აღწერა</th>
                <th></th>
            </tr>
            <tr ng-repeat="s in items">
                <td>{{$index+1}}</td>
                <td>{{s.title}}</td>
                <td>{{s.shortDescription}}</td>
                <td style="min-width: 105px;">
                    <button type="button" class="btn btn-xs" ng-click="editItem(s.id)">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="deleteItem(s.id)">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="addFiles(s.id)" title="სურათების ატვირთვა">
                        <span class="glyphicon glyphicon-file"></span>
                    </button>
                </td>
            </tr>
        </table>

    </div>
    <div ng-show="type == 'research'">

        <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewReferee();">დამატება</button>

        <form name="sportsmanForm" class="hidden" id="refereePanel">
            <div class="col-md-12 text-center"><h4>კვლევები</h4></div>

            <div class="form-group col-md-12">
                <label class="control-label">სათაური</label>
                <input type="text" ng-model="item.title" class="form-control input-sm"
                       placeholder="სათაური">
            </div>
            <div class="form-group col-md-12">
                <label class="control-label">მოკლე აღწერა</label>
                <textarea class="form-control" ng-model="item.shortDescription" rows="2"
                          placeholder="მოკლე აღწერა"></textarea>
            </div>
            <div class="form-group col-md-12">
                <label class="control-label">შინაარსი</label>
                <textarea class="form-control" ng-model="item.text" rows="2"
                          placeholder="შინაარსი"></textarea>
            </div>
            <div class="clear"></div>

            <div class="form-group col-md-12">
                <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveReferee();">
                <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="refereePanelHide();">
            </div>
        </form>

        <table class="table table-striped table-hover" id="refereeList">
            <tr>
                <th>ID</th>
                <th>სათაური</th>
                <th>მოკლე აღწერა</th>
                <th></th>
            </tr>
            <tr ng-repeat="s in research">
                <td>{{$index+1}}</td>
                <td>{{s.title}}</td>
                <td>{{s.shortDescription}}</td>
                <td style="min-width: 105px;">
                    <button type="button" class="btn btn-xs" ng-click="editReferee(s.id)">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="deleteItem(s.id)">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="addFiles(s.id)" title="სურათების ატვირთვა">
                        <span class="glyphicon glyphicon-file"></span>
                    </button>
                </td>
            </tr>
        </table>

    </div>
    <div ng-show="type == 'analisys'">
        <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewTrainer();">დამატება</button>

        <form name="sportsmanForm" class="hidden" id="trainerPanel">
            <div class="col-md-12 text-center"><h4>ანალიზი</h4></div>

            <div class="form-group col-md-12">
                <label class="control-label">სათაური</label>
                <input type="text" ng-model="item.title" class="form-control input-sm"
                       placeholder="სათაური">
            </div>
            <div class="form-group col-md-12">
                <label class="control-label">მოკლე აღწერა</label>
                <textarea class="form-control" ng-model="item.shortDescription" rows="2"
                          placeholder="მოკლე აღწერა"></textarea>
            </div>
            <div class="form-group col-md-12">
                <label class="control-label">შინაარსი</label>
                <textarea class="form-control" ng-model="item.text" rows="2"
                          placeholder="შინაარსი"></textarea>
            </div>

            <div class="clear"></div>

            <div class="form-group col-md-12">
                <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveTrainer();">
                <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="trainerPanelHide();">
            </div>
        </form>

        <table class="table table-striped table-hover" id="trainerList">
            <tr>
                <th>ID</th>
                <th>სათაური</th>
                <th>მოკლე აღწერა</th>
                <th></th>
            </tr>
            <tr ng-repeat="s in analisys">
                <td>{{$index+1}}</td>
                <td>{{s.title}}</td>
                <td>{{s.shortDescription}}</td>
                <td style="min-width: 105px;">
                    <button type="button" class="btn btn-xs" ng-click="editTrainer(s.id)">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="deleteItem(s.id)">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="addFiles(s.id)" title="სურათების ატვირთვა">
                        <span class="glyphicon glyphicon-file"></span>
                    </button>
                </td>
            </tr>
        </table>
    </div>

    <div ng-show="type == 'documentation'">
        <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewDocumentation();">დამატება</button>

        <form name="sportsmanForm" class="hidden" id="documentationPanel">
            <div class="col-md-12 text-center"><h4>დოკუმენტაცია</h4></div>

            <div class="form-group col-md-12">
                <label class="control-label">სათაური</label>
                <input type="text" ng-model="item.title" class="form-control input-sm"
                       placeholder="სათაური">
            </div>
            <div class="form-group col-md-12">
                <label class="control-label">მოკლე აღწერა</label>
                <textarea class="form-control" ng-model="item.shortDescription" rows="2"
                          placeholder="მოკლე აღწერა"></textarea>
            </div>
            <div class="form-group col-md-12">
                <label class="control-label">შინაარსი</label>
                <textarea class="form-control" ng-model="item.text" rows="2"
                          placeholder="შინაარსი"></textarea>
            </div>

            <div class="clear"></div>

            <div class="form-group col-md-12">
                <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveDocumentation();">
                <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="documentationPanelHide();">
            </div>
        </form>

        <table class="table table-striped table-hover" id="documentationList">
            <tr>
                <th>ID</th>
                <th>სათაური</th>
                <th>მოკლე აღწერა</th>
                <th></th>
            </tr>
            <tr ng-repeat="s in documentations">
                <td>{{$index+1}}</td>
                <td>{{s.title}}</td>
                <td>{{s.shortDescription}}</td>
                <td style="min-width: 105px;">
                    <button type="button" class="btn btn-xs" ng-click="editDocumentation(s.id)">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="deleteItem(s.id)">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="addFiles(s.id)" title="სურათების ატვირთვა">
                        <span class="glyphicon glyphicon-file"></span>
                    </button>
                </td>
            </tr>
        </table>
    </div>
    <div ng-show="type == 'history'">
        <button class="btn btn-primary btn-xs pull-right margin5" ng-click="addNewHistory();">დამატება</button>

        <form name="sportsmanForm" class="hidden" id="historyPanel">
            <div class="col-md-12 text-center"><h4>ისტორია</h4></div>

            <div class="form-group col-md-12">
                <label class="control-label">სათაური</label>
                <input type="text" ng-model="item.title" class="form-control input-sm"
                       placeholder="სათაური">
            </div>
            <div class="form-group col-md-12">
                <label class="control-label">მოკლე აღწერა</label>
                <textarea class="form-control" ng-model="item.shortDescription" rows="2"
                          placeholder="მოკლე აღწერა"></textarea>
            </div>
            <div class="form-group col-md-12">
                <label class="control-label">შინაარსი</label>
                <textarea class="form-control" ng-model="item.text" rows="2"
                          placeholder="შინაარსი"></textarea>
            </div>

            <div class="clear"></div>

            <div class="form-group col-md-12">
                <input type="button" class="btn btn-primary btn-sm" value="შენახვა" ng-click="saveHistory();">
                <input type="button" class="btn btn-default btn-sm" value="გაუქმება" ng-click="historyPanelHide();">
            </div>
        </form>

        <table class="table table-striped table-hover" id="historyList">
            <tr>
                <th>ID</th>
                <th>სათაური</th>
                <th>მოკლე აღწერა</th>
                <th></th>
            </tr>
            <tr ng-repeat="s in histories">
                <td>{{$index+1}}</td>
                <td>{{s.title}}</td>
                <td>{{s.shortDescription}}</td>
                <td style="min-width: 105px;">
                    <button type="button" class="btn btn-xs" ng-click="editHistory(s.id)">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="deleteItem(s.id)">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                    <button type="button" class="btn btn-xs" ng-click="addFiles(s.id)" title="სურათების ატვირთვა">
                        <span class="glyphicon glyphicon-file"></span>
                    </button>
                </td>
            </tr>
        </table>


    </div>
</div>
<%@include file="footer.jsp" %>
