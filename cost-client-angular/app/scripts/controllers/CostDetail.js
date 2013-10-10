'use strict';

angular.module('costsApp')
      .controller('CostDetailCtrl', function($scope, $routeParams, $location, CostService) {
        // Project selection list
        $scope.projects = ['BAFU', 'BIT', 'EJPD', 'UNISYS', 'POST', 'VBS'];

        if ($routeParams.costId === 'new') {
          // NEW
          $scope.title = 'Aufwand erfassen';
          $scope.cost = {};
        } else {
          // LOAD
          $scope.title = 'Aufwand bearbeiten';
          CostService.getActive().get({id: $routeParams.costId}, function(cost) {
            cost.workDay = new Date(cost.workDay);
            $scope.cost = cost;
          });
        }

        $scope.save = function() {
          CostService.getActive().save($scope.cost, function() {
            $location.url('/costs');
          });
        };

        $scope.delete = function() {
          CostService.getActive().delete({id: $scope.cost.id}, function() {
            $location.url('/costs');
          });
        };
      });
