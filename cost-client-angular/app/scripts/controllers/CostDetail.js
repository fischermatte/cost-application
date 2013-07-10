'use strict';

angular.module('costsApp')
  .controller('CostDetailCtrl', function ($scope, $routeParams, $location, CostService) {
    // Project selection list
    $scope.projects = ['BAFU', 'BIT', 'EJPD', 'UNISYS', 'POST', 'VBS'];

    if ($routeParams.costId === 'new') {
      // NEW
      $scope.title = 'Aufwand erfassen';
      $scope.cost = {workDay: null};
    } else {
      // LOAD
      $scope.title = 'Aufwand bearbeiten';
      CostService.get({id: $routeParams.costId}, function (cost) {
        cost.workDay = new Date(cost.workDay);
        $scope.cost = cost;
      });
    }

    $scope.save = function () {
      CostService.save($scope.cost, function () {
        $location.url('/costs');
      });
    };

    $scope.delete = function () {
      CostService.delete({id: $scope.cost.id}, function () {
        $location.url('/costs');
      });
    };
  });
