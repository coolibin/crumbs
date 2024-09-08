
version := "1.0.0-SNAPSHOT"
organization := "coolibin"
scalaVersion := "2.13.12"

lazy val practice = project
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.testng,
      Dependencies.scalatest,
      Dependencies.`slf4j-simple`
    )
  )

lazy val `design-patterns` = project
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.testng,
      Dependencies.scalatest,
      Dependencies.`slf4j-simple`
    )
  )

lazy val snippets = project
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.scalatest,
      Dependencies.`slf4j-simple`
    )
  )

lazy val `spark-sandbox` = project
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.sparkCore,
      Dependencies.sparkSql,
      Dependencies.`slf4j-simple`,
      Dependencies.logback_classic,
    )
  )

lazy val `test-tools` = project
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.scalatest,
      Dependencies.`slf4j-simple`
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
