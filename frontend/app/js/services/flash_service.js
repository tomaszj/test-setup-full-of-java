angular.module("app").factory("FlashService", function($q, $http, $rootScope) {

  var that = this;
  var upcomingMessages = [];
  var currentMessages = [];

  var getCurrentMessages = function() {
    return currentMessages;
  };

  var addCurrentMessage = function(message) {
    currentMessages.push({
      message: message
    });
    $rootScope.$emit('currentFlashMessageUpdated', currentMessages); 
  };

  var addMessage = function(message) {
    upcomingMessages.push({ 
      message: message
    });
  };

  $rootScope.$on('$routeChangeSuccess', function() {
    currentMessages = upcomingMessages; 
    upcomingMessages = [];
  });

  return {
    getCurrentMessages: getCurrentMessages,
    addCurrentMessage: addCurrentMessage,
    addMessage: addMessage
  };
});
