angular.module("appDev", ["app", "ngMockE2E"]).run(function($httpBackend) {
  // adds some basic utilities to the $rootScope for debugging purposes
  var b = $httpBackend;
  b.whenGET("/api/useless-info").respond("Wat? Wat?");

  b.whenPOST("/api/useless-info").respond("AWW YISS");

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

  b.whenGET("/api/businesses/1").respond({
    id: 1,
    name: "Business 1"
  });

  b.whenGET("/api/businesses/2").respond({
    id: 2,
    name: "Business 2"
  });
});
