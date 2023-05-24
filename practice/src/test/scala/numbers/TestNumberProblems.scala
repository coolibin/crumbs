package numbers

import $.FunSuite

class TestNumberProblems
  extends FunSuite {

  test("11 is prime") {
    NumberProblems.isPrime(11) shouldBe true
  }

  test("12 is not prime") {
    NumberProblems.isPrime(12) shouldBe false
  }

  test("1 is not prime") {
    NumberProblems.isPrime(1) shouldBe false
  }

  test("24688 is not prime") {
    NumberProblems.isPrime(24688) shouldBe false
  }

  test("6 = 2*3") {
    NumberProblems.decompose(6) should contain theSameElementsAs List(2, 3)
  }

  test("54 = 3*3*3*2") {
    NumberProblems.decompose(54) should contain theSameElementsAs List(3, 3, 3, 2)
  }

  test("128 = 2*2*2*2*2*2*2") {
    NumberProblems.decompose(128) should contain theSameElementsAs List(2, 2, 2, 2, 2, 2, 2)
  }


  test("0 = [0]") {
    NumberProblems.decompose(0) shouldBe List(0)
  }

  test("1 = [1]") {
    NumberProblems.decompose(1) shouldBe List(1)
  }

  test("5 = Nil") {
    NumberProblems.decompose(5) shouldBe List(5)
  }

}
