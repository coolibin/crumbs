package trees

import $.FunSuite

import scala.annotation.tailrec
import scala.collection.immutable.Queue

sealed abstract class BTree[+T] {
  def value: T

  def left: BTree[T]

  def right: BTree[T]

  def isEmpty: Boolean // true for NEnd

  def isLeaf: Boolean

  def collectLeaves: List[BTree[T]]

  def collectLeaves2: List[BTree[T]]

  def leafCount: Int

  def size: Int

  def collectNodes(level: Int): List[BTree[T]]

  def mirror: BTree[T]

  def sameShapeAs[S >: T](that: BTree[S]): Boolean

  def toList: List[T]
}

case object BEnd extends BTree[Nothing] {
  def value: Nothing = throw new NoSuchElementException()

  def left: BTree[Nothing] = throw new NoSuchElementException()

  def right: BTree[Nothing] = throw new NoSuchElementException()

  def isEmpty: Boolean = true

  def isLeaf = true

  def collectLeaves: List[BTree[Nothing]] = Nil

  def collectLeaves2: List[BTree[Nothing]] = Nil

  def leafCount: Int = 0

  val size: Int = 0

  def collectNodes(level: Int): List[BTree[Nothing]] = Nil

  def mirror: BTree[Nothing] = this

  def sameShapeAs[S >: Nothing](that: BTree[S]): Boolean = that.isEmpty

  def toList: List[Nothing] = List()
}

case class BNode[+T](val value: T, val left: BTree[T], val right: BTree[T]) extends BTree[T] {
  override def isEmpty: Boolean = false

  def isLeaf: Boolean = left.isEmpty && right.isEmpty

  def collectLeaves: List[BTree[T]] = {
    @tailrec
    def recursively(todo: List[BTree[T]], leaves: List[BTree[T]]): List[BTree[T]] = {
      if (todo.isEmpty) {
        // nothing else to traverse, returning the result
        leaves
      } else if (todo.head.isEmpty) {
        // reached the end of the branch
        // doing nothing with the end,
        // just excluding it from the plan
        recursively(todo.tail, leaves)
      } else if (todo.head.isLeaf) {
        // found the leaf,
        // adding it to the result, and continuing with the plan
        recursively(todo.tail, todo.head :: leaves)
      } else {
        // nothing of above happened
        // meaning we are looking at a regular node
        // just collecting the child nodes
        val node = todo.head
        recursively(node.left :: node.right :: todo.tail, leaves)
      }
    }

    recursively(List(this), Nil)
  }

  override def leafCount: Int = collectLeaves.length

  val size: Int = 1 + left.size + right.size

  def collectLeaves2: List[BTree[T]] = {
    @tailrec
    def recursively(todo: List[BTree[T]], result: List[BTree[T]]): List[BTree[T]] = {
      if (todo.isEmpty) {
        result
      } else if (todo.head.isLeaf) {
        recursively(todo.tail, todo.head :: result)
      } else {
        val node = todo.head
        recursively(node.left :: node.right :: todo.tail, result)
      }
    }

    recursively(List(this), List())
  }

  def collectNodes(level: Int): List[BTree[T]] = {
    @tailrec
    def recursively(layers: List[List[BTree[T]]]): List[BTree[T]] = {
      if (layers.length == level) {
        val nodes = layers.head.filterNot(_.isEmpty)
        val r = nodes.flatMap(n => List(n.left, n.right)).filterNot(_.isEmpty)
        r
      } else {
        val nodes: List[BTree[T]] = layers.head.filterNot(_.isEmpty)
        recursively(nodes.flatMap(n => List(n.left, n.right)) :: layers)
      }
    }

    if (level == 0) {
      List(this)
    } else {
      recursively(List(List(this)))
    }
  }

  /**
   * Complexity O(N)
   */
  def mirror: BTree[T] = {

    @tailrec
    def recursively(todos: List[BTree[T]], expanded: Set[BTree[T]], done: List[BTree[T]]): BTree[T] = {
      if (todos.isEmpty) done.head
      else {
        val node = todos.head
        if (node.isEmpty || node.isLeaf) {
          recursively(todos.tail, expanded, todos.head :: done)
        } else if (!expanded.contains(node)) {
          recursively(node.left :: node.right :: todos, expanded + node, done)
        } else {
          val newLeft = done.head
          val newRight = done.tail.head
          val newNode = BNode(node.value, newLeft, newRight)
          recursively(todos.tail, expanded, newNode :: done.tail.tail)
        }
      }
    }

    recursively(List(this), Set(), List())
  }

  def sameShapeAs[S >: T](that: BTree[S]): Boolean = {

    @tailrec
    def recursively(thisRemaining: List[BTree[S]], thatRemaining: List[BTree[S]]): Boolean = {
      if (thisRemaining.isEmpty)
        thatRemaining.isEmpty
      else if (thatRemaining.isEmpty)
        thisRemaining.isEmpty
      else {
        val thisNode = thisRemaining.head
        val thatNode = thatRemaining.head
        if (thisNode.isEmpty)
          thatNode.isEmpty && recursively(thisRemaining.tail, thatRemaining.tail)
        else if (thisNode.isLeaf)
          thatNode.isLeaf && recursively(thisRemaining.tail, thatRemaining.tail)
        else recursively(
          thisNode.left :: thisNode.right :: thisRemaining.tail,
          thatNode.left :: thatNode.right :: thatRemaining.tail)
      }

    }

    recursively(List(this), List(that))
  }

  /*
           ______1_______
          /              \
       __2__            __6__
      /     \          /     \
     3       4        7       8
              \
               5

   Options:
   - pre-order: [1 2 3 4 5 6 7 8]
   - in-order: [3 2 4 5 1 7 6 8]
   - post-order: [3 5 4 2 7 6 8 1] (?!)
   - per-level: [1 2 6 3 4 7 8 5]
  */


  def toList: List[T] = {

    def preOrderStack(node: BTree[T]): List[T] = {
      if (node.isEmpty) List()
      else node.value :: preOrderStack(node.left) ++ preOrderStack(node.right)
    }

    @tailrec
    def preOrderTailRec(stack: List[BTree[T]], visited: Set[BTree[T]], acc: Queue[BTree[T]]): List[T] = {
      if (stack.isEmpty) {
        acc.reverse.map(_.value).toList
      } else if (stack.head.isEmpty) {
        preOrderTailRec(stack.tail, visited, acc)
      } else if (stack.head.isLeaf) {
        preOrderTailRec(stack.tail, visited, stack.head +: acc)
      } else {
        preOrderTailRec(stack.head.left :: stack.head.right :: stack.tail, visited + stack.head, stack.head +: acc)
      }
    }

    preOrderTailRec(List(this), Set(), Queue())
  }

  def toListPerLevel: List[T] = {
    @tailrec
    def recursively(level: List[BTree[T]], acc: Queue[BTree[T]]): List[T] = {
      if (level.isEmpty) acc.map(_.value).toList
      else recursively(
        level.flatMap(n => List(n.left, n.right)).filterNot(_.isEmpty), acc ++ level
      )
    }

    recursively(List(this), Queue.empty[BTree[T]])
  }

}

