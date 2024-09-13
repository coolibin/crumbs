package ui.browser

sealed abstract class BrowserName(val name: String)

object BrowserNames {
  case object Chrome extends BrowserName("chrome")

  case object Firefox extends BrowserName("firefox")
}

