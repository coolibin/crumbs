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

      while (result.isEmpty && (left < right)) {
        if (v == array(mid)) {
          result = Some(mid)
        } else if (v < array(mid)) {
          right = mid - 1
          mid = (left + right)/2
        } else {
          left = mid
          mid = (left + right)/2
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

  test("searchIdxOf should return Some(index) if v exists in the array") {
    val array = Array(1, 3, 5, 7, 9, 11)
    val v = 3
    val result = searchIdxOf(v, array)
    result shouldBe Some(1)
  }

  test("searchIdxOf should return None if v does not exist in the array") {
    val array = Array(1, 3, 5, 7, 9, 11)
    val v = 4
    val result = searchIdxOf(v, array)
    result shouldBe None
  }

  test("searchIdxOf should return None if array is empty") {
    val array = Array.empty[Int]
    val v = 3
    val result = searchIdxOf(v, array)
    result shouldBe None
  }

  test("searchIdxOf should return Some(0) if v is the only element in the array") {
    val array = Array(3)
    val v = 3
    val result = searchIdxOf(v, array)
    result shouldBe Some(0)
  }

  test("searchIdxOf should return Some(index) if v exists multiple times in the array") {
    val array = Array(1, 2, 3, 3, 3, 4, 5)
    val v = 3
    val result = searchIdxOf(v, array)
    result shouldBe Some(2)
  }

  test("searchIdxOf should return Some(index) if v is the first element in the array") {
    val array = Array(1, 2, 3, 4, 5)
    val v = 1
    val result = searchIdxOf(v, array)
    result shouldBe Some(0)
  }

  test("searchIdxOf should return Some(index) if v is the last element in the array") {
    val array = Array(1, 2, 3, 4, 5)
    val v = 5
    val result = searchIdxOf(v, array)
    result shouldBe Some(4)
  }

  test("searchIdxOf should return None if v is smaller than the first element in the array") {
    val array = Array(1, 2, 3, 4, 5)
    val v = 0
    val result = searchIdxOf(v, array)
    result shouldBe None
  }

  test("searchIdxOf should return None if v is bigger than the last element in the array") {
    val array = Array(1, 2, 3, 4, 5)
    val v = 6
    val result = searchIdxOf(v, array)
    result shouldBe None
  }

}
