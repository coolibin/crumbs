package e2e.polymorph

object Step3 {
  trait HasStep3[P] {
    var step3dataOpt: Option[StepData3] = None
    var step3dataOpt2: Option[StepData32] = None

    def update(step3data: StepData3): P = {
      step3dataOpt = Some(step3data)
      this.asInstanceOf[P]
    }

    def update(step32data: StepData32): P = {
      step3dataOpt2 = Some(step32data)
      this.asInstanceOf[P]
    }

  }

  case class StepData32(
    step3input: String,
    step3output: String,
  )
  case class StepData3(
    step3input: String,
    step3output: String,
  )

  def run[A <: HasStep3[_]](data: A): A = {
    // ...
    data.update(
      data.step3dataOpt.getOrElse(StepData3("", ""))
        .copy(step3output = "step3 is complete")
    )
    data
  }

}
