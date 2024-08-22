import sbt.*

object Dependencies {

  object Versions {
    lazy val circe = "0.14.2"
  }

  lazy val `slf4j-simple` = "org.slf4j" % "slf4j-simple" % "1.7.36"
  lazy val circe_generic = "io.circe" %% "circe-generic" % Versions.circe
  lazy val circe_yaml = "io.circe" %% "circe-yaml" % Versions.circe
  lazy val circe_parser = "io.circe" %% "circe-parser" % Versions.circe

  lazy val circeDependencies: Seq[ModuleID] = Seq(circe_generic, circe_yaml, circe_parser)

  lazy val testng =  "org.testng" % "testng" % "7.7.1"
  lazy val scalatest =  "org.scalatest" %% "scalatest" % "3.2.3"
}
