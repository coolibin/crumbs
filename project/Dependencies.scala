import sbt._

object Dependencies {

  object Versions {
    lazy val circe = "0.14.2"
  }

  val circe_generic = "io.circe" %% "circe-generic" % Versions.circe
  val circe_yaml = "io.circe" %% "circe-yaml" % Versions.circe
  val circe_parser = "io.circe" %% "circe-parser" % Versions.circe

  lazy val circeDependencies: Seq[ModuleID] = Seq(circe_generic, circe_yaml, circe_parser)

}
