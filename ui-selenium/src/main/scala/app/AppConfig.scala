package app

import com.typesafe.config.Config

object AppConfig {
  val config: Config = com.typesafe.config.ConfigFactory.load()
}
