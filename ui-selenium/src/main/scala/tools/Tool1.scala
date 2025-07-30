package tools

import ui.browser.{Browser, BrowserConfig, BrowserFactory}

object Tool1
  extends ToolsBase {
  implicit val browser: Browser = BrowserFactory.createBrowser(BrowserConfig.chromeLocal)

  def main(args: Array[String]): Unit = {
    logger.info("Start tool")
    val driver = browser.driver
    driver.get("https://www.google.com")
    Thread.sleep(5000)
    logger.info("End tool")
    browser.quit()
  }
}
