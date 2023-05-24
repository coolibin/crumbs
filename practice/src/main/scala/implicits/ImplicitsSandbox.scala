package implicits

import $.FunSuite

class ImplicitsSandbox extends FunSuite {

  //implicit val classLevel = ImplicitHolder("classLevel")

  class Runner(input: String)(implicit impl: ImplicitHolder = ImplicitHolder("default")) {
    def run(): String = {
      input + ":" + impl.value
    }
  }

  test("test implicit") {
    //new Runner("hello")(ImplicitHolder("explicit")).run() shouldBe "hello:explicit"
    //new Runner("hello").run() shouldBe "hello:classLevel"
    //new Runner("hello").run() shouldBe "hello:packageLevel"

    //import ObjectWithImplicit._
    //new Runner("hello").run() shouldBe "hello:otherObject"

    new Runner("hello").run() shouldBe "hello:default"

  }



}
