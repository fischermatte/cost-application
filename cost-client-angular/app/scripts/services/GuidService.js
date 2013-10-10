'use strict';

angular.module('costsApp').service('GuidService', [function() {

    var s4 = function s4() {
      return Math.floor((1 + Math.random()) * 0x10000)
            .toString(16)
            .substring(1);
    };

    this.generate = function() {
      return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
            s4() + '-' + s4() + s4() + s4();
    };
  }]);
