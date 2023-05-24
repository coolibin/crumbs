package numbers

import scala.annotation.tailrec

object NumberProblems extends App {

  def isPrime(number: Int): Boolean = {
    @tailrec
    def recursively(divider: Int): Boolean = {
      if (divider > Math.sqrt(number)) true
      else (number % divider != 0) && recursively(divider + 1)
    }
    if (number == 1) false
    else recursively(2)
  }

  /**
   * 12
   * recursively(12, 2, []) =
   * recursively(6, 2, [2]) =
   * recursively(3, 2, [2, 2]) =
   *
   * 5
   * recursively(5, 2, Nil) =
   * recursively(5, 3, Nil) =
   * List(5)
   *
   *
   *
   */
  def decompose(number: Int): List[Int] = {
    @tailrec
    def recursively(remaining: Int, divisor: Int, accumulator: List[Int]): List[Int] = {
      if (divisor > Math.sqrt(remaining)) remaining :: accumulator
      else if (remaining % divisor != 0) recursively(remaining, divisor + 1, accumulator)
      else recursively(remaining / divisor, divisor, divisor :: accumulator)
    }
    if (number < 3) List(number)
    else recursively(number, 2, Nil)
  }


}
