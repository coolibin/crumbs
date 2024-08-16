package multithreading.javalike

object ProducerConsumerNaive {

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
      println("[consumer] actively waiting ! ...")
      while (container.isEmpty) {
        // this waiting is a waste
        // of the resources
        println("[consumer] actively waiting...")
      }
      println(s"[consumer] I have consumed ${container.get}")
    })

    val producer = new Thread(() => {
      println("[producer] computing...")
      Thread.sleep(500)
      val value = 42
      println(s"[producer] I have produced, after long work, the value $value")
      container.set(value)
    })

    consumer.start()
    producer.start()
  }
}
