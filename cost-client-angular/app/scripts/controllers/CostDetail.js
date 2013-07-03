'use strict';

angular.module('costsApp')
  .controller('CostDetailCtrl', function ($scope, $routeParams, CostService) {
    // Project selection list
    $scope.projects = ['BAFU','BIT','EJPD','UNISYS','POST','VBS'];

    if ($routeParams.costId === 'new') {
      // NEW
      $scope.title = 'Aufwand erfassen';
      $scope.cost = {workDay: new Date()};
    } else {
      // LOAD
      $scope.title = 'Aufwand bearbeiten';
      CostService.get({id: $routeParams.costId}, function(cost){
        cost.workDay = new Date(cost.workDay);
        $scope.cost = cost;
      });
    }

    $scope.save = function (){
        CostService.save($scope.cost);
    };
  });
