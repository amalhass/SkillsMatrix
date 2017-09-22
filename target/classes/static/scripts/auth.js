angular.module('app.auth', [])

/**
 * A simple example service that returns some data.
 */
    .factory('AuthService', function ($http, $window) {

        return {
            authLocal: function (requestAuth) {
                config = {
                    url: '/auth',
                    method: "POST",
                    data: requestAuth
                };
                return $http(config);
            },
            getUser: function () {
                config = {
                    url: '/user',
                    method: "GET"
                };
                return $http(config);
            },
            deconnexion: function () {
                config = {
                    url: '/deconnexion',
                    method: "GET"
                };
                return $http(config);
            }
        }

    })

    /**
     * Controleur pour la page d'authentification.
     */
    .controller(
        'AuthenticationController',
        ['$scope', '$location', '$animate', 'AuthService', '$modal',
            function ($scope, $location, $animate, AuthService, $modal) {
                $scope.login = {};

                // Executé lors du click sur le bouton de login
                this.submit = function () {
                    console.log("here");
                    var authuser = {
                        "_id": $scope.login._id,
                        "password": $scope.login.password
                    };
                    console.log(authuser);
                    AuthService.authLocal(authuser).success(function () {
                        $location.path('/');
                    })
                        .error(function () {
                            // si la connexion a échoué : "secoue" le formulaire
                            // de connexion
                            var elt = angular.element('.form-container');
                            $animate.addClass(elt, 'shake', function () {
                                $animate.removeClass(elt, 'shake');

                            });
                        });
                };

                $scope.person={
                    "_id":"",
                    "firstName":"",
                    "lastName":"",
                    "password":""

                };
                $scope.openModal = function () {
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
            }]);
