package ui.browser

import app.AppCtx
import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.remote.RemoteWebDriver

import java.util

class BrowserFactoryChrome(c: BrowserConfigChrome)(implicit appCtx: AppCtx)
  extends BrowserFactory
    with LazyLogging {

  private val defaultOptions: ChromeOptions = {
    val defaultOptions = new ChromeOptions()
    defaultOptions.addArguments("test-type")
    defaultOptions.addArguments("test-type=browser")
    defaultOptions.addArguments("disable-infobars")
    defaultOptions.addArguments("--disable-popup-blocking")
    defaultOptions.addArguments("--disable-default-apps")
    defaultOptions.addArguments("disable-features=DownloadBubble,DownloadBubbleV2")
    defaultOptions.addArguments("allow-file-access-from-files")
    defaultOptions.addArguments("use-fake-device-for-media-stream")
    defaultOptions.addArguments("use-fake-ui-for-media-stream")
    defaultOptions.addArguments("--log-level=3")
    defaultOptions.addArguments("--silent")
    defaultOptions.addArguments("--dns-prefetch-disable")
    defaultOptions.addArguments("--always-authorize-plugins")
    defaultOptions.setExperimentalOption(
      "excludeSwitches", {
        val enableAutomation = new Array[String](1)
        enableAutomation(0) = "enable-automation"
        enableAutomation
      }
    )
    defaultOptions
  }

  def createBrowser: Browser = {
    logger.info(s"Creating Chrome browser")
    val options = this.defaultOptions
    if (c.incognitoMode) options.addArguments("--incognito")
    if (c.ignoreSslErrors) options.addArguments("--ignore-ssl-errors=yes")
    if (c.ignoreCertificateErrors) options.addArguments("--ignore-certificate-errors")
    if (c.headless) options.addArguments("--headless")

    val prefs = new util.HashMap[String, Object]()
    prefs.put("profile.block_third_party_cookies", "false")
    prefs.put("browser.helperApps.alwaysAsk.force", "false")
    prefs.put("profile.default_content_settings.popups", "false")
    prefs.put("download.prompt_for_download", "false")
    prefs.put("download.directory_upgrade", "true")
    prefs.put("safebrowsing.enabled", "true")
    prefs.put("intl.accept_languages", "en-US")
    options.setExperimentalOption("prefs", prefs)

    val driver = c.hubUrl match {
      case Some(url) =>
        options.setCapability("enableVNC", true)
        options.setCapability("enableVideo", true)
        options.setCapability("sessionName", c.sessionName.getOrElse("Chrome"))
        new RemoteWebDriver(new java.net.URL(url), options)
      case None =>
        new ChromeDriver(options)
    }

    new Browser(c, driver)
  }
}
