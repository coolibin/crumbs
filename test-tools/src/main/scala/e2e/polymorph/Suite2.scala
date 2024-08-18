package e2e.polymorph

import org.scalatest.funsuite.AnyFunSuite

class Suite2 extends AnyFunSuite {

  class Suite2Data
    extends Step1.HasStep1[Suite2Data]
      with Step2.HasStep2[Suite2Data] {
    def withStep1Data(step1data: Step1.Step1Data): Suite2Data = {
      step1dataOpt = Some(step1data)
      this
    }
  }

  test("test2") {
    val testData = new Suite2Data().withStep1Data(Step1.Step1Data(step1input = "initialInput", step1output = "output1"))
    assert(testData.step1dataOpt.get.step1input == "initialInput")
    assert(testData.step2dataOpt.isEmpty)

    Step1.run(testData)
    assert(testData.step1dataOpt.get.step1output == "step1 is complete")

    Step2.run(testData)
    assert(testData.step1dataOpt.get.step1output == "step1 is complete OK!")
    assert(testData.step2dataOpt.get.step2output == "step2 is complete")
  }

}
