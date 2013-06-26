'use strict';

// Declare app level module which depends on filters, and services
angular.module('costsApp', ['ngGrid', 'ngResource']).
  config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {templateUrl: 'views/cost-list.html', controller: 'CostListCtrl'});
    $routeProvider.when('/costs', {templateUrl: 'views/cost-list.html', controller: 'CostListCtrl'});
    $routeProvider.when('/costs/new', {templateUrl: 'views/cost-form.html', controller: 'CostNewCtrl'});
    $routeProvider.when('/about', {templateUrl: 'views/about.html'});
  }]).
  factory('Costs', ['$resource', function ($resource) {
    return $resource('http://localhost\\:8080/costs',{},{
      query: { method: 'GET',params: null ,isArray:true }
    });
  }]);


