
version := "1.0.0-SNAPSHOT"
organization := "coolibin"
scalaVersion := "2.12.15"

lazy val practice = project
  .settings(
    libraryDependencies ++= Seq(
      "org.testng" % "testng" % "7.7.1",
      "org.scalatest" %% "scalatest" % "3.2.3",
      "org.slf4j" % "slf4j-simple" % "1.7.36"
    )
  )
