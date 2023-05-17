package list

import scala.annotation.tailrec

trait MyList[+T] {
  def isEmpty: Boolean

  def head: T

  def headOption: Option[T]

  def tail: MyList[T]

  def length: Int

  def prepend[B >: T](value: B): MyList[B] = this match {
    case Empty => Lst(value, Empty)
    case Lst(head, Empty) => Lst(value, Lst(head, Empty))
    case Lst(head, tail) => Lst(value, tail.prepend(head))
  }
}

case object Empty extends MyList[Nothing] {

  override def isEmpty: Boolean = true

  override def head: Nothing = throw new IllegalAccessError()

  override def headOption: Option[Nothing] = None

  override def tail: MyList[Nothing] = Empty

  override def length: Int = 0

}

case class Lst[T](val head: T, val tail: MyList[T]) extends MyList[T] {
  override def isEmpty: Boolean = false

  override def headOption: Option[T] = Some(head)

  override def length: Int = {
    @tailrec
    def recursively(lst: MyList[T], accumulator: Int): Int = {
      lst match {
        case Empty => accumulator
        case l => recursively(l.tail, accumulator + 1)
      }
    }
    recursively(this, 0)
  }
}

object Lst {
  def apply[T](h: T, other: T*): MyList[T] = {
    var result: MyList[T] = Empty
    other.reverse.foreach { el =>
      result = result.prepend(el)
    }
    result.prepend(h)
  }

}
