// with $resource
angular.module("app").controller("BusinessesController", function ($scope, $http, FlashService) {
  $scope.data = {
    businesses: [],
    newBusiness: {}
  };

  $scope.getBusinesses = function() {
    $http.get("/api/businesses").success(function(data) {
      $scope.data.businesses = data;
    });
  };

  $scope.createBusiness = function() {
    var newName = $scope.data.newBusiness.name;

    if (newName.length > 0) {
      $http.post("/api/businesses", {
        name: newName
      }).success(function(data) {
        FlashService.addCurrentMessage("Business added successfully.");
        $scope.getBusinesses();
      }); 
    }
  };

  $scope.getBusinesses();
});

