package stringreverse

import scala.annotation.tailrec

object ScalaSolution3
  extends $.Solution {

  def reversedString(in: String): String = {
    if (in == null || in.length < 2)
      in
    else {
      val charList = in.toList

      @tailrec
      def recursively(toSort: List[Char], h: Char, sorted: List[Char]): String = {
        toSort match {
          case Nil => sorted.mkString
          case x :: Nil => (x :: h :: sorted).mkString
          case x :: xs => recursively(xs, x, h :: sorted)
        }
      }

      recursively(charList.tail, charList.head, Nil)
    }
  }

  reversedString("abcd") shouldBe "dcba"
  reversedString("") shouldBe ""
  reversedString(null) shouldBe null
}