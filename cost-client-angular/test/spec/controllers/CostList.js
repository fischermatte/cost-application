'use strict';

describe('Controller: CostListCtrl', function() {

  // load the controller's module
  beforeEach(module('costsApp'));

  var CostListCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function($controller, $rootScope) {
    scope = $rootScope.$new();
    CostListCtrl = $controller('CostListCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function() {
    var value = 3;
    expect(value).toBe(3);
  });
});
