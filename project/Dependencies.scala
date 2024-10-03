import sbt.*

object Dependencies {

  object Versions {
    lazy val circe = "0.14.2"
    lazy val oracle = "12.1"
    lazy val `akka-typed` = "2.6.19"
  }

  lazy val config = "com.typesafe" % "config" % "1.4.3"
  lazy val `slf4j-simple` = "org.slf4j" % "slf4j-simple" % "1.7.36"
  lazy val logback_classic = "ch.qos.logback" % "logback-classic" % "1.1.7"
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
  lazy val circe_generic = "io.circe" %% "circe-generic" % Versions.circe
  lazy val circe_yaml = "io.circe" %% "circe-yaml" % Versions.circe
  lazy val circe_parser = "io.circe" %% "circe-parser" % Versions.circe
  lazy val oracle = "com.oracle" % "ojdbc7" % Versions.oracle

  lazy val circeDependencies: Seq[ModuleID] = Seq(circe_generic, circe_yaml, circe_parser)

  lazy val testng = "org.testng" % "testng" % "7.7.1"
  lazy val scalatest = "org.scalatest" %% "scalatest" % "3.2.3"

  lazy val sparkCore = "org.apache.spark" %% "spark-core" % "3.5.2"
  lazy val sparkSql = "org.apache.spark" %% "spark-sql" % "3.5.2"

  lazy val seleniumJava = "org.seleniumhq.selenium" % "selenium-java" % "3.141.59"

  lazy val `akka-typed` = "com.typesafe.akka" %% "akka-actor-typed" % Versions.`akka-typed`
}
