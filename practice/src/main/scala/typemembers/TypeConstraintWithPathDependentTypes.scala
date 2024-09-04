package typemembers

object TypeConstraintWithPathDependentTypes extends App {

  trait ItemLike {
    type Key
  }

  private trait Item[K] extends ItemLike {
    type Key = K
  }

  private trait ItemInt extends Item[Int]
  private trait ItemString extends Item[String]

  // API method
  // key type is a path-dependent type
  case class ReturnValue(value: Int)
  def get[ItemType <: ItemLike](key: ItemType#Key): ReturnValue = {
    key match {
      case i: Int =>  ReturnValue(222)
      case s: String =>  ReturnValue(333)
    }
  }

  private val itemByInt = get[ItemInt](42) // works ok
  private val itemByString = get[ItemString]("John") // works ok

  //get[ItemInt]("John") // compile error

  println(itemByInt)
  println(itemByString)
}
