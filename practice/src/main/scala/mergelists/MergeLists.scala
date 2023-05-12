package mergelists

object MergeLists extends App {

  /**
   * Definition for singly-linked list.
   * class ListNode(_x: Int = 0, _next: ListNode = null) {
   * var next: ListNode = _next
   * var x: Int = _x
   * }
   */


  def mergeTwoLists(list1: ListNode, list2: ListNode): ListNode = {
    val root: ListNode = new ListNode(0)
    var current: ListNode = root

    if (list1 == null && list2 == null) {
      null
    } else if (list1 == null) {
      list2
    } else if (list2 == null) {
      list1
    } else {
      var l1 = list1
      var l2 = list2

      while (l1 != null || l2 != null) {
        if (l2 == null) {
          current.next = l1
          l1 = l1.next
          current = current.next
        } else if (l1 != null && l1.x > l2.x) {
          current.next = l1
          l1 = l1.next
          current = current.next
        } else {
          current.next = l2
          l2 = l2.next
          current = current.next
        }
      }

      root.next
    }
  }

//  val list1 = new ListNode(1, _next = new ListNode(2, _next = new ListNode(3, _next = new ListNode(4))))
//  val list2 = new ListNode(1, _next = new ListNode(3, _next = new ListNode(4)))
  val list1 = null //new ListNode(1, _next = new ListNode(2, _next = new ListNode(3, _next = new ListNode(4))))
//  val list2 = new ListNode(1, _next = new ListNode(3, _next = new ListNode(4)))
  val list2 = new ListNode(0)

  val r = mergeTwoLists(list1, list2)
  println("end")
}
