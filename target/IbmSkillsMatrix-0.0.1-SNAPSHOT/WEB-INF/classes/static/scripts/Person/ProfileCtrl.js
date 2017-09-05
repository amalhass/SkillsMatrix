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
        "skills": [{"name": "", "level": ""}],
        "attachment":""
    };
    var img;
    $scope.updatePerson = function (person) {
        PersonService.update(person).then(function (response) {

            //$scope.folder="../images";
            $scope.imageName=$rootScope.connectedUser.firstName+".jpg";
            $rootScope.person.role=$('#role1 option:selected').text();
            console.log( $rootScope.person.job);
            $location.path("/dashboard");
        }, function (error) {
            console.log("update:error");
        });


    };

    $scope.getOne = function () {
        PersonService.getOne($rootScope.connectedUser._id).then(function (response) {

            $rootScope.person = response.data;
            var temp = $rootScope.connectedUser.role;
            var mySelect = document.getElementById('role1');
            for(var i, j = 0; i = mySelect.options[j]; j++) {
                if(i.value == temp) {
                    mySelect.selectedIndex = j;
                    break;
                }
            }
            //$scope.folder="../images";
            $scope.imageName=$rootScope.connectedUser.firstName+".jpg";

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




    $("#job1").change(function() {
        if ($(this).data('options') === undefined) {
            /*Taking an array of all options-2 and kind of embedding it on the select1*/
            $(this).data('options', $('#role1 option').clone());
        }
        var id = $(this).val();
        var options = $(this).data('options').filter('[data-value="' + id + '"]');
        $('#role1').html(options);
    });


    $(document).ready(function() {
        $("#upload-file-input").on("change", uploadFile);
    });

    /**
     * Upload the file sending it via Ajax at the Spring Boot server.
     */
    function uploadFile() {
        $.ajax({
            url: "person/uploadFile",
            type: "POST",
            data: new FormData($("#upload-file-form")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                
            },
            error: function () {
                // Handle upload error
                // ...
            }
        });
    }


    function getSelectedText(elementId) {
        var elt = document.getElementById(elementId);

        if (elt.selectedIndex == -1)
            return null;

        return elt.options[elt.selectedIndex].text;
    }

}]);

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
 $("#upload-file-input").on('change', function () {
 readURL(this);
 });
 $(".upload-button").on('click', function () {
 $("#upload-file-input").click();
 });

 });

/*function handleFileSelect(evt) {
 var files = evt.target.files; // FileList object

 // Loop through the FileList and render image files as thumbnails.
 for (var i = 0, f; f = files[i]; i++) {

 // Only process image files.
 if (!f.type.match('image.*')) {
 continue;
 }

 var reader = new FileReader();

 // Closure to capture the file information.
 reader.onload = (function(theFile) {
 return function(e) {
 // Render thumbnail.
 var span = document.createElement('span');
 span.innerHTML = ['<img class="thumb" src="', e.target.result,
 '" title="', escape(theFile.name), '"/>'].join('');

 document.getElementById('list').insertBefore(span, null);
 localStorage.setItem('img'+$scope.person._id, e.target.result);
 };
 })(f);

 // Read in the image file as a data URL.
 reader.readAsDataURL(f);
 }
 }

 document.getElementById('files').addEventListener('change', handleFileSelect, false);


 if(localStorage.img+$scope.person._id) {

 var span = document.createElement('span');
 span.innerHTML += ['<img class="thumb" src="', localStorage.img+$scope.person._id,
 '" title="test"/>'].join('');

 document.getElementById('files').insertBefore(span, null);

 }*/


/*
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

 });*/