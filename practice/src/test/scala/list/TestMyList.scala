package list

import $.FunSuite

class TestMyList
  extends FunSuite {

  test("MyList constructor should return a list") {
    Lst(1, Empty).head shouldBe 1
    Lst(1, Empty).tail shouldBe Empty
    Lst(1, Empty).headOption shouldBe Some(1)
  }

  test("prepend should add a head") {
    val list = Lst(1, Lst(2,3))
    list.head shouldBe 1
    list.prepend(5).head shouldBe 5
    list.prepend(5).tail.head shouldBe(1)
  }

  test("length should return a length") {
    val l = Lst(1,2,3)
    l.length shouldBe 3
    Empty.length shouldBe 0
    List(1).length shouldBe 1
  }

}
