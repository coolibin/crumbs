package duplicates

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.annotation.tailrec

/**
 * Given a list of numbers with duplicates find the first number that is not duplicated
 */

class Duplicates3 extends AnyFunSuite with Matchers {

  private def duplicates(list: List[Int]): Int = {

    // using the fact that
    // a XOR a = 0
    // a XOR 0 = a

    list.fold(0)(_ ^ _)
  }

  duplicates(List(1,2,3,1,2)) shouldBe 3
  duplicates(List(5,5,6)) shouldBe 6
  duplicates(List(99,88,66,88,99)) shouldBe 66

  private val range = (1 to 10000).toList
  duplicates(range ++ List(856868686) ++ range) shouldBe 856868686

  duplicates(Nil) shouldBe 0
}
