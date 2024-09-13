package tools

import app.{AppCtx, ToolCtx}
import com.typesafe.scalalogging.LazyLogging

abstract class ToolsBase
  extends LazyLogging {

  protected implicit val appCtx: AppCtx = new ToolCtx()

}