object BinaryTreeProblems extends App {

  val tree = BNode(1,
    BNode(2,
      BNode(3, BEnd, BEnd),
      BNode(4, BEnd,
        BNode(5, BEnd, BEnd))),
    BNode(6,
      BNode(7, BEnd, BEnd),
      BNode(8, BEnd, BEnd)
    )
  )


  println(tree.toList.mkString(","))

  println(tree.collectNodes(0).map(_.value))
  println(tree.collectNodes(1).map(_.value))
  println(tree.collectNodes(2).map(_.value))
  println(tree.collectNodes(3).map(_.value))
  println(tree.collectNodes(4).map(_.value))
  println(tree.size)
  println(tree.collectLeaves.map(_.value))
  println(tree.leafCount)

}

class TestBinaryTree extends FunSuite {
  private val tree = BNode(1,
    BNode(2,
      BNode(3, BEnd, BEnd),
      BNode(4, BEnd,
        BNode(5, BEnd, BEnd))),
    BNode(6,
      BNode(7, BEnd, BEnd),
      BNode(8, BEnd, BEnd)
    )
  )
  test("collectLeaves2") {
    tree.collectLeaves.map(_.value) should contain theSameElementsAs List(8, 7, 5, 3)
  }

  test("collectNodes(0)")(tree.collectNodes(0).map(_.value) should contain theSameElementsAs List(1))
  test("collectNodes(1)")(tree.collectNodes(1).map(_.value) should contain theSameElementsAs List(2, 6))
  test("collectNodes(2)")(tree.collectNodes(2).map(_.value) should contain theSameElementsAs List(3, 4, 7, 8))
  test("collectNodes(3)")(tree.collectNodes(3).map(_.value) should contain theSameElementsAs List(5))
  test("tree.toList")(tree.toList shouldBe List(1, 2, 3, 4, 5, 6, 7, 8))
  test("tree.toListPerLevel")(tree.toListPerLevel shouldBe List(1, 2, 6, 3, 4, 7, 8, 5))
}
