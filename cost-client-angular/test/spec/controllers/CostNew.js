'use strict';

describe('Controller: CostNewCtrl', function() {

  // load the controller's module
  beforeEach(module('costsApp'));

  var CostNewCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function($controller, $rootScope) {
    scope = $rootScope.$new();
    CostNewCtrl = $controller('CostNewCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function() {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
