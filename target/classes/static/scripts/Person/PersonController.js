/**
 * Created by amal.hassouni on 09/05/2017.
 */
'use strict';

angular.module('app').controller('PersonsCtrl', ['$scope', '$route', '$rootScope', '$routeParams', '$http', '$location','PersonService','$modal', function ($scope, $route, $rootScope, $routeParams, $http, $location, PersonService,$modal) {

        $scope.person={
            "_id":"",
            "firstName":"",
            "lastName":"",
            "password":""

        }


    $scope.getOne=function(){
       PersonService.getOne($rootScope.connectedUser._id).then(function(response){
           console.log($rootScope.connectedUser._id)
           $scope.person=response.data;
       },function(error){
           console.log("get person:error");
           }
       )
    };

}]);
