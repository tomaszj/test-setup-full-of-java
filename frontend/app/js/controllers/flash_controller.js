angular.module("app").controller("FlashController", function($scope, $rootScope, FlashService) {

  $scope.messages = FlashService.getCurrentMessages();

  $rootScope.$on('currentFlashMessageUpdated', function(event, data) {
    $scope.messages = data;
  });
});
