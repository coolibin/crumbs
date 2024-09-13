package ui.browser

case class BrowserConfigFirefox(
  browserName: BrowserName = BrowserNames.Firefox,
  hubUrl: Option[String] = None,
  incognitoMode: Boolean = false,
  disableExtensions: Boolean = false,
  headless: Boolean = false,
  sessionName: Option[String] = None,
) extends BrowserConfig
