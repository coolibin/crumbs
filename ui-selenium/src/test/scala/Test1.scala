class Test1 extends TestBase {

  test("Test1") {
    logger.info("Start test")

    val driver = browser.driver

    driver.get("https://www.google.com")

    Thread.sleep(2000)

    logger.info("End test")
  }
}
