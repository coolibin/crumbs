package tree

import $.FunSuite


object MutableTree {
//  trait Tree[+T] {
//    def toList[T]: List[T]
//  }
//  sealed case class Branch[T](val value: T, val left: Tree[T], val right: Tree[T])
//    extends Tree[T] {
//
//    // this is not tail recursive!
//    override def toList[T]: List[T] = {
//
//      def rec[T](n: Tree[T], accumulatorL: List[T], accumulatorR: List[T]): List[T] = {
//        n match {
//          case Leaf(v) => accumulatorL ++ List(v) ++ accumulatorR
//          case Branch(v, l, r) => rec(l, accumulatorL, Nil) ++ List(v) ++ rec(r, Nil, accumulatorR)
//        }
//      }
//
//      rec[T](this, Nil, Nil)
//    }
//
//  }
//  case class Leaf[T](value: T) extends Tree[T] {
//    override def toList[T]: List[T] = Nil //TODO
//  }
}

class TestMutableTree extends FunSuite {
//  import tree.MutableTree.Leaf
//
//  test("toList on empty tree should return Nil") {
//    Leaf(1).toList shouldBe List(1)
//  }
//
//  test("toList on a tree with one element") {
//    MutableTree.Branch(123, Leaf(1), Leaf(250)).toList shouldBe List(1, 123, 250)
//  }

}




