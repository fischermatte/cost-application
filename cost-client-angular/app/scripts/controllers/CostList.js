'use strict';

angular.module('costsApp')
  .controller('CostListCtrl', function ($scope, $http, Costs) {
    $scope.pagingOptions = {
      pageSizes: [20, 50, 100],
      pageSize: 20,
      totalServerItems: 0,
      currentPage: 1
    };
    $scope.loadCosts = function (pageSize, page) {
      $scope.costs = Costs.query();
    };
//    $scope.loadCosts($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function () {
      $scope.loadCosts($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
    }, true);

    $scope.gridOptions = {
      data: 'costs',
      enablePaging: true,
      showFooter: true,
      pagingOptions: $scope.pagingOptions,
      columnDefs: [
        {field: 'id', displayName: 'ID'},
        {field: 'title', displayName: 'Titel'},
        {field: 'time', displayName: 'Verbuchte Zeit'},
        {field: 'project', displayName: 'Projekt'},
        {field: 'workDay', displayName: 'Datum', cellFilter: 'date:"dd.MM.yyyy - HH:mm"'}
      ]
    };
  });
