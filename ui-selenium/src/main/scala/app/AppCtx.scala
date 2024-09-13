package app

import app.config.ConfigHolder

trait AppCtx {
  implicit val configHolder: ConfigHolder
}

class TestCtx extends AppCtx {
  implicit val configHolder: ConfigHolder = ConfigHolder(AppConfig.config)
}

class ToolCtx extends AppCtx {
  implicit val configHolder: ConfigHolder = ConfigHolder(AppConfig.config)
}
