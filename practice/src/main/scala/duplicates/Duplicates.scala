package duplicates

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.annotation.tailrec
import scala.collection.immutable.{Queue, TreeSet}

/**
 * Given a list of numbers with duplicates find the first number that is not duplicated
 */

class Duplicates extends AnyFunSuite with Matchers {

  private def duplicates(list: List[Int]): Int = {

    @tailrec
    def recursively(remained: List[Int], visited: Set[Int]): Int = {
      if (remained.isEmpty) visited.head
      else {
        if (visited.contains(remained.head)) {
          recursively(remained.tail, visited.filterNot(_ == remained.head))
        } else {
          recursively(remained.tail, visited + remained.head)
        }
      }
    }
    recursively(list, Set.empty[Int])
  }

  duplicates(List(1,2,3,1,2)) shouldBe 3
  duplicates(List(5,5,6)) shouldBe 6
  duplicates(List(99,88,66,88,99)) shouldBe 66

  private val range = (1 to 10000).toList
  duplicates(range ++ List(856868686) ++ range) shouldBe 856868686
}
