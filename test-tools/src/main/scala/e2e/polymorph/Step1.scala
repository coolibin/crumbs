package e2e.polymorph

object Step1 {

  trait HasStep1[P] {
    var step1dataOpt: Option[Step1Data] = None

    def update(step1data: Step1Data): P = {
      step1dataOpt = Some(step1data)
      this.asInstanceOf[P]
    }
  }

  case class Step1Data(
    step1input: String,
    step1output: String,
  )

  def run[A <: HasStep1[_]](data: A): A = {
    assert(data.step1dataOpt.nonEmpty)

    println(s"step1 is running with [${data.step1dataOpt.get.step1input}]...")

    assert(data.step1dataOpt.get.step1input == "initialInput")
    data.update(data.step1dataOpt.get.copy(step1output = "step1 is complete"))
    data
  }

}
