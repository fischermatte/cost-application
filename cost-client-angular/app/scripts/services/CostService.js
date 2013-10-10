'use strict';

// Declare app level module which depends on filters, and services
angular.module('costsApp').service('CostService', ['$rootScope', 'LocalCostService', 'RemoteCostService', function($rootScope, LocalCostService, RemoteCostService) {
    $rootScope.localMode = true;
    this.setStorageMode = function(storageMode) {
      $rootScope.storageMode = storageMode;
    };
    this.getActive = function() {
      if ($rootScope.localMode) {
        return LocalCostService;
      } else {
        return RemoteCostService;
      }
    };
    this.pushLocalToRemote = function(callback) {
      var outer = this;
      var costs = LocalCostService.query();
      if (!costs || costs.length === 0) {
        return callback();
      }
      var cost = costs[0];
      var uuid = cost.id;
      cost.id = null;
      RemoteCostService.save(cost, function() {
        LocalCostService.deleteByGuid(uuid);
        outer.pushLocalToRemote(callback);
      });
    };
  }]);
