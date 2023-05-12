package mergelists

object MergeLists2 extends App {

  /**
   * Definition for singly-linked list.
   * class ListNode(_x: Int = 0, _next: ListNode = null) {
   * var next: ListNode = _next
   * var x: Int = _x
   * }
   */

   val root = new ListNode(0)
   var current = root

  def  recursively(l1: ListNode, l2: ListNode): ListNode = {
    if (l1 == null && l2 == null) {
      root
    } else if (l1 == null && l2 != null) {
      current.next  = l2
      current = current.next
      recursively(l1, l2.next)
    } else if (l1 != null && l2 == null) {
      current.next = l2
      current = current.next
      recursively(l1.next, l2)
    } else if (l1.x >= l2.x) {
      current.next = l1
      current = current.next
      recursively(l1.next, l2)
    } else {
      current.next = l2
      current = current.next
      recursively(l1, l2.next)
    }

  }


  def mergeTwoLists(list1: ListNode, list2: ListNode): ListNode = {
   recursively(list1, list2).next
  }

  val list1 = new ListNode(1, _next = new ListNode(2, _next = new ListNode(3, _next = new ListNode(4))))
  val list2 = new ListNode(1, _next = new ListNode(3, _next = new ListNode(4)))
//  val list1 = null //new ListNode(1, _next = new ListNode(2, _next = new ListNode(3, _next = new ListNode(4))))
//  val list2 = new ListNode(1, _next = new ListNode(3, _next = new ListNode(4)))
//  val list2 = new ListNode(0)

  val r = mergeTwoLists(list1, list2)
  println("end")
}
