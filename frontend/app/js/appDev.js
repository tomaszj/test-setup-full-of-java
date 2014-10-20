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
    },
    {
      id: 2,
      name: "A business added last"
    }
  ]);

  b.whenPOST("/api/businesses").respond(function(method, url, data) {
    data = JSON.parse(data);

    var correctResponse = {
      id: 3,
      name: "Business 3"
    };

    if (data.name === "Exists") {
      return ['409', ""];
    } else {
      return ['200', correctResponse]; 
    }
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
