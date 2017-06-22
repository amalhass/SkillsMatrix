/**
 * Created by amal.hassouni on 15/05/2017.
 */

'use strict';

angular.module('app').controller('ProfileCtrl', ['$scope', '$route', '$rootScope', '$routeParams', '$http', '$location', 'PersonService', '$modal', '$localStorage', function ($scope, $route, $rootScope, $routeParams, $http, $location, PersonService, $modal, $localStorage) {

    $rootScope.person = {
        "_id": "",
        "firstName": "",
        "lastName": "",
        "password": "",
        "job": "",
        "role": "",
        "country": "",
        "site": "",
        "ibmId": "",
        "brand": "",
        "projectName": "",
        "startOfProject": "",
        "endOfProject": "",
        "useRate": "",
        "skills": [{"name": "", "level": ""}]
    };

    $scope.updatePerson = function (person) {
        // JSON.stringfy($rootScope.person.skills);
        //angular.toJson($rootScope.person.skills);
        PersonService.update(person).then(function (response) {
            $location.path("/dashboard");
        }, function (error) {
            console.log("update:error");
        });


    };
    $scope.getOne = function () {
        PersonService.getOne($rootScope.connectedUser._id).then(function (response) {
                $rootScope.person = response.data;
            }, function (error) {
                console.log("get person:error");
            }
        )
    };
    $scope.getOne($rootScope.connectedUser._id);


    $scope.openSkillsModal = function () {

        $rootScope.person["Content-Type"] = "application/json";
        $modal.open({
            templateUrl: 'myModalContent.html',
            backdrop: true, controller: function ($scope, $modalInstance, PersonService) {
                $scope.annulerSuppresion = function () {
                    $modalInstance.dismiss('cancel');

                };
                $scope.models = {
                    selected: null,
                    lists: {"A": []}
                };

                $scope.selectSkill = function () {
                    var selectElmt = document.getElementById("skills");
                    var textselectionne = selectElmt.options[selectElmt.selectedIndex].text;
                    var myObject = {"skill": []};
                    myObject.skill = textselectionne;
                    $scope.models.lists.A.push(myObject);

                };


                $scope.$watch('models', function (model) {
                    $scope.modelAsJson = angular.toJson(model, true);
                }, true);

                $scope.deleteSkill = function (index) {
                    $scope.models.lists.A.splice(index, 1);
                };
                $scope.validerSkills = function () {

                    var list = [];
                    angular.forEach($scope.models.lists.A, function (data) {
                        list.push(data.skill);
                    });

                    if (list.length > 0) {
                        for (var i = 0; i < list.length; i++) {
                            $rootScope.person.skills.name = list[i];

                            switch ($scope.stars) {
                                case 1 :
                                    $rootScope.person.skills.level = "Beginner skills";
                                    break;
                                case 2:
                                    $rootScope.person.skills.level = "Intermediate";
                                    break;
                                case 3:
                                    $rootScope.person.skills.level = "Senior";
                                    break;
                            }
                            var obj = {"name": $rootScope.person.skills.name, "level": $rootScope.person.skills.level}
                            $rootScope.person.skills.push(obj);
                        }
                    }
                    $modalInstance.dismiss('cancel');
                    console.log($rootScope.person.skills);

                };
                $scope.stars = 0;
                $scope.setStars = function (value) {
                    $scope.stars = value + 1;
                };
                $scope.litStars = function(value){
                    if($scope.stars>value){
                        return { "color" : "yellow" };
                    }
                    else {
                        return { "color" : "grey" };
                    }
                };



            }

        });
    };


    //Upload image from local
    $(document).ready(function () {

        var readURL = function (input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('.profile-pic').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }
        $(".file-upload").on('change', function () {
            readURL(this);
        });
        $(".upload-button").on('click', function () {
            $(".file-upload").click();
        });
    });


    $('.controls').on("input click", "#search", function (e) {
        var val = $(this).val();

        dataList.empty();

        if (val === "" || val.length < 10) return;

        if (testObj.results.length) {
            for (var i = 0, len = testObj.results.length; i < len; i++) {
                var opt = $("<option></option>").attr("value", testObj.results[i]['skill']);
                tempObj[testObj.results[i]['skill']] = testObj.results[i]['id'];

                dataList.append(opt);
            }
        }

    });


}]);
