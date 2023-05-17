package rotatedarray

import $.FunSuite

import java.security.spec.InvalidParameterSpecException
import scala.annotation.tailrec

/**
 * Example: A sorted array has been rotated so that the elements might appear
 * in the order 3 4 5 6 7 1 2. How would you find the minimum?
 *
 * 3 4 5  6 7 1 2
 *
 * 7,1,2, 3,4,5,6
 * 7, 1,2
 */

class FindMinimumInRotetedArray
  extends FunSuite {

  private def divideArray(a: Array[Int]): (Array[Int], Array[Int]) = {
    val mid = a.length / 2
    (a.take(mid), a.drop(mid))
  }

  @tailrec
  private def findMinimum(array: Array[Int]): Int = {
    if (array.isEmpty) {
      throw new InvalidParameterSpecException("array should not be empty!")
    } else if (array.length == 1) {
      array.head
    } else if (array.length <= 2) {
      Math.min(array(0), array(1))
    } else {
      val (left, right) = divideArray(array)
      if (left.head <= left.last) {
        findMinimum(right)
      } else {
        findMinimum(left)
      }
    }
  }

  test("1")(findMinimum(Array(3, 4, 5, 6, 7, 1, 2)) shouldBe 1)
  test("2")(findMinimum(Array(7, 1, 2, 3, 4, 5, 6)) shouldBe 1)
  test("3")(findMinimum(Array(0)) shouldBe 0)
}
