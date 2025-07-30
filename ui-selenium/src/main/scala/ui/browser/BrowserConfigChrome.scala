package ui.browser

case class BrowserConfigChrome(
  browserName: BrowserName = BrowserNames.Chrome,
  hubUrl: Option[String] = None,
  incognitoMode: Boolean = false,
  ignoreSslErrors: Boolean = true,
  ignoreCertificateErrors: Boolean = true,
  disableExtensions: Boolean = true,
  headless: Boolean = false,
  sessionName: Option[String] = None,
) extends BrowserConfig
