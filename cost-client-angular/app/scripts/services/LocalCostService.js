'use strict';

// Declare app level module which depends on filters, and services
angular.module('costsApp').service('LocalCostService', ['localStorageService', 'GuidService', function(localStorageService, GuidService) {
    this.save = function(cost, callback) {
      if (!cost.id) {
        cost.id = GuidService.generate();
      }
      localStorageService.add(cost.id, cost);
      callback();
    };
    this.query = function() {
      var keys = localStorageService.keys();
      var costs = [];
      for (var key in keys) {
        costs.push(localStorageService.get(keys[key]));
      }
      return costs;
    };

    this.get = function(param, callback) {
      var cost = localStorageService.get(param.id);
      callback(cost);
    };

    this.delete = function(cost, callback) {
      this.deleteByGuid(cost.id);
      callback(cost);
    };

    this.deleteByGuid = function(guid) {
      localStorageService.remove(guid);
    };

    this.deleteAll = function() {
      localStorageService.clearAll();
    };
  }]);
