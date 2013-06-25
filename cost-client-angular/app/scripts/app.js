'use strict';

// Declare app level module which depends on filters, and services
angular.module('costsApp', ['ngGrid']).
  config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {templateUrl: 'views/cost-list.html', controller: 'CostListCtrl'});
    $routeProvider.when('/costs', {templateUrl: 'views/cost-list.html', controller: 'CostListCtrl'});
    $routeProvider.when('/costs/new', {templateUrl: 'views/cost-form.html', controller: 'CostNewCtrl'});
    $routeProvider.when('/about', {templateUrl: 'views/about.html'});

    //$locationProvider.html5Mode(true);

    /*$rootScope.$on('$afterRouteChange', function(){
     $window.scrollTo(0,0);
     });*/
  }]);


