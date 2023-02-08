
version := "1.0.0-SNAPSHOT"

organization := "coolibin"

scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.3"
)




lazy val circe = project
  .settings(
    libraryDependencies ++= Dependencies.circeDependencies
  )