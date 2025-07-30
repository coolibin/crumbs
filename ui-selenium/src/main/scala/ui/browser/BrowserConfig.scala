package ui.browser

trait BrowserConfig



object BrowserConfig {
  def chromeLocal: BrowserConfigChrome = BrowserConfigChrome(
    browserName = BrowserNames.Chrome,
  )

  def chromeRemote: BrowserConfig = BrowserConfigChrome(
    browserName = BrowserNames.Chrome,
    hubUrl = Some("https://moon.nateralab.org/wd/hub"),
    sessionName = Some("crumbs")
  )
}
