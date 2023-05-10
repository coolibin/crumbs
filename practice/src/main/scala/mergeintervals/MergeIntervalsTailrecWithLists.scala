package mergeintervals

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.annotation.tailrec

/**
 * Given an array of intervals where intervals[1] = [start, end],
 * merge all overlapping intervals,
 * and return an array of the non-overlapping intervals
 * that cover all the intervals in the input
 *
 * This is a solution that uses Lists
 */

class MergeIntervalsTailrecWithLists
  extends AnyFunSuite
    with Matchers {

  private val joinIntervals: (List[Int], List[Int]) => List[Int] = (l, r) => List(l.head, r.tail.head)

  @tailrec
  private def recursively(rest: List[List[Int]], accumulator: List[List[Int]]): List[List[Int]] = {
    if (rest.isEmpty) {
      accumulator.reverse
    } else {
      val lastInterval = accumulator.head
      val currentInterval = rest.head
      if (lastInterval.tail.head >= currentInterval.head) {
        //join the intervals
        recursively(rest.tail, joinIntervals(lastInterval, currentInterval) :: accumulator.drop(1))
      } else {
        // leave the current interval as is
        recursively(rest.tail, currentInterval :: accumulator)
      }
    }
  }

  def mergeIntervals(intervals: List[List[Int]]): List[List[Int]] = {
    if (intervals.isEmpty) {
      List.empty
    } else {
      recursively(intervals.tail, List(intervals.head))
    }
  }


  test("List(List(1,3), List(2,6), List(8,10), List(15,18))") {
    val intervals = List(List(1, 3), List(2, 6), List(8, 10), List(15, 18))
    mergeIntervals(intervals) shouldBe List(List(1, 6), List(8, 10), List(15, 18))
  }

  test("List(List(1, 4), List(4, 5))") {
    val intervals = List(List(1, 4), List(4, 5))
    mergeIntervals(intervals) shouldBe List(List(1, 5))
  }

  test("Nil") {
    val intervals = Nil
    mergeIntervals(intervals) shouldBe Nil
  }

}
