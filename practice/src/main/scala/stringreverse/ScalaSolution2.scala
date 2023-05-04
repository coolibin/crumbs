package stringreverse

object ScalaSolution2
  extends $.Solution {

  def reversedString(in: String): String = {
    if (in == null || in.isEmpty)
      in
    else {
      val charList = in.toList
      // no tailrec
      def recursively(h: Char, tail: List[Char]): List[Char] = {
        tail match {
          case Nil => List(h)
          case x :: Nil => List(x, h)
          case x :: xs => recursively(x, xs) ++ List(h)
        }
      }
      recursively(charList.head, charList.tail).mkString
    }
  }

  reversedString("abcd") shouldBe "dcba"
  reversedString("") shouldBe ""
  reversedString(null) shouldBe null
}
