
object ScalaDays3 extends App {
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

  trait CropModule {
    lazy val field = wire[Field]
    lazy val digger = wire[Digger]
    lazy val potatoFarm = wire[PotatoFarm]
  }

  trait LivestockModule {
    lazy val cowPasture = wire[CowPasture]
    lazy val meatery = wire[Meatery]

    def potatoFarm: PotatoFarm
  }

  trait RestaurantModule extends CropModule with LivestockModule {
    lazy val restaurant = wire[Restaurant]
  }

  val app = new CropModule with LivestockModule with RestaurantModule
  app.restaurant.orderSteakWithPotatoes()
}
