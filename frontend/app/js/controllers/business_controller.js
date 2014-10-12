angular.module("app").controller("BusinessController", function ($scope, $http, $routeParams, $location, FlashService) {

  var businessId = $routeParams.id;

  $scope.data = {
    business: null,
  };

  $scope.deleteBusiness = function() {
    $http.delete("/api/businesses/" + $scope.data.business.id).success(function() {
      FlashService.addMessage("Business successfully removed.");
      $location.path("/businesses");
    });
  };

  $http.get("/api/businesses/" + businessId).success(function(data) {
    $scope.data.business = data;
  });
});

