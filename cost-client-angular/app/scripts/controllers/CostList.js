'use strict';

angular.module('costsApp')
  .controller('CostListCtrl', function ($scope, $http) {
    $scope.pagingOptions = {
      pageSizes: [20, 50, 100],
      pageSize: 20,
      totalServerItems: 0,
      currentPage: 1
    };
    $scope.setPagingData = function (data, page, pageSize) {
      var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
      $scope.myData = pagedData;
      $scope.pagingOptions.totalServerItems = data.length;
      if (!$scope.$$phase) {
        $scope.$apply();
      }
    };
    $scope.getPagedDataAsync = function (pageSize, page) {
      setTimeout(function () {
        var data;
        var url = 'https://cost-server.appspot.com/costs';
        // var url = 'http://localhost:8080/costs';
        $http.get(url).success(function (largeLoad) {
          $scope.setPagingData(largeLoad, page, pageSize);
        });
      }, 100);
    };
    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function () {
      $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
    }, true);

    $scope.gridOptions = {
      data: 'myData',
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
