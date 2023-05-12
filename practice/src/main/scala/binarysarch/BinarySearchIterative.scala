package binarysarch

import $.FunSuite

class BinarySearchIterative
  extends FunSuite {

  def searchIdxOf(v: Int, array: Array[Int]): Option[Int] = {
    if (array.isEmpty || array.last < v || array.head > v) {
      None
    } else if (array.length == 1) {
      if (array.head == v) Some(0) else None
    } else {
      var left = 0
      var right = array.length - 1
      var mid = right / 2
      var result: Option[Int] = None

      while (result.isEmpty && (left != right)) {
        if (v == array(mid)) {
          result = Some(mid)
        } else if (v < array(mid)) {
          right = mid
        } else {
          left = mid
        }
      }
      result
    }
  }

  test("1")(searchIdxOf(5, Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)) shouldBe Some(4))
  test("2")(searchIdxOf(11, Array(1, 2, 3, 4, 5, 6, 7, 8, 9)) shouldBe None)
  test("3")(searchIdxOf(11, Array()) shouldBe None)
  test("4")(searchIdxOf(11, Array(1)) shouldBe None)
  test("5")(searchIdxOf(1, Array(1)) shouldBe Some(0))
}
