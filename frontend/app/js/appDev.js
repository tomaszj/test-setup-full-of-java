angular.module("appDev", ["app", "ngMockE2E"]).run(function($httpBackend) {
  // adds some basic utilities to the $rootScope for debugging purposes
  var b = $httpBackend;

  b.whenGET("/api/businesses").respond([
    {
      id: 1,
      name: "Business 1"
    },
    {
      id: 2,
      name: "Business 2"
    }
  ]);

  b.whenPOST("/api/businesses").respond({
    id: 3,
    name: "Business 3"
  });

  b.whenGET("/api/businesses/1").respond({
    id: 1,
    name: "Business 1"
  });

  b.whenGET("/api/businesses/2").respond({
    id: 2,
    name: "Business 2"
  });

  b.whenDELETE("/api/businesses/1").respond(function() { 
    return [204, "", {}];
  });

  b.whenDELETE("/api/businesses/2").respond(function() { 
    return [204, "", {}];
  });
});
