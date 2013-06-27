'use strict';

angular.module('costsApp')
  .controller('CostDetailCtrl', function ($scope, $routeParams, CostService) {
    if ($routeParams.costId === 'new') {
      $scope.title = 'Aufwand erfassen';
      $scope.cost = {};
    } else {
      $scope.title = 'Aufwand bearbeiten';
      $scope.cost = CostService.get({id: $routeParams.costId});
    }
  });
