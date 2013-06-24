'use strict';

angular.module('costsApp')
  .controller('CostListCtrl', function ($scope, $http) {
    $scope.filterOptions = {
      filterText: '',
      useExternalFilter: true
    };
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
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
      setTimeout(function () {
        var data;
        if (searchText) {
          var ft = searchText.toLowerCase();
          $http.get('https://cost-server.appspot.com/costs').success(function (largeLoad) {
            data = largeLoad.filter(function (item) {
              return JSON.stringify(item).toLowerCase().indexOf(ft) !== -1;
            });
            $scope.setPagingData(data, page, pageSize);
          });
        } else {
          $http.get('https://cost-server.appspot.com/costs').success(function (largeLoad) {
            $scope.setPagingData(largeLoad, page, pageSize);
          });
        }
      }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function () {
      $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
    }, true);
    $scope.$watch('filterOptions', function () {
      $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
    }, true);

    $scope.gridOptions = {
      data: 'myData',
      enablePaging: true,
      showFooter: true,
      pagingOptions: $scope.pagingOptions,
      filterOptions: $scope.filterOptions
    };
  });
