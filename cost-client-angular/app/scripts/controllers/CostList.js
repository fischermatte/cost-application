'use strict';

angular.module('costsApp')
  .controller('CostListCtrl', function ($scope, $http, $location, CostService) {
    $scope.pagingOptions = {
      pageSizes: [25, 50, 100],
      pageSize: 25,
      totalServerItems: 0,
      currentPage: 1
    };

    $scope.$watch('pagingOptions', function () {
      $scope.costs = CostService.query({page: $scope.pagingOptions.currentPage, pageSize: $scope.pagingOptions.pageSize});
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
      ],
      afterSelectionChange: function(rowItem){
        $location.url('/costs/' + rowItem.entity.id);
      }
    };
  });
