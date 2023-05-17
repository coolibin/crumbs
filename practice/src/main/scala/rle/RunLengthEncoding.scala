package rle

import $.FunSuite

import scala.annotation.tailrec

/**
 * Given a string with a repeated characters
 * return a list of tuples containing a character and its number of occurrences:
 *
 * Input: "aabbbbcc"
 * Output: List(('a',2), ('b', 3), ('c', 2)
 *
 * Input: ""
 * Output: Nil
 */

class RunLengthEncoding
  extends FunSuite {

  /**
   * Complexity: O(N)
   */
  def rle(list: List[Char]): List[(Char, Int)] = {
    @tailrec
    def recursively(remaining: List[Char], current: (Char, Int), accumulator: List[(Char, Int)]): List[(Char, Int)] = {
      println(current.toString())
      // empty input
      if (remaining.isEmpty && current._2 == 0) accumulator
      // last element
      else if (remaining.isEmpty) (current :: accumulator).reverse
      // same element
      else if (remaining.head == current._1) recursively(remaining.tail, current.copy(_2 = current._2 + 1), accumulator)
      // new element
      else recursively(remaining.tail, (remaining.head, 1), current :: accumulator)
    }

    if (list.isEmpty) Nil
    else recursively(list, (list.head, 0), Nil)
  }

  test("1")(rle("abc".toList) shouldBe List(('a', 1), ('b', 1), ('c', 1)))
  test("2")(rle("aabbcc".toList) shouldBe List(('a', 2), ('b', 2), ('c', 2)))
  test("3")(rle("abbbc".toList) shouldBe List(('a', 1), ('b', 3), ('c', 1)))
  test("4")(rle("".toList) shouldBe Nil)
}
