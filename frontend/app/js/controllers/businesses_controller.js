// with $resource
angular.module("app").controller("BusinessesController", function ($scope, $http, FlashService) {
  $scope.data = {
    businesses: [],
    newBusiness: {},
    alreadyExistsMessageVisible: false
  };

  $scope.getBusinesses = function() {
    $http.get("/api/businesses").success(function(data) {
      $scope.data.businesses = data;
    });
  };

  $scope.createBusiness = function() {
    var newName = $scope.data.newBusiness.name;

    $scope.data.alreadyExistsMessageVisible = false;

    if (newName.length > 0) {
      $http.post("/api/businesses", {
        name: newName
      }).success(function(data) {
        $scope.data.newBusiness.name = "";
        FlashService.addCurrentMessage("Business added successfully.");
        $scope.getBusinesses();
      }).error(function(data, status) {
        if (status === 409) {
          $scope.data.alreadyExistsMessageVisible = true;
        }  
      }); 
    }
  };

  $scope.getBusinesses();
});

