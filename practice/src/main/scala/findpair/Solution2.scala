package findpair

import org.testng.Assert.assertEquals

/**
 * Given a sorted array of integers and the value
 * find the first found two elements that gives the value
 * ([3,5,10,20], 15) -> [5,10]
 */
object Solution2
  extends App {

  private def findPair(sortedArray: Array[Int], total: Int): Array[Int] = {
    var left: Int = 0
    var right: Int = sortedArray.length - 1
    var result: Array[Int] = Array.empty[Int]

    while((left < right) && result.isEmpty ) {
      if (sortedArray(right) > total) {
        right = right - 1
      } else if (sortedArray(left) + sortedArray(right) == total) {
        result = Array(sortedArray(left), sortedArray(right))
      } else if (sortedArray(left) + sortedArray(right) < total) {
        left = left + 1
      }
    }
    result
  }

  assertEquals(findPair(Array[Int](3, 5, 10, 20), 15), Array[Int](5, 10))
  assertEquals(findPair(Array[Int](3, 5, 10, 20), 30), Array[Int](10, 20))
  assertEquals(findPair(Array[Int](3, 5, 10, 20), 50), Array.empty[Int])
  assertEquals(findPair(Array.empty[Int], 50), Array.empty[Int])
}
