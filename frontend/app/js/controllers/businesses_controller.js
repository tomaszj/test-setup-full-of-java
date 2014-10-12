// with $resource
angular.module("app").controller("BusinessesController", function ($scope, $http) {
  $scope.data = {
    businesses: [],
  };

  $http.get("/api/businesses").success(function(data) {
    $scope.data.businesses = data;
  });
});

