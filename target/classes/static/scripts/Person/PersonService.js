/**
 * Created by amal.hassouni on 09/05/2017.
 */


angular.module('app')
    .factory('PersonService', ['$http', function ($http) {
        var urlBase = '/person';
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
        dataFactory.getBy=function (country,site,ibmId,skill) {
            return $http.get(urlBase+'/getBy?country="'+country+'"&site="'+site+'"&ibmId="'+ibmId+'"&Skill="'+skill+'"');
           // return $http.get(urlBase+'/getBy?country="'+country+'"&site="'+site+'"');
        }
        return dataFactory;
    }]);


