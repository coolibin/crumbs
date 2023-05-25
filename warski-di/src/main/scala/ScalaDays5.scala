import com.softwaremill.macwire.aop.{Interceptor, NoOpInterceptor, ProxyingInterceptor}

object ScalaDays5 extends App {

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
    lazy val restaurant = transactional(wire[Restaurant])

    def transactional: Interceptor
  }

  val app = new CropModule with LivestockModule with RestaurantModule {

    lazy val transactional = ProxyingInterceptor {
      ctx =>
        println("About to invoke restaurant")
        val result = ctx.proceed()
        println("Restaurant invoked!")
        result
    }
  }
  //app.restaurant.orderSteakWithPotatoes()

  val appTesting = new CropModule with LivestockModule with RestaurantModule {
    lazy val transactional = NoOpInterceptor

    override lazy val potatoFarm: PotatoFarm = new PotatoFarm(null, null)
  }

  appTesting.restaurant.orderSteakWithPotatoes()
}
