# Test Setup Full of Java

Sample setup of two apps (frontend and backend) tested with snappy mock-based Selenium tests and API integration tests.

The aim of this proof-of-concept is how to build complex apps, where acceptance tests can still be quickly ran. Hopefully that will prove that acceptance tests can provide an immediate feedback and should be included in continuous integration process.

# What's involved

* Frontend application based on AngularJS and [LinemanJS](http://linemanjs.com/) with Angular template.
* Suite of acceptance Selenium tests that would be derived from potential User Stories and defects. Based on JUnit.
* API built using DropWizard and Java. Contains API integration tests based on JUnit.

# License

Usual MIT.

# Credits

Thanks to [@wongatech](https://twitter.com/wongatech) for inspiration and information about his approach to acceptance tests on [AngularJSLondon meetup](http://www.meetup.com/AngularJS-London).
