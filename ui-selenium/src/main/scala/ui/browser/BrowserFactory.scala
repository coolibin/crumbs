package ui.browser

import app.AppCtx

abstract class BrowserFactory(implicit appCtx: AppCtx) {
  def createBrowser: Browser
}

object BrowserFactory {
  def createBrowser(browserConfig: BrowserConfig)(implicit appCtx: AppCtx): Browser = {
    browserConfig  match {
      case c: BrowserConfigChrome =>
        new BrowserFactoryChrome(c).createBrowser
      case c:BrowserConfigFirefox => ???
    }
  }
}
