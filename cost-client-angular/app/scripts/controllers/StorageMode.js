'use strict';

angular.module('costsApp')
      .controller('StorageModeCtrl', function($scope, $route, $location, $rootScope, CostService) {
        $scope.mode = function() {
          return $rootScope.localMode ? 'Local' : 'Remote';
        };

        $scope.toggleMode = function() {
          $rootScope.localMode = !$rootScope.localMode;
          $route.reload();
        };

        $scope.pushLocalToRemote = function() {
          CostService.pushLocalToRemote(function() {
            $rootScope.localMode = false;
            $route.reload();
          });
        };
      });
