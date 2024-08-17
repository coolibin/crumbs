package strings.countchars

import $.FunSuite

class CountChars extends FunSuite{

  private def countCharacters(s: String): Map[Char, Int] = {
    val result = s.groupBy(ch => ch)
      .map { case(ch, xs) => (ch, xs.length)}
    result
  }

  countCharacters("scala") shouldBe Map('s' -> 1, 'c' -> 1, 'a' -> 2, 'l' -> 1)


}
