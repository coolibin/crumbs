package has

import org.scalatest.funsuite.AnyFunSuite

class MultiStepScenario extends AnyFunSuite {

  object Step1 {
    case class Data(intValue: Int)
    def run(): Has[Data] =  Has(Data(42))
  }
  object Step2 {
    case class Data(stringValue: String)
    def run(d: Has[Step1.Data]): Has[Step1.Data] with Has[Step2.Data] = d combine Has(Data("step2 is complete"))
  }
  object Step3 {
    case class Data(booleanValue: Boolean)
    def run(d: Has[Step1.Data]): Has[Step1.Data] with Has[Step3.Data] = d combine Has(Data(true))
  }

  test("multi-step scenario") {
    val d1 = Step1.run()
    val d2 = Step2.run(d1)
    val d3 = Step3.run(d2)

    assert(d1.get[Step1.Data].intValue == 42)
    assert(d2.get[Step2.Data].stringValue == "step2 is complete")
    assert(d3.get[Step3.Data].booleanValue)

    println(d3)
  }
}
