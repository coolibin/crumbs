package has

import scala.language.implicitConversions
import scala.reflect.ClassTag

case class Step1(data1: Int)
case class Step2(data2: String)
case class Step3(data3: Boolean)

case class Has[Env] private (private val map: Map[String, Any])

object Has {
  implicit class HasOps[Self <: Has[_]](self: Self) {
    def combine[That <: Has[_]](that: That): Self with That =
      Has(self.map ++ that.map).asInstanceOf[Self with That]

    def get[Env](implicit ev: Self <:< Has[Env], tag: ClassTag[Env]): Env = {
      self.map(tag.toString()).asInstanceOf[Env]
    }
  }

  def apply[E](env: E)(implicit tag: ClassTag[E]): Has[E] =
    Has(Map(tag.toString() -> env))
}

object Exec {
  def main(args: Array[String]): Unit = {
    val step1 = Has(Step1(5))
    val step2 = Has(Step2("yo"))
    val step3 = Has(Step3(false))
    val env12 = step1.combine(step2)

    val env123: Has[Step1] with Has[Step2] with Has[Step3] =
      env12.combine(step3)

    println(env123.get[Step1].data1)
    println(env123.get[Step2].data2)
    println(env123.get[Step3].data3)
  }
}
