// with $resource
angular.module("app").controller("BusinessController", function ($scope, $http, $routeParams) {

  var businessId = $routeParams.id;

  $scope.data = {
    business: null,
  };

  $http.get("/api/businesses/" + businessId).success(function(data) {
    $scope.data.business = data;
  });
});

