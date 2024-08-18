package e2e.polymorph

import e2e.polymorph.Step1.{HasStep1, Step1Data}

object Step0 {

  def run[A <: HasStep1[A]](td: A): A = {

    println("Step0 is running with no any inputs, but produces an input for step 1...")

    td.update(Step1Data(step1input = "initialInput", step1output = ""))
  }

}
