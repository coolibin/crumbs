package reverseinteger

import scala.annotation.tailrec

object ReverseInteger extends App {

  // return a number with the digits reversed
  // if the result overflows Int, return 0

  // 253 (-> 352)
  // 253 % 10 = 25.3
  // = 25 % 10 = 2.5
  // = 2 % 10 = 0.2
  def reverseInteger(number: Int): Int = {
    @tailrec
    def recursively(remaining: Int, accumulator: Int): Int = {
      if (accumulator.toLong * 10 + remaining >= Integer.MAX_VALUE.toLong){
        0
      } else if (remaining < 10) {
        accumulator * 10 + remaining
      } else {
        val newRemain = remaining / 10
        val newNum = remaining % 10
        recursively(newRemain, accumulator * 10 + newNum)
      }
    }
    recursively(number, 0)
  }

  println(s"253 -> ${reverseInteger(253)}")
  println(s"987 -> ${reverseInteger(987)}")
  println(s"450 -> ${reverseInteger(450)}")
  println(s"0 -> ${reverseInteger(0)}")
  println(s"9 -> ${reverseInteger(9)}")
  println(s"123456789 -> ${reverseInteger(123456789)}")
  println(s"1234567899 -> ${reverseInteger(1234567899)}")
  println(s"2147483647 -> ${reverseInteger(2147483647)}")
}
