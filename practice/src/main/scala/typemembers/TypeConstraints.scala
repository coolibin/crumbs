package typemembers

object TypeConstraints extends App {

  trait ApplicableToNumbers {
    type A <: Int
  }

  trait MList {
    type A
    def head: A
    def tail: MList
  }

  class IntList(hd: Int, tl: IntList) extends MList with ApplicableToNumbers {
    type A = Int
    def head = hd
    def tail = tl
  }

  /* not allowed: */
  /*
  class StringList(hd: String, tl: StringList) extends MList with ApplicableToNumbers {
    type A = String
    def head = hd
    def tail = tl
  }*/
}
