angular.module("app").config(function($routeProvider, $locationProvider) {

  $locationProvider.html5Mode(true);

  $routeProvider.when('/businesses', {
    templateUrl: 'businesses.html',
    controller: 'BusinessesController'
  });

  $routeProvider.when('/businesses/:id', {
    templateUrl: 'business.html',
    controller: 'BusinessController'
  });

  $routeProvider.otherwise({ redirectTo: '/businesses' });
});
