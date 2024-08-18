package e2e.polymorph

import org.scalatest.funsuite.AnyFunSuite

class SuiteWithMultiTest extends AnyFunSuite with StopOnFirstFailure {

  class Suite1Data
    extends Step1.HasStep1[Suite1Data]
      with Step2.HasStep2[Suite1Data]
      with Step3.HasStep3[Suite1Data]

  object Suite1Data {
    def init(): Suite1Data = {
      val initialState = new Suite1Data()
      initialState.step1dataOpt = Some(Step1.Step1Data(step1input = "initialInput", step1output = ""))
      initialState
    }
  }

  private val testData = Suite1Data.init()

  test("step1") {
    Step1.run(testData)
    assert(testData.step1dataOpt.get.step1output == "step1 is complete")
  }

  test("step2") {
    Step2.run(testData)
    assert(testData.step1dataOpt.get.step1output == "step1 is complete OK!")
    assert(testData.step2dataOpt.get.step2output == "step2 is complete")
  }

  test("step3") {
    Step3.run(testData)
    assert(testData.step2dataOpt.get.step2output == "step2 is complete")
    assert(testData.step3dataOpt.get.step3output == "step3 is complete")

    println(s"Final state: \n${testData.step1dataOpt.get}\n${testData.step2dataOpt.get}\n${testData.step3dataOpt.get}")
  }
}
