package stringreverse

// no-tailrec solution
object ScalaSolution1
  extends $.Solution {

  def reversedString(in: String): String = {
    if (in == null || in.length < 2)
      in
    else
      in.takeRight(1) + reversedString(in.take(in.length - 1))
  }

  def reversedString2(in: String): String = {
    if (in == null || in.length < 2)
      in
    else
      reversedString2(in.drop(1)) + in.take(1)
  }

  reversedString("abcd") shouldBe "dcba"
  reversedString("") shouldBe ""
  reversedString(null) shouldBe null

  reversedString2("abcd") shouldBe "dcba"
  reversedString2("") shouldBe ""
  reversedString2(null) shouldBe null
}
