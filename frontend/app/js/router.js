angular.module("app").config(function($routeProvider, $locationProvider) {

  $locationProvider.html5Mode(true);

  $routeProvider.when('/useless-info', {
    templateUrl: 'useless-info.html',
    controller: 'UselessInfoController'
  });

  $routeProvider.otherwise({ redirectTo: '/useless-info' });

});
