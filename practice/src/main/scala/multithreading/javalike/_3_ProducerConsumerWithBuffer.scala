package multithreading.javalike

import scala.collection.mutable
import scala.util.Random

object _3_ProducerConsumerWithBuffer {

  def main(args: Array[String]): Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]()
    val capacity = 3

    val consumer = new Thread(() => {
      val random = new Random()
      while(true) {
        buffer.synchronized {
          if(buffer.isEmpty) {
            println("[consumer] buffer empty, waiting...")
            buffer.wait()
          }
          // there must be at least one value in the buffer
          val x = buffer.dequeue()
          println(s"[consumer] I consumed $x")
          // hey producer, there is an empty space available in case you was full
          buffer.notify()
        }
        Thread.sleep(random.nextInt(250))
      }
    })

    val producer = new Thread(() => {
      val random = new Random()
      var i = 0
      while(true) {
        buffer.synchronized{
          if (buffer.size == capacity) {
            println("[producer] buffer is full, waiting...")
            buffer.wait()
          }
          // at this point there must be at least one empty space in the buffer
          println(s"[producer] producing $i...")
          buffer.enqueue(i)
          // hay consumer, new value for you!
          buffer.notify()
          i += 1
        }
        Thread.sleep(random.nextInt(500))
      }
    })

    consumer.start()
    producer.start()
    println("Running further code")
  }
}
