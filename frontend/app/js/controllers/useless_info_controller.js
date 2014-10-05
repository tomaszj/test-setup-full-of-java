// with $resource
angular.module("app").controller("UselessInfoController", function ($scope, $http) {
  $scope.data = {
    uselessInfo: "Loading ..."
  };

  $http.get("/api/useless-info").success(function(data) {
    $scope.data.uselessInfo = data;
  });
});

