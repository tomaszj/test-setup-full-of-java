name := "test-api"

version := "1.0"

libraryDependencies += "io.dropwizard" % "dropwizard-core" % "0.7.1"

libraryDependencies += "io.dropwizard" % "dropwizard-testing" % "0.7.1" % Test

libraryDependencies += "io.dropwizard" % "dropwizard-client" % "0.7.1" % Test

libraryDependencies += "org.hamcrest" % "hamcrest-all" % "1.3" % Test


mainClass in Compile := Some("org.tomaszjaneczko.testpoc.api.TestAPIApplication")

