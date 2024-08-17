package concurrency

import java.util.concurrent.Executors

object Example1 {

  def main(args: Array[String]): Unit = {

    val pool = Executors.newFixedThreadPool(10)
    pool.execute(() => println("I'm running first"))

    pool.execute(() => {
      Thread.sleep(1000)
      println("I'm running after 1 second")
    })

    pool.execute(() => {
      Thread.sleep(1000)
      println("I'm running after 1 seconds - 2")
      Thread.sleep(1000)
      println("I'm running after 2 seconds")
    })

    println("Main thread is done.")
    pool.shutdown()
  }

}
