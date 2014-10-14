name := "test-selenium"

version := "1.0"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % Test,
  "org.seleniumhq.selenium" % "selenium-java" % "2.43.1" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test
) 

