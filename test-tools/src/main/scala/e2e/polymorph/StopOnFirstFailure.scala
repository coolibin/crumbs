package e2e.polymorph

import org.scalatest._

import scala.collection.mutable.ListBuffer

/**
 * Mix-in this trait when you need to stop the ScalaTest scenario
 * on a first failure
 */
trait StopOnFirstFailure extends SuiteMixin {
  this: Suite =>

  override def runTests(testName: Option[String], args: Args): Status = {
    import args._
    val stopRequested = stopper

    // if a testName is passed to run, just run that,
    // otherwise run the tests returned by testNames.
    testName match {
      case Some(tn) => runTest(tn, args)
      case None =>
        val statusList = new ListBuffer[Status]()
        val tests = testNames.iterator
        var failed = false
        for (test <- tests) {
          if (!failed) {
            val status = runTest(test, args)
            statusList += status
            failed = !status.succeeds()
          }
        }
        new CompositeStatus(Set.empty ++ statusList)
    }
  }
}
