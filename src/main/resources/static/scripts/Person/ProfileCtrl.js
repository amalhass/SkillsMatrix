/**
 * Created by amal.hassouni on 15/05/2017.
 */

'use strict';

angular.module('app').controller('ProfileCtrl', ['$scope', '$route', '$rootScope', '$routeParams', '$http', '$location', 'PersonService','$modal','$localStorage', function ($scope, $route, $rootScope, $routeParams, $http, $location, PersonService,$modal,$localStorage) {

    $scope.person={
        "_id":"",
        "firstName":"",
        "lastName":"",
        "password":"",
        "job":"",
        "role":"",
        "country":"",
        "site":"",
        "ibmId":"",
        "brand":"",
        "projectName":"",
        "endOfProject":"",
        "useRate":""
    }

    $scope.updatePerson=function (person) {
        PersonService.update(person).then(function(response){
            $location.path("/dashboard");
        },function(error){
            console.log("update:error");
        });


    };
    $scope.getOne=function(){
        PersonService.getOne($rootScope.connectedUser._id).then(function(response){
                console.log("i'm here");
                $scope.person=response.data;
            },function(error){
                console.log("get person:error");
            }
        )
    };
    $scope.getOne($rootScope.connectedUser._id);


    $scope.openSkillsModal = function () {
        $scope.person["Content-Type"]="application/json";
        $modal.open({
            templateUrl: 'myModalContent.html',
            backdrop: true, controller: function ($scope, $modalInstance,PersonService) {
                $scope.annulerSuppresion = function () {
                    $modalInstance.dismiss('cancel');

                };
                $scope.addPerson = function(){
                    var promise = PersonService.addPerson($scope.person);
                    promise.success(function(status){
                        $modalInstance.dismiss('cancel');
                        toastr.success('Registration Successful',"");

                    }).error(function(data,status){
                        toastr.error('Email is already taken',"");
                    });

                };
            }

        });
    }



    $(document).ready(function() {


        var readURL = function(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('.profile-pic').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }


        $(".file-upload").on('change', function(){
            readURL(this);
        });

        $(".upload-button").on('click', function() {
            $(".file-upload").click();
        });
    });
}]);
