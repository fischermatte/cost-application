'use strict';

// Declare app level module which depends on filters, and services
angular.module('costsApp').factory('CostService', ['$resource', function ($resource) {
  return $resource('http://localhost\\:8080/costs/:id/page/:page', {id: '@id', page: '@page'}, {
    query: { method: 'GET', params: {pageSize: 25}, isArray: true }
  });
}]);
