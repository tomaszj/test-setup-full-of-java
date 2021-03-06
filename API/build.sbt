import NativePackagerKeys._
import AssemblyKeys._

name := "test-api"

version := "1.0"

libraryDependencies += "io.dropwizard" % "dropwizard-core" % "0.7.1"

libraryDependencies += "io.dropwizard" % "dropwizard-assets" % "0.7.1"

libraryDependencies ++= Seq(
  "io.dropwizard" % "dropwizard-testing" % "0.7.1" % Test,
  "io.dropwizard" % "dropwizard-client" % "0.7.1" % Test,
  "org.hamcrest" % "hamcrest-all" % "1.3" % Test,
  "org.mockito" % "mockito-core" % "1.10.8" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test
)


{ // Needed for Heroku's stage. Uses sbt-native-packager plugin, defined in project/plugins.sbt
  val stage = TaskKey[Unit]("stage", "Prepares the project to be run, in environments that deploy source trees rather than packages.")
  stage in Compile := {}
}

// Indicates which class' main() method is going to be used by default.
mainClass in Compile := Some("org.tomaszjaneczko.testpoc.api.TestAPIApplication")

// Using java_server archetype from sbt-native-packager.
packageArchetype.java_server

// Adding assemblySettings to activate sbt-assembly.
assemblySettings

// Skip tests in assembly step
test in assembly := {}

// Final jar name.
jarName in assembly := "test-api.jar"

// removes all jar mappings in universal and appends the fat jar
mappings in Universal <<= (mappings in Universal, assembly in Compile) map { (mappings, fatJar) =>
    val filtered = mappings filter { case (file, name) =>  ! name.endsWith(".jar") }
    filtered :+ (fatJar -> ("lib/" + fatJar.getName))
}

// the bash scripts classpath only needs the fat jar
scriptClasspath := Seq( (jarName in assembly).value )
