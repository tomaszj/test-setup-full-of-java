angular.module("app").directive("flashMessages", function() {
  return {
    restrict: "E",
    controller: "FlashController",
    scope: true,
    templateUrl: "_flash_messages.html"
  };
});

