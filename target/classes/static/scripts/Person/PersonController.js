/**
 * Created by amal.hassouni on 09/05/2017.
 */
'use strict';

angular.module('app').controller('PersonsCtrl', ['$scope', '$route', '$rootScope', '$routeParams', '$http', '$location', 'PersonService', function ($scope, $route, $rootScope, $routeParams, $http, $location, PersonService) {




        $scope.person={
            "_id":"",
            "firstName":"",
            "lastName":"",
            "password":""

        }
    $scope.addPerson = function () {
        console.log("i'm here");
        $scope.person["Content-Type"]="application/json";
        PersonService.addPerson($scope.person);
        console.log($scope.person);
    }


}]);
