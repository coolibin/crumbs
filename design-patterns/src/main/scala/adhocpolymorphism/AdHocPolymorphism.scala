package adhocpolymorphism

object AdHocPolymorphism {

  trait Adder[T] {
    def sum(a: T, b: T): T
  }

  private object Adder {
    def sum[T: Adder](a: T, b: T): T = implicitly[Adder[T]].sum(a, b)

    implicit val int2Adder: Adder[Int] = (a: Int, b: Int) => a + b
    /**
     * This is equivalent to:
     * {{{
     * implicit val int2Adder: Adder[Int] = new Adder[Int] {
     *   override def sum(a: Int, b: Int): Int = a + b
     * }
     * }}}
     */
    implicit val string2Adder: Adder[String] = (a: String, b: String) =>
      s"$a concatenated with $b"
  }

  def main(args: Array[String]): Unit = {
    import Adder._
    println(sum(1, 2))
    println(sum("Hello", "World"))
  }
}
