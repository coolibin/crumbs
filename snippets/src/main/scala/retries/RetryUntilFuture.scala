package retries

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.Random

object RetryUntilFuture {

  private def retryUntil[A](action: () => Future[A], condition: A => Boolean): Future[A] = {
    action()
      .filter(condition)
      .recoverWith {
        case _ => retryUntil(action, condition)
      }
  }

  def main(args: Array[String]): Unit = {
    val random = new Random
    val actionF = () => Future {
      Thread.sleep(100)
      val nextValue = random.nextInt(100)
      println(s"generated $nextValue")
      nextValue
    }

    val resultF = retryUntil(actionF, (x: Int) => x < 20)
      .map { result =>
        println(s"settled at $result")
      }

    Await.ready(resultF, 10.seconds)
  }

}
