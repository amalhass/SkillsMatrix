/**
 * Created by amal.hassouni on 09/05/2017.
 */


angular.module('app')
    .factory('PersonService', ['$http', function ($http) {
        var urlBase = 'http://localhost:8080/person';
        var dataFactory = {};

        dataFactory.getAll = function () {
            return $http.get(urlBase);
        };


        dataFactory.addPerson = function (entity) {
            return $http.post(urlBase, entity);
        };

        dataFactory.getOne=function (id) {
            return $http.get(urlBase+'/getOne?Id=' +id);
        };
        dataFactory.update=function(entity){
            return $http.put(urlBase,entity)
        };
        dataFactory.getAttach=function (id) {
            return $http.get(urlBase+'/attach?Id=' +id)

        }
        return dataFactory;
    }]);


