object ScalaDays1 extends App {

  case class Field()
  case class Digger()
  case class PotatoFarm(field: Field, digger: Digger)

  case class CowPasture()
  case class Meatery(cowPasture: CowPasture)

  case class Restaurant(potatoFarm: PotatoFarm, meatery: Meatery) {
    def orderSteakWithPotatoes() = {
      println(s"Welcome to $this. Here's your order!")
    }
  }

  lazy val field = new Field()
  lazy val digger = new Digger()
  lazy val potatoFarm = new PotatoFarm(field, digger)

  lazy val cowPasture = new CowPasture()
  lazy val meatery = new Meatery(cowPasture)

  lazy val restaurant = new Restaurant(potatoFarm, meatery)
  restaurant.orderSteakWithPotatoes()
}
