package ui.browser

import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.WebDriver

class Browser(val browserConfig: BrowserConfig, val driver: WebDriver)
  extends LazyLogging {
  def close(): Unit = quit()

  def quit(): Unit = {
    if (driver != null) {
      logger.info("Quitting browser")
      driver.quit()
    }
  }
}