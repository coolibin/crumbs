import app.{AppCtx, TestCtx}
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import ui.base.BrowserHolder
import ui.browser.{Browser, BrowserConfig, BrowserFactory}

abstract class TestBase
  extends AnyFunSuite
    with BeforeAndAfterAll
    with LazyLogging
    with BrowserHolder {

  protected implicit val appCtx: AppCtx = new TestCtx()

  protected implicit val browser: Browser = BrowserFactory.createBrowser(BrowserConfig.chromeLocal)

  override def afterAll(): Unit = {
    super.afterAll()
    browser.quit()
  }
}
