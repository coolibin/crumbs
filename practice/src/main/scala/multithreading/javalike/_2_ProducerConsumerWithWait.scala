package multithreading.javalike

object _2_ProducerConsumerWithWait {

  class SimpleContainer {
    private var value: Int = 0
    def isEmpty: Boolean = value == 0
    def set(newValue: Int): Unit = value = newValue
    def get: Int = {
      val result = value
      value = 0
      result
    }
  }

  def main(args: Array[String]): Unit = {
    val container = new SimpleContainer
    val consumer = new Thread(() => {
      println("[consumer] passively waiting...")
      container.synchronized {
        container.wait() // no busy waiting
      }
      // at this point the container must have some value
      println(s"[consumer] I have consumed ${container.get}")
    })

    val producer = new Thread(() => {
      println("[producer] computing...")
      Thread.sleep(2000)
      val value = 42
      container.synchronized {
        println(s"[producer] I have produced, after long work, the value $value")
        container.set(value)
        container.notify() // signal to consumer thread to wake up
      }
    })

    consumer.start()
    producer.start()
  }
}
