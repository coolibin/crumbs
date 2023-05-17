package binarysarch

import $.FunSuite

import scala.annotation.tailrec

class BinarySearchRecursiveArray
  extends FunSuite{

  def searchIdxOf(v: Int, sortedArray: Array[Int]): Option[Int] = {
    @tailrec
    def recursively(start: Int, end: Int): Option[Int] = {
      val mid = (start + end) / 2
      sortedArray(mid) match {
        case x if v == x => Some(mid)
        case x if v > x =>
          recursively(mid, end)
        case x if v < x =>
          recursively(start, mid)
       }
    }

    if (sortedArray.isEmpty || v < sortedArray.head || v > sortedArray.last) {
      None
    } else {
      recursively(0, sortedArray.length - 1)
    }
  }

  test("1")(searchIdxOf(5, Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)) shouldBe Some(4))
  test("2")(searchIdxOf(11, Array(1, 2, 3, 4, 5, 6, 7, 8, 9)) shouldBe None)
  test("3")(searchIdxOf(11, Array()) shouldBe None)
  test("4")(searchIdxOf(11, Array(1)) shouldBe None)
  test("5")(searchIdxOf(1, Array(1)) shouldBe Some(0))
}
