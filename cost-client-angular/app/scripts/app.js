'use strict';

// Declare app level module which depends on filters, and services
angular.module('costsApp', ['ngGrid', 'ngResource', '$strap.directives', 'LocalStorageModule']).
      config(['$routeProvider', function($routeProvider) {
          $routeProvider.when('/', {templateUrl: 'views/cost-list.html', controller: 'CostListCtrl'});
          $routeProvider.when('/costs', {templateUrl: 'views/cost-list.html', controller: 'CostListCtrl'});
          $routeProvider.when('/costs/:costId', {templateUrl: 'views/cost-detail.html', controller: 'CostDetailCtrl'});
          $routeProvider.when('/about', {templateUrl: 'views/about.html'});
        }]).value('$strapConfig', {
  datepicker: {
    language: 'en',
    format: 'dd.mm.yyyy'
  }
});



