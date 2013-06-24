'use strict';

describe('Controller: CostDetailCtrl', function() {

  // load the controller's module
  beforeEach(module('costsApp'));

  var CostDetailCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function($controller, $rootScope) {
    scope = $rootScope.$new();
    CostDetailCtrl = $controller('CostDetailCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function() {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
