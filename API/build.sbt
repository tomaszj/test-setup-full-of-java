name := "test-api"

version := "1.0"

libraryDependencies += "io.dropwizard" % "dropwizard-core" % "0.7.1"

mainClass in Compile := Some("org.tomaszjaneczko.testpoc.api.TestAPIApplication")

