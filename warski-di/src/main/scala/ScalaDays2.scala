
object ScalaDays2 extends App {
  import com.softwaremill.macwire._
  case class Field()
  case class Digger()
  case class PotatoFarm(field: Field, digger: Digger) {
    println("New potato farm! Rejoice!")
  }

  case class CowPasture(potatoFarm: PotatoFarm)
  case class Meatery(cowPasture: CowPasture)

  case class Restaurant(potatoFarm: PotatoFarm, meatery: Meatery) {
    def orderSteakWithPotatoes(): Unit = {
      println(s"Welcome to $this. Here's your order!")
    }
  }

  lazy val field = wire[Field]
  lazy val digger = wire[Digger]
  def potatoFarm = wire[PotatoFarm]

  lazy val cowPasture = wire[CowPasture]
  lazy val meatery = wire[Meatery]

  lazy val restaurant = wire[Restaurant]
  restaurant.orderSteakWithPotatoes()
}
