package binarysarch

import $.FunSuite

import scala.annotation.tailrec

class BinarySearchRecursiveList
  extends FunSuite{

  def searchIdxOf(v: Int, sortedList: List[Int]): Option[Int] = {
    @tailrec
    def recursively(l: List[(Int, Int)]): Option[Int] = {
      l match {
        case Nil => None
        case x :: Nil => if (x._1 == v) Some(x._2) else None
        case l =>
          val (left, right) = l.splitAt(l.length/2)
          if (left.last._1 == v) {
            Some(left.last._2)
          } else if (left.last._1 > v) {
            recursively(left)
          } else {
            recursively(right)
          }
      }
    }
    recursively(sortedList.zipWithIndex)
  }

  test("1")(searchIdxOf(5, List(1,2,3,4,5,6,7,8,9)) shouldBe Some(4))
  test("2")(searchIdxOf(11, List(1,2,3,4,5,6,7,8,9)) shouldBe None)
  test("3")(searchIdxOf(11, List()) shouldBe None)
}
