package strings.countchars

import $.FunSuite

import scala.annotation.tailrec


class CountChars2 extends FunSuite {

  def countCharacters(s: String): Map[Char, Int] = {

    @tailrec
    def recursively(remained: String, acc: Map[Char, Int] = Map.empty): Map[Char, Int] = {
      if (remained.isEmpty)
        acc
      else {

        val el = remained.head
        val cnt = acc.getOrElse(el, 0)
        recursively(remained.tail, acc + (el -> (cnt + 1)))
      }
    }
    recursively(s)
  }

  countCharacters("scala") shouldBe Map('s' -> 1, 'c' -> 1, 'a' -> 2, 'l' -> 1)


}
