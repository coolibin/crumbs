package adhocpolymorphism

object Example1 {

  /**
   * Given different types of input, we want to process them differently.
   * While using the same method name, for each type of input,
   * we have a different type of output.
   */

  private case class InputType1(data: String)
  private case class InputType2(data: Int)
  trait StepOutputGeneric
  private case class StepOutputType1(outputString: String) extends StepOutputGeneric
  private case class StepOutputType2(outputBoolean: Boolean) extends StepOutputGeneric

  trait InputProcessor[T] {
    def run(data: T): StepOutputGeneric
  }

  private object InputProcessor {
    def run[T: InputProcessor](data: T): StepOutputGeneric =
      implicitly[InputProcessor[T]].run(data)

    // this is the real implementation of the processors:
    implicit val string2InputGenerator: InputProcessor[InputType1] = (data: InputType1) => {
      /* process input of type1 */
      StepOutputType1(data.data)
    }
    implicit val int2InputGenerator: InputProcessor[InputType2] = (data: InputType2) => {
      /* process input of type2 */
      StepOutputType2(data.data > 0)
    }
  }

  def main(args: Array[String]): Unit = {
    val inputType1 = InputType1("Hello")
    val inputType2 = InputType2(1)

    import InputProcessor._

    run(inputType1) match {
      case StepOutputType1(outputData) => println(s" String: $outputData")
      case _ => println("Invalid output")
    }

    run(inputType2) match {
      case StepOutputType2(outputBoolean) => println(s" Boolean: $outputBoolean")
      case _ => println("Invalid output")
    }
  }
}
