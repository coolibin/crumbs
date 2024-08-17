package largestnumber

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class LargestNumber extends AnyFunSuite with Matchers{

  // ln(10, 5)

  def ln(numbers: List[Int]): String = {
    val strings = numbers.map(_.toString).sorted.reverse
    strings.mkString
  }

  test("1") (ln(List(10, 5)) shouldBe "510")
  test("2") (ln(List(1,2,3, 9)) shouldBe "9321")
  test("3") (ln(List(10, 50, 55, 11)) shouldBe "55501110")
  test("4") (ln(List(4,9,88)) shouldBe "9884")

}
