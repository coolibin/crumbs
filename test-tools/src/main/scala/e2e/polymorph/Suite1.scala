package e2e.polymorph

import org.scalatest.funsuite.AnyFunSuite

class Suite1 extends AnyFunSuite {

  /**
   * Test data object specific to a suite
   */
  class Suite1Data
    extends Step1.HasStep1[Suite1Data]
      with Step2.HasStep2[Suite1Data]
      with Step3.HasStep3[Suite1Data]

  test("test1") {

    val testData = Step0.run(new Suite1Data())
    assert(testData.step1dataOpt.get.step1input == "initialInput")
    assert(testData.step2dataOpt.isEmpty)

    Step1.run(testData)
    assert(testData.step1dataOpt.get.step1output == "step1 is complete")

    Step2.run(testData)
    assert(testData.step1dataOpt.get.step1output == "step1 is complete OK!")
    assert(testData.step2dataOpt.get.step2output == "step2 is complete")

    println(s"Final state: \n${testData.step1dataOpt.get}\n${testData.step2dataOpt.get}")

  }

}
