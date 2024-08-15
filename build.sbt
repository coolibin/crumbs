
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

lazy val `design-patterns` = project
  .settings(
    libraryDependencies ++= Seq(
      "org.testng" % "testng" % "7.7.1",
      "org.scalatest" %% "scalatest" % "3.2.3",
      "org.slf4j" % "slf4j-simple" % "1.7.36"
    )
  )

lazy val `warski-di` = project
  .settings(
    libraryDependencies ++= Seq(
      "com.softwaremill.macwire" %% "macros" % "2.5.8" % "provided",
      "com.softwaremill.macwire" %% "macrosakka" % "2.5.8" % "provided",
      "com.softwaremill.macwire" %% "util" % "2.5.8",
      "com.softwaremill.macwire" %% "proxy" % "2.5.8",
      "org.scalatest" %% "scalatest" % "3.2.3"
    )
  )
