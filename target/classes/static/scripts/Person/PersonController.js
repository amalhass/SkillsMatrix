/**
 * Created by amal.hassouni on 09/05/2017.
 */
'use strict';

angular.module('app').controller('PersonsCtrl', ['$scope', '$route', '$rootScope', '$routeParams', '$http', '$location','PersonService','$modal', function ($scope, $route, $rootScope, $routeParams, $http, $location, PersonService,$modal) {

        $scope.person={
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
            "skills": [{"name": "", "level": ""}],
            "attachment":""
        };


    $scope.getOne=function(){
       PersonService.getOne($rootScope.connectedUser._id).then(function(response){
           console.log($rootScope.connectedUser._id);
           $scope.person=response.data;
       },function(error){
           console.log("get person:error");
           }
       )
    };

    $scope.getAll=function () {
        PersonService.getAll().then(function(response){
            $scope.person=response.data;
        },function(error){
            console.log("get all:error")
        }
        )
    };

    $scope.getBy=function () {
        var skills=$scope.person.skills.name;
        if($scope.person.country===undefined){$scope.person.country = "";}
        if($scope.person.site===undefined){$scope.person.site = "";}
        if($scope.person.ibmId===undefined){$scope.person.ibmId = "";}
        if(skills===undefined){skills = "";}
        PersonService.getBy($scope.person.country,$scope.person.site,$scope.person.ibmId,skills).then(function (response) {

            if($scope.person.country==="" && $scope.person.site==="" && $scope.person.ibmId===""&& skills=== undefined){
                PersonService.getAll().then(function(response){
                        $scope.person=response.data;
                        $scope.person.skills="";
                        console.log($scope.person);
                    },function(error){
                        console.log("get all:error")
                    }
                )
            }
            else
                $scope.person=response.data;
            $scope.person.skills="";
            console.log($scope.person);
        },function(error){
            console.log("searchError");
        })

    }

    $scope.exportData = function () {
        var blob = new Blob([document.getElementById('exportable').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "Report.xls");
    };

}]);
