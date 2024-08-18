package e2e.polymorph

import e2e.polymorph.Step1.HasStep1

object Step2 {
  trait HasStep2[P] {
    var step2dataOpt: Option[Step2Data] = None

    def update(step1data: Step2Data): P = {
      step2dataOpt = Some(step1data)
      this.asInstanceOf[P]
    }
  }

  case class Step2Data(
    step2input: String,
    step2output: String,
  )

  def run[A <: HasStep2[_] with HasStep1[_]](data: A): A = {
    assert(data.step1dataOpt.nonEmpty)
    val step1data = data.step1dataOpt.get

    println(s"step2 is running with [${data.step1dataOpt.get.step1output}]...")

    // updating the data related to the previous step if necessary
    data.update(
      step1data.copy(step1output = "step1 is complete OK!")
    )
    println(s"step2 updated step1 [${data.step1dataOpt.get.step1output}]...")

    data.update(
      Step2Data(
        step2input = step1data.step1output,
        step2output = "step2 is complete"
      )
    )
    data
  }

}
