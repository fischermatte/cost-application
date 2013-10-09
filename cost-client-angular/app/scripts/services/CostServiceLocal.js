'use strict';

// Declare app level module which depends on filters, and services
angular.module('costsApp').service('CostService', ['localStorageService', 'guidGenerator', function(localStorageService, guidGenerator) {
    this.save = function(cost, callback) {
      if (!cost.id) {
        cost.id = guidGenerator.generate();
      }
      localStorageService.add(cost.id, cost);
      if (angular.isFunction(callback))
        callback();
    };
    this.query = function(params) {
      var keys = localStorageService.keys();
      var costs = [];
      for (var key in keys) {
        costs.push(localStorageService.get(keys[key]));
      }
      return costs;
    };

    this.get = function(param, callback) {
      var cost = localStorageService.get(param.id);
      if (angular.isFunction(callback))
        callback(cost);
    };

    this.delete = function(cost, callback) {
      localStorageService.remove(cost.id);
      if (angular.isFunction(callback))
        callback(cost);
    };
  }]);
