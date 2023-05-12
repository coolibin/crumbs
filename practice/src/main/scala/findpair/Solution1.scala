package findpair

import org.testng.Assert.assertEquals

/**
 * Given a sorted array of integers and the value
 * find the first found two elements that gives the value
 * ([3,5,10, 20], 15) -> [5,10]
 */
object Solution1
  extends App {

  private def findPair(sortedArray: Array[Int], total: Int): Array[Int] = {
    var result = Array.empty[Int]
    sortedArray.zipWithIndex.foreach { case (left, idx) =>
      sortedArray.drop(idx + 1).foreach { right =>
        if (left + right == total) {
          result = Array(left, right)
        }
      }
    }
    result
  }

  assertEquals(findPair(Array[Int](3, 5, 10, 20), 15), Array[Int](5, 10))
  assertEquals(findPair(Array[Int](3, 5, 10, 20), 30), Array[Int](10, 20))
  assertEquals(findPair(Array[Int](3, 5, 10, 20), 50), Array.empty[Int])
}
