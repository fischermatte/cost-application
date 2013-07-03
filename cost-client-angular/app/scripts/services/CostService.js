'use strict';

// Declare app level module which depends on filters, and services
angular.module('costsApp').factory('CostService', ['$resource', function ($resource) {
  var url = "http://localhost\\:8080/";
//  var url = 'https://cost-server.appspot.com/';
  return $resource(url + 'costs/:id', {id: '@id'}, {
    query: { method: 'GET', params: {pageSize: 25, page: 1}, isArray: true }
  });
}]);
