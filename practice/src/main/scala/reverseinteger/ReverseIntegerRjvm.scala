package reverseinteger

import scala.annotation.tailrec

object ReverseIntegerRjvm extends App {

  // return a number with the digits reversed
  // if the result overflows Int, return 0

  // 253 (-> 352)
  // 253 % 10 = 25.3
  // = 25 % 10 = 2.5
  // = 2 % 10 = 0.2
  def reverseInteger(number: Int): Int = {
    @tailrec
    def recursively(remaining: Int, acc: Int): Int = {
      if (remaining == 0) {
        acc
      } else {
        val digit = remaining % 10
        val tentativeResult = acc * 10 + digit
        // can overflow
        // if overflows, the sign will change
        if ((acc >= 0) != (tentativeResult >= 0)) {
          0 // overflow
        } else {
          recursively(remaining / 10, tentativeResult)
        }
      }
    }
    if (number == Int.MinValue) 0
    else if (number >= 0) recursively(number, 0)
    else -recursively(-number, 0)
  }

  println(s"253 -> ${reverseInteger(253)}")
  println(s"987 -> ${reverseInteger(987)}")
  println(s"450 -> ${reverseInteger(450)}")
  println(s"0 -> ${reverseInteger(0)}")
  println(s"9 -> ${reverseInteger(9)}")
  println(s"123456789 -> ${reverseInteger(123456789)}")
  println(s"1234567899 -> ${reverseInteger(1234567899)}") // bug?
  println(s"2147483647 -> ${reverseInteger(2147483647)}")
}
