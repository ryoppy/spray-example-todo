name := """spray-todo-example"""

version := "1.0"

scalaVersion := "2.11.7"

seq(Revolver.settings: _*)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "io.spray" %% "spray-json" % "1.3.2",
  "io.spray" %% "spray-caching" % "1.3.3",
  "io.spray" %% "spray-routing" % "1.3.3",
  "io.spray" %% "spray-can" % "1.3.3",
  "io.spray" %% "spray-testkit" % "1.3.3"
)
